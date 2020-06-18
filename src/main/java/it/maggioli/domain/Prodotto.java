package it.maggioli.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Prodotto.
 */
@Entity
@Table(name = "prodotto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "prodotto")
public class Prodotto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nuova_licenza")
    private Long nuovaLicenza;

    @Column(name = "rinnovo_licenza")
    private Long rinnovoLicenza;

    @Column(name = "storage")
    private Long storage;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNuovaLicenza() {
        return nuovaLicenza;
    }

    public Prodotto nuovaLicenza(Long nuovaLicenza) {
        this.nuovaLicenza = nuovaLicenza;
        return this;
    }

    public void setNuovaLicenza(Long nuovaLicenza) {
        this.nuovaLicenza = nuovaLicenza;
    }

    public Long getRinnovoLicenza() {
        return rinnovoLicenza;
    }

    public Prodotto rinnovoLicenza(Long rinnovoLicenza) {
        this.rinnovoLicenza = rinnovoLicenza;
        return this;
    }

    public void setRinnovoLicenza(Long rinnovoLicenza) {
        this.rinnovoLicenza = rinnovoLicenza;
    }

    public Long getStorage() {
        return storage;
    }

    public Prodotto storage(Long storage) {
        this.storage = storage;
        return this;
    }

    public void setStorage(Long storage) {
        this.storage = storage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prodotto)) {
            return false;
        }
        return id != null && id.equals(((Prodotto) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Prodotto{" +
            "id=" + getId() +
            ", nuovaLicenza=" + getNuovaLicenza() +
            ", rinnovoLicenza=" + getRinnovoLicenza() +
            ", storage=" + getStorage() +
            "}";
    }
}
