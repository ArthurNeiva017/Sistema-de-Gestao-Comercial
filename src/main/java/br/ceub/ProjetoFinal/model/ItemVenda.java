package br.ceub.ProjetoFinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_venda")
public class ItemVenda {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(name="produtoId", nullable = false)
    private Produto produtoId;

    @Column(name="quantidade", nullable = false)
    private Integer quantidade;

    @Column(name="precoUnitario", nullable = false)
    private double precoUnitario;

    public ItemVenda() {
        super();
    }

    public ItemVenda(int id, Produto produtoId, int quantidade, double precoUnitario) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Produto produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    

}
