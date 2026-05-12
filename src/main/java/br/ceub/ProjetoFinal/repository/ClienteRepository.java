package br.ceub.ProjetoFinal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ceub.ProjetoFinal.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query("SELECT c FROM Cliente c")
	public List<Cliente> findAll();
	
	@Query("SELECT c FROM Cliente c WHERE c.id = :id")
	public Optional<Cliente> findById(Long id);
	
	@Query("SELECT c FROM Cliente c WHERE c.nome = :nome")
	public Optional<Cliente> findByNome(String nome);
	
	@Query("SELECT c FROM Cliente c WHERE c.ativo = true")
	public List<Cliente> findAllAtivos();
	
	@Query("DELETE FROM Cliente c WHERE c.id = :id")
	public void deleteById(Long id);

}
