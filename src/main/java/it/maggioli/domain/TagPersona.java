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
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Column(name = "tag")
    private Integer tag;

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

    public Integer getIdPersona() {
        return idPersona;
    }

    public TagPersona idPersona(Integer idPersona) {
        this.idPersona = idPersona;
        return this;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getTag() {
        return tag;
    }

    public TagPersona tag(Integer tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Persona getPersona() {
        return persona;
    }

    public TagPersona persona(Persona persona) {
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
            ", idPersona=" + getIdPersona() +
            ", tag=" + getTag() +
            "}";
    }
}
