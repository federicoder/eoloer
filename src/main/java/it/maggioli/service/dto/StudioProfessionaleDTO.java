package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.StudioProfessionale} entity.
 */
public class StudioProfessionaleDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long idUserAmministratore;


    private Long idPersonaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUserAmministratore() {
        return idUserAmministratore;
    }

    public void setIdUserAmministratore(Long idUserAmministratore) {
        this.idUserAmministratore = idUserAmministratore;
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
        if (!(o instanceof StudioProfessionaleDTO)) {
            return false;
        }

        return id != null && id.equals(((StudioProfessionaleDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StudioProfessionaleDTO{" +
            "id=" + getId() +
            ", idUserAmministratore=" + getIdUserAmministratore() +
            ", idPersonaId=" + getIdPersonaId() +
            "}";
    }
}
