package br.com.happhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.happhour.domain.UsuarioEmpresa;

/**
 * Spring Data JPA repository for the UsuarioEmpresa entity.
 */
public interface UsuarioEmpresaRepository extends JpaRepository<UsuarioEmpresa,Long> {

}
