package br.com.happhour.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.happhour.domain.Usuario;
import br.com.happhour.service.UsuarioService;
import br.com.happhour.web.rest.util.HeaderUtil;
import br.com.happhour.web.rest.util.ResponseUtil;

/**
 * REST controller for managing Usuario.
 */
@RestController
@RequestMapping("/api")
public class UsuarioResource {

	private final Logger log = LoggerFactory.getLogger(UsuarioResource.class);

	private static final String ENTITY_NAME = "usuario";

	private final UsuarioService usuarioService;

	public UsuarioResource(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	/**
	 * POST /usuarios : Create a new usuario.
	 *
	 * @param Usuario
	 *            the Usuario to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new Usuario, or with status 400 (Bad Request) if the usuario has
	 *         already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario Usuario) throws URISyntaxException {
		log.debug("REST request to save Usuario : {}", Usuario);
		if (Usuario.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new usuario cannot already have an ID"))
					.body(null);
		}
		Usuario result = usuarioService.createUsuarioFromProvider(Usuario);
		return ResponseEntity.created(new URI("/api/usuarios/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /usuarios : Updates an existing usuario.
	 *
	 * @param Usuario
	 *            the Usuario to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         Usuario, or with status 400 (Bad Request) if the Usuario is not
	 *         valid, or with status 500 (Internal Server Error) if the Usuario
	 *         couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/usuarios")
	public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario Usuario) throws URISyntaxException {
		log.debug("REST request to update Usuario : {}", Usuario);
		if (Usuario.getId() == null) {
			return createUsuario(Usuario);
		}
		Usuario result = usuarioService.save(Usuario);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Usuario.getId().toString()))
				.body(result);
	}

	/**
	 * GET /usuarios : get all the usuarios.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of usuarios
	 *         in body
	 */
	@GetMapping("/usuarios")
	public List<Usuario> getAllUsuarios() {
		log.debug("REST request to get all Usuarios");
		return usuarioService.findAll();
	}

	/**
	 * GET /usuarios/:id : get the "id" usuario.
	 *
	 * @param id
	 *            the id of the Usuario to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         Usuario, or with status 404 (Not Found)
	 */
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
		log.debug("REST request to get Usuario : {}", id);
		Usuario Usuario = usuarioService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Usuario));
	}

	/**
	 * DELETE /usuarios/:id : delete the "id" usuario.
	 *
	 * @param id
	 *            the id of the Usuario to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
		log.debug("REST request to delete Usuario : {}", id);
		usuarioService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
