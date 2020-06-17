package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A TagPersona.
 */
@Entity
@Table(name = "tag_persona")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "tagpersona")
public class TagPersona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_persona_ref", nullable = false)
    private Long idPersonaRef;

    @Column(name = "tag")
    private Long tag;

    @ManyToOne
    @JsonIgnoreProperties(value = "tagPersonas", allowSetters = true)
    private Persona idPersonaRef;

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

    public TagPersona idPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
        return this;
    }

    public void setIdPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Long getTag() {
        return tag;
    }

    public TagPersona tag(Long tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(Long tag) {
        this.tag = tag;
    }

    public Persona getIdPersonaRef() {
        return idPersonaRef;
    }

    public TagPersona idPersonaRef(Persona persona) {
        this.idPersonaRef = persona;
        return this;
    }

    public void setIdPersonaRef(Persona persona) {
        this.idPersonaRef = persona;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagPersona)) {
            return false;
        }
        return id != null && id.equals(((TagPersona) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagPersona{" +
            "id=" + getId() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", tag=" + getTag() +
            "}";
    }
}
