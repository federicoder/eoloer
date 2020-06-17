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

    @Max(value = 8L)
    @Column(name = "id_tipo_allegato_ref")
    private Long idTipoAllegatoRef;

    @Max(value = 8L)
    @Column(name = "id_task_ref")
    private Long idTaskRef;

    @Column(name = "formato")
    private Long formato;

    @Column(name = "note")
    private String note;

    @Column(name = "stato")
    private Long stato;

    @Column(name = "pubblico")
    private Long pubblico;

    @Column(name = "version")
    private String version;

    @NotNull
    @Column(name = "id_allegato_master", nullable = false)
    private Long idAllegatoMaster;

    @OneToMany(mappedBy = "allegatoTask")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AllegatoTask> ids = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "allegatoTasks", allowSetters = true)
    private TipoAllegato idTipoAllegatoRef;

    @ManyToOne
    @JsonIgnoreProperties(value = "allegatoTasks", allowSetters = true)
    private Task idTaskRef;

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private AllegatoTask allegatoTask;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTipoAllegatoRef() {
        return idTipoAllegatoRef;
    }

    public AllegatoTask idTipoAllegatoRef(Long idTipoAllegatoRef) {
        this.idTipoAllegatoRef = idTipoAllegatoRef;
        return this;
    }

    public void setIdTipoAllegatoRef(Long idTipoAllegatoRef) {
        this.idTipoAllegatoRef = idTipoAllegatoRef;
    }

    public Long getIdTaskRef() {
        return idTaskRef;
    }

    public AllegatoTask idTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
        return this;
    }

    public void setIdTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
    }

    public Long getFormato() {
        return formato;
    }

    public AllegatoTask formato(Long formato) {
        this.formato = formato;
        return this;
    }

    public void setFormato(Long formato) {
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

    public Long getStato() {
        return stato;
    }

    public AllegatoTask stato(Long stato) {
        this.stato = stato;
        return this;
    }

    public void setStato(Long stato) {
        this.stato = stato;
    }

    public Long getPubblico() {
        return pubblico;
    }

    public AllegatoTask pubblico(Long pubblico) {
        this.pubblico = pubblico;
        return this;
    }

    public void setPubblico(Long pubblico) {
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

    public Long getIdAllegatoMaster() {
        return idAllegatoMaster;
    }

    public AllegatoTask idAllegatoMaster(Long idAllegatoMaster) {
        this.idAllegatoMaster = idAllegatoMaster;
        return this;
    }

    public void setIdAllegatoMaster(Long idAllegatoMaster) {
        this.idAllegatoMaster = idAllegatoMaster;
    }

    public Set<AllegatoTask> getIds() {
        return ids;
    }

    public AllegatoTask ids(Set<AllegatoTask> allegatoTasks) {
        this.ids = allegatoTasks;
        return this;
    }

    public AllegatoTask addId(AllegatoTask allegatoTask) {
        this.ids.add(allegatoTask);
        allegatoTask.setAllegatoTask(this);
        return this;
    }

    public AllegatoTask removeId(AllegatoTask allegatoTask) {
        this.ids.remove(allegatoTask);
        allegatoTask.setAllegatoTask(null);
        return this;
    }

    public void setIds(Set<AllegatoTask> allegatoTasks) {
        this.ids = allegatoTasks;
    }

    public TipoAllegato getIdTipoAllegatoRef() {
        return idTipoAllegatoRef;
    }

    public AllegatoTask idTipoAllegatoRef(TipoAllegato tipoAllegato) {
        this.idTipoAllegatoRef = tipoAllegato;
        return this;
    }

    public void setIdTipoAllegatoRef(TipoAllegato tipoAllegato) {
        this.idTipoAllegatoRef = tipoAllegato;
    }

    public Task getIdTaskRef() {
        return idTaskRef;
    }

    public AllegatoTask idTaskRef(Task task) {
        this.idTaskRef = task;
        return this;
    }

    public void setIdTaskRef(Task task) {
        this.idTaskRef = task;
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
            ", idTipoAllegatoRef=" + getIdTipoAllegatoRef() +
            ", idTaskRef=" + getIdTaskRef() +
            ", formato=" + getFormato() +
            ", note='" + getNote() + "'" +
            ", stato=" + getStato() +
            ", pubblico=" + getPubblico() +
            ", version='" + getVersion() + "'" +
            ", idAllegatoMaster=" + getIdAllegatoMaster() +
            "}";
    }
}
