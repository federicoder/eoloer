package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.IndirizzoPersona} entity.
 */
public class IndirizzoPersonaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long idPersonaRef;

    private String indirizzo;

    private String comune;

    private Long cap;

    private String provincia;

    private String regione;

    private String nazione;


    private Long idPersonaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPersonaRef() {
        return idPersonaRef;
    }

    public void setIdPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
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

    public Long getCap() {
        return cap;
    }

    public void setCap(Long cap) {
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

    public Long getIdPersonaId() {
        return idPersonaId;
    }

    public void setIdPersonaId(Long personaId) {
        this.idPersonaId = personaId;
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
            ", idPersonaRef=" + getIdPersonaRef() +
            ", indirizzo='" + getIndirizzo() + "'" +
            ", comune='" + getComune() + "'" +
            ", cap=" + getCap() +
            ", provincia='" + getProvincia() + "'" +
            ", regione='" + getRegione() + "'" +
            ", nazione='" + getNazione() + "'" +
            ", idPersonaId=" + getIdPersonaId() +
            "}";
    }
}
