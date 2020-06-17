package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import it.maggioli.domain.enumeration.Ruoli;

/**
 * A DTO for the {@link it.maggioli.domain.RappresentanzaPratica} entity.
 */
public class RappresentanzaPraticaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idRuoloPersona;

    @NotNull
    private Integer idPersonaRef;

    private Ruoli ruoli;


    private Long personaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdRuoloPersona() {
        return idRuoloPersona;
    }

    public void setIdRuoloPersona(Integer idRuoloPersona) {
        this.idRuoloPersona = idRuoloPersona;
    }

    public Integer getIdPersonaRef() {
        return idPersonaRef;
    }

    public void setIdPersonaRef(Integer idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Ruoli getRuoli() {
        return ruoli;
    }

    public void setRuoli(Ruoli ruoli) {
        this.ruoli = ruoli;
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
        if (!(o instanceof RappresentanzaPraticaDTO)) {
            return false;
        }

        return id != null && id.equals(((RappresentanzaPraticaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RappresentanzaPraticaDTO{" +
            "id=" + getId() +
            ", idRuoloPersona=" + getIdRuoloPersona() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", ruoli='" + getRuoli() + "'" +
            ", personaId=" + getPersonaId() +
            "}";
    }
}
