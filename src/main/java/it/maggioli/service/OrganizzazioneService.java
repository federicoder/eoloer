package it.maggioli.service;

import it.maggioli.domain.Organizzazione;
import it.maggioli.repository.OrganizzazioneRepository;
import it.maggioli.repository.search.OrganizzazioneSearchRepository;
import it.maggioli.service.dto.OrganizzazioneDTO;
import it.maggioli.service.mapper.OrganizzazioneMapper;
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
 * Service Implementation for managing {@link Organizzazione}.
 */
@Service
@Transactional
public class OrganizzazioneService {

    private final Logger log = LoggerFactory.getLogger(OrganizzazioneService.class);

    private final OrganizzazioneRepository organizzazioneRepository;

    private final OrganizzazioneMapper organizzazioneMapper;

    private final OrganizzazioneSearchRepository organizzazioneSearchRepository;

    public OrganizzazioneService(OrganizzazioneRepository organizzazioneRepository, OrganizzazioneMapper organizzazioneMapper, OrganizzazioneSearchRepository organizzazioneSearchRepository) {
        this.organizzazioneRepository = organizzazioneRepository;
        this.organizzazioneMapper = organizzazioneMapper;
        this.organizzazioneSearchRepository = organizzazioneSearchRepository;
    }

    /**
     * Save a organizzazione.
     *
     * @param organizzazioneDTO the entity to save.
     * @return the persisted entity.
     */
    public OrganizzazioneDTO save(OrganizzazioneDTO organizzazioneDTO) {
        log.debug("Request to save Organizzazione : {}", organizzazioneDTO);
        Organizzazione organizzazione = organizzazioneMapper.toEntity(organizzazioneDTO);
        organizzazione = organizzazioneRepository.save(organizzazione);
        OrganizzazioneDTO result = organizzazioneMapper.toDto(organizzazione);
        organizzazioneSearchRepository.save(organizzazione);
        return result;
    }

    /**
     * Get all the organizzaziones.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OrganizzazioneDTO> findAll() {
        log.debug("Request to get all Organizzaziones");
        return organizzazioneRepository.findAll().stream()
            .map(organizzazioneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the organizzaziones where IdOrganizzazione is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<OrganizzazioneDTO> findAllWhereIdOrganizzazioneIsNull() {
        log.debug("Request to get all organizzaziones where IdOrganizzazione is null");
        return StreamSupport
            .stream(organizzazioneRepository.findAll().spliterator(), false)
            .filter(organizzazione -> organizzazione.getIdOrganizzazione() == null)
            .map(organizzazioneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one organizzazione by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrganizzazioneDTO> findOne(Long id) {
        log.debug("Request to get Organizzazione : {}", id);
        return organizzazioneRepository.findById(id)
            .map(organizzazioneMapper::toDto);
    }

    /**
     * Delete the organizzazione by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Organizzazione : {}", id);
        organizzazioneRepository.deleteById(id);
        organizzazioneSearchRepository.deleteById(id);
    }

    /**
     * Search for the organizzazione corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OrganizzazioneDTO> search(String query) {
        log.debug("Request to search Organizzaziones for query {}", query);
        return StreamSupport
            .stream(organizzazioneSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(organizzazioneMapper::toDto)
        .collect(Collectors.toList());
    }
}
