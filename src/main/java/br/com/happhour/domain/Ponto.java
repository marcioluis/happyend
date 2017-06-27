package br.com.happhour.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Ponto.
 */
@Entity
@Table(name = "ponto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ponto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_obtensao", nullable = false)
    private LocalDate dataObtensao;

    @Column(name = "data_expiracao")
    private LocalDate dataExpiracao;

    @NotNull
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Evento evento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataObtensao() {
        return dataObtensao;
    }

    public Ponto dataObtensao(LocalDate dataObtensao) {
        this.dataObtensao = dataObtensao;
        return this;
    }

    public void setDataObtensao(LocalDate dataObtensao) {
        this.dataObtensao = dataObtensao;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public Ponto dataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
        return this;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Ponto quantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Ponto usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public Ponto evento(Evento evento) {
        this.evento = evento;
        return this;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ponto ponto = (Ponto) o;
        if (ponto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ponto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ponto{" +
            "id=" + getId() +
            ", dataObtensao='" + getDataObtensao() + "'" +
            ", dataExpiracao='" + getDataExpiracao() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            "}";
    }
}
