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
    @Column(name = "id_dati_contabili", nullable = false)
    private Integer idDatiContabili;

    @NotNull
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private Persona persona;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdDatiContabili() {
        return idDatiContabili;
    }

    public DatiContabili idDatiContabili(Integer idDatiContabili) {
        this.idDatiContabili = idDatiContabili;
        return this;
    }

    public void setIdDatiContabili(Integer idDatiContabili) {
        this.idDatiContabili = idDatiContabili;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public DatiContabili idPersona(Integer idPersona) {
        this.idPersona = idPersona;
        return this;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Persona getPersona() {
        return persona;
    }

    public DatiContabili persona(Persona persona) {
        this.persona = persona;
        return this;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
            ", idDatiContabili=" + getIdDatiContabili() +
            ", idPersona=" + getIdPersona() +
            "}";
    }
}
