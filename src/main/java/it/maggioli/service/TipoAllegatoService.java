package it.maggioli.service;

import it.maggioli.domain.TipoAllegato;
import it.maggioli.repository.TipoAllegatoRepository;
import it.maggioli.repository.search.TipoAllegatoSearchRepository;
import it.maggioli.service.dto.TipoAllegatoDTO;
import it.maggioli.service.mapper.TipoAllegatoMapper;
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
 * Service Implementation for managing {@link TipoAllegato}.
 */
@Service
@Transactional
public class TipoAllegatoService {

    private final Logger log = LoggerFactory.getLogger(TipoAllegatoService.class);

    private final TipoAllegatoRepository tipoAllegatoRepository;

    private final TipoAllegatoMapper tipoAllegatoMapper;

    private final TipoAllegatoSearchRepository tipoAllegatoSearchRepository;

    public TipoAllegatoService(TipoAllegatoRepository tipoAllegatoRepository, TipoAllegatoMapper tipoAllegatoMapper, TipoAllegatoSearchRepository tipoAllegatoSearchRepository) {
        this.tipoAllegatoRepository = tipoAllegatoRepository;
        this.tipoAllegatoMapper = tipoAllegatoMapper;
        this.tipoAllegatoSearchRepository = tipoAllegatoSearchRepository;
    }

    /**
     * Save a tipoAllegato.
     *
     * @param tipoAllegatoDTO the entity to save.
     * @return the persisted entity.
     */
    public TipoAllegatoDTO save(TipoAllegatoDTO tipoAllegatoDTO) {
        log.debug("Request to save TipoAllegato : {}", tipoAllegatoDTO);
        TipoAllegato tipoAllegato = tipoAllegatoMapper.toEntity(tipoAllegatoDTO);
        tipoAllegato = tipoAllegatoRepository.save(tipoAllegato);
        TipoAllegatoDTO result = tipoAllegatoMapper.toDto(tipoAllegato);
        tipoAllegatoSearchRepository.save(tipoAllegato);
        return result;
    }

    /**
     * Get all the tipoAllegatoes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TipoAllegatoDTO> findAll() {
        log.debug("Request to get all TipoAllegatoes");
        return tipoAllegatoRepository.findAll().stream()
            .map(tipoAllegatoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one tipoAllegato by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TipoAllegatoDTO> findOne(Long id) {
        log.debug("Request to get TipoAllegato : {}", id);
        return tipoAllegatoRepository.findById(id)
            .map(tipoAllegatoMapper::toDto);
    }

    /**
     * Delete the tipoAllegato by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TipoAllegato : {}", id);
        tipoAllegatoRepository.deleteById(id);
        tipoAllegatoSearchRepository.deleteById(id);
    }

    /**
     * Search for the tipoAllegato corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TipoAllegatoDTO> search(String query) {
        log.debug("Request to search TipoAllegatoes for query {}", query);
        return StreamSupport
            .stream(tipoAllegatoSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(tipoAllegatoMapper::toDto)
        .collect(Collectors.toList());
    }
}
