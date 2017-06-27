package br.com.happhour.repository;

import br.com.happhour.domain.UsuarioSettings;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UsuarioSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsuarioSettingsRepository extends JpaRepository<UsuarioSettings,Long> {
    
}
