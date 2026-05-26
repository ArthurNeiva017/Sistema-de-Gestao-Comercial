package br.ceub.ProjetoFinal.controller;


@RestController
@RequestMapping("/api/vendas")
public class VendaController {

@Autowired
	private VendaService vendaService;

	@PostMapping
	public ResponseEntity<Venda> createVenda(@RequestBody Venda venda) {
		Venda savedVenda = vendaService.save(venda);
		return ResponseEntity.ok(savedVenda);
	}

	@GetMapping
	public ResponseEntity<List<Venda>> getAllVenda() {
		List<Venda> Vendas = vendaService.findAll();
		return ResponseEntity.ok(Vendas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venda> getVendaById(@PathVariable Integer id) {
		Optional<Venda> Venda = vendaService.findById(id);
		return Venda.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<Venda>> searchVendas(@RequestParam String nome) {
        List<Venda> Vendas = vendaService.findByNome(nome);
        return ResponseEntity.ok(Vendas);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Venda> updateVenda(@PathVariable Integer id, @RequestBody Venda vendaDetails) {
        Optional<Venda>optionalVenda = vendaService.findById(id);
        if (optionalVenda.isPresent()) {
            Venda venda = optionalVenda.get();
            venda.setData(vendaDetails.getData());
            venda.setValorTotal(vendaDetails.getValorTotal());
            venda.setClienteId(vendaDetails.getClienteId());
            venda.setUsuarioId(vendaDetails.getUsuarioId());
            produto.setQuantidade(produtoDetails.getQuantidade());
            Produto updatedProduto = produtoService.save(produto);
            return ResponseEntity.ok(updatedProduto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteVenda(@PathVariable Integer id) {
	        Optional<Venda> venda = vendaService.findById(id);
	        if (venda.isPresent()) {
	            vendaService.deleteById(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }


}
