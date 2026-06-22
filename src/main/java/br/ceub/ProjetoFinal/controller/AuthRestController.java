package br.ceub.ProjetoFinal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ceub.ProjetoFinal.dto.AuthResponse;
import br.ceub.ProjetoFinal.dto.LoginRequest;
import br.ceub.ProjetoFinal.dto.RegisterRequest;
import br.ceub.ProjetoFinal.model.Usuario;
import br.ceub.ProjetoFinal.repository.UsuarioRepository;
import br.ceub.ProjetoFinal.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Login com e-mail e senha para obter JWT")
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthRestController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    @Operation(summary = "Obter token JWT", description = "Autentica pelo e-mail e pela senha cadastrados na tabela de usuários")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        if (request.email() == null || request.email().isBlank()
                || request.password() == null || request.password().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email().trim(),
                            request.password()
                    )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var usuario = usuarioRepository.findByEmailIgnoreCase(request.email().trim());
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtService.generateToken(usuario);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    @Operation(summary = "Cadastrar um novo usuário", description = "Cria um novo usuário com nome, e-mail e senha criptografada")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        if (request.nome() == null || request.nome().isBlank()
                || request.email() == null || request.email().isBlank()
                || request.password() == null || request.password().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (usuarioRepository.findByEmailIgnoreCase(request.email().trim()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(request.nome().trim());
        novoUsuario.setEmail(request.email().trim().toLowerCase());
        novoUsuario.setSenha(passwordEncoder.encode(request.password()));

        usuarioRepository.save(novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
