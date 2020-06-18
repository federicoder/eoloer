package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.InvitoAttivita} entity.
 */
public class InvitoAttivitaDTO implements Serializable {
    
    private Long id;

    private Long idTaskRef;


    private Long idTaskId;

    private Long idTaskId;
    
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

    public Long getIdTaskId() {
        return idTaskId;
    }

    public void setIdTaskId(Long taskId) {
        this.idTaskId = taskId;
    }

    public Long getIdTaskId() {
        return idTaskId;
    }

    public void setIdTaskId(Long invitoId) {
        this.idTaskId = invitoId;
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
            ", idTaskId=" + getIdTaskId() +
            ", idTaskId=" + getIdTaskId() +
            "}";
    }
}
