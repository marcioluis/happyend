package br.com.happhour.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import br.com.happhour.domain.enumeration.TipoImagem;

/**
 * A Imagem.
 */
@Entity
@Table(name = "imagem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Imagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoImagem tipo;

    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    private Empresa empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoImagem getTipo() {
        return tipo;
    }

    public Imagem tipo(TipoImagem tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoImagem tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public Imagem url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Imagem empresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Imagem imagem = (Imagem) o;
        if (imagem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), imagem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Imagem{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
