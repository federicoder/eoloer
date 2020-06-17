package it.maggioli.web.rest;

import it.maggioli.service.EmailPersonaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.EmailPersonaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.EmailPersona}.
 */
@RestController
@RequestMapping("/api")
public class EmailPersonaResource {

    private final Logger log = LoggerFactory.getLogger(EmailPersonaResource.class);

    private static final String ENTITY_NAME = "emailPersona";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmailPersonaService emailPersonaService;

    public EmailPersonaResource(EmailPersonaService emailPersonaService) {
        this.emailPersonaService = emailPersonaService;
    }

    /**
     * {@code POST  /email-personas} : Create a new emailPersona.
     *
     * @param emailPersonaDTO the emailPersonaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new emailPersonaDTO, or with status {@code 400 (Bad Request)} if the emailPersona has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/email-personas")
    public ResponseEntity<EmailPersonaDTO> createEmailPersona(@Valid @RequestBody EmailPersonaDTO emailPersonaDTO) throws URISyntaxException {
        log.debug("REST request to save EmailPersona : {}", emailPersonaDTO);
        if (emailPersonaDTO.getId() != null) {
            throw new BadRequestAlertException("A new emailPersona cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmailPersonaDTO result = emailPersonaService.save(emailPersonaDTO);
        return ResponseEntity.created(new URI("/api/email-personas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /email-personas} : Updates an existing emailPersona.
     *
     * @param emailPersonaDTO the emailPersonaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emailPersonaDTO,
     * or with status {@code 400 (Bad Request)} if the emailPersonaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the emailPersonaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/email-personas")
    public ResponseEntity<EmailPersonaDTO> updateEmailPersona(@Valid @RequestBody EmailPersonaDTO emailPersonaDTO) throws URISyntaxException {
        log.debug("REST request to update EmailPersona : {}", emailPersonaDTO);
        if (emailPersonaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmailPersonaDTO result = emailPersonaService.save(emailPersonaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emailPersonaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /email-personas} : get all the emailPersonas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emailPersonas in body.
     */
    @GetMapping("/email-personas")
    public List<EmailPersonaDTO> getAllEmailPersonas() {
        log.debug("REST request to get all EmailPersonas");
        return emailPersonaService.findAll();
    }

    /**
     * {@code GET  /email-personas/:id} : get the "id" emailPersona.
     *
     * @param id the id of the emailPersonaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emailPersonaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/email-personas/{id}")
    public ResponseEntity<EmailPersonaDTO> getEmailPersona(@PathVariable Long id) {
        log.debug("REST request to get EmailPersona : {}", id);
        Optional<EmailPersonaDTO> emailPersonaDTO = emailPersonaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(emailPersonaDTO);
    }

    /**
     * {@code DELETE  /email-personas/:id} : delete the "id" emailPersona.
     *
     * @param id the id of the emailPersonaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/email-personas/{id}")
    public ResponseEntity<Void> deleteEmailPersona(@PathVariable Long id) {
        log.debug("REST request to delete EmailPersona : {}", id);
        emailPersonaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/email-personas?query=:query} : search for the emailPersona corresponding
     * to the query.
     *
     * @param query the query of the emailPersona search.
     * @return the result of the search.
     */
    @GetMapping("/_search/email-personas")
    public List<EmailPersonaDTO> searchEmailPersonas(@RequestParam String query) {
        log.debug("REST request to search EmailPersonas for query {}", query);
        return emailPersonaService.search(query);
    }
}
