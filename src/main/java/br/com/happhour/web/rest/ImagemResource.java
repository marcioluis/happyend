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

import br.com.happhour.domain.Imagem;
import br.com.happhour.repository.ImagemRepository;
import br.com.happhour.web.rest.util.HeaderUtil;
import br.com.happhour.web.rest.util.ResponseUtil;

/**
 * REST controller for managing Imagem.
 */
@RestController
@RequestMapping("/api")
public class ImagemResource {

    private final Logger log = LoggerFactory.getLogger(ImagemResource.class);

    private static final String ENTITY_NAME = "imagem";

    private final ImagemRepository imagemRepository;

    public ImagemResource(ImagemRepository imagemRepository) {
        this.imagemRepository = imagemRepository;
    }

    /**
     * POST  /imagems : Create a new imagem.
     *
     * @param imagem the imagem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new imagem, or with status 400 (Bad Request) if the imagem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/imagems")
    
    public ResponseEntity<Imagem> createImagem(@Valid @RequestBody Imagem imagem) throws URISyntaxException {
        log.debug("REST request to save Imagem : {}", imagem);
        if (imagem.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new imagem cannot already have an ID")).body(null);
        }
        Imagem result = imagemRepository.save(imagem);
        return ResponseEntity.created(new URI("/api/imagems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /imagems : Updates an existing imagem.
     *
     * @param imagem the imagem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated imagem,
     * or with status 400 (Bad Request) if the imagem is not valid,
     * or with status 500 (Internal Server Error) if the imagem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/imagems")
    
    public ResponseEntity<Imagem> updateImagem(@Valid @RequestBody Imagem imagem) throws URISyntaxException {
        log.debug("REST request to update Imagem : {}", imagem);
        if (imagem.getId() == null) {
            return createImagem(imagem);
        }
        Imagem result = imagemRepository.save(imagem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, imagem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /imagems : get all the imagems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of imagems in body
     */
    @GetMapping("/imagems")
    
    public List<Imagem> getAllImagems() {
        log.debug("REST request to get all Imagems");
        return imagemRepository.findAll();
    }

    /**
     * GET  /imagems/:id : get the "id" imagem.
     *
     * @param id the id of the imagem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the imagem, or with status 404 (Not Found)
     */
    @GetMapping("/imagems/{id}")
    
    public ResponseEntity<Imagem> getImagem(@PathVariable Long id) {
        log.debug("REST request to get Imagem : {}", id);
        Imagem imagem = imagemRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(imagem));
    }

    /**
     * DELETE  /imagems/:id : delete the "id" imagem.
     *
     * @param id the id of the imagem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/imagems/{id}")
    
    public ResponseEntity<Void> deleteImagem(@PathVariable Long id) {
        log.debug("REST request to delete Imagem : {}", id);
        imagemRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
