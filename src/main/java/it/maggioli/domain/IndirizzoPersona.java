package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A IndirizzoPersona.
 */
@Entity
@Table(name = "indirizzo_persona")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "indirizzopersona")
public class IndirizzoPersona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "comune")
    private String comune;

    @Column(name = "cap")
    private Integer cap;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "regione")
    private String regione;

    @Column(name = "nazione")
    private String nazione;

    @OneToOne(mappedBy = "id")
    @JsonIgnore
    private Persona idPersona;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public IndirizzoPersona idPersona(Integer idPersona) {
        this.idPersona = idPersona;
        return this;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public IndirizzoPersona indirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
        return this;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getComune() {
        return comune;
    }

    public IndirizzoPersona comune(String comune) {
        this.comune = comune;
        return this;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public Integer getCap() {
        return cap;
    }

    public IndirizzoPersona cap(Integer cap) {
        this.cap = cap;
        return this;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return provincia;
    }

    public IndirizzoPersona provincia(String provincia) {
        this.provincia = provincia;
        return this;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegione() {
        return regione;
    }

    public IndirizzoPersona regione(String regione) {
        this.regione = regione;
        return this;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getNazione() {
        return nazione;
    }

    public IndirizzoPersona nazione(String nazione) {
        this.nazione = nazione;
        return this;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public IndirizzoPersona idPersona(Persona persona) {
        this.idPersona = persona;
        return this;
    }

    public void setIdPersona(Persona persona) {
        this.idPersona = persona;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IndirizzoPersona)) {
            return false;
        }
        return id != null && id.equals(((IndirizzoPersona) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IndirizzoPersona{" +
            "id=" + getId() +
            ", idPersona=" + getIdPersona() +
            ", indirizzo='" + getIndirizzo() + "'" +
            ", comune='" + getComune() + "'" +
            ", cap=" + getCap() +
            ", provincia='" + getProvincia() + "'" +
            ", regione='" + getRegione() + "'" +
            ", nazione='" + getNazione() + "'" +
            "}";
    }
}
