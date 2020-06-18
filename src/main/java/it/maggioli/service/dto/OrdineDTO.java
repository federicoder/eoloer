package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Ordine} entity.
 */
public class OrdineDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long idStudioProfessionaleRef;

    private Long statoOrdine;

    private Long totImponibile;

    private Long totIva;

    private Long totOrdine;


    private Long idStudioProfessionaleId;
    
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

    public Long getStatoOrdine() {
        return statoOrdine;
    }

    public void setStatoOrdine(Long statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public Long getTotImponibile() {
        return totImponibile;
    }

    public void setTotImponibile(Long totImponibile) {
        this.totImponibile = totImponibile;
    }

    public Long getTotIva() {
        return totIva;
    }

    public void setTotIva(Long totIva) {
        this.totIva = totIva;
    }

    public Long getTotOrdine() {
        return totOrdine;
    }

    public void setTotOrdine(Long totOrdine) {
        this.totOrdine = totOrdine;
    }

    public Long getIdStudioProfessionaleId() {
        return idStudioProfessionaleId;
    }

    public void setIdStudioProfessionaleId(Long studioProfessionaleId) {
        this.idStudioProfessionaleId = studioProfessionaleId;
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
            ", idStudioProfessionaleRef=" + getIdStudioProfessionaleRef() +
            ", statoOrdine=" + getStatoOrdine() +
            ", totImponibile=" + getTotImponibile() +
            ", totIva=" + getTotIva() +
            ", totOrdine=" + getTotOrdine() +
            ", idStudioProfessionaleId=" + getIdStudioProfessionaleId() +
            "}";
    }
}
