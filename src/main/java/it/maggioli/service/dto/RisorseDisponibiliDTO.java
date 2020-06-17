package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.RisorseDisponibili} entity.
 */
public class RisorseDisponibiliDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idStudioProfessionale;

    private String dataAttivazioneLicenza;

    private Integer nrLicenza;

    private Integer storageTotale;


    private Long studioProfessionaleId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdStudioProfessionale() {
        return idStudioProfessionale;
    }

    public void setIdStudioProfessionale(Integer idStudioProfessionale) {
        this.idStudioProfessionale = idStudioProfessionale;
    }

    public String getDataAttivazioneLicenza() {
        return dataAttivazioneLicenza;
    }

    public void setDataAttivazioneLicenza(String dataAttivazioneLicenza) {
        this.dataAttivazioneLicenza = dataAttivazioneLicenza;
    }

    public Integer getNrLicenza() {
        return nrLicenza;
    }

    public void setNrLicenza(Integer nrLicenza) {
        this.nrLicenza = nrLicenza;
    }

    public Integer getStorageTotale() {
        return storageTotale;
    }

    public void setStorageTotale(Integer storageTotale) {
        this.storageTotale = storageTotale;
    }

    public Long getStudioProfessionaleId() {
        return studioProfessionaleId;
    }

    public void setStudioProfessionaleId(Long studioProfessionaleId) {
        this.studioProfessionaleId = studioProfessionaleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RisorseDisponibiliDTO)) {
            return false;
        }

        return id != null && id.equals(((RisorseDisponibiliDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RisorseDisponibiliDTO{" +
            "id=" + getId() +
            ", idStudioProfessionale=" + getIdStudioProfessionale() +
            ", dataAttivazioneLicenza='" + getDataAttivazioneLicenza() + "'" +
            ", nrLicenza=" + getNrLicenza() +
            ", storageTotale=" + getStorageTotale() +
            ", studioProfessionaleId=" + getStudioProfessionaleId() +
            "}";
    }
}
