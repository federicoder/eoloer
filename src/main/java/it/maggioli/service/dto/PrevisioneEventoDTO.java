package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.PrevisioneEvento} entity.
 */
public class PrevisioneEventoDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8)
    private Integer idTask;

    private String dataInizio;

    private String dataFine;

    private String luogo;

    private String indicazioniLuogo;

    private String version;


    private Long idTaskId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getIndicazioniLuogo() {
        return indicazioniLuogo;
    }

    public void setIndicazioniLuogo(String indicazioniLuogo) {
        this.indicazioniLuogo = indicazioniLuogo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getIdTaskId() {
        return idTaskId;
    }

    public void setIdTaskId(Long previsioneTaskId) {
        this.idTaskId = previsioneTaskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrevisioneEventoDTO)) {
            return false;
        }

        return id != null && id.equals(((PrevisioneEventoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrevisioneEventoDTO{" +
            "id=" + getId() +
            ", idTask=" + getIdTask() +
            ", dataInizio='" + getDataInizio() + "'" +
            ", dataFine='" + getDataFine() + "'" +
            ", luogo='" + getLuogo() + "'" +
            ", indicazioniLuogo='" + getIndicazioniLuogo() + "'" +
            ", version='" + getVersion() + "'" +
            ", idTaskId=" + getIdTaskId() +
            "}";
    }
}
