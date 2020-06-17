package it.maggioli.service;

import it.maggioli.domain.InvitoPratica;
import it.maggioli.repository.InvitoPraticaRepository;
import it.maggioli.repository.search.InvitoPraticaSearchRepository;
import it.maggioli.service.dto.InvitoPraticaDTO;
import it.maggioli.service.mapper.InvitoPraticaMapper;
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
 * Service Implementation for managing {@link InvitoPratica}.
 */
@Service
@Transactional
public class InvitoPraticaService {

    private final Logger log = LoggerFactory.getLogger(InvitoPraticaService.class);

    private final InvitoPraticaRepository invitoPraticaRepository;

    private final InvitoPraticaMapper invitoPraticaMapper;

    private final InvitoPraticaSearchRepository invitoPraticaSearchRepository;

    public InvitoPraticaService(InvitoPraticaRepository invitoPraticaRepository, InvitoPraticaMapper invitoPraticaMapper, InvitoPraticaSearchRepository invitoPraticaSearchRepository) {
        this.invitoPraticaRepository = invitoPraticaRepository;
        this.invitoPraticaMapper = invitoPraticaMapper;
        this.invitoPraticaSearchRepository = invitoPraticaSearchRepository;
    }

    /**
     * Save a invitoPratica.
     *
     * @param invitoPraticaDTO the entity to save.
     * @return the persisted entity.
     */
    public InvitoPraticaDTO save(InvitoPraticaDTO invitoPraticaDTO) {
        log.debug("Request to save InvitoPratica : {}", invitoPraticaDTO);
        InvitoPratica invitoPratica = invitoPraticaMapper.toEntity(invitoPraticaDTO);
        invitoPratica = invitoPraticaRepository.save(invitoPratica);
        InvitoPraticaDTO result = invitoPraticaMapper.toDto(invitoPratica);
        invitoPraticaSearchRepository.save(invitoPratica);
        return result;
    }

    /**
     * Get all the invitoPraticas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InvitoPraticaDTO> findAll() {
        log.debug("Request to get all InvitoPraticas");
        return invitoPraticaRepository.findAll().stream()
            .map(invitoPraticaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one invitoPratica by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InvitoPraticaDTO> findOne(Long id) {
        log.debug("Request to get InvitoPratica : {}", id);
        return invitoPraticaRepository.findById(id)
            .map(invitoPraticaMapper::toDto);
    }

    /**
     * Delete the invitoPratica by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InvitoPratica : {}", id);
        invitoPraticaRepository.deleteById(id);
        invitoPraticaSearchRepository.deleteById(id);
    }

    /**
     * Search for the invitoPratica corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InvitoPraticaDTO> search(String query) {
        log.debug("Request to search InvitoPraticas for query {}", query);
        return StreamSupport
            .stream(invitoPraticaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(invitoPraticaMapper::toDto)
        .collect(Collectors.toList());
    }
}
