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
    @Max(value = 8L)
    @Column(name = "id_task_ref", nullable = false)
    private Long idTaskRef;

    @Max(value = 8L)
    @Column(name = "qnt_ordine")
    private Long qntOrdine;

    @Max(value = 1L)
    @Column(name = "prc_previsione")
    private Long prcPrevisione;

    @Column(name = "check_list")
    private Long checkList;

    @Column(name = "id_task_milestone")
    private Long idTaskMilestone;

    @Column(name = "tipo_task")
    private String tipoTask;

    @Column(name = "version")
    private String version;

    @OneToMany(mappedBy = "previsioneTask")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PrevisioneTask> idPrevisioneTasks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "idPrevisioneTasks", allowSetters = true)
    private PrevisioneTask previsioneTask;

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

    public PrevisioneTask idTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
        return this;
    }

    public void setIdTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
    }

    public Long getQntOrdine() {
        return qntOrdine;
    }

    public PrevisioneTask qntOrdine(Long qntOrdine) {
        this.qntOrdine = qntOrdine;
        return this;
    }

    public void setQntOrdine(Long qntOrdine) {
        this.qntOrdine = qntOrdine;
    }

    public Long getPrcPrevisione() {
        return prcPrevisione;
    }

    public PrevisioneTask prcPrevisione(Long prcPrevisione) {
        this.prcPrevisione = prcPrevisione;
        return this;
    }

    public void setPrcPrevisione(Long prcPrevisione) {
        this.prcPrevisione = prcPrevisione;
    }

    public Long getCheckList() {
        return checkList;
    }

    public PrevisioneTask checkList(Long checkList) {
        this.checkList = checkList;
        return this;
    }

    public void setCheckList(Long checkList) {
        this.checkList = checkList;
    }

    public Long getIdTaskMilestone() {
        return idTaskMilestone;
    }

    public PrevisioneTask idTaskMilestone(Long idTaskMilestone) {
        this.idTaskMilestone = idTaskMilestone;
        return this;
    }

    public void setIdTaskMilestone(Long idTaskMilestone) {
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

    public Set<PrevisioneTask> getIdPrevisioneTasks() {
        return idPrevisioneTasks;
    }

    public PrevisioneTask idPrevisioneTasks(Set<PrevisioneTask> previsioneTasks) {
        this.idPrevisioneTasks = previsioneTasks;
        return this;
    }

    public PrevisioneTask addIdPrevisioneTask(PrevisioneTask previsioneTask) {
        this.idPrevisioneTasks.add(previsioneTask);
        previsioneTask.setPrevisioneTask(this);
        return this;
    }

    public PrevisioneTask removeIdPrevisioneTask(PrevisioneTask previsioneTask) {
        this.idPrevisioneTasks.remove(previsioneTask);
        previsioneTask.setPrevisioneTask(null);
        return this;
    }

    public void setIdPrevisioneTasks(Set<PrevisioneTask> previsioneTasks) {
        this.idPrevisioneTasks = previsioneTasks;
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
