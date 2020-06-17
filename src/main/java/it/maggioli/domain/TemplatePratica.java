package it.maggioli.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

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

    @Column(name = "nome_template")
    private Long nomeTemplate;

    @Column(name = "elenco_tag_ambito")
    private Long elencoTagAmbito;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomeTemplate() {
        return nomeTemplate;
    }

    public TemplatePratica nomeTemplate(Long nomeTemplate) {
        this.nomeTemplate = nomeTemplate;
        return this;
    }

    public void setNomeTemplate(Long nomeTemplate) {
        this.nomeTemplate = nomeTemplate;
    }

    public Long getElencoTagAmbito() {
        return elencoTagAmbito;
    }

    public TemplatePratica elencoTagAmbito(Long elencoTagAmbito) {
        this.elencoTagAmbito = elencoTagAmbito;
        return this;
    }

    public void setElencoTagAmbito(Long elencoTagAmbito) {
        this.elencoTagAmbito = elencoTagAmbito;
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
            ", nomeTemplate=" + getNomeTemplate() +
            ", elencoTagAmbito=" + getElencoTagAmbito() +
            "}";
    }
}
