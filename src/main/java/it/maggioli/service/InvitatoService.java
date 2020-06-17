package it.maggioli.service;

import it.maggioli.domain.Invitato;
import it.maggioli.repository.InvitatoRepository;
import it.maggioli.repository.search.InvitatoSearchRepository;
import it.maggioli.service.dto.InvitatoDTO;
import it.maggioli.service.mapper.InvitatoMapper;
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
 * Service Implementation for managing {@link Invitato}.
 */
@Service
@Transactional
public class InvitatoService {

    private final Logger log = LoggerFactory.getLogger(InvitatoService.class);

    private final InvitatoRepository invitatoRepository;

    private final InvitatoMapper invitatoMapper;

    private final InvitatoSearchRepository invitatoSearchRepository;

    public InvitatoService(InvitatoRepository invitatoRepository, InvitatoMapper invitatoMapper, InvitatoSearchRepository invitatoSearchRepository) {
        this.invitatoRepository = invitatoRepository;
        this.invitatoMapper = invitatoMapper;
        this.invitatoSearchRepository = invitatoSearchRepository;
    }

    /**
     * Save a invitato.
     *
     * @param invitatoDTO the entity to save.
     * @return the persisted entity.
     */
    public InvitatoDTO save(InvitatoDTO invitatoDTO) {
        log.debug("Request to save Invitato : {}", invitatoDTO);
        Invitato invitato = invitatoMapper.toEntity(invitatoDTO);
        invitato = invitatoRepository.save(invitato);
        InvitatoDTO result = invitatoMapper.toDto(invitato);
        invitatoSearchRepository.save(invitato);
        return result;
    }

    /**
     * Get all the invitatoes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InvitatoDTO> findAll() {
        log.debug("Request to get all Invitatoes");
        return invitatoRepository.findAll().stream()
            .map(invitatoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one invitato by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InvitatoDTO> findOne(Long id) {
        log.debug("Request to get Invitato : {}", id);
        return invitatoRepository.findById(id)
            .map(invitatoMapper::toDto);
    }

    /**
     * Delete the invitato by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Invitato : {}", id);
        invitatoRepository.deleteById(id);
        invitatoSearchRepository.deleteById(id);
    }

    /**
     * Search for the invitato corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InvitatoDTO> search(String query) {
        log.debug("Request to search Invitatoes for query {}", query);
        return StreamSupport
            .stream(invitatoSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(invitatoMapper::toDto)
        .collect(Collectors.toList());
    }
}
