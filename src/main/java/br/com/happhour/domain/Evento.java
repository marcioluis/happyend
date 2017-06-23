package br.com.happhour.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Evento.
 */
@Entity
@Table(name = "evento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_evento")
    private ZonedDateTime dataEvento;

    @Column(name = "finalizado")
    private Boolean finalizado;

    @Column(name = "on_demand")
    private Boolean onDemand;

    @Column(name = "hora_inicio_happy_on_demand")
    private ZonedDateTime horaInicioHappyOnDemand;

    @Column(name = "hora_agendada_fim_happy_on_demand")
    private ZonedDateTime horaAgendadaFimHappyOnDemand;

    @OneToMany(mappedBy = "evento")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UsuarioEvento> usuarios = new HashSet<>();

    @OneToMany(mappedBy = "evento")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ponto> pontos = new HashSet<>();

    @ManyToOne
    private Empresa empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataEvento() {
        return dataEvento;
    }

    public Evento dataEvento(ZonedDateTime dataEvento) {
        this.dataEvento = dataEvento;
        return this;
    }

    public void setDataEvento(ZonedDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Boolean isFinalizado() {
        return finalizado;
    }

    public Evento finalizado(Boolean finalizado) {
        this.finalizado = finalizado;
        return this;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Boolean isOnDemand() {
        return onDemand;
    }

    public Evento onDemand(Boolean onDemand) {
        this.onDemand = onDemand;
        return this;
    }

    public void setOnDemand(Boolean onDemand) {
        this.onDemand = onDemand;
    }

    public ZonedDateTime getHoraInicioHappyOnDemand() {
        return horaInicioHappyOnDemand;
    }

    public Evento horaInicioHappyOnDemand(ZonedDateTime horaInicioHappyOnDemand) {
        this.horaInicioHappyOnDemand = horaInicioHappyOnDemand;
        return this;
    }

    public void setHoraInicioHappyOnDemand(ZonedDateTime horaInicioHappyOnDemand) {
        this.horaInicioHappyOnDemand = horaInicioHappyOnDemand;
    }

    public ZonedDateTime getHoraAgendadaFimHappyOnDemand() {
        return horaAgendadaFimHappyOnDemand;
    }

    public Evento horaAgendadaFimHappyOnDemand(ZonedDateTime horaAgendadaFimHappyOnDemand) {
        this.horaAgendadaFimHappyOnDemand = horaAgendadaFimHappyOnDemand;
        return this;
    }

    public void setHoraAgendadaFimHappyOnDemand(ZonedDateTime horaAgendadaFimHappyOnDemand) {
        this.horaAgendadaFimHappyOnDemand = horaAgendadaFimHappyOnDemand;
    }

    public Set<UsuarioEvento> getUsuarios() {
        return usuarios;
    }

    public Evento usuarios(Set<UsuarioEvento> usuarioEventos) {
        this.usuarios = usuarioEventos;
        return this;
    }

    public Evento addUsuarios(UsuarioEvento usuarioEvento) {
        this.usuarios.add(usuarioEvento);
        usuarioEvento.setEvento(this);
        return this;
    }

    public Evento removeUsuarios(UsuarioEvento usuarioEvento) {
        this.usuarios.remove(usuarioEvento);
        usuarioEvento.setEvento(null);
        return this;
    }

    public void setUsuarios(Set<UsuarioEvento> usuarioEventos) {
        this.usuarios = usuarioEventos;
    }

    public Set<Ponto> getPontos() {
        return pontos;
    }

    public Evento pontos(Set<Ponto> pontos) {
        this.pontos = pontos;
        return this;
    }

    public Evento addPontos(Ponto ponto) {
        this.pontos.add(ponto);
        ponto.setEvento(this);
        return this;
    }

    public Evento removePontos(Ponto ponto) {
        this.pontos.remove(ponto);
        ponto.setEvento(null);
        return this;
    }

    public void setPontos(Set<Ponto> pontos) {
        this.pontos = pontos;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Evento empresa(Empresa empresa) {
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
        Evento evento = (Evento) o;
        if (evento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Evento{" +
            "id=" + getId() +
            ", dataEvento='" + getDataEvento() + "'" +
            ", finalizado='" + isFinalizado() + "'" +
            ", onDemand='" + isOnDemand() + "'" +
            ", horaInicioHappyOnDemand='" + getHoraInicioHappyOnDemand() + "'" +
            ", horaAgendadaFimHappyOnDemand='" + getHoraAgendadaFimHappyOnDemand() + "'" +
            "}";
    }
}
