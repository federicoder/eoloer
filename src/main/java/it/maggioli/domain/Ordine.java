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
 * A Ordine.
 */
@Entity
@Table(name = "ordine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "ordine")
public class Ordine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_studio_professionale", nullable = false)
    private Integer idStudioProfessionale;

    @Column(name = "stato_ordine")
    private Integer statoOrdine;

    @Column(name = "tot_imponibile")
    private Integer totImponibile;

    @Column(name = "tot_iva")
    private Integer totIva;

    @Column(name = "tot_ordine")
    private Integer totOrdine;

    @OneToMany(mappedBy = "ordine")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<LineaOrdine> ids = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private StudioProfessionale studioProfessionale;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdStudioProfessionale() {
        return idStudioProfessionale;
    }

    public Ordine idStudioProfessionale(Integer idStudioProfessionale) {
        this.idStudioProfessionale = idStudioProfessionale;
        return this;
    }

    public void setIdStudioProfessionale(Integer idStudioProfessionale) {
        this.idStudioProfessionale = idStudioProfessionale;
    }

    public Integer getStatoOrdine() {
        return statoOrdine;
    }

    public Ordine statoOrdine(Integer statoOrdine) {
        this.statoOrdine = statoOrdine;
        return this;
    }

    public void setStatoOrdine(Integer statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public Integer getTotImponibile() {
        return totImponibile;
    }

    public Ordine totImponibile(Integer totImponibile) {
        this.totImponibile = totImponibile;
        return this;
    }

    public void setTotImponibile(Integer totImponibile) {
        this.totImponibile = totImponibile;
    }

    public Integer getTotIva() {
        return totIva;
    }

    public Ordine totIva(Integer totIva) {
        this.totIva = totIva;
        return this;
    }

    public void setTotIva(Integer totIva) {
        this.totIva = totIva;
    }

    public Integer getTotOrdine() {
        return totOrdine;
    }

    public Ordine totOrdine(Integer totOrdine) {
        this.totOrdine = totOrdine;
        return this;
    }

    public void setTotOrdine(Integer totOrdine) {
        this.totOrdine = totOrdine;
    }

    public Set<LineaOrdine> getIds() {
        return ids;
    }

    public Ordine ids(Set<LineaOrdine> lineaOrdines) {
        this.ids = lineaOrdines;
        return this;
    }

    public Ordine addId(LineaOrdine lineaOrdine) {
        this.ids.add(lineaOrdine);
        lineaOrdine.setOrdine(this);
        return this;
    }

    public Ordine removeId(LineaOrdine lineaOrdine) {
        this.ids.remove(lineaOrdine);
        lineaOrdine.setOrdine(null);
        return this;
    }

    public void setIds(Set<LineaOrdine> lineaOrdines) {
        this.ids = lineaOrdines;
    }

    public StudioProfessionale getStudioProfessionale() {
        return studioProfessionale;
    }

    public Ordine studioProfessionale(StudioProfessionale studioProfessionale) {
        this.studioProfessionale = studioProfessionale;
        return this;
    }

    public void setStudioProfessionale(StudioProfessionale studioProfessionale) {
        this.studioProfessionale = studioProfessionale;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ordine)) {
            return false;
        }
        return id != null && id.equals(((Ordine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ordine{" +
            "id=" + getId() +
            ", idStudioProfessionale=" + getIdStudioProfessionale() +
            ", statoOrdine=" + getStatoOrdine() +
            ", totImponibile=" + getTotImponibile() +
            ", totIva=" + getTotIva() +
            ", totOrdine=" + getTotOrdine() +
            "}";
    }
}
