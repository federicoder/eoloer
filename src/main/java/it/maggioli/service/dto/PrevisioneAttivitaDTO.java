package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.PrevisioneAttivita} entity.
 */
public class PrevisioneAttivitaDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8)
    private Integer idTask;

    private String dataPianificata;

    private String oraPianificata;

    private String dataScadenza;

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

    public String getDataPianificata() {
        return dataPianificata;
    }

    public void setDataPianificata(String dataPianificata) {
        this.dataPianificata = dataPianificata;
    }

    public String getOraPianificata() {
        return oraPianificata;
    }

    public void setOraPianificata(String oraPianificata) {
        this.oraPianificata = oraPianificata;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
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
        if (!(o instanceof PrevisioneAttivitaDTO)) {
            return false;
        }

        return id != null && id.equals(((PrevisioneAttivitaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrevisioneAttivitaDTO{" +
            "id=" + getId() +
            ", idTask=" + getIdTask() +
            ", dataPianificata='" + getDataPianificata() + "'" +
            ", oraPianificata='" + getOraPianificata() + "'" +
            ", dataScadenza='" + getDataScadenza() + "'" +
            ", version='" + getVersion() + "'" +
            ", idTaskId=" + getIdTaskId() +
            "}";
    }
}
