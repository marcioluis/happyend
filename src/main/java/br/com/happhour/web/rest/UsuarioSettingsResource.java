package br.com.happhour.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.happhour.domain.UsuarioSettings;
import br.com.happhour.repository.UsuarioSettingsRepository;
import br.com.happhour.web.rest.util.HeaderUtil;
import br.com.happhour.web.rest.util.ResponseUtil;

/**
 * REST controller for managing UsuarioSettings.
 */
@RestController
@RequestMapping("/api")
public class UsuarioSettingsResource {

    private final Logger log = LoggerFactory.getLogger(UsuarioSettingsResource.class);

    private static final String ENTITY_NAME = "usuarioSettings";

    private final UsuarioSettingsRepository usuarioSettingsRepository;

    public UsuarioSettingsResource(UsuarioSettingsRepository usuarioSettingsRepository) {
        this.usuarioSettingsRepository = usuarioSettingsRepository;
    }

    /**
     * POST  /usuario-settings : Create a new usuarioSettings.
     *
     * @param usuarioSettings the usuarioSettings to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usuarioSettings, or with status 400 (Bad Request) if the usuarioSettings has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/usuario-settings")
    
    public ResponseEntity<UsuarioSettings> createUsuarioSettings(@RequestBody UsuarioSettings usuarioSettings) throws URISyntaxException {
        log.debug("REST request to save UsuarioSettings : {}", usuarioSettings);
        if (usuarioSettings.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new usuarioSettings cannot already have an ID")).body(null);
        }
        UsuarioSettings result = usuarioSettingsRepository.save(usuarioSettings);
        return ResponseEntity.created(new URI("/api/usuario-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /usuario-settings : Updates an existing usuarioSettings.
     *
     * @param usuarioSettings the usuarioSettings to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usuarioSettings,
     * or with status 400 (Bad Request) if the usuarioSettings is not valid,
     * or with status 500 (Internal Server Error) if the usuarioSettings couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/usuario-settings")
    
    public ResponseEntity<UsuarioSettings> updateUsuarioSettings(@RequestBody UsuarioSettings usuarioSettings) throws URISyntaxException {
        log.debug("REST request to update UsuarioSettings : {}", usuarioSettings);
        if (usuarioSettings.getId() == null) {
            return createUsuarioSettings(usuarioSettings);
        }
        UsuarioSettings result = usuarioSettingsRepository.save(usuarioSettings);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, usuarioSettings.getId().toString()))
            .body(result);
    }

    /**
     * GET  /usuario-settings : get all the usuarioSettings.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of usuarioSettings in body
     */
    @GetMapping("/usuario-settings")
    
    public List<UsuarioSettings> getAllUsuarioSettings(@RequestParam(required = false) String filter) {
        if ("usuario-is-null".equals(filter)) {
            log.debug("REST request to get all UsuarioSettingss where usuario is null");
            return StreamSupport
                .stream(usuarioSettingsRepository.findAll().spliterator(), false)
                .filter(usuarioSettings -> usuarioSettings.getUsuario() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all UsuarioSettings");
        return usuarioSettingsRepository.findAll();
    }

    /**
     * GET  /usuario-settings/:id : get the "id" usuarioSettings.
     *
     * @param id the id of the usuarioSettings to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usuarioSettings, or with status 404 (Not Found)
     */
    @GetMapping("/usuario-settings/{id}")
    
    public ResponseEntity<UsuarioSettings> getUsuarioSettings(@PathVariable Long id) {
        log.debug("REST request to get UsuarioSettings : {}", id);
        UsuarioSettings usuarioSettings = usuarioSettingsRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(usuarioSettings));
    }

    /**
     * DELETE  /usuario-settings/:id : delete the "id" usuarioSettings.
     *
     * @param id the id of the usuarioSettings to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/usuario-settings/{id}")
    
    public ResponseEntity<Void> deleteUsuarioSettings(@PathVariable Long id) {
        log.debug("REST request to delete UsuarioSettings : {}", id);
        usuarioSettingsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
