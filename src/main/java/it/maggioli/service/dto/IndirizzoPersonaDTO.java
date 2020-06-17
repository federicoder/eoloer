package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.IndirizzoPersona} entity.
 */
public class IndirizzoPersonaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idPersona;

    private String indirizzo;

    private String comune;

    private Integer cap;

    private String provincia;

    private String regione;

    private String nazione;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IndirizzoPersonaDTO)) {
            return false;
        }

        return id != null && id.equals(((IndirizzoPersonaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IndirizzoPersonaDTO{" +
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
