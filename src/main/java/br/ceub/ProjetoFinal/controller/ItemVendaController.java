package br.ceub.ProjetoFinal.controller;

@RestController
@RequestMapping("/api/itensvenda")
public class ItemVendaController {

    @Autowired
    private ItemVendaService itemVendaService;

    @PostMapping
    public ResponseEntity<ItemVenda> createItemVenda(@RequestBody ItemVenda itemVenda) {
        ItemVenda savedItemVenda = itemVendaService.save(itemVenda);
        return ResponseEntity.ok(savedItemVenda);
    }

    @GetMapping
    public ResponseEntity<List<ItemVenda>> getAllItemVenda() {
        List<ItemVenda> ItemVendas = itemVendaService.findAll();
        return ResponseEntity.ok(ItemVendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemVenda> getItemVendaById(@PathVariable Integer id) {
        Optional<ItemVenda> ItemVenda = itemVendaService.findById(id);
        return ItemVenda.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemVenda>> searchItemVendas(@RequestParam String nome) {
        List<ItemVenda> ItemVendas = itemVendaService.findByNome(nome);
        return ResponseEntity.ok(ItemVendas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemVenda> updateItemVenda(@PathVariable Integer id, @RequestBody ItemVenda itemVendaDetails) {
        Optional<ItemVenda> optionalItemVenda = itemVendaService.findById(id);
        if (optionalItemVenda.isPresent()) {
            ItemVenda itemVenda = optionalItemVenda.get();
            itemVenda.setVendaId(itemVendaDetails.getVendaId());
            itemVenda.setProdutoId(itemVendaDetails.getProdutoId());
            itemVenda.setQuantidade(itemVendaDetails.getQuantidade());
            itemVenda.setPrecoUnitario(itemVendaDetails.getPrecoUnitario());
            ItemVenda updatedItemVenda = itemVendaService.save(itemVenda);
            return ResponseEntity.ok(updatedItemVenda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemVenda(@PathVariable Integer id) {
        Optional<ItemVenda> itemVenda = itemVendaService.findById(id);
        if (itemVenda.isPresent()) {
            itemVendaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
