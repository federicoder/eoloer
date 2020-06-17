package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Prodotto} entity.
 */
public class ProdottoDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idProdotto;

    private Integer nuovaLicenza;

    private Integer rinnovoLicenza;

    private Integer storage;


    private Long idProdottoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
    }

    public Integer getNuovaLicenza() {
        return nuovaLicenza;
    }

    public void setNuovaLicenza(Integer nuovaLicenza) {
        this.nuovaLicenza = nuovaLicenza;
    }

    public Integer getRinnovoLicenza() {
        return rinnovoLicenza;
    }

    public void setRinnovoLicenza(Integer rinnovoLicenza) {
        this.rinnovoLicenza = rinnovoLicenza;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public Long getIdProdottoId() {
        return idProdottoId;
    }

    public void setIdProdottoId(Long lineaOrdineId) {
        this.idProdottoId = lineaOrdineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProdottoDTO)) {
            return false;
        }

        return id != null && id.equals(((ProdottoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProdottoDTO{" +
            "id=" + getId() +
            ", idProdotto=" + getIdProdotto() +
            ", nuovaLicenza=" + getNuovaLicenza() +
            ", rinnovoLicenza=" + getRinnovoLicenza() +
            ", storage=" + getStorage() +
            ", idProdottoId=" + getIdProdottoId() +
            "}";
    }
}
