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
    private Integer nuovaLicenza;

    @Column(name = "rinnovo_licenza")
    private Integer rinnovoLicenza;

    @Column(name = "storage")
    private Integer storage;

    @OneToOne
    @JoinColumn(unique = true)
    private LineaOrdine id;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNuovaLicenza() {
        return nuovaLicenza;
    }

    public Prodotto nuovaLicenza(Integer nuovaLicenza) {
        this.nuovaLicenza = nuovaLicenza;
        return this;
    }

    public void setNuovaLicenza(Integer nuovaLicenza) {
        this.nuovaLicenza = nuovaLicenza;
    }

    public Integer getRinnovoLicenza() {
        return rinnovoLicenza;
    }

    public Prodotto rinnovoLicenza(Integer rinnovoLicenza) {
        this.rinnovoLicenza = rinnovoLicenza;
        return this;
    }

    public void setRinnovoLicenza(Integer rinnovoLicenza) {
        this.rinnovoLicenza = rinnovoLicenza;
    }

    public Integer getStorage() {
        return storage;
    }

    public Prodotto storage(Integer storage) {
        this.storage = storage;
        return this;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public LineaOrdine getId() {
        return id;
    }

    public Prodotto id(LineaOrdine lineaOrdine) {
        this.id = lineaOrdine;
        return this;
    }

    public void setId(LineaOrdine lineaOrdine) {
        this.id = lineaOrdine;
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
