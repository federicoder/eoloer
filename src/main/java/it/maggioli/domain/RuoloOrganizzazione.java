package it.maggioli.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A RuoloOrganizzazione.
 */
@Entity
@Table(name = "ruolo_organizzazione")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "ruoloorganizzazione")
public class RuoloOrganizzazione implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ruolo_in_org")
    private Long ruoloInOrg;

    @OneToOne
    @JoinColumn(unique = true)
    private Organizzazione idOrganizzazione;

    @OneToOne
    @JoinColumn(unique = true)
    private PersonaFisica idPersonaFisica;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuoloInOrg() {
        return ruoloInOrg;
    }

    public RuoloOrganizzazione ruoloInOrg(Long ruoloInOrg) {
        this.ruoloInOrg = ruoloInOrg;
        return this;
    }

    public void setRuoloInOrg(Long ruoloInOrg) {
        this.ruoloInOrg = ruoloInOrg;
    }

    public Organizzazione getIdOrganizzazione() {
        return idOrganizzazione;
    }

    public RuoloOrganizzazione idOrganizzazione(Organizzazione organizzazione) {
        this.idOrganizzazione = organizzazione;
        return this;
    }

    public void setIdOrganizzazione(Organizzazione organizzazione) {
        this.idOrganizzazione = organizzazione;
    }

    public PersonaFisica getIdPersonaFisica() {
        return idPersonaFisica;
    }

    public RuoloOrganizzazione idPersonaFisica(PersonaFisica personaFisica) {
        this.idPersonaFisica = personaFisica;
        return this;
    }

    public void setIdPersonaFisica(PersonaFisica personaFisica) {
        this.idPersonaFisica = personaFisica;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RuoloOrganizzazione)) {
            return false;
        }
        return id != null && id.equals(((RuoloOrganizzazione) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RuoloOrganizzazione{" +
            "id=" + getId() +
            ", ruoloInOrg=" + getRuoloInOrg() +
            "}";
    }
}
