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
    @Max(value = 8L)
    @Column(name = "id_task_ref", nullable = false)
    private Long idTaskRef;

    @Column(name = "data_inizio")
    private String dataInizio;

    @Column(name = "data_fine")
    private String dataFine;

    @Column(name = "time_line")
    private Long timeLine;

    @Column(name = "version")
    private String version;

    @OneToOne(mappedBy = "id")
    @JsonIgnore
    private Task idTaskRef;

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

    public ConsuntivoTask idTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
        return this;
    }

    public void setIdTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
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

    public Long getTimeLine() {
        return timeLine;
    }

    public ConsuntivoTask timeLine(Long timeLine) {
        this.timeLine = timeLine;
        return this;
    }

    public void setTimeLine(Long timeLine) {
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

    public Task getIdTaskRef() {
        return idTaskRef;
    }

    public ConsuntivoTask idTaskRef(Task task) {
        this.idTaskRef = task;
        return this;
    }

    public void setIdTaskRef(Task task) {
        this.idTaskRef = task;
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
            ", idTaskRef=" + getIdTaskRef() +
            ", dataInizio='" + getDataInizio() + "'" +
            ", dataFine='" + getDataFine() + "'" +
            ", timeLine=" + getTimeLine() +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
