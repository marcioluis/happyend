package br.com.happhour.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_settings")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserSettings implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@Column(name = "search_radius")
	private Integer searchRadius;

	@Column(name = "geofances")
	private Boolean geofances;

	@Column(name = "notifications")
	private Boolean notifications;

	@Column(name = "promotions")
	private Boolean promotions;

	@OneToOne(mappedBy = "settings", orphanRemoval = true)
	@JsonIgnore
	private Usuario usuario;

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

	public Usuario getUsuario() {
		return usuario;
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

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		usuario.setSettings(this);
	}

}
