package br.ceub.ProjetoFinal.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendas")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="data", nullable = false)
	private LocalDate data;

	@Column(name="valorTotal", nullable = false)
	private Double valorTotal;

    @ManyToOne
    @Column(name="clienteId", nullable = false)z
    private Cliente clienteId;
    
    @ManyToOne
    @Column(name="usuarioId", nullable = false)
    private Usuario usuarioId;
    
    public Venda() {
        super();
    }

    public Venda(int id, LocalDate data, double valorTotal, Cliente clienteId, Usuario usuarioId) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    
    

}
