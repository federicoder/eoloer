package it.maggioli.service;

import it.maggioli.domain.StudioProfessionale;
import it.maggioli.repository.StudioProfessionaleRepository;
import it.maggioli.repository.search.StudioProfessionaleSearchRepository;
import it.maggioli.service.dto.StudioProfessionaleDTO;
import it.maggioli.service.mapper.StudioProfessionaleMapper;
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
 * Service Implementation for managing {@link StudioProfessionale}.
 */
@Service
@Transactional
public class StudioProfessionaleService {

    private final Logger log = LoggerFactory.getLogger(StudioProfessionaleService.class);

    private final StudioProfessionaleRepository studioProfessionaleRepository;

    private final StudioProfessionaleMapper studioProfessionaleMapper;

    private final StudioProfessionaleSearchRepository studioProfessionaleSearchRepository;

    public StudioProfessionaleService(StudioProfessionaleRepository studioProfessionaleRepository, StudioProfessionaleMapper studioProfessionaleMapper, StudioProfessionaleSearchRepository studioProfessionaleSearchRepository) {
        this.studioProfessionaleRepository = studioProfessionaleRepository;
        this.studioProfessionaleMapper = studioProfessionaleMapper;
        this.studioProfessionaleSearchRepository = studioProfessionaleSearchRepository;
    }

    /**
     * Save a studioProfessionale.
     *
     * @param studioProfessionaleDTO the entity to save.
     * @return the persisted entity.
     */
    public StudioProfessionaleDTO save(StudioProfessionaleDTO studioProfessionaleDTO) {
        log.debug("Request to save StudioProfessionale : {}", studioProfessionaleDTO);
        StudioProfessionale studioProfessionale = studioProfessionaleMapper.toEntity(studioProfessionaleDTO);
        studioProfessionale = studioProfessionaleRepository.save(studioProfessionale);
        StudioProfessionaleDTO result = studioProfessionaleMapper.toDto(studioProfessionale);
        studioProfessionaleSearchRepository.save(studioProfessionale);
        return result;
    }

    /**
     * Get all the studioProfessionales.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<StudioProfessionaleDTO> findAll() {
        log.debug("Request to get all StudioProfessionales");
        return studioProfessionaleRepository.findAll().stream()
            .map(studioProfessionaleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the studioProfessionales where IdStudioProfessionale is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<StudioProfessionaleDTO> findAllWhereIdStudioProfessionaleIsNull() {
        log.debug("Request to get all studioProfessionales where IdStudioProfessionale is null");
        return StreamSupport
            .stream(studioProfessionaleRepository.findAll().spliterator(), false)
            .filter(studioProfessionale -> studioProfessionale.getIdStudioProfessionale() == null)
            .map(studioProfessionaleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one studioProfessionale by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StudioProfessionaleDTO> findOne(Long id) {
        log.debug("Request to get StudioProfessionale : {}", id);
        return studioProfessionaleRepository.findById(id)
            .map(studioProfessionaleMapper::toDto);
    }

    /**
     * Delete the studioProfessionale by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete StudioProfessionale : {}", id);
        studioProfessionaleRepository.deleteById(id);
        studioProfessionaleSearchRepository.deleteById(id);
    }

    /**
     * Search for the studioProfessionale corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<StudioProfessionaleDTO> search(String query) {
        log.debug("Request to search StudioProfessionales for query {}", query);
        return StreamSupport
            .stream(studioProfessionaleSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(studioProfessionaleMapper::toDto)
        .collect(Collectors.toList());
    }
}
