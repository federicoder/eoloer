package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A NotaTask.
 */
@Entity
@Table(name = "nota_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "notatask")
public class NotaTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Max(value = 8L)
    @Column(name = "id_nota_task", nullable = false)
    private Long idNotaTask;

    @Max(value = 8L)
    @Column(name = "id_task_ref")
    private Long idTaskRef;

    @Column(name = "data")
    private String data;

    @Column(name = "nota")
    private String nota;

    @Column(name = "version")
    private String version;

    @ManyToOne
    @JsonIgnoreProperties(value = "notaTasks", allowSetters = true)
    private Task idTaskRef;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdNotaTask() {
        return idNotaTask;
    }

    public NotaTask idNotaTask(Long idNotaTask) {
        this.idNotaTask = idNotaTask;
        return this;
    }

    public void setIdNotaTask(Long idNotaTask) {
        this.idNotaTask = idNotaTask;
    }

    public Long getIdTaskRef() {
        return idTaskRef;
    }

    public NotaTask idTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
        return this;
    }

    public void setIdTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
    }

    public String getData() {
        return data;
    }

    public NotaTask data(String data) {
        this.data = data;
        return this;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNota() {
        return nota;
    }

    public NotaTask nota(String nota) {
        this.nota = nota;
        return this;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getVersion() {
        return version;
    }

    public NotaTask version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Task getIdTaskRef() {
        return idTaskRef;
    }

    public NotaTask idTaskRef(Task task) {
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
        if (!(o instanceof NotaTask)) {
            return false;
        }
        return id != null && id.equals(((NotaTask) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotaTask{" +
            "id=" + getId() +
            ", idNotaTask=" + getIdNotaTask() +
            ", idTaskRef=" + getIdTaskRef() +
            ", data='" + getData() + "'" +
            ", nota='" + getNota() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
