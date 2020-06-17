package it.maggioli.service;

import it.maggioli.domain.NotaPratica;
import it.maggioli.repository.NotaPraticaRepository;
import it.maggioli.repository.search.NotaPraticaSearchRepository;
import it.maggioli.service.dto.NotaPraticaDTO;
import it.maggioli.service.mapper.NotaPraticaMapper;
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
 * Service Implementation for managing {@link NotaPratica}.
 */
@Service
@Transactional
public class NotaPraticaService {

    private final Logger log = LoggerFactory.getLogger(NotaPraticaService.class);

    private final NotaPraticaRepository notaPraticaRepository;

    private final NotaPraticaMapper notaPraticaMapper;

    private final NotaPraticaSearchRepository notaPraticaSearchRepository;

    public NotaPraticaService(NotaPraticaRepository notaPraticaRepository, NotaPraticaMapper notaPraticaMapper, NotaPraticaSearchRepository notaPraticaSearchRepository) {
        this.notaPraticaRepository = notaPraticaRepository;
        this.notaPraticaMapper = notaPraticaMapper;
        this.notaPraticaSearchRepository = notaPraticaSearchRepository;
    }

    /**
     * Save a notaPratica.
     *
     * @param notaPraticaDTO the entity to save.
     * @return the persisted entity.
     */
    public NotaPraticaDTO save(NotaPraticaDTO notaPraticaDTO) {
        log.debug("Request to save NotaPratica : {}", notaPraticaDTO);
        NotaPratica notaPratica = notaPraticaMapper.toEntity(notaPraticaDTO);
        notaPratica = notaPraticaRepository.save(notaPratica);
        NotaPraticaDTO result = notaPraticaMapper.toDto(notaPratica);
        notaPraticaSearchRepository.save(notaPratica);
        return result;
    }

    /**
     * Get all the notaPraticas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NotaPraticaDTO> findAll() {
        log.debug("Request to get all NotaPraticas");
        return notaPraticaRepository.findAll().stream()
            .map(notaPraticaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one notaPratica by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NotaPraticaDTO> findOne(Long id) {
        log.debug("Request to get NotaPratica : {}", id);
        return notaPraticaRepository.findById(id)
            .map(notaPraticaMapper::toDto);
    }

    /**
     * Delete the notaPratica by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NotaPratica : {}", id);
        notaPraticaRepository.deleteById(id);
        notaPraticaSearchRepository.deleteById(id);
    }

    /**
     * Search for the notaPratica corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NotaPraticaDTO> search(String query) {
        log.debug("Request to search NotaPraticas for query {}", query);
        return StreamSupport
            .stream(notaPraticaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(notaPraticaMapper::toDto)
        .collect(Collectors.toList());
    }
}
