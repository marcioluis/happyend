package br.com.happhour.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Evento.
 */
@Entity
@Table(name = "evento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
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
    private Set<UsuarioEvento> participantes = new HashSet<>();

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

    public Set<UsuarioEvento> getParticipantes() {
        return participantes;
    }

    public Evento participantes(Set<UsuarioEvento> usuarioEventos) {
        this.participantes = usuarioEventos;
        return this;
    }

    public Evento addParticipantes(UsuarioEvento usuarioEvento) {
        this.participantes.add(usuarioEvento);
        usuarioEvento.setEvento(this);
        return this;
    }

    public Evento removeParticipantes(UsuarioEvento usuarioEvento) {
        this.participantes.remove(usuarioEvento);
        usuarioEvento.setEvento(null);
        return this;
    }

    public void setParticipantes(Set<UsuarioEvento> usuarioEventos) {
        this.participantes = usuarioEventos;
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
        if (evento.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Evento{" +
            "id=" + id +
            ", dataEvento='" + dataEvento + "'" +
            ", finalizado='" + finalizado + "'" +
            ", onDemand='" + onDemand + "'" +
            ", horaInicioHappyOnDemand='" + horaInicioHappyOnDemand + "'" +
            ", horaAgendadaFimHappyOnDemand='" + horaAgendadaFimHappyOnDemand + "'" +
            '}';
    }
}
