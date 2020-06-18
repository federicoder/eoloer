package it.maggioli.service;

import it.maggioli.domain.PersonaFisica;
import it.maggioli.repository.PersonaFisicaRepository;
import it.maggioli.repository.search.PersonaFisicaSearchRepository;
import it.maggioli.service.dto.PersonaFisicaDTO;
import it.maggioli.service.mapper.PersonaFisicaMapper;
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
 * Service Implementation for managing {@link PersonaFisica}.
 */
@Service
@Transactional
public class PersonaFisicaService {

    private final Logger log = LoggerFactory.getLogger(PersonaFisicaService.class);

    private final PersonaFisicaRepository personaFisicaRepository;

    private final PersonaFisicaMapper personaFisicaMapper;

    private final PersonaFisicaSearchRepository personaFisicaSearchRepository;

    public PersonaFisicaService(PersonaFisicaRepository personaFisicaRepository, PersonaFisicaMapper personaFisicaMapper, PersonaFisicaSearchRepository personaFisicaSearchRepository) {
        this.personaFisicaRepository = personaFisicaRepository;
        this.personaFisicaMapper = personaFisicaMapper;
        this.personaFisicaSearchRepository = personaFisicaSearchRepository;
    }

    /**
     * Save a personaFisica.
     *
     * @param personaFisicaDTO the entity to save.
     * @return the persisted entity.
     */
    public PersonaFisicaDTO save(PersonaFisicaDTO personaFisicaDTO) {
        log.debug("Request to save PersonaFisica : {}", personaFisicaDTO);
        PersonaFisica personaFisica = personaFisicaMapper.toEntity(personaFisicaDTO);
        personaFisica = personaFisicaRepository.save(personaFisica);
        PersonaFisicaDTO result = personaFisicaMapper.toDto(personaFisica);
        personaFisicaSearchRepository.save(personaFisica);
        return result;
    }

    /**
     * Get all the personaFisicas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PersonaFisicaDTO> findAll() {
        log.debug("Request to get all PersonaFisicas");
        return personaFisicaRepository.findAll().stream()
            .map(personaFisicaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one personaFisica by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PersonaFisicaDTO> findOne(Long id) {
        log.debug("Request to get PersonaFisica : {}", id);
        return personaFisicaRepository.findById(id)
            .map(personaFisicaMapper::toDto);
    }

    /**
     * Delete the personaFisica by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PersonaFisica : {}", id);
        personaFisicaRepository.deleteById(id);
        personaFisicaSearchRepository.deleteById(id);
    }

    /**
     * Search for the personaFisica corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PersonaFisicaDTO> search(String query) {
        log.debug("Request to search PersonaFisicas for query {}", query);
        return StreamSupport
            .stream(personaFisicaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(personaFisicaMapper::toDto)
        .collect(Collectors.toList());
    }
}
