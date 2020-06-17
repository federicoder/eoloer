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
    @Column(name = "id_task_ref", nullable = false)
    private Integer idTaskRef;

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
    private Set<PrevisioneTask> idTaskRefs = new HashSet<>();

    @OneToOne(mappedBy = "idTaskRef")
    @JsonIgnore
    private PrevisioneAttivita idTaskRef;

    @OneToOne(mappedBy = "idTaskRef")
    @JsonIgnore
    private PrevisioneEvento idTaskRef;

    @OneToOne(mappedBy = "idTask")
    @JsonIgnore
    private Task idTaskRef;

    @ManyToOne
    @JsonIgnoreProperties(value = "idTaskRefs", allowSetters = true)
    private PrevisioneTask previsioneTask;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTaskRef() {
        return idTaskRef;
    }

    public PrevisioneTask idTaskRef(Integer idTaskRef) {
        this.idTaskRef = idTaskRef;
        return this;
    }

    public void setIdTaskRef(Integer idTaskRef) {
        this.idTaskRef = idTaskRef;
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

    public Set<PrevisioneTask> getIdTaskRefs() {
        return idTaskRefs;
    }

    public PrevisioneTask idTaskRefs(Set<PrevisioneTask> previsioneTasks) {
        this.idTaskRefs = previsioneTasks;
        return this;
    }

    public PrevisioneTask addIdTaskRef(PrevisioneTask previsioneTask) {
        this.idTaskRefs.add(previsioneTask);
        previsioneTask.setPrevisioneTask(this);
        return this;
    }

    public PrevisioneTask removeIdTaskRef(PrevisioneTask previsioneTask) {
        this.idTaskRefs.remove(previsioneTask);
        previsioneTask.setPrevisioneTask(null);
        return this;
    }

    public void setIdTaskRefs(Set<PrevisioneTask> previsioneTasks) {
        this.idTaskRefs = previsioneTasks;
    }

    public PrevisioneAttivita getIdTaskRef() {
        return idTaskRef;
    }

    public PrevisioneTask idTaskRef(PrevisioneAttivita previsioneAttivita) {
        this.idTaskRef = previsioneAttivita;
        return this;
    }

    public void setIdTaskRef(PrevisioneAttivita previsioneAttivita) {
        this.idTaskRef = previsioneAttivita;
    }

    public PrevisioneEvento getIdTaskRef() {
        return idTaskRef;
    }

    public PrevisioneTask idTaskRef(PrevisioneEvento previsioneEvento) {
        this.idTaskRef = previsioneEvento;
        return this;
    }

    public void setIdTaskRef(PrevisioneEvento previsioneEvento) {
        this.idTaskRef = previsioneEvento;
    }

    public Task getIdTaskRef() {
        return idTaskRef;
    }

    public PrevisioneTask idTaskRef(Task task) {
        this.idTaskRef = task;
        return this;
    }

    public void setIdTaskRef(Task task) {
        this.idTaskRef = task;
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
            ", idTaskRef=" + getIdTaskRef() +
            ", qntOrdine=" + getQntOrdine() +
            ", prcPrevisione=" + getPrcPrevisione() +
            ", checkList=" + getCheckList() +
            ", idTaskMilestone=" + getIdTaskMilestone() +
            ", tipoTask='" + getTipoTask() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
