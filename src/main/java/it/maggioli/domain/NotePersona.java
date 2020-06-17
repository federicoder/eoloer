package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A NotePersona.
 */
@Entity
@Table(name = "note_persona")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "notepersona")
public class NotePersona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_persona_ref", nullable = false)
    private Integer idPersonaRef;

    @NotNull
    @Column(name = "id_note_persona", nullable = false)
    private Integer idNotePersona;

    @Column(name = "testo")
    private String testo;

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

    public NotePersona idPersonaRef(Integer idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
        return this;
    }

    public void setIdPersonaRef(Integer idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Integer getIdNotePersona() {
        return idNotePersona;
    }

    public NotePersona idNotePersona(Integer idNotePersona) {
        this.idNotePersona = idNotePersona;
        return this;
    }

    public void setIdNotePersona(Integer idNotePersona) {
        this.idNotePersona = idNotePersona;
    }

    public String getTesto() {
        return testo;
    }

    public NotePersona testo(String testo) {
        this.testo = testo;
        return this;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Persona getPersona() {
        return persona;
    }

    public NotePersona persona(Persona persona) {
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
        if (!(o instanceof NotePersona)) {
            return false;
        }
        return id != null && id.equals(((NotePersona) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotePersona{" +
            "id=" + getId() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", idNotePersona=" + getIdNotePersona() +
            ", testo='" + getTesto() + "'" +
            "}";
    }
}
