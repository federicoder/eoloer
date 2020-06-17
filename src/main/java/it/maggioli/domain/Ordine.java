package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Ordine.
 */
@Entity
@Table(name = "ordine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "ordine")
public class Ordine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_studio_professionale_ref", nullable = false)
    private Long idStudioProfessionaleRef;

    @Column(name = "stato_ordine")
    private Long statoOrdine;

    @Column(name = "tot_imponibile")
    private Long totImponibile;

    @Column(name = "tot_iva")
    private Long totIva;

    @Column(name = "tot_ordine")
    private Long totOrdine;

    @ManyToOne
    @JsonIgnoreProperties(value = "ordines", allowSetters = true)
    private StudioProfessionale idStudioProfessionaleRef;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdStudioProfessionaleRef() {
        return idStudioProfessionaleRef;
    }

    public Ordine idStudioProfessionaleRef(Long idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
        return this;
    }

    public void setIdStudioProfessionaleRef(Long idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
    }

    public Long getStatoOrdine() {
        return statoOrdine;
    }

    public Ordine statoOrdine(Long statoOrdine) {
        this.statoOrdine = statoOrdine;
        return this;
    }

    public void setStatoOrdine(Long statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public Long getTotImponibile() {
        return totImponibile;
    }

    public Ordine totImponibile(Long totImponibile) {
        this.totImponibile = totImponibile;
        return this;
    }

    public void setTotImponibile(Long totImponibile) {
        this.totImponibile = totImponibile;
    }

    public Long getTotIva() {
        return totIva;
    }

    public Ordine totIva(Long totIva) {
        this.totIva = totIva;
        return this;
    }

    public void setTotIva(Long totIva) {
        this.totIva = totIva;
    }

    public Long getTotOrdine() {
        return totOrdine;
    }

    public Ordine totOrdine(Long totOrdine) {
        this.totOrdine = totOrdine;
        return this;
    }

    public void setTotOrdine(Long totOrdine) {
        this.totOrdine = totOrdine;
    }

    public StudioProfessionale getIdStudioProfessionaleRef() {
        return idStudioProfessionaleRef;
    }

    public Ordine idStudioProfessionaleRef(StudioProfessionale studioProfessionale) {
        this.idStudioProfessionaleRef = studioProfessionale;
        return this;
    }

    public void setIdStudioProfessionaleRef(StudioProfessionale studioProfessionale) {
        this.idStudioProfessionaleRef = studioProfessionale;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ordine)) {
            return false;
        }
        return id != null && id.equals(((Ordine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ordine{" +
            "id=" + getId() +
            ", idStudioProfessionaleRef=" + getIdStudioProfessionaleRef() +
            ", statoOrdine=" + getStatoOrdine() +
            ", totImponibile=" + getTotImponibile() +
            ", totIva=" + getTotIva() +
            ", totOrdine=" + getTotOrdine() +
            "}";
    }
}
