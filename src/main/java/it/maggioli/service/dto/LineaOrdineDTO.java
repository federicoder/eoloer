package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.LineaOrdine} entity.
 */
public class LineaOrdineDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long idOrdineRef;

    @NotNull
    private Long idProdottoRef;

    private Long quantita;

    private Long importo;

    private String codIva;


    private Long ordineId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrdineRef() {
        return idOrdineRef;
    }

    public void setIdOrdineRef(Long idOrdineRef) {
        this.idOrdineRef = idOrdineRef;
    }

    public Long getIdProdottoRef() {
        return idProdottoRef;
    }

    public void setIdProdottoRef(Long idProdottoRef) {
        this.idProdottoRef = idProdottoRef;
    }

    public Long getQuantita() {
        return quantita;
    }

    public void setQuantita(Long quantita) {
        this.quantita = quantita;
    }

    public Long getImporto() {
        return importo;
    }

    public void setImporto(Long importo) {
        this.importo = importo;
    }

    public String getCodIva() {
        return codIva;
    }

    public void setCodIva(String codIva) {
        this.codIva = codIva;
    }

    public Long getOrdineId() {
        return ordineId;
    }

    public void setOrdineId(Long ordineId) {
        this.ordineId = ordineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LineaOrdineDTO)) {
            return false;
        }

        return id != null && id.equals(((LineaOrdineDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LineaOrdineDTO{" +
            "id=" + getId() +
            ", idOrdineRef=" + getIdOrdineRef() +
            ", idProdottoRef=" + getIdProdottoRef() +
            ", quantita=" + getQuantita() +
            ", importo=" + getImporto() +
            ", codIva='" + getCodIva() + "'" +
            ", ordineId=" + getOrdineId() +
            "}";
    }
}
