package br.com.happhour.web.rest;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.happhour.domain.Usuario;

/**
 * REST controller for managing Usuario.
 */
@RestController
@RequestMapping("/api")
public class UsuarioResource {

	private final Logger log = LoggerFactory.getLogger(UsuarioResource.class);

	private static final String ENTITY_NAME = "usuario";

	/**
	 * POST /usuarios : Create a new usuario.
	 *
	 * @param usuario
	 *            the usuario to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new usuario, or with status 400 (Bad Request) if the usuario has
	 *         already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) throws URISyntaxException {

		return null;// ResponseEntity.created(new
					// URI("/api/usuarios/1")).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
					// "id")).body(result);
	}
}
