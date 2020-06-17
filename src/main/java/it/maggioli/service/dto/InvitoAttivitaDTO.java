package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.InvitoAttivita} entity.
 */
public class InvitoAttivitaDTO implements Serializable {
    
    private Long id;

    private Long idTaskRef;


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

    public Long getIdTaskRefId() {
        return idTaskRefId;
    }

    public void setIdTaskRefId(Long invitoId) {
        this.idTaskRefId = invitoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvitoAttivitaDTO)) {
            return false;
        }

        return id != null && id.equals(((InvitoAttivitaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvitoAttivitaDTO{" +
            "id=" + getId() +
            ", idTaskRef=" + getIdTaskRef() +
            ", idTaskRefId=" + getIdTaskRefId() +
            "}";
    }
}
