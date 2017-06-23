package br.com.happhour.repository;

import br.com.happhour.domain.UsuarioEvento;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UsuarioEvento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsuarioEventoRepository extends JpaRepository<UsuarioEvento,Long> {
    
}
