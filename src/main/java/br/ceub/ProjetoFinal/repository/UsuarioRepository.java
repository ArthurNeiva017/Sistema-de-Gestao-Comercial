package br.ceub.ProjetoFinal.repository;

import br.ceub.ProjetoFinal.model.Usuario;

public interface UsuarioRepository {

	Usuario findByEmailIgnoreCase(String trim);

}
