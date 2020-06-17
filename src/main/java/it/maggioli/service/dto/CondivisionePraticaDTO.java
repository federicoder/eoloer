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


    private Long ruoloId;

    private Long idUserConcedenteId;

    private Long idUserAmmessoId;

    private Long idPraticaRefId;
    
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

    public Long getRuoloId() {
        return ruoloId;
    }

    public void setRuoloId(Long rappresentanzaPraticaId) {
        this.ruoloId = rappresentanzaPraticaId;
    }

    public Long getIdUserConcedenteId() {
        return idUserConcedenteId;
    }

    public void setIdUserConcedenteId(Long personaId) {
        this.idUserConcedenteId = personaId;
    }

    public Long getIdUserAmmessoId() {
        return idUserAmmessoId;
    }

    public void setIdUserAmmessoId(Long userPersonaId) {
        this.idUserAmmessoId = userPersonaId;
    }

    public Long getIdPraticaRefId() {
        return idPraticaRefId;
    }

    public void setIdPraticaRefId(Long praticaId) {
        this.idPraticaRefId = praticaId;
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
            ", ruoloId=" + getRuoloId() +
            ", idUserConcedenteId=" + getIdUserConcedenteId() +
            ", idUserAmmessoId=" + getIdUserAmmessoId() +
            ", idPraticaRefId=" + getIdPraticaRefId() +
            "}";
    }
}
