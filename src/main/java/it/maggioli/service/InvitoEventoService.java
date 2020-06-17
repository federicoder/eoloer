package it.maggioli.service;

import it.maggioli.domain.InvitoEvento;
import it.maggioli.repository.InvitoEventoRepository;
import it.maggioli.repository.search.InvitoEventoSearchRepository;
import it.maggioli.service.dto.InvitoEventoDTO;
import it.maggioli.service.mapper.InvitoEventoMapper;
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
 * Service Implementation for managing {@link InvitoEvento}.
 */
@Service
@Transactional
public class InvitoEventoService {

    private final Logger log = LoggerFactory.getLogger(InvitoEventoService.class);

    private final InvitoEventoRepository invitoEventoRepository;

    private final InvitoEventoMapper invitoEventoMapper;

    private final InvitoEventoSearchRepository invitoEventoSearchRepository;

    public InvitoEventoService(InvitoEventoRepository invitoEventoRepository, InvitoEventoMapper invitoEventoMapper, InvitoEventoSearchRepository invitoEventoSearchRepository) {
        this.invitoEventoRepository = invitoEventoRepository;
        this.invitoEventoMapper = invitoEventoMapper;
        this.invitoEventoSearchRepository = invitoEventoSearchRepository;
    }

    /**
     * Save a invitoEvento.
     *
     * @param invitoEventoDTO the entity to save.
     * @return the persisted entity.
     */
    public InvitoEventoDTO save(InvitoEventoDTO invitoEventoDTO) {
        log.debug("Request to save InvitoEvento : {}", invitoEventoDTO);
        InvitoEvento invitoEvento = invitoEventoMapper.toEntity(invitoEventoDTO);
        invitoEvento = invitoEventoRepository.save(invitoEvento);
        InvitoEventoDTO result = invitoEventoMapper.toDto(invitoEvento);
        invitoEventoSearchRepository.save(invitoEvento);
        return result;
    }

    /**
     * Get all the invitoEventos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InvitoEventoDTO> findAll() {
        log.debug("Request to get all InvitoEventos");
        return invitoEventoRepository.findAll().stream()
            .map(invitoEventoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one invitoEvento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InvitoEventoDTO> findOne(Long id) {
        log.debug("Request to get InvitoEvento : {}", id);
        return invitoEventoRepository.findById(id)
            .map(invitoEventoMapper::toDto);
    }

    /**
     * Delete the invitoEvento by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InvitoEvento : {}", id);
        invitoEventoRepository.deleteById(id);
        invitoEventoSearchRepository.deleteById(id);
    }

    /**
     * Search for the invitoEvento corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InvitoEventoDTO> search(String query) {
        log.debug("Request to search InvitoEventos for query {}", query);
        return StreamSupport
            .stream(invitoEventoSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(invitoEventoMapper::toDto)
        .collect(Collectors.toList());
    }
}
