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
    @Max(value = 8)
    @Column(name = "id_template_task_ref", nullable = false)
    private Integer idTemplateTaskRef;

    @Column(name = "id_tipo_allegato_ref")
    private Integer idTipoAllegatoRef;

    @Column(name = "formato")
    private Integer formato;

    @Column(name = "id_file_ref")
    private Integer idFileRef;

    @Column(name = "pub_priv")
    private Integer pubPriv;

    @ManyToOne
    @JsonIgnoreProperties(value = "idTemplateTasks", allowSetters = true)
    private TemplateTask templateTask;

    @ManyToOne
    @JsonIgnoreProperties(value = "idTipoAllegatoes", allowSetters = true)
    private TipoAllegato tipoAllegato;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTemplateTaskRef() {
        return idTemplateTaskRef;
    }

    public AllegatoTemplateTask idTemplateTaskRef(Integer idTemplateTaskRef) {
        this.idTemplateTaskRef = idTemplateTaskRef;
        return this;
    }

    public void setIdTemplateTaskRef(Integer idTemplateTaskRef) {
        this.idTemplateTaskRef = idTemplateTaskRef;
    }

    public Integer getIdTipoAllegatoRef() {
        return idTipoAllegatoRef;
    }

    public AllegatoTemplateTask idTipoAllegatoRef(Integer idTipoAllegatoRef) {
        this.idTipoAllegatoRef = idTipoAllegatoRef;
        return this;
    }

    public void setIdTipoAllegatoRef(Integer idTipoAllegatoRef) {
        this.idTipoAllegatoRef = idTipoAllegatoRef;
    }

    public Integer getFormato() {
        return formato;
    }

    public AllegatoTemplateTask formato(Integer formato) {
        this.formato = formato;
        return this;
    }

    public void setFormato(Integer formato) {
        this.formato = formato;
    }

    public Integer getIdFileRef() {
        return idFileRef;
    }

    public AllegatoTemplateTask idFileRef(Integer idFileRef) {
        this.idFileRef = idFileRef;
        return this;
    }

    public void setIdFileRef(Integer idFileRef) {
        this.idFileRef = idFileRef;
    }

    public Integer getPubPriv() {
        return pubPriv;
    }

    public AllegatoTemplateTask pubPriv(Integer pubPriv) {
        this.pubPriv = pubPriv;
        return this;
    }

    public void setPubPriv(Integer pubPriv) {
        this.pubPriv = pubPriv;
    }

    public TemplateTask getTemplateTask() {
        return templateTask;
    }

    public AllegatoTemplateTask templateTask(TemplateTask templateTask) {
        this.templateTask = templateTask;
        return this;
    }

    public void setTemplateTask(TemplateTask templateTask) {
        this.templateTask = templateTask;
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
