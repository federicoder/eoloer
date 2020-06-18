package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A AllegatoTemplateTask.
 */
@Entity
@Table(name = "allegato_template_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "allegatotemplatetask")
public class AllegatoTemplateTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Max(value = 8L)
    @Column(name = "id_template_task_ref", nullable = false)
    private Long idTemplateTaskRef;

    @Column(name = "id_tipo_allegato_ref")
    private Long idTipoAllegatoRef;

    @Column(name = "formato")
    private Long formato;

    @Column(name = "id_file_ref")
    private Long idFileRef;

    @Column(name = "pub_priv")
    private Long pubPriv;

    @ManyToOne
    @JsonIgnoreProperties(value = "allegatoTemplateTasks", allowSetters = true)
    private TemplateTask idTemplateTask;

    @ManyToOne
    @JsonIgnoreProperties(value = "allegatoTemplateTasks", allowSetters = true)
    private TipoAllegato tipoAllegato;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTemplateTaskRef() {
        return idTemplateTaskRef;
    }

    public AllegatoTemplateTask idTemplateTaskRef(Long idTemplateTaskRef) {
        this.idTemplateTaskRef = idTemplateTaskRef;
        return this;
    }

    public void setIdTemplateTaskRef(Long idTemplateTaskRef) {
        this.idTemplateTaskRef = idTemplateTaskRef;
    }

    public Long getIdTipoAllegatoRef() {
        return idTipoAllegatoRef;
    }

    public AllegatoTemplateTask idTipoAllegatoRef(Long idTipoAllegatoRef) {
        this.idTipoAllegatoRef = idTipoAllegatoRef;
        return this;
    }

    public void setIdTipoAllegatoRef(Long idTipoAllegatoRef) {
        this.idTipoAllegatoRef = idTipoAllegatoRef;
    }

    public Long getFormato() {
        return formato;
    }

    public AllegatoTemplateTask formato(Long formato) {
        this.formato = formato;
        return this;
    }

    public void setFormato(Long formato) {
        this.formato = formato;
    }

    public Long getIdFileRef() {
        return idFileRef;
    }

    public AllegatoTemplateTask idFileRef(Long idFileRef) {
        this.idFileRef = idFileRef;
        return this;
    }

    public void setIdFileRef(Long idFileRef) {
        this.idFileRef = idFileRef;
    }

    public Long getPubPriv() {
        return pubPriv;
    }

    public AllegatoTemplateTask pubPriv(Long pubPriv) {
        this.pubPriv = pubPriv;
        return this;
    }

    public void setPubPriv(Long pubPriv) {
        this.pubPriv = pubPriv;
    }

    public TemplateTask getIdTemplateTask() {
        return idTemplateTask;
    }

    public AllegatoTemplateTask idTemplateTask(TemplateTask templateTask) {
        this.idTemplateTask = templateTask;
        return this;
    }

    public void setIdTemplateTask(TemplateTask templateTask) {
        this.idTemplateTask = templateTask;
    }

    public TipoAllegato getTipoAllegato() {
        return tipoAllegato;
    }

    public AllegatoTemplateTask tipoAllegato(TipoAllegato tipoAllegato) {
        this.tipoAllegato = tipoAllegato;
        return this;
    }

    public void setTipoAllegato(TipoAllegato tipoAllegato) {
        this.tipoAllegato = tipoAllegato;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AllegatoTemplateTask)) {
            return false;
        }
        return id != null && id.equals(((AllegatoTemplateTask) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AllegatoTemplateTask{" +
            "id=" + getId() +
            ", idTemplateTaskRef=" + getIdTemplateTaskRef() +
            ", idTipoAllegatoRef=" + getIdTipoAllegatoRef() +
            ", formato=" + getFormato() +
            ", idFileRef=" + getIdFileRef() +
            ", pubPriv=" + getPubPriv() +
            "}";
    }
}
