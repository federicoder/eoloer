package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.NotePersona} entity.
 */
public class NotePersonaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idPersona;

    @NotNull
    private Integer idNote;

    private String testo;


    private Long personaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer idNote) {
        this.idNote = idNote;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
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
            ", idPersona=" + getIdPersona() +
            ", idNote=" + getIdNote() +
            ", testo='" + getTesto() + "'" +
            ", personaId=" + getPersonaId() +
            "}";
    }
}
