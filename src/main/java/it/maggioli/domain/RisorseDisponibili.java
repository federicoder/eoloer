package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A RisorseDisponibili.
 */
@Entity
@Table(name = "risorse_disponibili")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "risorsedisponibili")
public class RisorseDisponibili implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_studio_professionale_ref", nullable = false)
    private Long idStudioProfessionaleRef;

    @Column(name = "data_attivazione_licenza")
    private String dataAttivazioneLicenza;

    @Column(name = "nr_licenza")
    private Long nrLicenza;

    @Column(name = "storage_totale")
    private Long storageTotale;

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private StudioProfessionale studioProfessionale;

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

    public RisorseDisponibili idStudioProfessionaleRef(Long idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
        return this;
    }

    public void setIdStudioProfessionaleRef(Long idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
    }

    public String getDataAttivazioneLicenza() {
        return dataAttivazioneLicenza;
    }

    public RisorseDisponibili dataAttivazioneLicenza(String dataAttivazioneLicenza) {
        this.dataAttivazioneLicenza = dataAttivazioneLicenza;
        return this;
    }

    public void setDataAttivazioneLicenza(String dataAttivazioneLicenza) {
        this.dataAttivazioneLicenza = dataAttivazioneLicenza;
    }

    public Long getNrLicenza() {
        return nrLicenza;
    }

    public RisorseDisponibili nrLicenza(Long nrLicenza) {
        this.nrLicenza = nrLicenza;
        return this;
    }

    public void setNrLicenza(Long nrLicenza) {
        this.nrLicenza = nrLicenza;
    }

    public Long getStorageTotale() {
        return storageTotale;
    }

    public RisorseDisponibili storageTotale(Long storageTotale) {
        this.storageTotale = storageTotale;
        return this;
    }

    public void setStorageTotale(Long storageTotale) {
        this.storageTotale = storageTotale;
    }

    public StudioProfessionale getStudioProfessionale() {
        return studioProfessionale;
    }

    public RisorseDisponibili studioProfessionale(StudioProfessionale studioProfessionale) {
        this.studioProfessionale = studioProfessionale;
        return this;
    }

    public void setStudioProfessionale(StudioProfessionale studioProfessionale) {
        this.studioProfessionale = studioProfessionale;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RisorseDisponibili)) {
            return false;
        }
        return id != null && id.equals(((RisorseDisponibili) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RisorseDisponibili{" +
            "id=" + getId() +
            ", idStudioProfessionaleRef=" + getIdStudioProfessionaleRef() +
            ", dataAttivazioneLicenza='" + getDataAttivazioneLicenza() + "'" +
            ", nrLicenza=" + getNrLicenza() +
            ", storageTotale=" + getStorageTotale() +
            "}";
    }
}
