package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.TelefonoPersona} entity.
 */
public class TelefonoPersonaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idPersonaRef;

    private Integer etichetta;

    private Integer valore;


    private Long personaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPersonaRef() {
        return idPersonaRef;
    }

    public void setIdPersonaRef(Integer idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Integer getEtichetta() {
        return etichetta;
    }

    public void setEtichetta(Integer etichetta) {
        this.etichetta = etichetta;
    }

    public Integer getValore() {
        return valore;
    }

    public void setValore(Integer valore) {
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
