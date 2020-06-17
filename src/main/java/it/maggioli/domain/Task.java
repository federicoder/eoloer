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

    @Max(value = 8)
    @Column(name = "id_pratica")
    private Integer idPratica;

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

    @Column(name = "condivisione_pratica_id")
    private Integer condivisionePraticaId;

    @Max(value = 8)
    @Column(name = "assegnazione_task_id")
    private Integer assegnazioneTaskId;

    @Max(value = 8)
    @Column(name = "invito_id")
    private Integer invitoId;

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

    @OneToMany(mappedBy = "task")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AllegatoTask> ids = new HashSet<>();

    @OneToMany(mappedBy = "task")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<NotaTask> ids = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private Pratica pratica;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPratica() {
        return idPratica;
    }

    public Task idPratica(Integer idPratica) {
        this.idPratica = idPratica;
        return this;
    }

    public void setIdPratica(Integer idPratica) {
        this.idPratica = idPratica;
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

    public Integer getCondivisionePraticaId() {
        return condivisionePraticaId;
    }

    public Task condivisionePraticaId(Integer condivisionePraticaId) {
        this.condivisionePraticaId = condivisionePraticaId;
        return this;
    }

    public void setCondivisionePraticaId(Integer condivisionePraticaId) {
        this.condivisionePraticaId = condivisionePraticaId;
    }

    public Integer getAssegnazioneTaskId() {
        return assegnazioneTaskId;
    }

    public Task assegnazioneTaskId(Integer assegnazioneTaskId) {
        this.assegnazioneTaskId = assegnazioneTaskId;
        return this;
    }

    public void setAssegnazioneTaskId(Integer assegnazioneTaskId) {
        this.assegnazioneTaskId = assegnazioneTaskId;
    }

    public Integer getInvitoId() {
        return invitoId;
    }

    public Task invitoId(Integer invitoId) {
        this.invitoId = invitoId;
        return this;
    }

    public void setInvitoId(Integer invitoId) {
        this.invitoId = invitoId;
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

    public Set<AllegatoTask> getIds() {
        return ids;
    }

    public Task ids(Set<AllegatoTask> allegatoTasks) {
        this.ids = allegatoTasks;
        return this;
    }

    public Task addId(AllegatoTask allegatoTask) {
        this.ids.add(allegatoTask);
        allegatoTask.setTask(this);
        return this;
    }

    public Task removeId(AllegatoTask allegatoTask) {
        this.ids.remove(allegatoTask);
        allegatoTask.setTask(null);
        return this;
    }

    public void setIds(Set<AllegatoTask> allegatoTasks) {
        this.ids = allegatoTasks;
    }

    public Set<NotaTask> getIds() {
        return ids;
    }

    public Task ids(Set<NotaTask> notaTasks) {
        this.ids = notaTasks;
        return this;
    }

    public Task addId(NotaTask notaTask) {
        this.ids.add(notaTask);
        notaTask.setTask(this);
        return this;
    }

    public Task removeId(NotaTask notaTask) {
        this.ids.remove(notaTask);
        notaTask.setTask(null);
        return this;
    }

    public void setIds(Set<NotaTask> notaTasks) {
        this.ids = notaTasks;
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
            ", idPratica=" + getIdPratica() +
            ", nome='" + getNome() + "'" +
            ", stato=" + getStato() +
            ", prioritario=" + getPrioritario() +
            ", pubblico=" + getPubblico() +
            ", version='" + getVersion() + "'" +
            ", condivisionePraticaId=" + getCondivisionePraticaId() +
            ", assegnazioneTaskId=" + getAssegnazioneTaskId() +
            ", invitoId=" + getInvitoId() +
            "}";
    }
}
