package br.ceub.ProjetoFinal.controller;

import java.util.List;
import java.util.Optional;

import br.ceub.ProjetoFinal.dto.ClientUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import br.ceub.ProjetoFinal.model.Cliente;
import br.ceub.ProjetoFinal.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<Cliente> createLivro(@RequestBody Cliente cliente) {
		Cliente savedCliente = clienteService.save(cliente);
		return ResponseEntity.ok(savedCliente);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> getAllCliente() {
		List<Cliente> clientes = clienteService.findAll();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable long id) {
		Optional<Cliente> Cliente = clienteService.findById((long)id);
		return Cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/search")
    public ResponseEntity<Optional<Cliente>> searchClientes(@RequestParam String nome) {
        Optional<Cliente> clientes = clienteService.findByNome(nome);
        return ResponseEntity.ok(clientes);
    }

	@PatchMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody ClientUpdate clienteDetails) {

		Optional<Cliente> optionalCliente = clienteService.findById(id);

		if (optionalCliente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Cliente cliente = optionalCliente.get();

		if (clienteDetails.nome() != null && !clienteDetails.nome().isBlank()) {
			cliente.setNome(clienteDetails.nome());
		}

		if (clienteDetails.email() != null && !clienteDetails.email().isBlank()) {
			cliente.setEmail(clienteDetails.email());
		}

		if (clienteDetails.cpf() != null && !clienteDetails.cpf().isBlank()) {
			cliente.setCpf(clienteDetails.cpf());
		}

		if (clienteDetails.endereco() != null && !clienteDetails.endereco().isBlank()) {
			cliente.setEndereco(clienteDetails.endereco());
		}

		if (clienteDetails.telefone() != null) {
			cliente.setTelefone(clienteDetails.telefone());
		}

		Cliente updatedCliente = clienteService.save(cliente);
		return ResponseEntity.ok(updatedCliente);
	}

	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
		 Optional<Cliente> cliente = clienteService.findById(id);

		 if (cliente.isPresent()) {
				 clienteService.deleteById(id);
				 return ResponseEntity.noContent().build();
	    }
		 return ResponseEntity.notFound().build();

}}
