package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.RisorseDisponibili} entity.
 */
public class RisorseDisponibiliDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long idStudioProfessionaleRef;

    private String dataAttivazioneLicenza;

    private Long nrLicenza;

    private Long storageTotale;


    private Long idStudioProfessionaleRefId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdStudioProfessionaleRef() {
        return idStudioProfessionaleRef;
    }

    public void setIdStudioProfessionaleRef(Long idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
    }

    public String getDataAttivazioneLicenza() {
        return dataAttivazioneLicenza;
    }

    public void setDataAttivazioneLicenza(String dataAttivazioneLicenza) {
        this.dataAttivazioneLicenza = dataAttivazioneLicenza;
    }

    public Long getNrLicenza() {
        return nrLicenza;
    }

    public void setNrLicenza(Long nrLicenza) {
        this.nrLicenza = nrLicenza;
    }

    public Long getStorageTotale() {
        return storageTotale;
    }

    public void setStorageTotale(Long storageTotale) {
        this.storageTotale = storageTotale;
    }

    public Long getIdStudioProfessionaleRefId() {
        return idStudioProfessionaleRefId;
    }

    public void setIdStudioProfessionaleRefId(Long studioProfessionaleId) {
        this.idStudioProfessionaleRefId = studioProfessionaleId;
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
            ", idStudioProfessionaleRef=" + getIdStudioProfessionaleRef() +
            ", dataAttivazioneLicenza='" + getDataAttivazioneLicenza() + "'" +
            ", nrLicenza=" + getNrLicenza() +
            ", storageTotale=" + getStorageTotale() +
            ", idStudioProfessionaleRefId=" + getIdStudioProfessionaleRefId() +
            "}";
    }
}
