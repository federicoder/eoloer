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
    @Column(name = "id_template_pratica", nullable = false)
    private Integer idTemplatePratica;

    @Column(name = "nome_template")
    private Integer nomeTemplate;

    @Column(name = "elenco_tag_ambito")
    private Integer elencoTagAmbito;

    @OneToMany(mappedBy = "templatePratica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TemplateTask> idTemplatePraticas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTemplatePratica() {
        return idTemplatePratica;
    }

    public TemplatePratica idTemplatePratica(Integer idTemplatePratica) {
        this.idTemplatePratica = idTemplatePratica;
        return this;
    }

    public void setIdTemplatePratica(Integer idTemplatePratica) {
        this.idTemplatePratica = idTemplatePratica;
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

    public Set<TemplateTask> getIdTemplatePraticas() {
        return idTemplatePraticas;
    }

    public TemplatePratica idTemplatePraticas(Set<TemplateTask> templateTasks) {
        this.idTemplatePraticas = templateTasks;
        return this;
    }

    public TemplatePratica addIdTemplatePratica(TemplateTask templateTask) {
        this.idTemplatePraticas.add(templateTask);
        templateTask.setTemplatePratica(this);
        return this;
    }

    public TemplatePratica removeIdTemplatePratica(TemplateTask templateTask) {
        this.idTemplatePraticas.remove(templateTask);
        templateTask.setTemplatePratica(null);
        return this;
    }

    public void setIdTemplatePraticas(Set<TemplateTask> templateTasks) {
        this.idTemplatePraticas = templateTasks;
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
            ", idTemplatePratica=" + getIdTemplatePratica() +
            ", nomeTemplate=" + getNomeTemplate() +
            ", elencoTagAmbito=" + getElencoTagAmbito() +
            "}";
    }
}
