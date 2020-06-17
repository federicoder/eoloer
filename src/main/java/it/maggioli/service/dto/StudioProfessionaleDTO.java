package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.StudioProfessionale} entity.
 */
public class StudioProfessionaleDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idUserAmministratore;


    private Long personaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUserAmministratore() {
        return idUserAmministratore;
    }

    public void setIdUserAmministratore(Integer idUserAmministratore) {
        this.idUserAmministratore = idUserAmministratore;
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
            ", personaId=" + getPersonaId() +
            "}";
    }
}
