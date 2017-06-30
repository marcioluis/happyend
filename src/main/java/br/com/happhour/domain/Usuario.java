package br.com.happhour.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * A Usuario.
 */
@Entity
@Table(name = "usuario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "display_name", nullable = false)
    private String displayName;

    @NotNull
    @Column(name = "family_name", nullable = false)
    private String familyName;

    @NotNull
    @Column(name = "given_name", nullable = false)
    private String givenName;

    /**
     * Login provider
     */
    @NotNull
    @ApiModelProperty(value = "Login provider", required = true)
    @Column(name = "provider", nullable = false)
    private String provider;

    /**
     * Google Auth code that can be exchanged for an access token and refresh
     * token for offline access
     */
    @ApiModelProperty(value = "Google Auth code that can be exchanged for an access token and refresh token for offline access")
    @Column(name = "auth_code")
    private String authCode;

    /**
     * Id from the provider, google or facebook
     */
    @ApiModelProperty(value = "Id from the provider, google or facebook")
    @Column(name = "provider_user_id")
    private String providerUserId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "gender")
    private String gender;

    @NotNull
    @Column(name = "telephone", nullable = false)
    private String telephone;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UsuarioEmpresa> empresas = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UsuarioEvento> eventos = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ponto> pontos = new HashSet<>();

	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private UsuarioSettings settings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public Usuario email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Usuario displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public Usuario familyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public Usuario givenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getProvider() {
        return provider;
    }

    public Usuario provider(String provider) {
        this.provider = provider;
        return this;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAuthCode() {
        return authCode;
    }

    public Usuario authCode(String authCode) {
        this.authCode = authCode;
        return this;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public Usuario providerUserId(String providerUserId) {
        this.providerUserId = providerUserId;
        return this;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Usuario imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGender() {
        return gender;
    }

    public Usuario gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public Usuario telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<UsuarioEmpresa> getEmpresas() {
        return empresas;
    }

    public Usuario empresas(Set<UsuarioEmpresa> usuarioEmpresas) {
        this.empresas = usuarioEmpresas;
        return this;
    }

    public Usuario addEmpresas(UsuarioEmpresa usuarioEmpresa) {
        this.empresas.add(usuarioEmpresa);
        usuarioEmpresa.setUsuario(this);
        return this;
    }

    public Usuario removeEmpresas(UsuarioEmpresa usuarioEmpresa) {
        this.empresas.remove(usuarioEmpresa);
        usuarioEmpresa.setUsuario(null);
        return this;
    }

    public void setEmpresas(Set<UsuarioEmpresa> usuarioEmpresas) {
        this.empresas = usuarioEmpresas;
    }

    public Set<UsuarioEvento> getEventos() {
        return eventos;
    }

    public Usuario eventos(Set<UsuarioEvento> usuarioEventos) {
        this.eventos = usuarioEventos;
        return this;
    }

    public Usuario addEventos(UsuarioEvento usuarioEvento) {
        this.eventos.add(usuarioEvento);
        usuarioEvento.setUsuario(this);
        return this;
    }

    public Usuario removeEventos(UsuarioEvento usuarioEvento) {
        this.eventos.remove(usuarioEvento);
        usuarioEvento.setUsuario(null);
        return this;
    }

    public void setEventos(Set<UsuarioEvento> usuarioEventos) {
        this.eventos = usuarioEventos;
    }

    public Set<Ponto> getPontos() {
        return pontos;
    }

    public Usuario pontos(Set<Ponto> pontos) {
        this.pontos = pontos;
        return this;
    }

    public Usuario addPontos(Ponto ponto) {
        this.pontos.add(ponto);
        ponto.setUsuario(this);
        return this;
    }

    public Usuario removePontos(Ponto ponto) {
        this.pontos.remove(ponto);
        ponto.setUsuario(null);
        return this;
    }

    public void setPontos(Set<Ponto> pontos) {
        this.pontos = pontos;
    }

    public UsuarioSettings getSettings() {
        return settings;
    }

    public Usuario settings(UsuarioSettings usuarioSettings) {
        this.settings = usuarioSettings;
        return this;
    }

    public void setSettings(UsuarioSettings usuarioSettings) {
        this.settings = usuarioSettings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        if (usuario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usuario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Usuario{" +
            "id=" + getId() +
            ", email='" + getEmail() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", familyName='" + getFamilyName() + "'" +
            ", givenName='" + getGivenName() + "'" +
            ", provider='" + getProvider() + "'" +
            ", authCode='" + getAuthCode() + "'" +
            ", providerUserId='" + getProviderUserId() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", gender='" + getGender() + "'" +
            ", telephone='" + getTelephone() + "'" +
            "}";
    }
}
