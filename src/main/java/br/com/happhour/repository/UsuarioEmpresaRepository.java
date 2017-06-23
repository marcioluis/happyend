package br.com.happhour.repository;

import br.com.happhour.domain.UsuarioEmpresa;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UsuarioEmpresa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsuarioEmpresaRepository extends JpaRepository<UsuarioEmpresa,Long> {
    
}
