package br.com.pedidos.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "pedidos")
@Entity
public class PedidosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idPedido")
    private Long idPedido;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "idCliente")
    private Long idClente;

    @Column(name = "status")
    private String status;

    @Column(name = "nr_bin_inicial")
    private String sessao;

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Long getIdClente() {
        return idClente;
    }

    public void setIdClente(Long idClente) {
        this.idClente = idClente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PedidosEntity [idPedido=");
        builder.append(idPedido);
        builder.append(", data=");
        builder.append(data);
        builder.append(", idClente=");
        builder.append(idClente);
        builder.append(", status=");
        builder.append(status);
        builder.append(", sessao=");
        builder.append(sessao);
        builder.append("]");
        return builder.toString();
    }

}
