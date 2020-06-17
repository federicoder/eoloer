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

    @NotNull
    @Max(value = 8)
    @Column(name = "id_task", nullable = false)
    private Integer idTask;

    @Max(value = 8)
    @Column(name = "id_pratica_ref")
    private Integer idPraticaRef;

    @Column(name = "nome")
    private String nome;

    @Column(name = "stato")
    private Integer stato;

    @Column(name = "prioritario")
    private Integer prioritario;

    @Column(name = "pubblico")
    private Integer pubblico;

    @Column(name = "version")
    private String version;

    @Column(name = "id_condivisione_pratica_ref")
    private Integer idCondivisionePraticaRef;

    @Max(value = 8)
    @Column(name = "id_assegnazione_task_ref")
    private Integer idAssegnazioneTaskRef;

    @Max(value = 8)
    @Column(name = "id_invito_ref")
    private Integer idInvitoRef;

    @OneToOne
    @JoinColumn(unique = true)
    private ConsuntivoTask idTask;

    @OneToOne
    @JoinColumn(unique = true)
    private PrevisioneTask idTask;

    @OneToOne
    @JoinColumn(unique = true)
    private AssegnazioneTask idTask;

    @OneToOne
    @JoinColumn(unique = true)
    private InvitoAttivita idTask;

    @OneToMany(mappedBy = "task")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AllegatoTask> idTasks = new HashSet<>();

    @OneToMany(mappedBy = "task")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<NotaTask> idTasks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "idPraticas", allowSetters = true)
    private Pratica pratica;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public Task idTask(Integer idTask) {
        this.idTask = idTask;
        return this;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public Integer getIdPraticaRef() {
        return idPraticaRef;
    }

    public Task idPraticaRef(Integer idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
        return this;
    }

    public void setIdPraticaRef(Integer idPraticaRef) {
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

    public Integer getStato() {
        return stato;
    }

    public Task stato(Integer stato) {
        this.stato = stato;
        return this;
    }

    public void setStato(Integer stato) {
        this.stato = stato;
    }

    public Integer getPrioritario() {
        return prioritario;
    }

    public Task prioritario(Integer prioritario) {
        this.prioritario = prioritario;
        return this;
    }

    public void setPrioritario(Integer prioritario) {
        this.prioritario = prioritario;
    }

    public Integer getPubblico() {
        return pubblico;
    }

    public Task pubblico(Integer pubblico) {
        this.pubblico = pubblico;
        return this;
    }

    public void setPubblico(Integer pubblico) {
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

    public Integer getIdCondivisionePraticaRef() {
        return idCondivisionePraticaRef;
    }

    public Task idCondivisionePraticaRef(Integer idCondivisionePraticaRef) {
        this.idCondivisionePraticaRef = idCondivisionePraticaRef;
        return this;
    }

    public void setIdCondivisionePraticaRef(Integer idCondivisionePraticaRef) {
        this.idCondivisionePraticaRef = idCondivisionePraticaRef;
    }

    public Integer getIdAssegnazioneTaskRef() {
        return idAssegnazioneTaskRef;
    }

    public Task idAssegnazioneTaskRef(Integer idAssegnazioneTaskRef) {
        this.idAssegnazioneTaskRef = idAssegnazioneTaskRef;
        return this;
    }

    public void setIdAssegnazioneTaskRef(Integer idAssegnazioneTaskRef) {
        this.idAssegnazioneTaskRef = idAssegnazioneTaskRef;
    }

    public Integer getIdInvitoRef() {
        return idInvitoRef;
    }

    public Task idInvitoRef(Integer idInvitoRef) {
        this.idInvitoRef = idInvitoRef;
        return this;
    }

    public void setIdInvitoRef(Integer idInvitoRef) {
        this.idInvitoRef = idInvitoRef;
    }

    public ConsuntivoTask getIdTask() {
        return idTask;
    }

    public Task idTask(ConsuntivoTask consuntivoTask) {
        this.idTask = consuntivoTask;
        return this;
    }

    public void setIdTask(ConsuntivoTask consuntivoTask) {
        this.idTask = consuntivoTask;
    }

    public PrevisioneTask getIdTask() {
        return idTask;
    }

    public Task idTask(PrevisioneTask previsioneTask) {
        this.idTask = previsioneTask;
        return this;
    }

    public void setIdTask(PrevisioneTask previsioneTask) {
        this.idTask = previsioneTask;
    }

    public AssegnazioneTask getIdTask() {
        return idTask;
    }

    public Task idTask(AssegnazioneTask assegnazioneTask) {
        this.idTask = assegnazioneTask;
        return this;
    }

    public void setIdTask(AssegnazioneTask assegnazioneTask) {
        this.idTask = assegnazioneTask;
    }

    public InvitoAttivita getIdTask() {
        return idTask;
    }

    public Task idTask(InvitoAttivita invitoAttivita) {
        this.idTask = invitoAttivita;
        return this;
    }

    public void setIdTask(InvitoAttivita invitoAttivita) {
        this.idTask = invitoAttivita;
    }

    public Set<AllegatoTask> getIdTasks() {
        return idTasks;
    }

    public Task idTasks(Set<AllegatoTask> allegatoTasks) {
        this.idTasks = allegatoTasks;
        return this;
    }

    public Task addIdTask(AllegatoTask allegatoTask) {
        this.idTasks.add(allegatoTask);
        allegatoTask.setTask(this);
        return this;
    }

    public Task removeIdTask(AllegatoTask allegatoTask) {
        this.idTasks.remove(allegatoTask);
        allegatoTask.setTask(null);
        return this;
    }

    public void setIdTasks(Set<AllegatoTask> allegatoTasks) {
        this.idTasks = allegatoTasks;
    }

    public Set<NotaTask> getIdTasks() {
        return idTasks;
    }

    public Task idTasks(Set<NotaTask> notaTasks) {
        this.idTasks = notaTasks;
        return this;
    }

    public Task addIdTask(NotaTask notaTask) {
        this.idTasks.add(notaTask);
        notaTask.setTask(this);
        return this;
    }

    public Task removeIdTask(NotaTask notaTask) {
        this.idTasks.remove(notaTask);
        notaTask.setTask(null);
        return this;
    }

    public void setIdTasks(Set<NotaTask> notaTasks) {
        this.idTasks = notaTasks;
    }

    public Pratica getPratica() {
        return pratica;
    }

    public Task pratica(Pratica pratica) {
        this.pratica = pratica;
        return this;
    }

    public void setPratica(Pratica pratica) {
        this.pratica = pratica;
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
            ", idTask=" + getIdTask() +
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
