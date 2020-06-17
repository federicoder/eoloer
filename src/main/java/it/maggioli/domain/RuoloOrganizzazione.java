package it.maggioli.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

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

    @NotNull
    @Column(name = "id_ruolo_organizzazione", nullable = false)
    private Integer idRuoloOrganizzazione;

    @Column(name = "ruolo_in_org")
    private Integer ruoloInOrg;

    @OneToOne
    @JoinColumn(unique = true)
    private Organizzazione idRuoloOrganizzazione;

    @OneToOne
    @JoinColumn(unique = true)
    private PersonaFisica idRuoloOrganizzazione;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdRuoloOrganizzazione() {
        return idRuoloOrganizzazione;
    }

    public RuoloOrganizzazione idRuoloOrganizzazione(Integer idRuoloOrganizzazione) {
        this.idRuoloOrganizzazione = idRuoloOrganizzazione;
        return this;
    }

    public void setIdRuoloOrganizzazione(Integer idRuoloOrganizzazione) {
        this.idRuoloOrganizzazione = idRuoloOrganizzazione;
    }

    public Integer getRuoloInOrg() {
        return ruoloInOrg;
    }

    public RuoloOrganizzazione ruoloInOrg(Integer ruoloInOrg) {
        this.ruoloInOrg = ruoloInOrg;
        return this;
    }

    public void setRuoloInOrg(Integer ruoloInOrg) {
        this.ruoloInOrg = ruoloInOrg;
    }

    public Organizzazione getIdRuoloOrganizzazione() {
        return idRuoloOrganizzazione;
    }

    public RuoloOrganizzazione idRuoloOrganizzazione(Organizzazione organizzazione) {
        this.idRuoloOrganizzazione = organizzazione;
        return this;
    }

    public void setIdRuoloOrganizzazione(Organizzazione organizzazione) {
        this.idRuoloOrganizzazione = organizzazione;
    }

    public PersonaFisica getIdRuoloOrganizzazione() {
        return idRuoloOrganizzazione;
    }

    public RuoloOrganizzazione idRuoloOrganizzazione(PersonaFisica personaFisica) {
        this.idRuoloOrganizzazione = personaFisica;
        return this;
    }

    public void setIdRuoloOrganizzazione(PersonaFisica personaFisica) {
        this.idRuoloOrganizzazione = personaFisica;
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
            ", idRuoloOrganizzazione=" + getIdRuoloOrganizzazione() +
            ", ruoloInOrg=" + getRuoloInOrg() +
            "}";
    }
}
