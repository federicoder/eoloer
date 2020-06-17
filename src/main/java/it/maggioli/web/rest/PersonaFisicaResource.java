package it.maggioli.web.rest;

import it.maggioli.service.PersonaFisicaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.PersonaFisicaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.PersonaFisica}.
 */
@RestController
@RequestMapping("/api")
public class PersonaFisicaResource {

    private final Logger log = LoggerFactory.getLogger(PersonaFisicaResource.class);

    private static final String ENTITY_NAME = "personaFisica";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonaFisicaService personaFisicaService;

    public PersonaFisicaResource(PersonaFisicaService personaFisicaService) {
        this.personaFisicaService = personaFisicaService;
    }

    /**
     * {@code POST  /persona-fisicas} : Create a new personaFisica.
     *
     * @param personaFisicaDTO the personaFisicaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personaFisicaDTO, or with status {@code 400 (Bad Request)} if the personaFisica has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/persona-fisicas")
    public ResponseEntity<PersonaFisicaDTO> createPersonaFisica(@Valid @RequestBody PersonaFisicaDTO personaFisicaDTO) throws URISyntaxException {
        log.debug("REST request to save PersonaFisica : {}", personaFisicaDTO);
        if (personaFisicaDTO.getId() != null) {
            throw new BadRequestAlertException("A new personaFisica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonaFisicaDTO result = personaFisicaService.save(personaFisicaDTO);
        return ResponseEntity.created(new URI("/api/persona-fisicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /persona-fisicas} : Updates an existing personaFisica.
     *
     * @param personaFisicaDTO the personaFisicaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personaFisicaDTO,
     * or with status {@code 400 (Bad Request)} if the personaFisicaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personaFisicaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/persona-fisicas")
    public ResponseEntity<PersonaFisicaDTO> updatePersonaFisica(@Valid @RequestBody PersonaFisicaDTO personaFisicaDTO) throws URISyntaxException {
        log.debug("REST request to update PersonaFisica : {}", personaFisicaDTO);
        if (personaFisicaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonaFisicaDTO result = personaFisicaService.save(personaFisicaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personaFisicaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /persona-fisicas} : get all the personaFisicas.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personaFisicas in body.
     */
    @GetMapping("/persona-fisicas")
    public List<PersonaFisicaDTO> getAllPersonaFisicas(@RequestParam(required = false) String filter) {
        if ("idruolopersona-is-null".equals(filter)) {
            log.debug("REST request to get all PersonaFisicas where idRuoloPersona is null");
            return personaFisicaService.findAllWhereIdRuoloPersonaIsNull();
        }
        log.debug("REST request to get all PersonaFisicas");
        return personaFisicaService.findAll();
    }

    /**
     * {@code GET  /persona-fisicas/:id} : get the "id" personaFisica.
     *
     * @param id the id of the personaFisicaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personaFisicaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/persona-fisicas/{id}")
    public ResponseEntity<PersonaFisicaDTO> getPersonaFisica(@PathVariable Long id) {
        log.debug("REST request to get PersonaFisica : {}", id);
        Optional<PersonaFisicaDTO> personaFisicaDTO = personaFisicaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personaFisicaDTO);
    }

    /**
     * {@code DELETE  /persona-fisicas/:id} : delete the "id" personaFisica.
     *
     * @param id the id of the personaFisicaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/persona-fisicas/{id}")
    public ResponseEntity<Void> deletePersonaFisica(@PathVariable Long id) {
        log.debug("REST request to delete PersonaFisica : {}", id);
        personaFisicaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/persona-fisicas?query=:query} : search for the personaFisica corresponding
     * to the query.
     *
     * @param query the query of the personaFisica search.
     * @return the result of the search.
     */
    @GetMapping("/_search/persona-fisicas")
    public List<PersonaFisicaDTO> searchPersonaFisicas(@RequestParam String query) {
        log.debug("REST request to search PersonaFisicas for query {}", query);
        return personaFisicaService.search(query);
    }
}
