package it.maggioli.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A PrevisioneAttivita.
 */
@Entity
@Table(name = "previsione_attivita")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "previsioneattivita")
public class PrevisioneAttivita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Max(value = 8)
    @Column(name = "id_task", nullable = false)
    private Integer idTask;

    @Column(name = "data_pianificata")
    private String dataPianificata;

    @Column(name = "ora_pianificata")
    private String oraPianificata;

    @Column(name = "data_scadenza")
    private String dataScadenza;

    @Column(name = "version")
    private String version;

    @OneToOne
    @JoinColumn(unique = true)
    private PrevisioneTask idTask;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public PrevisioneAttivita idTask(Integer idTask) {
        this.idTask = idTask;
        return this;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getDataPianificata() {
        return dataPianificata;
    }

    public PrevisioneAttivita dataPianificata(String dataPianificata) {
        this.dataPianificata = dataPianificata;
        return this;
    }

    public void setDataPianificata(String dataPianificata) {
        this.dataPianificata = dataPianificata;
    }

    public String getOraPianificata() {
        return oraPianificata;
    }

    public PrevisioneAttivita oraPianificata(String oraPianificata) {
        this.oraPianificata = oraPianificata;
        return this;
    }

    public void setOraPianificata(String oraPianificata) {
        this.oraPianificata = oraPianificata;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public PrevisioneAttivita dataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
        return this;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public String getVersion() {
        return version;
    }

    public PrevisioneAttivita version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public PrevisioneTask getIdTask() {
        return idTask;
    }

    public PrevisioneAttivita idTask(PrevisioneTask previsioneTask) {
        this.idTask = previsioneTask;
        return this;
    }

    public void setIdTask(PrevisioneTask previsioneTask) {
        this.idTask = previsioneTask;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrevisioneAttivita)) {
            return false;
        }
        return id != null && id.equals(((PrevisioneAttivita) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrevisioneAttivita{" +
            "id=" + getId() +
            ", idTask=" + getIdTask() +
            ", dataPianificata='" + getDataPianificata() + "'" +
            ", oraPianificata='" + getOraPianificata() + "'" +
            ", dataScadenza='" + getDataScadenza() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
