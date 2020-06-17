package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.TagPersona} entity.
 */
public class TagPersonaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idPersonaRef;

    private Integer tag;


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

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
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
        if (!(o instanceof TagPersonaDTO)) {
            return false;
        }

        return id != null && id.equals(((TagPersonaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagPersonaDTO{" +
            "id=" + getId() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", tag=" + getTag() +
            ", personaId=" + getPersonaId() +
            "}";
    }
}
