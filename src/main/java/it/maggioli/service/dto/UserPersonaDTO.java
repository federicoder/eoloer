package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.UserPersona} entity.
 */
public class UserPersonaDTO implements Serializable {
    
    private Long id;

    private Integer idPersona;

    private Integer nomeUser;


    private Long personaFisicaId;
    
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
            ", idPersona=" + getIdPersona() +
            ", nomeUser=" + getNomeUser() +
            ", personaFisicaId=" + getPersonaFisicaId() +
            "}";
    }
}
