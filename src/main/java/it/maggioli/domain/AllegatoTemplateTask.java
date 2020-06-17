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
    @Column(name = "id_template_task", nullable = false)
    private Integer idTemplateTask;

    @Column(name = "tipo_allegato")
    private Integer tipoAllegato;

    @Column(name = "formato")
    private Integer formato;

    @Column(name = "id_file")
    private Integer idFile;

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

    public Integer getIdTemplateTask() {
        return idTemplateTask;
    }

    public AllegatoTemplateTask idTemplateTask(Integer idTemplateTask) {
        this.idTemplateTask = idTemplateTask;
        return this;
    }

    public void setIdTemplateTask(Integer idTemplateTask) {
        this.idTemplateTask = idTemplateTask;
    }

    public Integer getTipoAllegato() {
        return tipoAllegato;
    }

    public AllegatoTemplateTask tipoAllegato(Integer tipoAllegato) {
        this.tipoAllegato = tipoAllegato;
        return this;
    }

    public void setTipoAllegato(Integer tipoAllegato) {
        this.tipoAllegato = tipoAllegato;
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

    public Integer getIdFile() {
        return idFile;
    }

    public AllegatoTemplateTask idFile(Integer idFile) {
        this.idFile = idFile;
        return this;
    }

    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
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
            ", idTemplateTask=" + getIdTemplateTask() +
            ", tipoAllegato=" + getTipoAllegato() +
            ", formato=" + getFormato() +
            ", idFile=" + getIdFile() +
            ", pubPriv=" + getPubPriv() +
            "}";
    }
}
