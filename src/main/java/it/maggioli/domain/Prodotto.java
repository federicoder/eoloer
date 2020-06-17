package it.maggioli.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

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

    @NotNull
    @Column(name = "id_prodotto", nullable = false)
    private Integer idProdotto;

    @Column(name = "nuova_licenza")
    private Integer nuovaLicenza;

    @Column(name = "rinnovo_licenza")
    private Integer rinnovoLicenza;

    @Column(name = "storage")
    private Integer storage;

    @OneToOne
    @JoinColumn(unique = true)
    private LineaOrdine idProdotto;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdProdotto() {
        return idProdotto;
    }

    public Prodotto idProdotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
        return this;
    }

    public void setIdProdotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
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

    public LineaOrdine getIdProdotto() {
        return idProdotto;
    }

    public Prodotto idProdotto(LineaOrdine lineaOrdine) {
        this.idProdotto = lineaOrdine;
        return this;
    }

    public void setIdProdotto(LineaOrdine lineaOrdine) {
        this.idProdotto = lineaOrdine;
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
            ", idProdotto=" + getIdProdotto() +
            ", nuovaLicenza=" + getNuovaLicenza() +
            ", rinnovoLicenza=" + getRinnovoLicenza() +
            ", storage=" + getStorage() +
            "}";
    }
}
