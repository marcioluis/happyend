package br.com.happhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.happhour.domain.Usuario;


/**
 * Spring Data JPA repository for the Usuario entity.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
	Usuario findByProviderUserIdAndProvider(String providerId, String providerName);
}
