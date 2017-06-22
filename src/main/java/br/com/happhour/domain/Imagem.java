package br.com.happhour.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
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
        if (imagem.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, imagem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Imagem{" +
            "id=" + id +
            ", tipo='" + tipo + "'" +
            ", url='" + url + "'" +
            '}';
    }
}
