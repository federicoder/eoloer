package it.maggioli.web.rest;

import it.maggioli.service.ProdottoService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.ProdottoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link it.maggioli.domain.Prodotto}.
 */
@RestController
@RequestMapping("/api")
public class ProdottoResource {

    private final Logger log = LoggerFactory.getLogger(ProdottoResource.class);

    private static final String ENTITY_NAME = "prodotto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProdottoService prodottoService;

    public ProdottoResource(ProdottoService prodottoService) {
        this.prodottoService = prodottoService;
    }

    /**
     * {@code POST  /prodottos} : Create a new prodotto.
     *
     * @param prodottoDTO the prodottoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prodottoDTO, or with status {@code 400 (Bad Request)} if the prodotto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prodottos")
    public ResponseEntity<ProdottoDTO> createProdotto(@Valid @RequestBody ProdottoDTO prodottoDTO) throws URISyntaxException {
        log.debug("REST request to save Prodotto : {}", prodottoDTO);
        if (prodottoDTO.getId() != null) {
            throw new BadRequestAlertException("A new prodotto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProdottoDTO result = prodottoService.save(prodottoDTO);
        return ResponseEntity.created(new URI("/api/prodottos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prodottos} : Updates an existing prodotto.
     *
     * @param prodottoDTO the prodottoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prodottoDTO,
     * or with status {@code 400 (Bad Request)} if the prodottoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prodottoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prodottos")
    public ResponseEntity<ProdottoDTO> updateProdotto(@Valid @RequestBody ProdottoDTO prodottoDTO) throws URISyntaxException {
        log.debug("REST request to update Prodotto : {}", prodottoDTO);
        if (prodottoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProdottoDTO result = prodottoService.save(prodottoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prodottoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /prodottos} : get all the prodottos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prodottos in body.
     */
    @GetMapping("/prodottos")
    public List<ProdottoDTO> getAllProdottos() {
        log.debug("REST request to get all Prodottos");
        return prodottoService.findAll();
    }

    /**
     * {@code GET  /prodottos/:id} : get the "id" prodotto.
     *
     * @param id the id of the prodottoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prodottoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prodottos/{id}")
    public ResponseEntity<ProdottoDTO> getProdotto(@PathVariable Long id) {
        log.debug("REST request to get Prodotto : {}", id);
        Optional<ProdottoDTO> prodottoDTO = prodottoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(prodottoDTO);
    }

    /**
     * {@code DELETE  /prodottos/:id} : delete the "id" prodotto.
     *
     * @param id the id of the prodottoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prodottos/{id}")
    public ResponseEntity<Void> deleteProdotto(@PathVariable Long id) {
        log.debug("REST request to delete Prodotto : {}", id);
        prodottoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/prodottos?query=:query} : search for the prodotto corresponding
     * to the query.
     *
     * @param query the query of the prodotto search.
     * @return the result of the search.
     */
    @GetMapping("/_search/prodottos")
    public List<ProdottoDTO> searchProdottos(@RequestParam String query) {
        log.debug("REST request to search Prodottos for query {}", query);
        return prodottoService.search(query);
    }
}
