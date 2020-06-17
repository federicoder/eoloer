package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Task.
 */
@Entity
@Table(name = "task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Max(value = 8L)
    @Column(name = "id_pratica_ref")
    private Long idPraticaRef;

    @Column(name = "nome")
    private String nome;

    @Column(name = "stato")
    private Long stato;

    @Column(name = "prioritario")
    private Long prioritario;

    @Column(name = "pubblico")
    private Long pubblico;

    @Column(name = "version")
    private String version;

    @Column(name = "id_condivisione_pratica_ref")
    private Long idCondivisionePraticaRef;

    @Max(value = 8L)
    @Column(name = "id_assegnazione_task_ref")
    private Long idAssegnazioneTaskRef;

    @Max(value = 8L)
    @Column(name = "id_invito_ref")
    private Long idInvitoRef;

    @OneToOne
    @JoinColumn(unique = true)
    private ConsuntivoTask id;

    @OneToOne
    @JoinColumn(unique = true)
    private PrevisioneTask id;

    @OneToOne
    @JoinColumn(unique = true)
    private AssegnazioneTask id;

    @OneToOne
    @JoinColumn(unique = true)
    private InvitoAttivita id;

    @ManyToOne
    @JsonIgnoreProperties(value = "tasks", allowSetters = true)
    private Pratica idPraticaRef;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPraticaRef() {
        return idPraticaRef;
    }

    public Task idPraticaRef(Long idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
        return this;
    }

    public void setIdPraticaRef(Long idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
    }

    public String getNome() {
        return nome;
    }

    public Task nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getStato() {
        return stato;
    }

    public Task stato(Long stato) {
        this.stato = stato;
        return this;
    }

    public void setStato(Long stato) {
        this.stato = stato;
    }

    public Long getPrioritario() {
        return prioritario;
    }

    public Task prioritario(Long prioritario) {
        this.prioritario = prioritario;
        return this;
    }

    public void setPrioritario(Long prioritario) {
        this.prioritario = prioritario;
    }

    public Long getPubblico() {
        return pubblico;
    }

    public Task pubblico(Long pubblico) {
        this.pubblico = pubblico;
        return this;
    }

    public void setPubblico(Long pubblico) {
        this.pubblico = pubblico;
    }

    public String getVersion() {
        return version;
    }

    public Task version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getIdCondivisionePraticaRef() {
        return idCondivisionePraticaRef;
    }

    public Task idCondivisionePraticaRef(Long idCondivisionePraticaRef) {
        this.idCondivisionePraticaRef = idCondivisionePraticaRef;
        return this;
    }

    public void setIdCondivisionePraticaRef(Long idCondivisionePraticaRef) {
        this.idCondivisionePraticaRef = idCondivisionePraticaRef;
    }

    public Long getIdAssegnazioneTaskRef() {
        return idAssegnazioneTaskRef;
    }

    public Task idAssegnazioneTaskRef(Long idAssegnazioneTaskRef) {
        this.idAssegnazioneTaskRef = idAssegnazioneTaskRef;
        return this;
    }

    public void setIdAssegnazioneTaskRef(Long idAssegnazioneTaskRef) {
        this.idAssegnazioneTaskRef = idAssegnazioneTaskRef;
    }

    public Long getIdInvitoRef() {
        return idInvitoRef;
    }

    public Task idInvitoRef(Long idInvitoRef) {
        this.idInvitoRef = idInvitoRef;
        return this;
    }

    public void setIdInvitoRef(Long idInvitoRef) {
        this.idInvitoRef = idInvitoRef;
    }

    public ConsuntivoTask getId() {
        return id;
    }

    public Task id(ConsuntivoTask consuntivoTask) {
        this.id = consuntivoTask;
        return this;
    }

    public void setId(ConsuntivoTask consuntivoTask) {
        this.id = consuntivoTask;
    }

    public PrevisioneTask getId() {
        return id;
    }

    public Task id(PrevisioneTask previsioneTask) {
        this.id = previsioneTask;
        return this;
    }

    public void setId(PrevisioneTask previsioneTask) {
        this.id = previsioneTask;
    }

    public AssegnazioneTask getId() {
        return id;
    }

    public Task id(AssegnazioneTask assegnazioneTask) {
        this.id = assegnazioneTask;
        return this;
    }

    public void setId(AssegnazioneTask assegnazioneTask) {
        this.id = assegnazioneTask;
    }

    public InvitoAttivita getId() {
        return id;
    }

    public Task id(InvitoAttivita invitoAttivita) {
        this.id = invitoAttivita;
        return this;
    }

    public void setId(InvitoAttivita invitoAttivita) {
        this.id = invitoAttivita;
    }

    public Pratica getIdPraticaRef() {
        return idPraticaRef;
    }

    public Task idPraticaRef(Pratica pratica) {
        this.idPraticaRef = pratica;
        return this;
    }

    public void setIdPraticaRef(Pratica pratica) {
        this.idPraticaRef = pratica;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        return id != null && id.equals(((Task) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Task{" +
            "id=" + getId() +
            ", idPraticaRef=" + getIdPraticaRef() +
            ", nome='" + getNome() + "'" +
            ", stato=" + getStato() +
            ", prioritario=" + getPrioritario() +
            ", pubblico=" + getPubblico() +
            ", version='" + getVersion() + "'" +
            ", idCondivisionePraticaRef=" + getIdCondivisionePraticaRef() +
            ", idAssegnazioneTaskRef=" + getIdAssegnazioneTaskRef() +
            ", idInvitoRef=" + getIdInvitoRef() +
            "}";
    }
}
