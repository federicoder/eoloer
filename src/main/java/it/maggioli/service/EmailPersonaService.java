package it.maggioli.service;

import it.maggioli.domain.EmailPersona;
import it.maggioli.repository.EmailPersonaRepository;
import it.maggioli.repository.search.EmailPersonaSearchRepository;
import it.maggioli.service.dto.EmailPersonaDTO;
import it.maggioli.service.mapper.EmailPersonaMapper;
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
 * Service Implementation for managing {@link EmailPersona}.
 */
@Service
@Transactional
public class EmailPersonaService {

    private final Logger log = LoggerFactory.getLogger(EmailPersonaService.class);

    private final EmailPersonaRepository emailPersonaRepository;

    private final EmailPersonaMapper emailPersonaMapper;

    private final EmailPersonaSearchRepository emailPersonaSearchRepository;

    public EmailPersonaService(EmailPersonaRepository emailPersonaRepository, EmailPersonaMapper emailPersonaMapper, EmailPersonaSearchRepository emailPersonaSearchRepository) {
        this.emailPersonaRepository = emailPersonaRepository;
        this.emailPersonaMapper = emailPersonaMapper;
        this.emailPersonaSearchRepository = emailPersonaSearchRepository;
    }

    /**
     * Save a emailPersona.
     *
     * @param emailPersonaDTO the entity to save.
     * @return the persisted entity.
     */
    public EmailPersonaDTO save(EmailPersonaDTO emailPersonaDTO) {
        log.debug("Request to save EmailPersona : {}", emailPersonaDTO);
        EmailPersona emailPersona = emailPersonaMapper.toEntity(emailPersonaDTO);
        emailPersona = emailPersonaRepository.save(emailPersona);
        EmailPersonaDTO result = emailPersonaMapper.toDto(emailPersona);
        emailPersonaSearchRepository.save(emailPersona);
        return result;
    }

    /**
     * Get all the emailPersonas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EmailPersonaDTO> findAll() {
        log.debug("Request to get all EmailPersonas");
        return emailPersonaRepository.findAll().stream()
            .map(emailPersonaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one emailPersona by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmailPersonaDTO> findOne(Long id) {
        log.debug("Request to get EmailPersona : {}", id);
        return emailPersonaRepository.findById(id)
            .map(emailPersonaMapper::toDto);
    }

    /**
     * Delete the emailPersona by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EmailPersona : {}", id);
        emailPersonaRepository.deleteById(id);
        emailPersonaSearchRepository.deleteById(id);
    }

    /**
     * Search for the emailPersona corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EmailPersonaDTO> search(String query) {
        log.debug("Request to search EmailPersonas for query {}", query);
        return StreamSupport
            .stream(emailPersonaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(emailPersonaMapper::toDto)
        .collect(Collectors.toList());
    }
}
