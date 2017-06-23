package br.com.happhour.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Usuario.
 */
@Entity
@Table(name = "usuario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usuario implements Serializable {

	private static final long serialVersionUID = -5533625347903297768L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	/**
	 * Token returned from the provider, problably expired in future uses after
	 * first login. Necessary to get the user id, 'subject', from google
	 */
	@Column(name = "provider_id_token")
	private String providerIdToken;

	@Email
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
	@Column(name = "provider", nullable = false)
	private String provider;

	@NotNull
	@Column(name = "telephone", nullable = false)
	private String telephone;

	/**
	 * Google Auth code that can be exchanged for an access token and refresh
	 * token for offline access
	 */
	@Column(name = "auth_code")
	private String authCode;

	/**
	 * Id from the provider, google or facebook
	 */
	@Column(name = "provider_user_id")
	private String providerUserId;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "gender")
	private String gender;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private UserSettings settings;

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

	public Long getId() {
		return id;
	}

	public UserSettings getSettings() {
		return settings;
	}

	public void setSettings(UserSettings settings) {
		this.settings = settings;
		settings.setUsuario(this);
	}

	public String getProviderIdToken() {
		return providerIdToken;
	}

	public String getEmail() {
		return email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getProvider() {
		return provider;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getAuthCode() {
		return authCode;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getGender() {
		return gender;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProviderIdToken(String providerIdToken) {
		this.providerIdToken = providerIdToken;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Usuario usuario = (Usuario) o;
		if (usuario.id == null || id == null) {
			return false;
		}
		return Objects.equals(id, usuario.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "Usuario{" + "id=" + id + ", email='" + email + "'" + ", idToken='" + providerIdToken + "'"
				+ ", displayName='" + displayName + "'" + ", familyName='" + familyName + "'" + ", givenName='"
				+ givenName + "'" + ", provider='" + provider + "'" + ", providerUserId='" + providerUserId + "'"
				+ ", imageUrl='" + imageUrl + "'" + ", gender='" + gender + "'" + '}';
	}
}
