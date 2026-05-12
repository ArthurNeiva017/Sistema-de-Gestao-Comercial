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
import br.ceub.ProjetoFinal.model.Produto;
import br.ceub.ProjetoFinal.service.ProdutoService;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
		Produto savedProduto = produtoService.save(produto);
		return ResponseEntity.ok(savedProduto);
	}

	@GetMapping
	public ResponseEntity<List<Produto>> getAllProduto() {
		List<Produto> Produtos = produtoService.findAll();
		return ResponseEntity.ok(Produtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) {
		Optional<Produto> Produto = produtoService.findById(id);
		return Produto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<Produto>> searchProdutos(@RequestParam String nome) {
        List<Produto> produtos = produtoService.findByNome(nome);
        return ResponseEntity.ok(produtos);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Integer id, @RequestBody Produto produtoDetails) {
        Optional<Produto>optionalProduto = produtoService.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            produto.setNome(produtoDetails.getNome());
            produto.setDescricao(produtoDetails.getDescricao());
            produto.setPreco(produtoDetails.getPreco());
            produto.setQuantidade(produtoDetails.getQuantidade());
            Produto updatedProduto = produtoService.save(produto);
            return ResponseEntity.ok(updatedProduto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduto(@PathVariable Integer id) {
	        Optional<Produto> produto = produtoService.findById(id);
	        if (produto.isPresent()) {
	            produtoService.deleteById(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
