package it.maggioli.service;

import it.maggioli.domain.TagPersona;
import it.maggioli.repository.TagPersonaRepository;
import it.maggioli.repository.search.TagPersonaSearchRepository;
import it.maggioli.service.dto.TagPersonaDTO;
import it.maggioli.service.mapper.TagPersonaMapper;
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
 * Service Implementation for managing {@link TagPersona}.
 */
@Service
@Transactional
public class TagPersonaService {

    private final Logger log = LoggerFactory.getLogger(TagPersonaService.class);

    private final TagPersonaRepository tagPersonaRepository;

    private final TagPersonaMapper tagPersonaMapper;

    private final TagPersonaSearchRepository tagPersonaSearchRepository;

    public TagPersonaService(TagPersonaRepository tagPersonaRepository, TagPersonaMapper tagPersonaMapper, TagPersonaSearchRepository tagPersonaSearchRepository) {
        this.tagPersonaRepository = tagPersonaRepository;
        this.tagPersonaMapper = tagPersonaMapper;
        this.tagPersonaSearchRepository = tagPersonaSearchRepository;
    }

    /**
     * Save a tagPersona.
     *
     * @param tagPersonaDTO the entity to save.
     * @return the persisted entity.
     */
    public TagPersonaDTO save(TagPersonaDTO tagPersonaDTO) {
        log.debug("Request to save TagPersona : {}", tagPersonaDTO);
        TagPersona tagPersona = tagPersonaMapper.toEntity(tagPersonaDTO);
        tagPersona = tagPersonaRepository.save(tagPersona);
        TagPersonaDTO result = tagPersonaMapper.toDto(tagPersona);
        tagPersonaSearchRepository.save(tagPersona);
        return result;
    }

    /**
     * Get all the tagPersonas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagPersonaDTO> findAll() {
        log.debug("Request to get all TagPersonas");
        return tagPersonaRepository.findAll().stream()
            .map(tagPersonaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one tagPersona by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagPersonaDTO> findOne(Long id) {
        log.debug("Request to get TagPersona : {}", id);
        return tagPersonaRepository.findById(id)
            .map(tagPersonaMapper::toDto);
    }

    /**
     * Delete the tagPersona by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagPersona : {}", id);
        tagPersonaRepository.deleteById(id);
        tagPersonaSearchRepository.deleteById(id);
    }

    /**
     * Search for the tagPersona corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagPersonaDTO> search(String query) {
        log.debug("Request to search TagPersonas for query {}", query);
        return StreamSupport
            .stream(tagPersonaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(tagPersonaMapper::toDto)
        .collect(Collectors.toList());
    }
}
