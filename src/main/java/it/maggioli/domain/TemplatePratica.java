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
 * A TemplatePratica.
 */
@Entity
@Table(name = "template_pratica")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "templatepratica")
public class TemplatePratica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Max(value = 8)
    @Column(name = "id_template", nullable = false)
    private Integer idTemplate;

    @Column(name = "nome_template")
    private Integer nomeTemplate;

    @Column(name = "elenco_tag_ambito")
    private Integer elencoTagAmbito;

    @OneToMany(mappedBy = "templatePratica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TemplateTask> idTemplates = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTemplate() {
        return idTemplate;
    }

    public TemplatePratica idTemplate(Integer idTemplate) {
        this.idTemplate = idTemplate;
        return this;
    }

    public void setIdTemplate(Integer idTemplate) {
        this.idTemplate = idTemplate;
    }

    public Integer getNomeTemplate() {
        return nomeTemplate;
    }

    public TemplatePratica nomeTemplate(Integer nomeTemplate) {
        this.nomeTemplate = nomeTemplate;
        return this;
    }

    public void setNomeTemplate(Integer nomeTemplate) {
        this.nomeTemplate = nomeTemplate;
    }

    public Integer getElencoTagAmbito() {
        return elencoTagAmbito;
    }

    public TemplatePratica elencoTagAmbito(Integer elencoTagAmbito) {
        this.elencoTagAmbito = elencoTagAmbito;
        return this;
    }

    public void setElencoTagAmbito(Integer elencoTagAmbito) {
        this.elencoTagAmbito = elencoTagAmbito;
    }

    public Set<TemplateTask> getIdTemplates() {
        return idTemplates;
    }

    public TemplatePratica idTemplates(Set<TemplateTask> templateTasks) {
        this.idTemplates = templateTasks;
        return this;
    }

    public TemplatePratica addIdTemplate(TemplateTask templateTask) {
        this.idTemplates.add(templateTask);
        templateTask.setTemplatePratica(this);
        return this;
    }

    public TemplatePratica removeIdTemplate(TemplateTask templateTask) {
        this.idTemplates.remove(templateTask);
        templateTask.setTemplatePratica(null);
        return this;
    }

    public void setIdTemplates(Set<TemplateTask> templateTasks) {
        this.idTemplates = templateTasks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TemplatePratica)) {
            return false;
        }
        return id != null && id.equals(((TemplatePratica) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TemplatePratica{" +
            "id=" + getId() +
            ", idTemplate=" + getIdTemplate() +
            ", nomeTemplate=" + getNomeTemplate() +
            ", elencoTagAmbito=" + getElencoTagAmbito() +
            "}";
    }
}
