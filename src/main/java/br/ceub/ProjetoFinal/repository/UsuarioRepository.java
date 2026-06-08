package br.ceub.ProjetoFinal.repository;

public interface UsuarioRepository {

	Object findByEmailIgnoreCase(String trim);

}
