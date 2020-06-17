package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.CondivisionePratica} entity.
 */
public class CondivisionePraticaDTO implements Serializable {
    
    private Long id;

    @Max(value = 8)
    private Integer idUserAmmesso;

    private Integer ruolo;

    private Integer idUserConcedente;

    private Integer statoInvito;

    private Integer idPratica;


    private Long ruoloId;

    private Long idUserConcedenteId;

    private Long praticaId;

    private Long userPersonaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUserAmmesso() {
        return idUserAmmesso;
    }

    public void setIdUserAmmesso(Integer idUserAmmesso) {
        this.idUserAmmesso = idUserAmmesso;
    }

    public Integer getRuolo() {
        return ruolo;
    }

    public void setRuolo(Integer ruolo) {
        this.ruolo = ruolo;
    }

    public Integer getIdUserConcedente() {
        return idUserConcedente;
    }

    public void setIdUserConcedente(Integer idUserConcedente) {
        this.idUserConcedente = idUserConcedente;
    }

    public Integer getStatoInvito() {
        return statoInvito;
    }

    public void setStatoInvito(Integer statoInvito) {
        this.statoInvito = statoInvito;
    }

    public Integer getIdPratica() {
        return idPratica;
    }

    public void setIdPratica(Integer idPratica) {
        this.idPratica = idPratica;
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

    public Long getPraticaId() {
        return praticaId;
    }

    public void setPraticaId(Long praticaId) {
        this.praticaId = praticaId;
    }

    public Long getUserPersonaId() {
        return userPersonaId;
    }

    public void setUserPersonaId(Long userPersonaId) {
        this.userPersonaId = userPersonaId;
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
            ", idPratica=" + getIdPratica() +
            ", ruoloId=" + getRuoloId() +
            ", idUserConcedenteId=" + getIdUserConcedenteId() +
            ", praticaId=" + getPraticaId() +
            ", userPersonaId=" + getUserPersonaId() +
            "}";
    }
}
