package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.CondivisionePratica} entity.
 */
public class CondivisionePraticaDTO implements Serializable {
    
    private Long id;

    @Max(value = 8L)
    private Long idUserAmmesso;

    private Long ruolo;

    private Long idUserConcedente;

    private Long statoInvito;

    private Long idPraticaRef;


    private Long idRuoloPersonaId;

    private Long idPersonaId;

    private Long idUserPersonaId;

    private Long idPraticaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUserAmmesso() {
        return idUserAmmesso;
    }

    public void setIdUserAmmesso(Long idUserAmmesso) {
        this.idUserAmmesso = idUserAmmesso;
    }

    public Long getRuolo() {
        return ruolo;
    }

    public void setRuolo(Long ruolo) {
        this.ruolo = ruolo;
    }

    public Long getIdUserConcedente() {
        return idUserConcedente;
    }

    public void setIdUserConcedente(Long idUserConcedente) {
        this.idUserConcedente = idUserConcedente;
    }

    public Long getStatoInvito() {
        return statoInvito;
    }

    public void setStatoInvito(Long statoInvito) {
        this.statoInvito = statoInvito;
    }

    public Long getIdPraticaRef() {
        return idPraticaRef;
    }

    public void setIdPraticaRef(Long idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
    }

    public Long getIdRuoloPersonaId() {
        return idRuoloPersonaId;
    }

    public void setIdRuoloPersonaId(Long rappresentanzaPraticaId) {
        this.idRuoloPersonaId = rappresentanzaPraticaId;
    }

    public Long getIdPersonaId() {
        return idPersonaId;
    }

    public void setIdPersonaId(Long personaId) {
        this.idPersonaId = personaId;
    }

    public Long getIdUserPersonaId() {
        return idUserPersonaId;
    }

    public void setIdUserPersonaId(Long userPersonaId) {
        this.idUserPersonaId = userPersonaId;
    }

    public Long getIdPraticaId() {
        return idPraticaId;
    }

    public void setIdPraticaId(Long praticaId) {
        this.idPraticaId = praticaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CondivisionePraticaDTO)) {
            return false;
        }

        return id != null && id.equals(((CondivisionePraticaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CondivisionePraticaDTO{" +
            "id=" + getId() +
            ", idUserAmmesso=" + getIdUserAmmesso() +
            ", ruolo=" + getRuolo() +
            ", idUserConcedente=" + getIdUserConcedente() +
            ", statoInvito=" + getStatoInvito() +
            ", idPraticaRef=" + getIdPraticaRef() +
            ", idRuoloPersonaId=" + getIdRuoloPersonaId() +
            ", idPersonaId=" + getIdPersonaId() +
            ", idUserPersonaId=" + getIdUserPersonaId() +
            ", idPraticaId=" + getIdPraticaId() +
            "}";
    }
}
