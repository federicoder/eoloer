package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Ordine} entity.
 */
public class OrdineDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idStudioProfessionale;

    private Integer statoOrdine;

    private Integer totImponibile;

    private Integer totIva;

    private Integer totOrdine;


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

    public Integer getStatoOrdine() {
        return statoOrdine;
    }

    public void setStatoOrdine(Integer statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public Integer getTotImponibile() {
        return totImponibile;
    }

    public void setTotImponibile(Integer totImponibile) {
        this.totImponibile = totImponibile;
    }

    public Integer getTotIva() {
        return totIva;
    }

    public void setTotIva(Integer totIva) {
        this.totIva = totIva;
    }

    public Integer getTotOrdine() {
        return totOrdine;
    }

    public void setTotOrdine(Integer totOrdine) {
        this.totOrdine = totOrdine;
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
        if (!(o instanceof OrdineDTO)) {
            return false;
        }

        return id != null && id.equals(((OrdineDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrdineDTO{" +
            "id=" + getId() +
            ", idStudioProfessionale=" + getIdStudioProfessionale() +
            ", statoOrdine=" + getStatoOrdine() +
            ", totImponibile=" + getTotImponibile() +
            ", totIva=" + getTotIva() +
            ", totOrdine=" + getTotOrdine() +
            ", studioProfessionaleId=" + getStudioProfessionaleId() +
            "}";
    }
}
