package it.maggioli.web.rest;

import it.maggioli.service.NotePersonaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.NotePersonaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.NotePersona}.
 */
@RestController
@RequestMapping("/api")
public class NotePersonaResource {

    private final Logger log = LoggerFactory.getLogger(NotePersonaResource.class);

    private static final String ENTITY_NAME = "notePersona";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotePersonaService notePersonaService;

    public NotePersonaResource(NotePersonaService notePersonaService) {
        this.notePersonaService = notePersonaService;
    }

    /**
     * {@code POST  /note-personas} : Create a new notePersona.
     *
     * @param notePersonaDTO the notePersonaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notePersonaDTO, or with status {@code 400 (Bad Request)} if the notePersona has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/note-personas")
    public ResponseEntity<NotePersonaDTO> createNotePersona(@Valid @RequestBody NotePersonaDTO notePersonaDTO) throws URISyntaxException {
        log.debug("REST request to save NotePersona : {}", notePersonaDTO);
        if (notePersonaDTO.getId() != null) {
            throw new BadRequestAlertException("A new notePersona cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotePersonaDTO result = notePersonaService.save(notePersonaDTO);
        return ResponseEntity.created(new URI("/api/note-personas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /note-personas} : Updates an existing notePersona.
     *
     * @param notePersonaDTO the notePersonaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notePersonaDTO,
     * or with status {@code 400 (Bad Request)} if the notePersonaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notePersonaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/note-personas")
    public ResponseEntity<NotePersonaDTO> updateNotePersona(@Valid @RequestBody NotePersonaDTO notePersonaDTO) throws URISyntaxException {
        log.debug("REST request to update NotePersona : {}", notePersonaDTO);
        if (notePersonaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotePersonaDTO result = notePersonaService.save(notePersonaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notePersonaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /note-personas} : get all the notePersonas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notePersonas in body.
     */
    @GetMapping("/note-personas")
    public List<NotePersonaDTO> getAllNotePersonas() {
        log.debug("REST request to get all NotePersonas");
        return notePersonaService.findAll();
    }

    /**
     * {@code GET  /note-personas/:id} : get the "id" notePersona.
     *
     * @param id the id of the notePersonaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notePersonaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/note-personas/{id}")
    public ResponseEntity<NotePersonaDTO> getNotePersona(@PathVariable Long id) {
        log.debug("REST request to get NotePersona : {}", id);
        Optional<NotePersonaDTO> notePersonaDTO = notePersonaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notePersonaDTO);
    }

    /**
     * {@code DELETE  /note-personas/:id} : delete the "id" notePersona.
     *
     * @param id the id of the notePersonaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/note-personas/{id}")
    public ResponseEntity<Void> deleteNotePersona(@PathVariable Long id) {
        log.debug("REST request to delete NotePersona : {}", id);
        notePersonaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/note-personas?query=:query} : search for the notePersona corresponding
     * to the query.
     *
     * @param query the query of the notePersona search.
     * @return the result of the search.
     */
    @GetMapping("/_search/note-personas")
    public List<NotePersonaDTO> searchNotePersonas(@RequestParam String query) {
        log.debug("REST request to search NotePersonas for query {}", query);
        return notePersonaService.search(query);
    }
}
