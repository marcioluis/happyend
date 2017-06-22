package br.com.happhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.happhour.domain.Imagem;

/**
 * Spring Data JPA repository for the Imagem entity.
 */
public interface ImagemRepository extends JpaRepository<Imagem,Long> {

}
