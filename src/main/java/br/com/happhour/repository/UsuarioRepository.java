package br.com.happhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.happhour.domain.Usuario;

/**
 * Spring Data JPA repository for the Usuario entity.
 */
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
