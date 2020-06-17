package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A InvitoEvento.
 */
@Entity
@Table(name = "invito_evento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "invitoevento")
public class InvitoEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_task_ref")
    private Integer idTaskRef;

    @Column(name = "luogo_fisico")
    private String luogoFisico;

    @Column(name = "indicazioni_luogo")
    private String indicazioniLuogo;

    @Column(name = "data_inizio")
    private String dataInizio;

    @Column(name = "ora_inizio")
    private String oraInizio;

    @Column(name = "data_fine")
    private String dataFine;

    @Column(name = "ora_fine")
    private String oraFine;

    @Column(name = "url_stanza_virtuale")
    private String urlStanzaVirtuale;

    @OneToOne
    @JoinColumn(unique = true)
    private Invito idTaskRef;

    @ManyToOne
    @JsonIgnoreProperties(value = "idTaskRefs", allowSetters = true)
    private PrevisioneEvento previsioneEvento;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTaskRef() {
        return idTaskRef;
    }

    public InvitoEvento idTaskRef(Integer idTaskRef) {
        this.idTaskRef = idTaskRef;
        return this;
    }

    public void setIdTaskRef(Integer idTaskRef) {
        this.idTaskRef = idTaskRef;
    }

    public String getLuogoFisico() {
        return luogoFisico;
    }

    public InvitoEvento luogoFisico(String luogoFisico) {
        this.luogoFisico = luogoFisico;
        return this;
    }

    public void setLuogoFisico(String luogoFisico) {
        this.luogoFisico = luogoFisico;
    }

    public String getIndicazioniLuogo() {
        return indicazioniLuogo;
    }

    public InvitoEvento indicazioniLuogo(String indicazioniLuogo) {
        this.indicazioniLuogo = indicazioniLuogo;
        return this;
    }

    public void setIndicazioniLuogo(String indicazioniLuogo) {
        this.indicazioniLuogo = indicazioniLuogo;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public InvitoEvento dataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
        return this;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getOraInizio() {
        return oraInizio;
    }

    public InvitoEvento oraInizio(String oraInizio) {
        this.oraInizio = oraInizio;
        return this;
    }

    public void setOraInizio(String oraInizio) {
        this.oraInizio = oraInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public InvitoEvento dataFine(String dataFine) {
        this.dataFine = dataFine;
        return this;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public String getOraFine() {
        return oraFine;
    }

    public InvitoEvento oraFine(String oraFine) {
        this.oraFine = oraFine;
        return this;
    }

    public void setOraFine(String oraFine) {
        this.oraFine = oraFine;
    }

    public String getUrlStanzaVirtuale() {
        return urlStanzaVirtuale;
    }

    public InvitoEvento urlStanzaVirtuale(String urlStanzaVirtuale) {
        this.urlStanzaVirtuale = urlStanzaVirtuale;
        return this;
    }

    public void setUrlStanzaVirtuale(String urlStanzaVirtuale) {
        this.urlStanzaVirtuale = urlStanzaVirtuale;
    }

    public Invito getIdTaskRef() {
        return idTaskRef;
    }

    public InvitoEvento idTaskRef(Invito invito) {
        this.idTaskRef = invito;
        return this;
    }

    public void setIdTaskRef(Invito invito) {
        this.idTaskRef = invito;
    }

    public PrevisioneEvento getPrevisioneEvento() {
        return previsioneEvento;
    }

    public InvitoEvento previsioneEvento(PrevisioneEvento previsioneEvento) {
        this.previsioneEvento = previsioneEvento;
        return this;
    }

    public void setPrevisioneEvento(PrevisioneEvento previsioneEvento) {
        this.previsioneEvento = previsioneEvento;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvitoEvento)) {
            return false;
        }
        return id != null && id.equals(((InvitoEvento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvitoEvento{" +
            "id=" + getId() +
            ", idTaskRef=" + getIdTaskRef() +
            ", luogoFisico='" + getLuogoFisico() + "'" +
            ", indicazioniLuogo='" + getIndicazioniLuogo() + "'" +
            ", dataInizio='" + getDataInizio() + "'" +
            ", oraInizio='" + getOraInizio() + "'" +
            ", dataFine='" + getDataFine() + "'" +
            ", oraFine='" + getOraFine() + "'" +
            ", urlStanzaVirtuale='" + getUrlStanzaVirtuale() + "'" +
            "}";
    }
}
