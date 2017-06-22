package br.com.happhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.happhour.domain.Evento;

/**
 * Spring Data JPA repository for the Evento entity.
 */
public interface EventoRepository extends JpaRepository<Evento,Long> {

}
