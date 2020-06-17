package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.AssegnazioneTask} entity.
 */
public class AssegnazioneTaskDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8)
    private Integer idAssegnazioneTask;

    @Max(value = 8)
    private Integer idTaskRef;

    @Max(value = 8)
    private Integer idUserAmmesso;

    private Integer ruolo;

    private Integer idUserConcedente;

    private Integer statoAssegnazione;


    private Long ruoloId;

    private Long userPersonaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdAssegnazioneTask() {
        return idAssegnazioneTask;
    }

    public void setIdAssegnazioneTask(Integer idAssegnazioneTask) {
        this.idAssegnazioneTask = idAssegnazioneTask;
    }

    public Integer getIdTaskRef() {
        return idTaskRef;
    }

    public void setIdTaskRef(Integer idTaskRef) {
        this.idTaskRef = idTaskRef;
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

    public Integer getStatoAssegnazione() {
        return statoAssegnazione;
    }

    public void setStatoAssegnazione(Integer statoAssegnazione) {
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
            ", idAssegnazioneTask=" + getIdAssegnazioneTask() +
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
