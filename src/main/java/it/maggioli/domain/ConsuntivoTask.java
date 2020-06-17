package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A ConsuntivoTask.
 */
@Entity
@Table(name = "consuntivo_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "consuntivotask")
public class ConsuntivoTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Max(value = 8)
    @Column(name = "id_task", nullable = false)
    private Integer idTask;

    @Column(name = "data_inizio")
    private String dataInizio;

    @Column(name = "data_fine")
    private String dataFine;

    @Column(name = "time_line")
    private Integer timeLine;

    @Column(name = "version")
    private String version;

    @OneToOne(mappedBy = "id")
    @JsonIgnore
    private Task idTask;

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

    public ConsuntivoTask idTask(Integer idTask) {
        this.idTask = idTask;
        return this;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public ConsuntivoTask dataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
        return this;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public ConsuntivoTask dataFine(String dataFine) {
        this.dataFine = dataFine;
        return this;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public Integer getTimeLine() {
        return timeLine;
    }

    public ConsuntivoTask timeLine(Integer timeLine) {
        this.timeLine = timeLine;
        return this;
    }

    public void setTimeLine(Integer timeLine) {
        this.timeLine = timeLine;
    }

    public String getVersion() {
        return version;
    }

    public ConsuntivoTask version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Task getIdTask() {
        return idTask;
    }

    public ConsuntivoTask idTask(Task task) {
        this.idTask = task;
        return this;
    }

    public void setIdTask(Task task) {
        this.idTask = task;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConsuntivoTask)) {
            return false;
        }
        return id != null && id.equals(((ConsuntivoTask) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConsuntivoTask{" +
            "id=" + getId() +
            ", idTask=" + getIdTask() +
            ", dataInizio='" + getDataInizio() + "'" +
            ", dataFine='" + getDataFine() + "'" +
            ", timeLine=" + getTimeLine() +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
