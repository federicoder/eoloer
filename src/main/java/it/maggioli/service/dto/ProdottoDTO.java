package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Prodotto} entity.
 */
public class ProdottoDTO implements Serializable {
    
    private Long id;

    private Long nuovaLicenza;

    private Long rinnovoLicenza;

    private Long storage;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNuovaLicenza() {
        return nuovaLicenza;
    }

    public void setNuovaLicenza(Long nuovaLicenza) {
        this.nuovaLicenza = nuovaLicenza;
    }

    public Long getRinnovoLicenza() {
        return rinnovoLicenza;
    }

    public void setRinnovoLicenza(Long rinnovoLicenza) {
        this.rinnovoLicenza = rinnovoLicenza;
    }

    public Long getStorage() {
        return storage;
    }

    public void setStorage(Long storage) {
        this.storage = storage;
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
            "}";
    }
}
