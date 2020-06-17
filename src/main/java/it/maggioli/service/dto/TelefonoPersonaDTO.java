package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.TelefonoPersona} entity.
 */
public class TelefonoPersonaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long idPersonaRef;

    private Long etichetta;

    private Long valore;


    private Long personaId;
    
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

    public Long getEtichetta() {
        return etichetta;
    }

    public void setEtichetta(Long etichetta) {
        this.etichetta = etichetta;
    }

    public Long getValore() {
        return valore;
    }

    public void setValore(Long valore) {
        this.valore = valore;
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
        if (!(o instanceof TelefonoPersonaDTO)) {
            return false;
        }

        return id != null && id.equals(((TelefonoPersonaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TelefonoPersonaDTO{" +
            "id=" + getId() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", etichetta=" + getEtichetta() +
            ", valore=" + getValore() +
            ", personaId=" + getPersonaId() +
            "}";
    }
}
