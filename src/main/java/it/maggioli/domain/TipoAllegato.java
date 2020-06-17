package it.maggioli.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TipoAllegato.
 */
@Entity
@Table(name = "tipo_allegato")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "tipoallegato")
public class TipoAllegato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Max(value = 8)
    @Column(name = "id_tipo_allegato")
    private Integer idTipoAllegato;

    @Column(name = "nome")
    private String nome;

    @Column(name = "formati_ammessi")
    private String formatiAmmessi;

    @Column(name = "max_dimensione_ammessa")
    private String maxDimensioneAmmessa;

    @Column(name = "version")
    private String version;

    @OneToMany(mappedBy = "tipoAllegato")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AllegatoTemplateTask> idTipoAllegatoes = new HashSet<>();

    @OneToMany(mappedBy = "tipoAllegato")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AllegatoTask> idTipoAllegatoes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTipoAllegato() {
        return idTipoAllegato;
    }

    public TipoAllegato idTipoAllegato(Integer idTipoAllegato) {
        this.idTipoAllegato = idTipoAllegato;
        return this;
    }

    public void setIdTipoAllegato(Integer idTipoAllegato) {
        this.idTipoAllegato = idTipoAllegato;
    }

    public String getNome() {
        return nome;
    }

    public TipoAllegato nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormatiAmmessi() {
        return formatiAmmessi;
    }

    public TipoAllegato formatiAmmessi(String formatiAmmessi) {
        this.formatiAmmessi = formatiAmmessi;
        return this;
    }

    public void setFormatiAmmessi(String formatiAmmessi) {
        this.formatiAmmessi = formatiAmmessi;
    }

    public String getMaxDimensioneAmmessa() {
        return maxDimensioneAmmessa;
    }

    public TipoAllegato maxDimensioneAmmessa(String maxDimensioneAmmessa) {
        this.maxDimensioneAmmessa = maxDimensioneAmmessa;
        return this;
    }

    public void setMaxDimensioneAmmessa(String maxDimensioneAmmessa) {
        this.maxDimensioneAmmessa = maxDimensioneAmmessa;
    }

    public String getVersion() {
        return version;
    }

    public TipoAllegato version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Set<AllegatoTemplateTask> getIdTipoAllegatoes() {
        return idTipoAllegatoes;
    }

    public TipoAllegato idTipoAllegatoes(Set<AllegatoTemplateTask> allegatoTemplateTasks) {
        this.idTipoAllegatoes = allegatoTemplateTasks;
        return this;
    }

    public TipoAllegato addIdTipoAllegato(AllegatoTemplateTask allegatoTemplateTask) {
        this.idTipoAllegatoes.add(allegatoTemplateTask);
        allegatoTemplateTask.setTipoAllegato(this);
        return this;
    }

    public TipoAllegato removeIdTipoAllegato(AllegatoTemplateTask allegatoTemplateTask) {
        this.idTipoAllegatoes.remove(allegatoTemplateTask);
        allegatoTemplateTask.setTipoAllegato(null);
        return this;
    }

    public void setIdTipoAllegatoes(Set<AllegatoTemplateTask> allegatoTemplateTasks) {
        this.idTipoAllegatoes = allegatoTemplateTasks;
    }

    public Set<AllegatoTask> getIdTipoAllegatoes() {
        return idTipoAllegatoes;
    }

    public TipoAllegato idTipoAllegatoes(Set<AllegatoTask> allegatoTasks) {
        this.idTipoAllegatoes = allegatoTasks;
        return this;
    }

    public TipoAllegato addIdTipoAllegato(AllegatoTask allegatoTask) {
        this.idTipoAllegatoes.add(allegatoTask);
        allegatoTask.setTipoAllegato(this);
        return this;
    }

    public TipoAllegato removeIdTipoAllegato(AllegatoTask allegatoTask) {
        this.idTipoAllegatoes.remove(allegatoTask);
        allegatoTask.setTipoAllegato(null);
        return this;
    }

    public void setIdTipoAllegatoes(Set<AllegatoTask> allegatoTasks) {
        this.idTipoAllegatoes = allegatoTasks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoAllegato)) {
            return false;
        }
        return id != null && id.equals(((TipoAllegato) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoAllegato{" +
            "id=" + getId() +
            ", idTipoAllegato=" + getIdTipoAllegato() +
            ", nome='" + getNome() + "'" +
            ", formatiAmmessi='" + getFormatiAmmessi() + "'" +
            ", maxDimensioneAmmessa='" + getMaxDimensioneAmmessa() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
