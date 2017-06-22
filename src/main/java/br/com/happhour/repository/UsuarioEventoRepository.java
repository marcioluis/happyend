package br.com.happhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.happhour.domain.UsuarioEvento;

/**
 * Spring Data JPA repository for the UsuarioEvento entity.
 */
public interface UsuarioEventoRepository extends JpaRepository<UsuarioEvento,Long> {

}
