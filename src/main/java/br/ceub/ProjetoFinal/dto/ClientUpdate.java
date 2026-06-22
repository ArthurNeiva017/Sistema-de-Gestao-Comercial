package br.ceub.ProjetoFinal.dto;

public record ClientUpdate(
        String nome,
        String email,
        String cpf,
        Integer telefone,
        String endereco
) {
}
