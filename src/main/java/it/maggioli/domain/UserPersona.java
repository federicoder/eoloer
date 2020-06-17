package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A UserPersona.
 */
@Entity
@Table(name = "user_persona")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "userpersona")
public class UserPersona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_persona_ref")
    private Long idPersonaRef;

    @Column(name = "nome_user")
    private Long nomeUser;

    @ManyToOne
    @JsonIgnoreProperties(value = "userPersonas", allowSetters = true)
    private PersonaFisica idPersonaRef;

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

    public UserPersona idPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
        return this;
    }

    public void setIdPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Long getNomeUser() {
        return nomeUser;
    }

    public UserPersona nomeUser(Long nomeUser) {
        this.nomeUser = nomeUser;
        return this;
    }

    public void setNomeUser(Long nomeUser) {
        this.nomeUser = nomeUser;
    }

    public PersonaFisica getIdPersonaRef() {
        return idPersonaRef;
    }

    public UserPersona idPersonaRef(PersonaFisica personaFisica) {
        this.idPersonaRef = personaFisica;
        return this;
    }

    public void setIdPersonaRef(PersonaFisica personaFisica) {
        this.idPersonaRef = personaFisica;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserPersona)) {
            return false;
        }
        return id != null && id.equals(((UserPersona) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserPersona{" +
            "id=" + getId() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", nomeUser=" + getNomeUser() +
            "}";
    }
}
