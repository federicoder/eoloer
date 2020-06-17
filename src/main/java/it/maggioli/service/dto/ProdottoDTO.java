package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Prodotto} entity.
 */
public class ProdottoDTO implements Serializable {
    
    private Long id;

    private Integer nuovaLicenza;

    private Integer rinnovoLicenza;

    private Integer storage;


    private Long idId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long lineaOrdineId) {
        this.idId = lineaOrdineId;
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
            ", nuovaLicenza=" + getNuovaLicenza() +
            ", rinnovoLicenza=" + getRinnovoLicenza() +
            ", storage=" + getStorage() +
            ", idId=" + getIdId() +
            "}";
    }
}
