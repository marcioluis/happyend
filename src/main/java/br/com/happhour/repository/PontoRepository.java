package br.com.happhour.repository;

import br.com.happhour.domain.Ponto;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Ponto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PontoRepository extends JpaRepository<Ponto,Long> {
    
}
