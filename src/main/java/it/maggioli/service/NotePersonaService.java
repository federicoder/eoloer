package it.maggioli.service;

import it.maggioli.domain.NotePersona;
import it.maggioli.repository.NotePersonaRepository;
import it.maggioli.repository.search.NotePersonaSearchRepository;
import it.maggioli.service.dto.NotePersonaDTO;
import it.maggioli.service.mapper.NotePersonaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link NotePersona}.
 */
@Service
@Transactional
public class NotePersonaService {

    private final Logger log = LoggerFactory.getLogger(NotePersonaService.class);

    private final NotePersonaRepository notePersonaRepository;

    private final NotePersonaMapper notePersonaMapper;

    private final NotePersonaSearchRepository notePersonaSearchRepository;

    public NotePersonaService(NotePersonaRepository notePersonaRepository, NotePersonaMapper notePersonaMapper, NotePersonaSearchRepository notePersonaSearchRepository) {
        this.notePersonaRepository = notePersonaRepository;
        this.notePersonaMapper = notePersonaMapper;
        this.notePersonaSearchRepository = notePersonaSearchRepository;
    }

    /**
     * Save a notePersona.
     *
     * @param notePersonaDTO the entity to save.
     * @return the persisted entity.
     */
    public NotePersonaDTO save(NotePersonaDTO notePersonaDTO) {
        log.debug("Request to save NotePersona : {}", notePersonaDTO);
        NotePersona notePersona = notePersonaMapper.toEntity(notePersonaDTO);
        notePersona = notePersonaRepository.save(notePersona);
        NotePersonaDTO result = notePersonaMapper.toDto(notePersona);
        notePersonaSearchRepository.save(notePersona);
        return result;
    }

    /**
     * Get all the notePersonas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NotePersonaDTO> findAll() {
        log.debug("Request to get all NotePersonas");
        return notePersonaRepository.findAll().stream()
            .map(notePersonaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one notePersona by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NotePersonaDTO> findOne(Long id) {
        log.debug("Request to get NotePersona : {}", id);
        return notePersonaRepository.findById(id)
            .map(notePersonaMapper::toDto);
    }

    /**
     * Delete the notePersona by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NotePersona : {}", id);
        notePersonaRepository.deleteById(id);
        notePersonaSearchRepository.deleteById(id);
    }

    /**
     * Search for the notePersona corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NotePersonaDTO> search(String query) {
        log.debug("Request to search NotePersonas for query {}", query);
        return StreamSupport
            .stream(notePersonaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(notePersonaMapper::toDto)
        .collect(Collectors.toList());
    }
}
