package br.com.happhour.repository;

import br.com.happhour.domain.Imagem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Imagem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImagemRepository extends JpaRepository<Imagem,Long> {
    
}
