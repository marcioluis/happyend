package br.com.happhour.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Usuario entity.
 */
public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * Token returned from the provider, problably expired in future uses after
	 * first login. Necessary to get the user id, 'subject', from google
	 */
	private String providerIdToken;

    @NotNull
    private String email;

    @NotNull
    private String displayName;

    @NotNull
    private String familyName;

    @NotNull
    private String givenName;

    @NotNull
    private String provider;

    private String authCode;

    private String providerUserId;

    private String imageUrl;

    private String gender;

    @NotNull
    private String telephone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UsuarioDTO usuarioDTO = (UsuarioDTO) o;
        if(usuarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usuarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
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

	public String getProviderIdToken() {
		return providerIdToken;
	}

	public void setProviderIdToken(String providerIdToken) {
		this.providerIdToken = providerIdToken;
	}
}
