package br.com.happhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.happhour.domain.Ponto;

/**
 * Spring Data JPA repository for the Ponto entity.
 */
public interface PontoRepository extends JpaRepository<Ponto,Long> {

}
