package it.maggioli.domain;

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
    @Column(name = "id_ordine_ref", nullable = false)
    private Long idOrdineRef;

    @NotNull
    @Column(name = "id_prodotto_ref", nullable = false)
    private Long idProdottoRef;

    @Column(name = "quantita")
    private Long quantita;

    @Column(name = "importo")
    private Long importo;

    @Column(name = "cod_iva")
    private String codIva;

    @OneToOne
    @JoinColumn(unique = true)
    private Prodotto idProdotto;

    @ManyToOne
    @JsonIgnoreProperties(value = "lineaOrdines", allowSetters = true)
    private Ordine idOrdine;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrdineRef() {
        return idOrdineRef;
    }

    public LineaOrdine idOrdineRef(Long idOrdineRef) {
        this.idOrdineRef = idOrdineRef;
        return this;
    }

    public void setIdOrdineRef(Long idOrdineRef) {
        this.idOrdineRef = idOrdineRef;
    }

    public Long getIdProdottoRef() {
        return idProdottoRef;
    }

    public LineaOrdine idProdottoRef(Long idProdottoRef) {
        this.idProdottoRef = idProdottoRef;
        return this;
    }

    public void setIdProdottoRef(Long idProdottoRef) {
        this.idProdottoRef = idProdottoRef;
    }

    public Long getQuantita() {
        return quantita;
    }

    public LineaOrdine quantita(Long quantita) {
        this.quantita = quantita;
        return this;
    }

    public void setQuantita(Long quantita) {
        this.quantita = quantita;
    }

    public Long getImporto() {
        return importo;
    }

    public LineaOrdine importo(Long importo) {
        this.importo = importo;
        return this;
    }

    public void setImporto(Long importo) {
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

    public Ordine getIdOrdine() {
        return idOrdine;
    }

    public LineaOrdine idOrdine(Ordine ordine) {
        this.idOrdine = ordine;
        return this;
    }

    public void setIdOrdine(Ordine ordine) {
        this.idOrdine = ordine;
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
            ", idOrdineRef=" + getIdOrdineRef() +
            ", idProdottoRef=" + getIdProdottoRef() +
            ", quantita=" + getQuantita() +
            ", importo=" + getImporto() +
            ", codIva='" + getCodIva() + "'" +
            "}";
    }
}
