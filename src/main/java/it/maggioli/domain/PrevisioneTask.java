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
 * A PrevisioneTask.
 */
@Entity
@Table(name = "previsione_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "previsionetask")
public class PrevisioneTask implements Serializable {

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
    @Column(name = "qnt_ordine")
    private Integer qntOrdine;

    @Max(value = 1)
    @Column(name = "prc_previsione")
    private Integer prcPrevisione;

    @Column(name = "check_list")
    private Integer checkList;

    @Column(name = "id_task_milestone")
    private Integer idTaskMilestone;

    @Column(name = "tipo_task")
    private String tipoTask;

    @Column(name = "version")
    private String version;

    @OneToMany(mappedBy = "previsioneTask")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PrevisioneTask> idTasks = new HashSet<>();

    @OneToOne(mappedBy = "idTask")
    @JsonIgnore
    private PrevisioneAttivita idTask;

    @OneToOne(mappedBy = "idTask")
    @JsonIgnore
    private PrevisioneEvento idTask;

    @OneToOne(mappedBy = "id")
    @JsonIgnore
    private Task idTask;

    @ManyToOne
    @JsonIgnoreProperties(value = "idTasks", allowSetters = true)
    private PrevisioneTask previsioneTask;

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

    public PrevisioneTask idTask(Integer idTask) {
        this.idTask = idTask;
        return this;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public Integer getQntOrdine() {
        return qntOrdine;
    }

    public PrevisioneTask qntOrdine(Integer qntOrdine) {
        this.qntOrdine = qntOrdine;
        return this;
    }

    public void setQntOrdine(Integer qntOrdine) {
        this.qntOrdine = qntOrdine;
    }

    public Integer getPrcPrevisione() {
        return prcPrevisione;
    }

    public PrevisioneTask prcPrevisione(Integer prcPrevisione) {
        this.prcPrevisione = prcPrevisione;
        return this;
    }

    public void setPrcPrevisione(Integer prcPrevisione) {
        this.prcPrevisione = prcPrevisione;
    }

    public Integer getCheckList() {
        return checkList;
    }

    public PrevisioneTask checkList(Integer checkList) {
        this.checkList = checkList;
        return this;
    }

    public void setCheckList(Integer checkList) {
        this.checkList = checkList;
    }

    public Integer getIdTaskMilestone() {
        return idTaskMilestone;
    }

    public PrevisioneTask idTaskMilestone(Integer idTaskMilestone) {
        this.idTaskMilestone = idTaskMilestone;
        return this;
    }

    public void setIdTaskMilestone(Integer idTaskMilestone) {
        this.idTaskMilestone = idTaskMilestone;
    }

    public String getTipoTask() {
        return tipoTask;
    }

    public PrevisioneTask tipoTask(String tipoTask) {
        this.tipoTask = tipoTask;
        return this;
    }

    public void setTipoTask(String tipoTask) {
        this.tipoTask = tipoTask;
    }

    public String getVersion() {
        return version;
    }

    public PrevisioneTask version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Set<PrevisioneTask> getIdTasks() {
        return idTasks;
    }

    public PrevisioneTask idTasks(Set<PrevisioneTask> previsioneTasks) {
        this.idTasks = previsioneTasks;
        return this;
    }

    public PrevisioneTask addIdTask(PrevisioneTask previsioneTask) {
        this.idTasks.add(previsioneTask);
        previsioneTask.setPrevisioneTask(this);
        return this;
    }

    public PrevisioneTask removeIdTask(PrevisioneTask previsioneTask) {
        this.idTasks.remove(previsioneTask);
        previsioneTask.setPrevisioneTask(null);
        return this;
    }

    public void setIdTasks(Set<PrevisioneTask> previsioneTasks) {
        this.idTasks = previsioneTasks;
    }

    public PrevisioneAttivita getIdTask() {
        return idTask;
    }

    public PrevisioneTask idTask(PrevisioneAttivita previsioneAttivita) {
        this.idTask = previsioneAttivita;
        return this;
    }

    public void setIdTask(PrevisioneAttivita previsioneAttivita) {
        this.idTask = previsioneAttivita;
    }

    public PrevisioneEvento getIdTask() {
        return idTask;
    }

    public PrevisioneTask idTask(PrevisioneEvento previsioneEvento) {
        this.idTask = previsioneEvento;
        return this;
    }

    public void setIdTask(PrevisioneEvento previsioneEvento) {
        this.idTask = previsioneEvento;
    }

    public Task getIdTask() {
        return idTask;
    }

    public PrevisioneTask idTask(Task task) {
        this.idTask = task;
        return this;
    }

    public void setIdTask(Task task) {
        this.idTask = task;
    }

    public PrevisioneTask getPrevisioneTask() {
        return previsioneTask;
    }

    public PrevisioneTask previsioneTask(PrevisioneTask previsioneTask) {
        this.previsioneTask = previsioneTask;
        return this;
    }

    public void setPrevisioneTask(PrevisioneTask previsioneTask) {
        this.previsioneTask = previsioneTask;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrevisioneTask)) {
            return false;
        }
        return id != null && id.equals(((PrevisioneTask) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrevisioneTask{" +
            "id=" + getId() +
            ", idTask=" + getIdTask() +
            ", qntOrdine=" + getQntOrdine() +
            ", prcPrevisione=" + getPrcPrevisione() +
            ", checkList=" + getCheckList() +
            ", idTaskMilestone=" + getIdTaskMilestone() +
            ", tipoTask='" + getTipoTask() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
