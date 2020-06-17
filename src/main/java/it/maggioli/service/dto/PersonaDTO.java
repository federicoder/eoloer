package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Persona} entity.
 */
public class PersonaDTO implements Serializable {
    
    private Long id;

    @Max(value = 8L)
    private Long idStudioProfessionaleRef;

    private String codiceFiscale;

    private String areaDiInteresse;

    private String titolo;

    private String cognome;

    private String nome;

    private String dataDiNascita;

    private String luogoDiNascita;

    private String professione;

    private Long tipo;

    private String discriminator;

    private Long idRuoloPersonaRef;

    private Long tipoRuoloUtente;


    private Long idId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdStudioProfessionaleRef() {
        return idStudioProfessionaleRef;
    }

    public void setIdStudioProfessionaleRef(Long idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getAreaDiInteresse() {
        return areaDiInteresse;
    }

    public void setAreaDiInteresse(String areaDiInteresse) {
        this.areaDiInteresse = areaDiInteresse;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getLuogoDiNascita() {
        return luogoDiNascita;
    }

    public void setLuogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
    }

    public String getProfessione() {
        return professione;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public Long getIdRuoloPersonaRef() {
        return idRuoloPersonaRef;
    }

    public void setIdRuoloPersonaRef(Long idRuoloPersonaRef) {
        this.idRuoloPersonaRef = idRuoloPersonaRef;
    }

    public Long getTipoRuoloUtente() {
        return tipoRuoloUtente;
    }

    public void setTipoRuoloUtente(Long tipoRuoloUtente) {
        this.tipoRuoloUtente = tipoRuoloUtente;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long indirizzoPersonaId) {
        this.idId = indirizzoPersonaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonaDTO)) {
            return false;
        }

        return id != null && id.equals(((PersonaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonaDTO{" +
            "id=" + getId() +
            ", idStudioProfessionaleRef=" + getIdStudioProfessionaleRef() +
            ", codiceFiscale='" + getCodiceFiscale() + "'" +
            ", areaDiInteresse='" + getAreaDiInteresse() + "'" +
            ", titolo='" + getTitolo() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", nome='" + getNome() + "'" +
            ", dataDiNascita='" + getDataDiNascita() + "'" +
            ", luogoDiNascita='" + getLuogoDiNascita() + "'" +
            ", professione='" + getProfessione() + "'" +
            ", tipo=" + getTipo() +
            ", discriminator='" + getDiscriminator() + "'" +
            ", idRuoloPersonaRef=" + getIdRuoloPersonaRef() +
            ", tipoRuoloUtente=" + getTipoRuoloUtente() +
            ", idId=" + getIdId() +
            "}";
    }
}
