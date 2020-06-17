package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.ConsuntivoTask} entity.
 */
public class ConsuntivoTaskDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8L)
    private Long idTaskRef;

    private String dataInizio;

    private String dataFine;

    private Long timeLine;

    private String version;

    
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

    public Long getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(Long timeLine) {
        this.timeLine = timeLine;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConsuntivoTaskDTO)) {
            return false;
        }

        return id != null && id.equals(((ConsuntivoTaskDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConsuntivoTaskDTO{" +
            "id=" + getId() +
            ", idTaskRef=" + getIdTaskRef() +
            ", dataInizio='" + getDataInizio() + "'" +
            ", dataFine='" + getDataFine() + "'" +
            ", timeLine=" + getTimeLine() +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
