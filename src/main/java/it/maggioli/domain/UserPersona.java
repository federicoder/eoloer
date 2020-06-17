package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

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

    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "nome_user")
    private Integer nomeUser;

    @OneToMany(mappedBy = "userPersona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CondivisionePratica> ids = new HashSet<>();

    @OneToMany(mappedBy = "userPersona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AssegnazioneTask> ids = new HashSet<>();

    @OneToMany(mappedBy = "userPersona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Invitato> ids = new HashSet<>();

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

    public Integer getIdPersona() {
        return idPersona;
    }

    public UserPersona idPersona(Integer idPersona) {
        this.idPersona = idPersona;
        return this;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
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

    public Set<CondivisionePratica> getIds() {
        return ids;
    }

    public UserPersona ids(Set<CondivisionePratica> condivisionePraticas) {
        this.ids = condivisionePraticas;
        return this;
    }

    public UserPersona addId(CondivisionePratica condivisionePratica) {
        this.ids.add(condivisionePratica);
        condivisionePratica.setUserPersona(this);
        return this;
    }

    public UserPersona removeId(CondivisionePratica condivisionePratica) {
        this.ids.remove(condivisionePratica);
        condivisionePratica.setUserPersona(null);
        return this;
    }

    public void setIds(Set<CondivisionePratica> condivisionePraticas) {
        this.ids = condivisionePraticas;
    }

    public Set<AssegnazioneTask> getIds() {
        return ids;
    }

    public UserPersona ids(Set<AssegnazioneTask> assegnazioneTasks) {
        this.ids = assegnazioneTasks;
        return this;
    }

    public UserPersona addId(AssegnazioneTask assegnazioneTask) {
        this.ids.add(assegnazioneTask);
        assegnazioneTask.setUserPersona(this);
        return this;
    }

    public UserPersona removeId(AssegnazioneTask assegnazioneTask) {
        this.ids.remove(assegnazioneTask);
        assegnazioneTask.setUserPersona(null);
        return this;
    }

    public void setIds(Set<AssegnazioneTask> assegnazioneTasks) {
        this.ids = assegnazioneTasks;
    }

    public Set<Invitato> getIds() {
        return ids;
    }

    public UserPersona ids(Set<Invitato> invitatoes) {
        this.ids = invitatoes;
        return this;
    }

    public UserPersona addId(Invitato invitato) {
        this.ids.add(invitato);
        invitato.setUserPersona(this);
        return this;
    }

    public UserPersona removeId(Invitato invitato) {
        this.ids.remove(invitato);
        invitato.setUserPersona(null);
        return this;
    }

    public void setIds(Set<Invitato> invitatoes) {
        this.ids = invitatoes;
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
            ", idPersona=" + getIdPersona() +
            ", nomeUser=" + getNomeUser() +
            "}";
    }
}
