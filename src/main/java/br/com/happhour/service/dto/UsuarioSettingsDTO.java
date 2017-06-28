package br.com.happhour.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the user settings entity
 * 
 * @author marcio.arrosi
 * 
 */
public class UsuarioSettingsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer searchRadius;
	private Boolean geofances;
	private Boolean notifications;
	private Boolean promotions;
	private Long usuarioId;
	
	public Long getId() {
		return id;
	}

	public Integer getSearchRadius() {
		return searchRadius;
	}

	public Boolean getGeofances() {
		return geofances;
	}

	public Boolean getNotifications() {
		return notifications;
	}

	public Boolean getPromotions() {
		return promotions;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSearchRadius(Integer searchRadius) {
		this.searchRadius = searchRadius;
	}

	public void setGeofances(Boolean geofances) {
		this.geofances = geofances;
	}

	public void setNotifications(Boolean notifications) {
		this.notifications = notifications;
	}

	public void setPromotions(Boolean promotions) {
		this.promotions = promotions;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UsuarioSettingsDTO dto = (UsuarioSettingsDTO) o;
        if(dto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UsuarioSettingsDTO{" +
            "id=" + getId() +
            ", searchRadius='" + getSearchRadius() + "'" +
				", geofances='" + getGeofances() + "'" + 
            ", notifications='" + getNotifications() + "'" +
			", promotions='" + getPromotions() + "'" + 
            ", userId= '" + getUsuarioId() + "'" +
			"}";
    }

}
