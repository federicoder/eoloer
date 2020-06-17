package it.maggioli.service;

import it.maggioli.domain.Prodotto;
import it.maggioli.repository.ProdottoRepository;
import it.maggioli.repository.search.ProdottoSearchRepository;
import it.maggioli.service.dto.ProdottoDTO;
import it.maggioli.service.mapper.ProdottoMapper;
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
 * Service Implementation for managing {@link Prodotto}.
 */
@Service
@Transactional
public class ProdottoService {

    private final Logger log = LoggerFactory.getLogger(ProdottoService.class);

    private final ProdottoRepository prodottoRepository;

    private final ProdottoMapper prodottoMapper;

    private final ProdottoSearchRepository prodottoSearchRepository;

    public ProdottoService(ProdottoRepository prodottoRepository, ProdottoMapper prodottoMapper, ProdottoSearchRepository prodottoSearchRepository) {
        this.prodottoRepository = prodottoRepository;
        this.prodottoMapper = prodottoMapper;
        this.prodottoSearchRepository = prodottoSearchRepository;
    }

    /**
     * Save a prodotto.
     *
     * @param prodottoDTO the entity to save.
     * @return the persisted entity.
     */
    public ProdottoDTO save(ProdottoDTO prodottoDTO) {
        log.debug("Request to save Prodotto : {}", prodottoDTO);
        Prodotto prodotto = prodottoMapper.toEntity(prodottoDTO);
        prodotto = prodottoRepository.save(prodotto);
        ProdottoDTO result = prodottoMapper.toDto(prodotto);
        prodottoSearchRepository.save(prodotto);
        return result;
    }

    /**
     * Get all the prodottos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProdottoDTO> findAll() {
        log.debug("Request to get all Prodottos");
        return prodottoRepository.findAll().stream()
            .map(prodottoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one prodotto by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProdottoDTO> findOne(Long id) {
        log.debug("Request to get Prodotto : {}", id);
        return prodottoRepository.findById(id)
            .map(prodottoMapper::toDto);
    }

    /**
     * Delete the prodotto by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Prodotto : {}", id);
        prodottoRepository.deleteById(id);
        prodottoSearchRepository.deleteById(id);
    }

    /**
     * Search for the prodotto corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProdottoDTO> search(String query) {
        log.debug("Request to search Prodottos for query {}", query);
        return StreamSupport
            .stream(prodottoSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(prodottoMapper::toDto)
        .collect(Collectors.toList());
    }
}
