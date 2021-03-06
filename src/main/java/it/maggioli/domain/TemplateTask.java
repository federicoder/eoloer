package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

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

    @Column(name = "ordine_esecuzione")
    private Long ordineEsecuzione;

    @Column(name = "nome")
    private Long nome;

    @Column(name = "note")
    private Long note;

    @Column(name = "pub_priv")
    private Long pubPriv;

    @Column(name = "id_template_pratica_ref")
    private Long idTemplatePraticaRef;

    @OneToMany(mappedBy = "templateTask")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TemplateTask> idTemplateTasks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "templateTasks", allowSetters = true)
    private TemplatePratica idTemplatePratica;

    @ManyToOne
    @JsonIgnoreProperties(value = "templateTasks", allowSetters = true)
    private TemplateTask idTemplateTask;

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

    public Long getOrdineEsecuzione() {
        return ordineEsecuzione;
    }

    public TemplateTask ordineEsecuzione(Long ordineEsecuzione) {
        this.ordineEsecuzione = ordineEsecuzione;
        return this;
    }

    public void setOrdineEsecuzione(Long ordineEsecuzione) {
        this.ordineEsecuzione = ordineEsecuzione;
    }

    public Long getNome() {
        return nome;
    }

    public TemplateTask nome(Long nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(Long nome) {
        this.nome = nome;
    }

    public Long getNote() {
        return note;
    }

    public TemplateTask note(Long note) {
        this.note = note;
        return this;
    }

    public void setNote(Long note) {
        this.note = note;
    }

    public Long getPubPriv() {
        return pubPriv;
    }

    public TemplateTask pubPriv(Long pubPriv) {
        this.pubPriv = pubPriv;
        return this;
    }

    public void setPubPriv(Long pubPriv) {
        this.pubPriv = pubPriv;
    }

    public Long getIdTemplatePraticaRef() {
        return idTemplatePraticaRef;
    }

    public TemplateTask idTemplatePraticaRef(Long idTemplatePraticaRef) {
        this.idTemplatePraticaRef = idTemplatePraticaRef;
        return this;
    }

    public void setIdTemplatePraticaRef(Long idTemplatePraticaRef) {
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

    public TemplatePratica getIdTemplatePratica() {
        return idTemplatePratica;
    }

    public TemplateTask idTemplatePratica(TemplatePratica templatePratica) {
        this.idTemplatePratica = templatePratica;
        return this;
    }

    public void setIdTemplatePratica(TemplatePratica templatePratica) {
        this.idTemplatePratica = templatePratica;
    }

    public TemplateTask getIdTemplateTask() {
        return idTemplateTask;
    }

    public TemplateTask idTemplateTask(TemplateTask templateTask) {
        this.idTemplateTask = templateTask;
        return this;
    }

    public void setIdTemplateTask(TemplateTask templateTask) {
        this.idTemplateTask = templateTask;
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
            ", ordineEsecuzione=" + getOrdineEsecuzione() +
            ", nome=" + getNome() +
            ", note=" + getNote() +
            ", pubPriv=" + getPubPriv() +
            ", idTemplatePraticaRef=" + getIdTemplatePraticaRef() +
            "}";
    }
}
