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

import br.com.happhour.domain.UsuarioEmpresa;
import br.com.happhour.repository.UsuarioEmpresaRepository;
import br.com.happhour.web.rest.util.HeaderUtil;
import br.com.happhour.web.rest.util.ResponseUtil;

/**
 * REST controller for managing UsuarioEmpresa.
 */
@RestController
@RequestMapping("/api")
public class UsuarioEmpresaResource {

    private final Logger log = LoggerFactory.getLogger(UsuarioEmpresaResource.class);

    private static final String ENTITY_NAME = "usuarioEmpresa";

    private final UsuarioEmpresaRepository usuarioEmpresaRepository;

    public UsuarioEmpresaResource(UsuarioEmpresaRepository usuarioEmpresaRepository) {
        this.usuarioEmpresaRepository = usuarioEmpresaRepository;
    }

    /**
     * POST  /usuario-empresas : Create a new usuarioEmpresa.
     *
     * @param usuarioEmpresa the usuarioEmpresa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usuarioEmpresa, or with status 400 (Bad Request) if the usuarioEmpresa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/usuario-empresas")
    
    public ResponseEntity<UsuarioEmpresa> createUsuarioEmpresa(@Valid @RequestBody UsuarioEmpresa usuarioEmpresa) throws URISyntaxException {
        log.debug("REST request to save UsuarioEmpresa : {}", usuarioEmpresa);
        if (usuarioEmpresa.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new usuarioEmpresa cannot already have an ID")).body(null);
        }
        UsuarioEmpresa result = usuarioEmpresaRepository.save(usuarioEmpresa);
        return ResponseEntity.created(new URI("/api/usuario-empresas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /usuario-empresas : Updates an existing usuarioEmpresa.
     *
     * @param usuarioEmpresa the usuarioEmpresa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usuarioEmpresa,
     * or with status 400 (Bad Request) if the usuarioEmpresa is not valid,
     * or with status 500 (Internal Server Error) if the usuarioEmpresa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/usuario-empresas")
    
    public ResponseEntity<UsuarioEmpresa> updateUsuarioEmpresa(@Valid @RequestBody UsuarioEmpresa usuarioEmpresa) throws URISyntaxException {
        log.debug("REST request to update UsuarioEmpresa : {}", usuarioEmpresa);
        if (usuarioEmpresa.getId() == null) {
            return createUsuarioEmpresa(usuarioEmpresa);
        }
        UsuarioEmpresa result = usuarioEmpresaRepository.save(usuarioEmpresa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, usuarioEmpresa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /usuario-empresas : get all the usuarioEmpresas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of usuarioEmpresas in body
     */
    @GetMapping("/usuario-empresas")
    
    public List<UsuarioEmpresa> getAllUsuarioEmpresas() {
        log.debug("REST request to get all UsuarioEmpresas");
        return usuarioEmpresaRepository.findAll();
    }

    /**
     * GET  /usuario-empresas/:id : get the "id" usuarioEmpresa.
     *
     * @param id the id of the usuarioEmpresa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usuarioEmpresa, or with status 404 (Not Found)
     */
    @GetMapping("/usuario-empresas/{id}")
    
    public ResponseEntity<UsuarioEmpresa> getUsuarioEmpresa(@PathVariable Long id) {
        log.debug("REST request to get UsuarioEmpresa : {}", id);
        UsuarioEmpresa usuarioEmpresa = usuarioEmpresaRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(usuarioEmpresa));
    }

    /**
     * DELETE  /usuario-empresas/:id : delete the "id" usuarioEmpresa.
     *
     * @param id the id of the usuarioEmpresa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/usuario-empresas/{id}")
    
    public ResponseEntity<Void> deleteUsuarioEmpresa(@PathVariable Long id) {
        log.debug("REST request to delete UsuarioEmpresa : {}", id);
        usuarioEmpresaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
