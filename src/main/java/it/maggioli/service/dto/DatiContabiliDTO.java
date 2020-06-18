package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.DatiContabili} entity.
 */
public class DatiContabiliDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long idPersonaRef;


    private Long idPersonaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPersonaRef() {
        return idPersonaRef;
    }

    public void setIdPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Long getIdPersonaId() {
        return idPersonaId;
    }

    public void setIdPersonaId(Long personaId) {
        this.idPersonaId = personaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DatiContabiliDTO)) {
            return false;
        }

        return id != null && id.equals(((DatiContabiliDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DatiContabiliDTO{" +
            "id=" + getId() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", idPersonaId=" + getIdPersonaId() +
            "}";
    }
}
