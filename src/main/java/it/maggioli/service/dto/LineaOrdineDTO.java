package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.LineaOrdine} entity.
 */
public class LineaOrdineDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idOrdineRef;

    @NotNull
    private Integer idProdottoRef;

    private Integer quantita;

    private Integer importo;

    private String codIva;


    private Long ordineId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdOrdineRef() {
        return idOrdineRef;
    }

    public void setIdOrdineRef(Integer idOrdineRef) {
        this.idOrdineRef = idOrdineRef;
    }

    public Integer getIdProdottoRef() {
        return idProdottoRef;
    }

    public void setIdProdottoRef(Integer idProdottoRef) {
        this.idProdottoRef = idProdottoRef;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Integer getImporto() {
        return importo;
    }

    public void setImporto(Integer importo) {
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
