package br.ceub.ProjetoFinal.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ceub.ProjetoFinal.model.ItemVenda;
import br.ceub.ProjetoFinal.repository.ItemVendaRepository;

@Service
public class ItemVendaService {
	
	@Autowired
	private ItemVendaRepository itemVendaRepository;
	
	public ItemVenda save(ItemVenda itemVenda) {
		return itemVendaRepository.save(itemVenda);
	}
	
	public java.util.List<ItemVenda> findAll() {
		return itemVendaRepository.findAll();
	}
	
	public Optional<ItemVenda> findById(Integer id) {
		return itemVendaRepository.findById(id);
	}
	
	public Optional<ItemVenda> findByVendaId(Integer vendaId) {
		return itemVendaRepository.findByVendaId(vendaId);
	}
	
	public void deleteById(Integer id) {
		itemVendaRepository.deleteById(id);
	}
	
}
