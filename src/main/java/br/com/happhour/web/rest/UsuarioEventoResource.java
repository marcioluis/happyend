package br.com.happhour.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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

import br.com.happhour.domain.UsuarioEvento;
import br.com.happhour.repository.UsuarioEventoRepository;
import br.com.happhour.web.rest.util.HeaderUtil;
import br.com.happhour.web.rest.util.ResponseUtil;

/**
 * REST controller for managing UsuarioEvento.
 */
@RestController
@RequestMapping("/api")
public class UsuarioEventoResource {

    private final Logger log = LoggerFactory.getLogger(UsuarioEventoResource.class);

    private static final String ENTITY_NAME = "usuarioEvento";

    private final UsuarioEventoRepository usuarioEventoRepository;

    public UsuarioEventoResource(UsuarioEventoRepository usuarioEventoRepository) {
        this.usuarioEventoRepository = usuarioEventoRepository;
    }

    /**
     * POST  /usuario-eventos : Create a new usuarioEvento.
     *
     * @param usuarioEvento the usuarioEvento to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usuarioEvento, or with status 400 (Bad Request) if the usuarioEvento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/usuario-eventos")
    
    public ResponseEntity<UsuarioEvento> createUsuarioEvento(@RequestBody UsuarioEvento usuarioEvento) throws URISyntaxException {
        log.debug("REST request to save UsuarioEvento : {}", usuarioEvento);
        if (usuarioEvento.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new usuarioEvento cannot already have an ID")).body(null);
        }
        UsuarioEvento result = usuarioEventoRepository.save(usuarioEvento);
        return ResponseEntity.created(new URI("/api/usuario-eventos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /usuario-eventos : Updates an existing usuarioEvento.
     *
     * @param usuarioEvento the usuarioEvento to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usuarioEvento,
     * or with status 400 (Bad Request) if the usuarioEvento is not valid,
     * or with status 500 (Internal Server Error) if the usuarioEvento couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/usuario-eventos")
    
    public ResponseEntity<UsuarioEvento> updateUsuarioEvento(@RequestBody UsuarioEvento usuarioEvento) throws URISyntaxException {
        log.debug("REST request to update UsuarioEvento : {}", usuarioEvento);
        if (usuarioEvento.getId() == null) {
            return createUsuarioEvento(usuarioEvento);
        }
        UsuarioEvento result = usuarioEventoRepository.save(usuarioEvento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, usuarioEvento.getId().toString()))
            .body(result);
    }

    /**
     * GET  /usuario-eventos : get all the usuarioEventos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of usuarioEventos in body
     */
    @GetMapping("/usuario-eventos")
    
    public List<UsuarioEvento> getAllUsuarioEventos() {
        log.debug("REST request to get all UsuarioEventos");
        return usuarioEventoRepository.findAll();
    }

    /**
     * GET  /usuario-eventos/:id : get the "id" usuarioEvento.
     *
     * @param id the id of the usuarioEvento to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usuarioEvento, or with status 404 (Not Found)
     */
    @GetMapping("/usuario-eventos/{id}")
    
    public ResponseEntity<UsuarioEvento> getUsuarioEvento(@PathVariable Long id) {
        log.debug("REST request to get UsuarioEvento : {}", id);
        UsuarioEvento usuarioEvento = usuarioEventoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(usuarioEvento));
    }

    /**
     * DELETE  /usuario-eventos/:id : delete the "id" usuarioEvento.
     *
     * @param id the id of the usuarioEvento to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/usuario-eventos/{id}")
    
    public ResponseEntity<Void> deleteUsuarioEvento(@PathVariable Long id) {
        log.debug("REST request to delete UsuarioEvento : {}", id);
        usuarioEventoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
