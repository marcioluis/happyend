package br.com.happhour.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Empresa.
 */
@Entity
@Table(name = "empresa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "nome_exibicao")
    private String nomeExibicao;

    @NotNull
    @Column(name = "ativa", nullable = false)
    private Boolean ativa;

    @Column(name = "happy_on_demand")
    private Boolean happyOnDemand;

    @Column(name = "telefone_principal")
    private String telefonePrincipal;

    @Column(name = "telefone_secundario")
    private String telefoneSecundario;

    @Column(name = "site")
    private String site;

    @NotNull
    @Column(name = "email_contato", nullable = false)
    private String emailContato;

    @NotNull
    @Column(name = "latitude", precision=10, scale=2, nullable = false)
    private BigDecimal latitude;

    @NotNull
    @Column(name = "longitude", precision=10, scale=2, nullable = false)
    private BigDecimal longitude;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UsuarioEmpresa> usuarios = new HashSet<>();

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Imagem> imagens = new HashSet<>();

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Evento> eventos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Empresa cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public Empresa nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public Empresa nomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
        return this;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public Boolean isAtiva() {
        return ativa;
    }

    public Empresa ativa(Boolean ativa) {
        this.ativa = ativa;
        return this;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public Boolean isHappyOnDemand() {
        return happyOnDemand;
    }

    public Empresa happyOnDemand(Boolean happyOnDemand) {
        this.happyOnDemand = happyOnDemand;
        return this;
    }

    public void setHappyOnDemand(Boolean happyOnDemand) {
        this.happyOnDemand = happyOnDemand;
    }

    public String getTelefonePrincipal() {
        return telefonePrincipal;
    }

    public Empresa telefonePrincipal(String telefonePrincipal) {
        this.telefonePrincipal = telefonePrincipal;
        return this;
    }

    public void setTelefonePrincipal(String telefonePrincipal) {
        this.telefonePrincipal = telefonePrincipal;
    }

    public String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public Empresa telefoneSecundario(String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
        return this;
    }

    public void setTelefoneSecundario(String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
    }

    public String getSite() {
        return site;
    }

    public Empresa site(String site) {
        this.site = site;
        return this;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public Empresa emailContato(String emailContato) {
        this.emailContato = emailContato;
        return this;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public Empresa latitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Empresa longitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Set<UsuarioEmpresa> getUsuarios() {
        return usuarios;
    }

    public Empresa usuarios(Set<UsuarioEmpresa> usuarioEmpresas) {
        this.usuarios = usuarioEmpresas;
        return this;
    }

    public Empresa addUsuarios(UsuarioEmpresa usuarioEmpresa) {
        this.usuarios.add(usuarioEmpresa);
        usuarioEmpresa.setEmpresa(this);
        return this;
    }

    public Empresa removeUsuarios(UsuarioEmpresa usuarioEmpresa) {
        this.usuarios.remove(usuarioEmpresa);
        usuarioEmpresa.setEmpresa(null);
        return this;
    }

    public void setUsuarios(Set<UsuarioEmpresa> usuarioEmpresas) {
        this.usuarios = usuarioEmpresas;
    }

    public Set<Imagem> getImagens() {
        return imagens;
    }

    public Empresa imagens(Set<Imagem> imagems) {
        this.imagens = imagems;
        return this;
    }

    public Empresa addImagens(Imagem imagem) {
        this.imagens.add(imagem);
        imagem.setEmpresa(this);
        return this;
    }

    public Empresa removeImagens(Imagem imagem) {
        this.imagens.remove(imagem);
        imagem.setEmpresa(null);
        return this;
    }

    public void setImagens(Set<Imagem> imagems) {
        this.imagens = imagems;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public Empresa eventos(Set<Evento> eventos) {
        this.eventos = eventos;
        return this;
    }

    public Empresa addEventos(Evento evento) {
        this.eventos.add(evento);
        evento.setEmpresa(this);
        return this;
    }

    public Empresa removeEventos(Evento evento) {
        this.eventos.remove(evento);
        evento.setEmpresa(null);
        return this;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Empresa empresa = (Empresa) o;
        if (empresa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empresa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Empresa{" +
            "id=" + getId() +
            ", cnpj='" + getCnpj() + "'" +
            ", nome='" + getNome() + "'" +
            ", nomeExibicao='" + getNomeExibicao() + "'" +
            ", ativa='" + isAtiva() + "'" +
            ", happyOnDemand='" + isHappyOnDemand() + "'" +
            ", telefonePrincipal='" + getTelefonePrincipal() + "'" +
            ", telefoneSecundario='" + getTelefoneSecundario() + "'" +
            ", site='" + getSite() + "'" +
            ", emailContato='" + getEmailContato() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            "}";
    }
}
