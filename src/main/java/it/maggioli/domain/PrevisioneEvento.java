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
 * A PrevisioneEvento.
 */
@Entity
@Table(name = "previsione_evento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "previsioneevento")
public class PrevisioneEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Max(value = 8)
    @Column(name = "id_task", nullable = false)
    private Integer idTask;

    @Column(name = "data_inizio")
    private String dataInizio;

    @Column(name = "data_fine")
    private String dataFine;

    @Column(name = "luogo")
    private String luogo;

    @Column(name = "indicazioni_luogo")
    private String indicazioniLuogo;

    @Column(name = "version")
    private String version;

    @OneToOne
    @JoinColumn(unique = true)
    private PrevisioneTask idTask;

    @OneToMany(mappedBy = "previsioneEvento")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<InvitoEvento> idTasks = new HashSet<>();

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

    public PrevisioneEvento idTask(Integer idTask) {
        this.idTask = idTask;
        return this;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public PrevisioneEvento dataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
        return this;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public PrevisioneEvento dataFine(String dataFine) {
        this.dataFine = dataFine;
        return this;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public String getLuogo() {
        return luogo;
    }

    public PrevisioneEvento luogo(String luogo) {
        this.luogo = luogo;
        return this;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getIndicazioniLuogo() {
        return indicazioniLuogo;
    }

    public PrevisioneEvento indicazioniLuogo(String indicazioniLuogo) {
        this.indicazioniLuogo = indicazioniLuogo;
        return this;
    }

    public void setIndicazioniLuogo(String indicazioniLuogo) {
        this.indicazioniLuogo = indicazioniLuogo;
    }

    public String getVersion() {
        return version;
    }

    public PrevisioneEvento version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public PrevisioneTask getIdTask() {
        return idTask;
    }

    public PrevisioneEvento idTask(PrevisioneTask previsioneTask) {
        this.idTask = previsioneTask;
        return this;
    }

    public void setIdTask(PrevisioneTask previsioneTask) {
        this.idTask = previsioneTask;
    }

    public Set<InvitoEvento> getIdTasks() {
        return idTasks;
    }

    public PrevisioneEvento idTasks(Set<InvitoEvento> invitoEventos) {
        this.idTasks = invitoEventos;
        return this;
    }

    public PrevisioneEvento addIdTask(InvitoEvento invitoEvento) {
        this.idTasks.add(invitoEvento);
        invitoEvento.setPrevisioneEvento(this);
        return this;
    }

    public PrevisioneEvento removeIdTask(InvitoEvento invitoEvento) {
        this.idTasks.remove(invitoEvento);
        invitoEvento.setPrevisioneEvento(null);
        return this;
    }

    public void setIdTasks(Set<InvitoEvento> invitoEventos) {
        this.idTasks = invitoEventos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrevisioneEvento)) {
            return false;
        }
        return id != null && id.equals(((PrevisioneEvento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrevisioneEvento{" +
            "id=" + getId() +
            ", idTask=" + getIdTask() +
            ", dataInizio='" + getDataInizio() + "'" +
            ", dataFine='" + getDataFine() + "'" +
            ", luogo='" + getLuogo() + "'" +
            ", indicazioniLuogo='" + getIndicazioniLuogo() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
