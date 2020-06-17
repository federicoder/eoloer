package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.PersonaFisica} entity.
 */
public class PersonaFisicaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idPersonaFisica;

    @NotNull
    private Integer idPersonaRef;

    private Integer idRuoloPersonaRef;

    private String titolo;

    private String cognome;

    private String nome;

    private String dataDiNascita;

    private String luogoDiNascita;

    private String professione;


    private Long idPersonaRefId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPersonaFisica() {
        return idPersonaFisica;
    }

    public void setIdPersonaFisica(Integer idPersonaFisica) {
        this.idPersonaFisica = idPersonaFisica;
    }

    public Integer getIdPersonaRef() {
        return idPersonaRef;
    }

    public void setIdPersonaRef(Integer idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Integer getIdRuoloPersonaRef() {
        return idRuoloPersonaRef;
    }

    public void setIdRuoloPersonaRef(Integer idRuoloPersonaRef) {
        this.idRuoloPersonaRef = idRuoloPersonaRef;
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

    public Long getIdPersonaRefId() {
        return idPersonaRefId;
    }

    public void setIdPersonaRefId(Long personaId) {
        this.idPersonaRefId = personaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonaFisicaDTO)) {
            return false;
        }

        return id != null && id.equals(((PersonaFisicaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonaFisicaDTO{" +
            "id=" + getId() +
            ", idPersonaFisica=" + getIdPersonaFisica() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", idRuoloPersonaRef=" + getIdRuoloPersonaRef() +
            ", titolo='" + getTitolo() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", nome='" + getNome() + "'" +
            ", dataDiNascita='" + getDataDiNascita() + "'" +
            ", luogoDiNascita='" + getLuogoDiNascita() + "'" +
            ", professione='" + getProfessione() + "'" +
            ", idPersonaRefId=" + getIdPersonaRefId() +
            "}";
    }
}
