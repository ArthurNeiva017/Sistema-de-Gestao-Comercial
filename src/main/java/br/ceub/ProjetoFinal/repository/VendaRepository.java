package br.ceub.ProjetoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ceub.ProjetoFinal.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {

	@Query("SELECT c FROM Venda c")
	public List<Venda> findAll();
	
	@Query("SELECT c FROM Venda c WHERE c.id = :id")
	public Optional<Venda> findById(Integer id);
	
	@Query("SELECT c FROM Venda c WHERE c.data = :data")
	public Optional<Venda> findByData(LocalDate data);
	
	@Query("DELETE FROM Venda c WHERE c.id = :id")
	public void deleteById(Integer id);

	
}
