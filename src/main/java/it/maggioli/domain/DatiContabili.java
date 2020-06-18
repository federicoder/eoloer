package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A DatiContabili.
 */
@Entity
@Table(name = "dati_contabili")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "daticontabili")
public class DatiContabili implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_persona_ref", nullable = false)
    private Long idPersonaRef;

    @ManyToOne
    @JsonIgnoreProperties(value = "datiContabilis", allowSetters = true)
    private Persona idPersona;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPersonaRef() {
        return idPersonaRef;
    }

    public DatiContabili idPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
        return this;
    }

    public void setIdPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public DatiContabili idPersona(Persona persona) {
        this.idPersona = persona;
        return this;
    }

    public void setIdPersona(Persona persona) {
        this.idPersona = persona;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DatiContabili)) {
            return false;
        }
        return id != null && id.equals(((DatiContabili) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DatiContabili{" +
            "id=" + getId() +
            ", idPersonaRef=" + getIdPersonaRef() +
            "}";
    }
}
