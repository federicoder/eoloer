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
 * A AllegatoTask.
 */
@Entity
@Table(name = "allegato_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "allegatotask")
public class AllegatoTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Max(value = 8)
    @Column(name = "id_allegato_task", nullable = false)
    private Integer idAllegatoTask;

    @Max(value = 8)
    @Column(name = "id_tipo")
    private Integer idTipo;

    @Max(value = 8)
    @Column(name = "id_task")
    private Integer idTask;

    @Column(name = "formato")
    private Integer formato;

    @Column(name = "note")
    private String note;

    @Column(name = "stato")
    private Integer stato;

    @Column(name = "pubblico")
    private Integer pubblico;

    @Column(name = "version")
    private String version;

    @NotNull
    @Column(name = "id_allegato_master", nullable = false)
    private Integer idAllegatoMaster;

    @OneToMany(mappedBy = "allegatoTask")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AllegatoTask> idAllegatoTasks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "idTipoAllegatoes", allowSetters = true)
    private TipoAllegato tipoAllegato;

    @ManyToOne
    @JsonIgnoreProperties(value = "idAllegatoTasks", allowSetters = true)
    private AllegatoTask allegatoTask;

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private Task task;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdAllegatoTask() {
        return idAllegatoTask;
    }

    public AllegatoTask idAllegatoTask(Integer idAllegatoTask) {
        this.idAllegatoTask = idAllegatoTask;
        return this;
    }

    public void setIdAllegatoTask(Integer idAllegatoTask) {
        this.idAllegatoTask = idAllegatoTask;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public AllegatoTask idTipo(Integer idTipo) {
        this.idTipo = idTipo;
        return this;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public AllegatoTask idTask(Integer idTask) {
        this.idTask = idTask;
        return this;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public Integer getFormato() {
        return formato;
    }

    public AllegatoTask formato(Integer formato) {
        this.formato = formato;
        return this;
    }

    public void setFormato(Integer formato) {
        this.formato = formato;
    }

    public String getNote() {
        return note;
    }

    public AllegatoTask note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStato() {
        return stato;
    }

    public AllegatoTask stato(Integer stato) {
        this.stato = stato;
        return this;
    }

    public void setStato(Integer stato) {
        this.stato = stato;
    }

    public Integer getPubblico() {
        return pubblico;
    }

    public AllegatoTask pubblico(Integer pubblico) {
        this.pubblico = pubblico;
        return this;
    }

    public void setPubblico(Integer pubblico) {
        this.pubblico = pubblico;
    }

    public String getVersion() {
        return version;
    }

    public AllegatoTask version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getIdAllegatoMaster() {
        return idAllegatoMaster;
    }

    public AllegatoTask idAllegatoMaster(Integer idAllegatoMaster) {
        this.idAllegatoMaster = idAllegatoMaster;
        return this;
    }

    public void setIdAllegatoMaster(Integer idAllegatoMaster) {
        this.idAllegatoMaster = idAllegatoMaster;
    }

    public Set<AllegatoTask> getIdAllegatoTasks() {
        return idAllegatoTasks;
    }

    public AllegatoTask idAllegatoTasks(Set<AllegatoTask> allegatoTasks) {
        this.idAllegatoTasks = allegatoTasks;
        return this;
    }

    public AllegatoTask addIdAllegatoTask(AllegatoTask allegatoTask) {
        this.idAllegatoTasks.add(allegatoTask);
        allegatoTask.setAllegatoTask(this);
        return this;
    }

    public AllegatoTask removeIdAllegatoTask(AllegatoTask allegatoTask) {
        this.idAllegatoTasks.remove(allegatoTask);
        allegatoTask.setAllegatoTask(null);
        return this;
    }

    public void setIdAllegatoTasks(Set<AllegatoTask> allegatoTasks) {
        this.idAllegatoTasks = allegatoTasks;
    }

    public TipoAllegato getTipoAllegato() {
        return tipoAllegato;
    }

    public AllegatoTask tipoAllegato(TipoAllegato tipoAllegato) {
        this.tipoAllegato = tipoAllegato;
        return this;
    }

    public void setTipoAllegato(TipoAllegato tipoAllegato) {
        this.tipoAllegato = tipoAllegato;
    }

    public AllegatoTask getAllegatoTask() {
        return allegatoTask;
    }

    public AllegatoTask allegatoTask(AllegatoTask allegatoTask) {
        this.allegatoTask = allegatoTask;
        return this;
    }

    public void setAllegatoTask(AllegatoTask allegatoTask) {
        this.allegatoTask = allegatoTask;
    }

    public Task getTask() {
        return task;
    }

    public AllegatoTask task(Task task) {
        this.task = task;
        return this;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AllegatoTask)) {
            return false;
        }
        return id != null && id.equals(((AllegatoTask) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AllegatoTask{" +
            "id=" + getId() +
            ", idAllegatoTask=" + getIdAllegatoTask() +
            ", idTipo=" + getIdTipo() +
            ", idTask=" + getIdTask() +
            ", formato=" + getFormato() +
            ", note='" + getNote() + "'" +
            ", stato=" + getStato() +
            ", pubblico=" + getPubblico() +
            ", version='" + getVersion() + "'" +
            ", idAllegatoMaster=" + getIdAllegatoMaster() +
            "}";
    }
}
