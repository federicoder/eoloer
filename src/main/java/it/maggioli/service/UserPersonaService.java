package it.maggioli.service;

import it.maggioli.domain.UserPersona;
import it.maggioli.repository.UserPersonaRepository;
import it.maggioli.repository.search.UserPersonaSearchRepository;
import it.maggioli.service.dto.UserPersonaDTO;
import it.maggioli.service.mapper.UserPersonaMapper;
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
 * Service Implementation for managing {@link UserPersona}.
 */
@Service
@Transactional
public class UserPersonaService {

    private final Logger log = LoggerFactory.getLogger(UserPersonaService.class);

    private final UserPersonaRepository userPersonaRepository;

    private final UserPersonaMapper userPersonaMapper;

    private final UserPersonaSearchRepository userPersonaSearchRepository;

    public UserPersonaService(UserPersonaRepository userPersonaRepository, UserPersonaMapper userPersonaMapper, UserPersonaSearchRepository userPersonaSearchRepository) {
        this.userPersonaRepository = userPersonaRepository;
        this.userPersonaMapper = userPersonaMapper;
        this.userPersonaSearchRepository = userPersonaSearchRepository;
    }

    /**
     * Save a userPersona.
     *
     * @param userPersonaDTO the entity to save.
     * @return the persisted entity.
     */
    public UserPersonaDTO save(UserPersonaDTO userPersonaDTO) {
        log.debug("Request to save UserPersona : {}", userPersonaDTO);
        UserPersona userPersona = userPersonaMapper.toEntity(userPersonaDTO);
        userPersona = userPersonaRepository.save(userPersona);
        UserPersonaDTO result = userPersonaMapper.toDto(userPersona);
        userPersonaSearchRepository.save(userPersona);
        return result;
    }

    /**
     * Get all the userPersonas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserPersonaDTO> findAll() {
        log.debug("Request to get all UserPersonas");
        return userPersonaRepository.findAll().stream()
            .map(userPersonaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one userPersona by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserPersonaDTO> findOne(Long id) {
        log.debug("Request to get UserPersona : {}", id);
        return userPersonaRepository.findById(id)
            .map(userPersonaMapper::toDto);
    }

    /**
     * Delete the userPersona by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserPersona : {}", id);
        userPersonaRepository.deleteById(id);
        userPersonaSearchRepository.deleteById(id);
    }

    /**
     * Search for the userPersona corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserPersonaDTO> search(String query) {
        log.debug("Request to search UserPersonas for query {}", query);
        return StreamSupport
            .stream(userPersonaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(userPersonaMapper::toDto)
        .collect(Collectors.toList());
    }
}
