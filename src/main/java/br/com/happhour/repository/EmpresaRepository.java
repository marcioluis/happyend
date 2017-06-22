package br.com.happhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.happhour.domain.Empresa;

/**
 * Spring Data JPA repository for the Empresa entity.
 */
public interface EmpresaRepository extends JpaRepository<Empresa,Long> {

}
