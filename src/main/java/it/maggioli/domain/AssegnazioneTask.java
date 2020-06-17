package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * A AssegnazioneTask.
 */
@Entity
@Table(name = "assegnazione_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "assegnazionetask")
public class AssegnazioneTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Max(value = 8L)
    @Column(name = "id_task_ref")
    private Long idTaskRef;

    @Max(value = 8L)
    @Column(name = "id_user_ammesso")
    private Long idUserAmmesso;

    @Column(name = "ruolo")
    private Long ruolo;

    @Column(name = "id_user_concedente")
    private Long idUserConcedente;

    @Column(name = "stato_assegnazione")
    private Long statoAssegnazione;

    @OneToOne
    @JoinColumn(unique = true)
    private RappresentanzaPratica ruolo;

    @OneToMany(mappedBy = "assegnazioneTask")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Invito> idUserConcedentes = new HashSet<>();

    @OneToOne(mappedBy = "id")
    @JsonIgnore
    private Task idTaskRef;

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private UserPersona userPersona;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTaskRef() {
        return idTaskRef;
    }

    public AssegnazioneTask idTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
        return this;
    }

    public void setIdTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
    }

    public Long getIdUserAmmesso() {
        return idUserAmmesso;
    }

    public AssegnazioneTask idUserAmmesso(Long idUserAmmesso) {
        this.idUserAmmesso = idUserAmmesso;
        return this;
    }

    public void setIdUserAmmesso(Long idUserAmmesso) {
        this.idUserAmmesso = idUserAmmesso;
    }

    public Long getRuolo() {
        return ruolo;
    }

    public AssegnazioneTask ruolo(Long ruolo) {
        this.ruolo = ruolo;
        return this;
    }

    public void setRuolo(Long ruolo) {
        this.ruolo = ruolo;
    }

    public Long getIdUserConcedente() {
        return idUserConcedente;
    }

    public AssegnazioneTask idUserConcedente(Long idUserConcedente) {
        this.idUserConcedente = idUserConcedente;
        return this;
    }

    public void setIdUserConcedente(Long idUserConcedente) {
        this.idUserConcedente = idUserConcedente;
    }

    public Long getStatoAssegnazione() {
        return statoAssegnazione;
    }

    public AssegnazioneTask statoAssegnazione(Long statoAssegnazione) {
        this.statoAssegnazione = statoAssegnazione;
        return this;
    }

    public void setStatoAssegnazione(Long statoAssegnazione) {
        this.statoAssegnazione = statoAssegnazione;
    }

    public RappresentanzaPratica getRuolo() {
        return ruolo;
    }

    public AssegnazioneTask ruolo(RappresentanzaPratica rappresentanzaPratica) {
        this.ruolo = rappresentanzaPratica;
        return this;
    }

    public void setRuolo(RappresentanzaPratica rappresentanzaPratica) {
        this.ruolo = rappresentanzaPratica;
    }

    public Set<Invito> getIdUserConcedentes() {
        return idUserConcedentes;
    }

    public AssegnazioneTask idUserConcedentes(Set<Invito> invitos) {
        this.idUserConcedentes = invitos;
        return this;
    }

    public AssegnazioneTask addIdUserConcedente(Invito invito) {
        this.idUserConcedentes.add(invito);
        invito.setAssegnazioneTask(this);
        return this;
    }

    public AssegnazioneTask removeIdUserConcedente(Invito invito) {
        this.idUserConcedentes.remove(invito);
        invito.setAssegnazioneTask(null);
        return this;
    }

    public void setIdUserConcedentes(Set<Invito> invitos) {
        this.idUserConcedentes = invitos;
    }

    public Task getIdTaskRef() {
        return idTaskRef;
    }

    public AssegnazioneTask idTaskRef(Task task) {
        this.idTaskRef = task;
        return this;
    }

    public void setIdTaskRef(Task task) {
        this.idTaskRef = task;
    }

    public UserPersona getUserPersona() {
        return userPersona;
    }

    public AssegnazioneTask userPersona(UserPersona userPersona) {
        this.userPersona = userPersona;
        return this;
    }

    public void setUserPersona(UserPersona userPersona) {
        this.userPersona = userPersona;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssegnazioneTask)) {
            return false;
        }
        return id != null && id.equals(((AssegnazioneTask) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AssegnazioneTask{" +
            "id=" + getId() +
            ", idTaskRef=" + getIdTaskRef() +
            ", idUserAmmesso=" + getIdUserAmmesso() +
            ", ruolo=" + getRuolo() +
            ", idUserConcedente=" + getIdUserConcedente() +
            ", statoAssegnazione=" + getStatoAssegnazione() +
            "}";
    }
}
