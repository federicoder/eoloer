package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @NotNull
    @Column(name = "id_user_persona", nullable = false)
    private Integer idUserPersona;

    @Column(name = "id_persona_ref")
    private Integer idPersonaRef;

    @Column(name = "nome_user")
    private Integer nomeUser;

    @OneToMany(mappedBy = "userPersona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CondivisionePratica> idUserPersonas = new HashSet<>();

    @OneToMany(mappedBy = "userPersona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AssegnazioneTask> idUserPersonas = new HashSet<>();

    @OneToMany(mappedBy = "userPersona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Invitato> idUserPersonas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "idPersonaFisicas", allowSetters = true)
    private PersonaFisica personaFisica;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUserPersona() {
        return idUserPersona;
    }

    public UserPersona idUserPersona(Integer idUserPersona) {
        this.idUserPersona = idUserPersona;
        return this;
    }

    public void setIdUserPersona(Integer idUserPersona) {
        this.idUserPersona = idUserPersona;
    }

    public Integer getIdPersonaRef() {
        return idPersonaRef;
    }

    public UserPersona idPersonaRef(Integer idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
        return this;
    }

    public void setIdPersonaRef(Integer idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Integer getNomeUser() {
        return nomeUser;
    }

    public UserPersona nomeUser(Integer nomeUser) {
        this.nomeUser = nomeUser;
        return this;
    }

    public void setNomeUser(Integer nomeUser) {
        this.nomeUser = nomeUser;
    }

    public Set<CondivisionePratica> getIdUserPersonas() {
        return idUserPersonas;
    }

    public UserPersona idUserPersonas(Set<CondivisionePratica> condivisionePraticas) {
        this.idUserPersonas = condivisionePraticas;
        return this;
    }

    public UserPersona addIdUserPersona(CondivisionePratica condivisionePratica) {
        this.idUserPersonas.add(condivisionePratica);
        condivisionePratica.setUserPersona(this);
        return this;
    }

    public UserPersona removeIdUserPersona(CondivisionePratica condivisionePratica) {
        this.idUserPersonas.remove(condivisionePratica);
        condivisionePratica.setUserPersona(null);
        return this;
    }

    public void setIdUserPersonas(Set<CondivisionePratica> condivisionePraticas) {
        this.idUserPersonas = condivisionePraticas;
    }

    public Set<AssegnazioneTask> getIdUserPersonas() {
        return idUserPersonas;
    }

    public UserPersona idUserPersonas(Set<AssegnazioneTask> assegnazioneTasks) {
        this.idUserPersonas = assegnazioneTasks;
        return this;
    }

    public UserPersona addIdUserPersona(AssegnazioneTask assegnazioneTask) {
        this.idUserPersonas.add(assegnazioneTask);
        assegnazioneTask.setUserPersona(this);
        return this;
    }

    public UserPersona removeIdUserPersona(AssegnazioneTask assegnazioneTask) {
        this.idUserPersonas.remove(assegnazioneTask);
        assegnazioneTask.setUserPersona(null);
        return this;
    }

    public void setIdUserPersonas(Set<AssegnazioneTask> assegnazioneTasks) {
        this.idUserPersonas = assegnazioneTasks;
    }

    public Set<Invitato> getIdUserPersonas() {
        return idUserPersonas;
    }

    public UserPersona idUserPersonas(Set<Invitato> invitatoes) {
        this.idUserPersonas = invitatoes;
        return this;
    }

    public UserPersona addIdUserPersona(Invitato invitato) {
        this.idUserPersonas.add(invitato);
        invitato.setUserPersona(this);
        return this;
    }

    public UserPersona removeIdUserPersona(Invitato invitato) {
        this.idUserPersonas.remove(invitato);
        invitato.setUserPersona(null);
        return this;
    }

    public void setIdUserPersonas(Set<Invitato> invitatoes) {
        this.idUserPersonas = invitatoes;
    }

    public PersonaFisica getPersonaFisica() {
        return personaFisica;
    }

    public UserPersona personaFisica(PersonaFisica personaFisica) {
        this.personaFisica = personaFisica;
        return this;
    }

    public void setPersonaFisica(PersonaFisica personaFisica) {
        this.personaFisica = personaFisica;
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
            ", idUserPersona=" + getIdUserPersona() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", nomeUser=" + getNomeUser() +
            "}";
    }
}
