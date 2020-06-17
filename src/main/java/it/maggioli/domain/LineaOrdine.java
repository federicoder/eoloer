package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A LineaOrdine.
 */
@Entity
@Table(name = "linea_ordine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "lineaordine")
public class LineaOrdine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_ordine", nullable = false)
    private Integer idOrdine;

    @NotNull
    @Column(name = "id_prodotto", nullable = false)
    private Integer idProdotto;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "importo")
    private Integer importo;

    @Column(name = "cod_iva")
    private String codIva;

    @OneToOne(mappedBy = "id")
    @JsonIgnore
    private Prodotto idProdotto;

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private Ordine ordine;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdOrdine() {
        return idOrdine;
    }

    public LineaOrdine idOrdine(Integer idOrdine) {
        this.idOrdine = idOrdine;
        return this;
    }

    public void setIdOrdine(Integer idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Integer getIdProdotto() {
        return idProdotto;
    }

    public LineaOrdine idProdotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
        return this;
    }

    public void setIdProdotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public LineaOrdine quantita(Integer quantita) {
        this.quantita = quantita;
        return this;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Integer getImporto() {
        return importo;
    }

    public LineaOrdine importo(Integer importo) {
        this.importo = importo;
        return this;
    }

    public void setImporto(Integer importo) {
        this.importo = importo;
    }

    public String getCodIva() {
        return codIva;
    }

    public LineaOrdine codIva(String codIva) {
        this.codIva = codIva;
        return this;
    }

    public void setCodIva(String codIva) {
        this.codIva = codIva;
    }

    public Prodotto getIdProdotto() {
        return idProdotto;
    }

    public LineaOrdine idProdotto(Prodotto prodotto) {
        this.idProdotto = prodotto;
        return this;
    }

    public void setIdProdotto(Prodotto prodotto) {
        this.idProdotto = prodotto;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public LineaOrdine ordine(Ordine ordine) {
        this.ordine = ordine;
        return this;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LineaOrdine)) {
            return false;
        }
        return id != null && id.equals(((LineaOrdine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LineaOrdine{" +
            "id=" + getId() +
            ", idOrdine=" + getIdOrdine() +
            ", idProdotto=" + getIdProdotto() +
            ", quantita=" + getQuantita() +
            ", importo=" + getImporto() +
            ", codIva='" + getCodIva() + "'" +
            "}";
    }
}
