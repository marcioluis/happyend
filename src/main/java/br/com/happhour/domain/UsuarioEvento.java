package br.com.happhour.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UsuarioEvento.
 */
@Entity
@Table(name = "usuario_evento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UsuarioEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "data_chegada")
    private ZonedDateTime dataChegada;

    @ManyToOne
    private Evento evento;

    @ManyToOne
    private Usuario usuario;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsuarioEvento usuarioEvento = (UsuarioEvento) o;
        if (usuarioEvento.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, usuarioEvento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UsuarioEvento{" +
            "id=" + id +
            ", dataChegada='" + dataChegada + "'" +
            '}';
    }
}
