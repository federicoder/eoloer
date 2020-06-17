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
 * A TemplateTask.
 */
@Entity
@Table(name = "template_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "templatetask")
public class TemplateTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Max(value = 8)
    @Column(name = "id_template_task", nullable = false)
    private Integer idTemplateTask;

    @Column(name = "ordine_esecuzione")
    private Integer ordineEsecuzione;

    @Column(name = "nome")
    private Integer nome;

    @Column(name = "note")
    private Integer note;

    @Column(name = "pub_priv")
    private Integer pubPriv;

    @Column(name = "id_template_pratica_ref")
    private Integer idTemplatePraticaRef;

    @OneToMany(mappedBy = "templateTask")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TemplateTask> idTemplateTasks = new HashSet<>();

    @OneToMany(mappedBy = "templateTask")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AllegatoTemplateTask> idTemplateTasks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "idTemplatePraticas", allowSetters = true)
    private TemplatePratica templatePratica;

    @ManyToOne
    @JsonIgnoreProperties(value = "idTemplateTasks", allowSetters = true)
    private TemplateTask templateTask;

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

    public TemplateTask idTemplateTask(Integer idTemplateTask) {
        this.idTemplateTask = idTemplateTask;
        return this;
    }

    public void setIdTemplateTask(Integer idTemplateTask) {
        this.idTemplateTask = idTemplateTask;
    }

    public Integer getOrdineEsecuzione() {
        return ordineEsecuzione;
    }

    public TemplateTask ordineEsecuzione(Integer ordineEsecuzione) {
        this.ordineEsecuzione = ordineEsecuzione;
        return this;
    }

    public void setOrdineEsecuzione(Integer ordineEsecuzione) {
        this.ordineEsecuzione = ordineEsecuzione;
    }

    public Integer getNome() {
        return nome;
    }

    public TemplateTask nome(Integer nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(Integer nome) {
        this.nome = nome;
    }

    public Integer getNote() {
        return note;
    }

    public TemplateTask note(Integer note) {
        this.note = note;
        return this;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Integer getPubPriv() {
        return pubPriv;
    }

    public TemplateTask pubPriv(Integer pubPriv) {
        this.pubPriv = pubPriv;
        return this;
    }

    public void setPubPriv(Integer pubPriv) {
        this.pubPriv = pubPriv;
    }

    public Integer getIdTemplatePraticaRef() {
        return idTemplatePraticaRef;
    }

    public TemplateTask idTemplatePraticaRef(Integer idTemplatePraticaRef) {
        this.idTemplatePraticaRef = idTemplatePraticaRef;
        return this;
    }

    public void setIdTemplatePraticaRef(Integer idTemplatePraticaRef) {
        this.idTemplatePraticaRef = idTemplatePraticaRef;
    }

    public Set<TemplateTask> getIdTemplateTasks() {
        return idTemplateTasks;
    }

    public TemplateTask idTemplateTasks(Set<TemplateTask> templateTasks) {
        this.idTemplateTasks = templateTasks;
        return this;
    }

    public TemplateTask addIdTemplateTask(TemplateTask templateTask) {
        this.idTemplateTasks.add(templateTask);
        templateTask.setTemplateTask(this);
        return this;
    }

    public TemplateTask removeIdTemplateTask(TemplateTask templateTask) {
        this.idTemplateTasks.remove(templateTask);
        templateTask.setTemplateTask(null);
        return this;
    }

    public void setIdTemplateTasks(Set<TemplateTask> templateTasks) {
        this.idTemplateTasks = templateTasks;
    }

    public Set<AllegatoTemplateTask> getIdTemplateTasks() {
        return idTemplateTasks;
    }

    public TemplateTask idTemplateTasks(Set<AllegatoTemplateTask> allegatoTemplateTasks) {
        this.idTemplateTasks = allegatoTemplateTasks;
        return this;
    }

    public TemplateTask addIdTemplateTask(AllegatoTemplateTask allegatoTemplateTask) {
        this.idTemplateTasks.add(allegatoTemplateTask);
        allegatoTemplateTask.setTemplateTask(this);
        return this;
    }

    public TemplateTask removeIdTemplateTask(AllegatoTemplateTask allegatoTemplateTask) {
        this.idTemplateTasks.remove(allegatoTemplateTask);
        allegatoTemplateTask.setTemplateTask(null);
        return this;
    }

    public void setIdTemplateTasks(Set<AllegatoTemplateTask> allegatoTemplateTasks) {
        this.idTemplateTasks = allegatoTemplateTasks;
    }

    public TemplatePratica getTemplatePratica() {
        return templatePratica;
    }

    public TemplateTask templatePratica(TemplatePratica templatePratica) {
        this.templatePratica = templatePratica;
        return this;
    }

    public void setTemplatePratica(TemplatePratica templatePratica) {
        this.templatePratica = templatePratica;
    }

    public TemplateTask getTemplateTask() {
        return templateTask;
    }

    public TemplateTask templateTask(TemplateTask templateTask) {
        this.templateTask = templateTask;
        return this;
    }

    public void setTemplateTask(TemplateTask templateTask) {
        this.templateTask = templateTask;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TemplateTask)) {
            return false;
        }
        return id != null && id.equals(((TemplateTask) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TemplateTask{" +
            "id=" + getId() +
            ", idTemplateTask=" + getIdTemplateTask() +
            ", ordineEsecuzione=" + getOrdineEsecuzione() +
            ", nome=" + getNome() +
            ", note=" + getNote() +
            ", pubPriv=" + getPubPriv() +
            ", idTemplatePraticaRef=" + getIdTemplatePraticaRef() +
            "}";
    }
}
