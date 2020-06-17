package it.maggioli.service;

import it.maggioli.domain.Persona;
import it.maggioli.repository.PersonaRepository;
import it.maggioli.repository.search.PersonaSearchRepository;
import it.maggioli.service.dto.PersonaDTO;
import it.maggioli.service.mapper.PersonaMapper;
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
 * Service Implementation for managing {@link Persona}.
 */
@Service
@Transactional
public class PersonaService {

    private final Logger log = LoggerFactory.getLogger(PersonaService.class);

    private final PersonaRepository personaRepository;

    private final PersonaMapper personaMapper;

    private final PersonaSearchRepository personaSearchRepository;

    public PersonaService(PersonaRepository personaRepository, PersonaMapper personaMapper, PersonaSearchRepository personaSearchRepository) {
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
        this.personaSearchRepository = personaSearchRepository;
    }

    /**
     * Save a persona.
     *
     * @param personaDTO the entity to save.
     * @return the persisted entity.
     */
    public PersonaDTO save(PersonaDTO personaDTO) {
        log.debug("Request to save Persona : {}", personaDTO);
        Persona persona = personaMapper.toEntity(personaDTO);
        persona = personaRepository.save(persona);
        PersonaDTO result = personaMapper.toDto(persona);
        personaSearchRepository.save(persona);
        return result;
    }

    /**
     * Get all the personas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PersonaDTO> findAll() {
        log.debug("Request to get all Personas");
        return personaRepository.findAll().stream()
            .map(personaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the personas where IdPersona is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PersonaDTO> findAllWhereIdPersonaIsNull() {
        log.debug("Request to get all personas where IdPersona is null");
        return StreamSupport
            .stream(personaRepository.findAll().spliterator(), false)
            .filter(persona -> persona.getIdPersona() == null)
            .map(personaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the personas where IdPersona is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PersonaDTO> findAllWhereIdPersonaIsNull() {
        log.debug("Request to get all personas where IdPersona is null");
        return StreamSupport
            .stream(personaRepository.findAll().spliterator(), false)
            .filter(persona -> persona.getIdPersona() == null)
            .map(personaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the personas where IdPersona is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PersonaDTO> findAllWhereIdPersonaIsNull() {
        log.debug("Request to get all personas where IdPersona is null");
        return StreamSupport
            .stream(personaRepository.findAll().spliterator(), false)
            .filter(persona -> persona.getIdPersona() == null)
            .map(personaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one persona by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PersonaDTO> findOne(Long id) {
        log.debug("Request to get Persona : {}", id);
        return personaRepository.findById(id)
            .map(personaMapper::toDto);
    }

    /**
     * Delete the persona by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Persona : {}", id);
        personaRepository.deleteById(id);
        personaSearchRepository.deleteById(id);
    }

    /**
     * Search for the persona corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PersonaDTO> search(String query) {
        log.debug("Request to search Personas for query {}", query);
        return StreamSupport
            .stream(personaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(personaMapper::toDto)
        .collect(Collectors.toList());
    }
}
