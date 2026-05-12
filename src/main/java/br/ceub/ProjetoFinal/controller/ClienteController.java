package br.ceub.ProjetoFinal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
	
	@PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable long id, @RequestBody Cliente clienteDetails) {
        Optional<Cliente> optionalCliente = clienteService.findById((long)id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setNome(clienteDetails.getNome());
            cliente.setEmail(clienteDetails.getEmail());
            cliente.setTelefone(clienteDetails.getTelefone());
            cliente.setEndereco(clienteDetails.getEndereco());
            Cliente updatedCliente = clienteService.save(cliente);
            return ResponseEntity.ok(updatedCliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCliente(@PathVariable long id) {
	        Optional<Cliente> cliente = clienteService.findById(id);
	        if (cliente.isPresent()) {
	            clienteService.deleteById(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
