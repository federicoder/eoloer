package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.AssegnazioneTask} entity.
 */
public class AssegnazioneTaskDTO implements Serializable {
    
    private Long id;

    @Max(value = 8L)
    private Long idTaskRef;

    @Max(value = 8L)
    private Long idUserAmmesso;

    private Long ruolo;

    private Long idUserConcedente;

    private Long statoAssegnazione;


    private Long ruoloId;

    private Long userPersonaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTaskRef() {
        return idTaskRef;
    }

    public void setIdTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
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

    public Long getStatoAssegnazione() {
        return statoAssegnazione;
    }

    public void setStatoAssegnazione(Long statoAssegnazione) {
        this.statoAssegnazione = statoAssegnazione;
    }

    public Long getRuoloId() {
        return ruoloId;
    }

    public void setRuoloId(Long rappresentanzaPraticaId) {
        this.ruoloId = rappresentanzaPraticaId;
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
        if (!(o instanceof AssegnazioneTaskDTO)) {
            return false;
        }

        return id != null && id.equals(((AssegnazioneTaskDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AssegnazioneTaskDTO{" +
            "id=" + getId() +
            ", idTaskRef=" + getIdTaskRef() +
            ", idUserAmmesso=" + getIdUserAmmesso() +
            ", ruolo=" + getRuolo() +
            ", idUserConcedente=" + getIdUserConcedente() +
            ", statoAssegnazione=" + getStatoAssegnazione() +
            ", ruoloId=" + getRuoloId() +
            ", userPersonaId=" + getUserPersonaId() +
            "}";
    }
}
