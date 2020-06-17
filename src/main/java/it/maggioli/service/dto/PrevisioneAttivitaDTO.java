package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.PrevisioneAttivita} entity.
 */
public class PrevisioneAttivitaDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8L)
    private Long idTaskRef;

    private String dataPianificata;

    private String oraPianificata;

    private String dataScadenza;

    private String version;


    private Long idTaskRefId;
    
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

    public Long getIdTaskRefId() {
        return idTaskRefId;
    }

    public void setIdTaskRefId(Long previsioneTaskId) {
        this.idTaskRefId = previsioneTaskId;
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
            ", idTaskRef=" + getIdTaskRef() +
            ", dataPianificata='" + getDataPianificata() + "'" +
            ", oraPianificata='" + getOraPianificata() + "'" +
            ", dataScadenza='" + getDataScadenza() + "'" +
            ", version='" + getVersion() + "'" +
            ", idTaskRefId=" + getIdTaskRefId() +
            "}";
    }
}
