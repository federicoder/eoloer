package it.maggioli.web.rest;

import it.maggioli.service.UserPersonaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.UserPersonaDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link it.maggioli.domain.UserPersona}.
 */
@RestController
@RequestMapping("/api")
public class UserPersonaResource {

    private final Logger log = LoggerFactory.getLogger(UserPersonaResource.class);

    private static final String ENTITY_NAME = "userPersona";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserPersonaService userPersonaService;

    public UserPersonaResource(UserPersonaService userPersonaService) {
        this.userPersonaService = userPersonaService;
    }

    /**
     * {@code POST  /user-personas} : Create a new userPersona.
     *
     * @param userPersonaDTO the userPersonaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userPersonaDTO, or with status {@code 400 (Bad Request)} if the userPersona has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-personas")
    public ResponseEntity<UserPersonaDTO> createUserPersona(@RequestBody UserPersonaDTO userPersonaDTO) throws URISyntaxException {
        log.debug("REST request to save UserPersona : {}", userPersonaDTO);
        if (userPersonaDTO.getId() != null) {
            throw new BadRequestAlertException("A new userPersona cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserPersonaDTO result = userPersonaService.save(userPersonaDTO);
        return ResponseEntity.created(new URI("/api/user-personas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-personas} : Updates an existing userPersona.
     *
     * @param userPersonaDTO the userPersonaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userPersonaDTO,
     * or with status {@code 400 (Bad Request)} if the userPersonaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userPersonaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-personas")
    public ResponseEntity<UserPersonaDTO> updateUserPersona(@RequestBody UserPersonaDTO userPersonaDTO) throws URISyntaxException {
        log.debug("REST request to update UserPersona : {}", userPersonaDTO);
        if (userPersonaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserPersonaDTO result = userPersonaService.save(userPersonaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userPersonaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-personas} : get all the userPersonas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userPersonas in body.
     */
    @GetMapping("/user-personas")
    public List<UserPersonaDTO> getAllUserPersonas() {
        log.debug("REST request to get all UserPersonas");
        return userPersonaService.findAll();
    }

    /**
     * {@code GET  /user-personas/:id} : get the "id" userPersona.
     *
     * @param id the id of the userPersonaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userPersonaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-personas/{id}")
    public ResponseEntity<UserPersonaDTO> getUserPersona(@PathVariable Long id) {
        log.debug("REST request to get UserPersona : {}", id);
        Optional<UserPersonaDTO> userPersonaDTO = userPersonaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userPersonaDTO);
    }

    /**
     * {@code DELETE  /user-personas/:id} : delete the "id" userPersona.
     *
     * @param id the id of the userPersonaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-personas/{id}")
    public ResponseEntity<Void> deleteUserPersona(@PathVariable Long id) {
        log.debug("REST request to delete UserPersona : {}", id);
        userPersonaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/user-personas?query=:query} : search for the userPersona corresponding
     * to the query.
     *
     * @param query the query of the userPersona search.
     * @return the result of the search.
     */
    @GetMapping("/_search/user-personas")
    public List<UserPersonaDTO> searchUserPersonas(@RequestParam String query) {
        log.debug("REST request to search UserPersonas for query {}", query);
        return userPersonaService.search(query);
    }
}
