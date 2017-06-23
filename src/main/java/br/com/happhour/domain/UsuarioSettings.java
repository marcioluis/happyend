package br.com.happhour.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A UsuarioSettings.
 */
@Entity
@Table(name = "usuario_settings")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UsuarioSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "search_radius")
    private Integer searchRadius;

    @Column(name = "geofances")
    private Boolean geofances;

    @Column(name = "notifications")
    private Boolean notifications;

    @Column(name = "promotions")
    private Boolean promotions;

    @OneToOne(mappedBy = "settings")
    @JsonIgnore
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSearchRadius() {
        return searchRadius;
    }

    public UsuarioSettings searchRadius(Integer searchRadius) {
        this.searchRadius = searchRadius;
        return this;
    }

    public void setSearchRadius(Integer searchRadius) {
        this.searchRadius = searchRadius;
    }

    public Boolean isGeofances() {
        return geofances;
    }

    public UsuarioSettings geofances(Boolean geofances) {
        this.geofances = geofances;
        return this;
    }

    public void setGeofances(Boolean geofances) {
        this.geofances = geofances;
    }

    public Boolean isNotifications() {
        return notifications;
    }

    public UsuarioSettings notifications(Boolean notifications) {
        this.notifications = notifications;
        return this;
    }

    public void setNotifications(Boolean notifications) {
        this.notifications = notifications;
    }

    public Boolean isPromotions() {
        return promotions;
    }

    public UsuarioSettings promotions(Boolean promotions) {
        this.promotions = promotions;
        return this;
    }

    public void setPromotions(Boolean promotions) {
        this.promotions = promotions;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public UsuarioSettings usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsuarioSettings usuarioSettings = (UsuarioSettings) o;
        if (usuarioSettings.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usuarioSettings.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UsuarioSettings{" +
            "id=" + getId() +
            ", searchRadius='" + getSearchRadius() + "'" +
            ", geofances='" + isGeofances() + "'" +
            ", notifications='" + isNotifications() + "'" +
            ", promotions='" + isPromotions() + "'" +
            "}";
    }
}
