package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.UserPersona} entity.
 */
public class UserPersonaDTO implements Serializable {
    
    private Long id;

    private Long idPersonaRef;

    private Long nomeUser;


    private Long idPersonaRefId;
    
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

    public Long getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(Long nomeUser) {
        this.nomeUser = nomeUser;
    }

    public Long getIdPersonaRefId() {
        return idPersonaRefId;
    }

    public void setIdPersonaRefId(Long personaFisicaId) {
        this.idPersonaRefId = personaFisicaId;
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
            ", idPersonaRef=" + getIdPersonaRef() +
            ", nomeUser=" + getNomeUser() +
            ", idPersonaRefId=" + getIdPersonaRefId() +
            "}";
    }
}
