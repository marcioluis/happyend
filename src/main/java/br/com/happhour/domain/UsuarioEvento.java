package br.com.happhour.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A UsuarioEvento.
 */
@Entity
@Table(name = "usuario_evento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UsuarioEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_chegada")
    private ZonedDateTime dataChegada;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Evento evento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataChegada() {
        return dataChegada;
    }

    public UsuarioEvento dataChegada(ZonedDateTime dataChegada) {
        this.dataChegada = dataChegada;
        return this;
    }

    public void setDataChegada(ZonedDateTime dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public UsuarioEvento usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public UsuarioEvento evento(Evento evento) {
        this.evento = evento;
        return this;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsuarioEvento usuarioEvento = (UsuarioEvento) o;
        if (usuarioEvento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usuarioEvento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UsuarioEvento{" +
            "id=" + getId() +
            ", dataChegada='" + getDataChegada() + "'" +
            "}";
    }
}
