package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A TelefonoPersona.
 */
@Entity
@Table(name = "telefono_persona")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "telefonopersona")
public class TelefonoPersona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_persona_ref", nullable = false)
    private Integer idPersonaRef;

    @Column(name = "etichetta")
    private Integer etichetta;

    @Column(name = "valore")
    private Integer valore;

    @ManyToOne
    @JsonIgnoreProperties(value = "idPersonas", allowSetters = true)
    private Persona persona;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPersonaRef() {
        return idPersonaRef;
    }

    public TelefonoPersona idPersonaRef(Integer idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
        return this;
    }

    public void setIdPersonaRef(Integer idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Integer getEtichetta() {
        return etichetta;
    }

    public TelefonoPersona etichetta(Integer etichetta) {
        this.etichetta = etichetta;
        return this;
    }

    public void setEtichetta(Integer etichetta) {
        this.etichetta = etichetta;
    }

    public Integer getValore() {
        return valore;
    }

    public TelefonoPersona valore(Integer valore) {
        this.valore = valore;
        return this;
    }

    public void setValore(Integer valore) {
        this.valore = valore;
    }

    public Persona getPersona() {
        return persona;
    }

    public TelefonoPersona persona(Persona persona) {
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
        if (!(o instanceof TelefonoPersona)) {
            return false;
        }
        return id != null && id.equals(((TelefonoPersona) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TelefonoPersona{" +
            "id=" + getId() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", etichetta=" + getEtichetta() +
            ", valore=" + getValore() +
            "}";
    }
}
