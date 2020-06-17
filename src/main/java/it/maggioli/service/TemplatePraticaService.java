package it.maggioli.service;

import it.maggioli.domain.TemplatePratica;
import it.maggioli.repository.TemplatePraticaRepository;
import it.maggioli.repository.search.TemplatePraticaSearchRepository;
import it.maggioli.service.dto.TemplatePraticaDTO;
import it.maggioli.service.mapper.TemplatePraticaMapper;
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
 * Service Implementation for managing {@link TemplatePratica}.
 */
@Service
@Transactional
public class TemplatePraticaService {

    private final Logger log = LoggerFactory.getLogger(TemplatePraticaService.class);

    private final TemplatePraticaRepository templatePraticaRepository;

    private final TemplatePraticaMapper templatePraticaMapper;

    private final TemplatePraticaSearchRepository templatePraticaSearchRepository;

    public TemplatePraticaService(TemplatePraticaRepository templatePraticaRepository, TemplatePraticaMapper templatePraticaMapper, TemplatePraticaSearchRepository templatePraticaSearchRepository) {
        this.templatePraticaRepository = templatePraticaRepository;
        this.templatePraticaMapper = templatePraticaMapper;
        this.templatePraticaSearchRepository = templatePraticaSearchRepository;
    }

    /**
     * Save a templatePratica.
     *
     * @param templatePraticaDTO the entity to save.
     * @return the persisted entity.
     */
    public TemplatePraticaDTO save(TemplatePraticaDTO templatePraticaDTO) {
        log.debug("Request to save TemplatePratica : {}", templatePraticaDTO);
        TemplatePratica templatePratica = templatePraticaMapper.toEntity(templatePraticaDTO);
        templatePratica = templatePraticaRepository.save(templatePratica);
        TemplatePraticaDTO result = templatePraticaMapper.toDto(templatePratica);
        templatePraticaSearchRepository.save(templatePratica);
        return result;
    }

    /**
     * Get all the templatePraticas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TemplatePraticaDTO> findAll() {
        log.debug("Request to get all TemplatePraticas");
        return templatePraticaRepository.findAll().stream()
            .map(templatePraticaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one templatePratica by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TemplatePraticaDTO> findOne(Long id) {
        log.debug("Request to get TemplatePratica : {}", id);
        return templatePraticaRepository.findById(id)
            .map(templatePraticaMapper::toDto);
    }

    /**
     * Delete the templatePratica by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TemplatePratica : {}", id);
        templatePraticaRepository.deleteById(id);
        templatePraticaSearchRepository.deleteById(id);
    }

    /**
     * Search for the templatePratica corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TemplatePraticaDTO> search(String query) {
        log.debug("Request to search TemplatePraticas for query {}", query);
        return StreamSupport
            .stream(templatePraticaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(templatePraticaMapper::toDto)
        .collect(Collectors.toList());
    }
}
