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
    private Long idRuoloPersona;

    @NotNull
    private Long idPersonaRef;

    private Ruoli ruoli;


    private Long idPersonaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdRuoloPersona() {
        return idRuoloPersona;
    }

    public void setIdRuoloPersona(Long idRuoloPersona) {
        this.idRuoloPersona = idRuoloPersona;
    }

    public Long getIdPersonaRef() {
        return idPersonaRef;
    }

    public void setIdPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Ruoli getRuoli() {
        return ruoli;
    }

    public void setRuoli(Ruoli ruoli) {
        this.ruoli = ruoli;
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
            ", idPersonaId=" + getIdPersonaId() +
            "}";
    }
}
