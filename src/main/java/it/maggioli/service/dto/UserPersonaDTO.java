package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.UserPersona} entity.
 */
public class UserPersonaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idUserPersona;

    private Integer idPersonaRef;

    private Integer nomeUser;


    private Long personaFisicaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUserPersona() {
        return idUserPersona;
    }

    public void setIdUserPersona(Integer idUserPersona) {
        this.idUserPersona = idUserPersona;
    }

    public Integer getIdPersonaRef() {
        return idPersonaRef;
    }

    public void setIdPersonaRef(Integer idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Integer getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(Integer nomeUser) {
        this.nomeUser = nomeUser;
    }

    public Long getPersonaFisicaId() {
        return personaFisicaId;
    }

    public void setPersonaFisicaId(Long personaFisicaId) {
        this.personaFisicaId = personaFisicaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserPersonaDTO)) {
            return false;
        }

        return id != null && id.equals(((UserPersonaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserPersonaDTO{" +
            "id=" + getId() +
            ", idUserPersona=" + getIdUserPersona() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", nomeUser=" + getNomeUser() +
            ", personaFisicaId=" + getPersonaFisicaId() +
            "}";
    }
}
