package br.com.happhour.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import br.com.happhour.domain.enumeration.TipoAcesso;

/**
 * A UsuarioEmpresa.
 */
@Entity
@Table(name = "usuario_empresa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UsuarioEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_acesso")
    private TipoAcesso tipoAcesso;

    @NotNull
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "data_ativado")
    private LocalDate dataAtivado;

    @Column(name = "data_inativado")
    private LocalDate dataInativado;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Empresa empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public UsuarioEmpresa tipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
        return this;
    }

    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public UsuarioEmpresa ativo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataAtivado() {
        return dataAtivado;
    }

    public UsuarioEmpresa dataAtivado(LocalDate dataAtivado) {
        this.dataAtivado = dataAtivado;
        return this;
    }

    public void setDataAtivado(LocalDate dataAtivado) {
        this.dataAtivado = dataAtivado;
    }

    public LocalDate getDataInativado() {
        return dataInativado;
    }

    public UsuarioEmpresa dataInativado(LocalDate dataInativado) {
        this.dataInativado = dataInativado;
        return this;
    }

    public void setDataInativado(LocalDate dataInativado) {
        this.dataInativado = dataInativado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public UsuarioEmpresa usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public UsuarioEmpresa empresa(Empresa empresa) {
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
        UsuarioEmpresa usuarioEmpresa = (UsuarioEmpresa) o;
        if (usuarioEmpresa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usuarioEmpresa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UsuarioEmpresa{" +
            "id=" + getId() +
            ", tipoAcesso='" + getTipoAcesso() + "'" +
            ", ativo='" + isAtivo() + "'" +
            ", dataAtivado='" + getDataAtivado() + "'" +
            ", dataInativado='" + getDataInativado() + "'" +
            "}";
    }
}
