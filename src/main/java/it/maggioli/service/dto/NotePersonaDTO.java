package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.NotePersona} entity.
 */
public class NotePersonaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long idPersonaRef;

    private String testo;


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

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
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
        if (!(o instanceof NotePersonaDTO)) {
            return false;
        }

        return id != null && id.equals(((NotePersonaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotePersonaDTO{" +
            "id=" + getId() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", testo='" + getTesto() + "'" +
            ", idPersonaId=" + getIdPersonaId() +
            "}";
    }
}
