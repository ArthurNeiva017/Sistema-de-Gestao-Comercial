package br.ceub.ProjetoFinal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ceub.ProjetoFinal.model.ItemVenda;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Integer> {

	@Query("SELECT c FROM ItemVenda c")
	public List<ItemVenda> findAll();
	
	@Query("SELECT c FROM ItemVenda c WHERE c.id = :id")
	public Optional<ItemVenda> findById(Integer id);
	
	@Query("SELECT c FROM ItemVenda c WHERE c.vendaId = :vendaId")
	public Optional<ItemVenda> findByVendaId(Integer vendaId);
	
	@Query("DELETE FROM ItemVenda c WHERE c.id = :id")
	public void deleteById(Integer id);

	
}
