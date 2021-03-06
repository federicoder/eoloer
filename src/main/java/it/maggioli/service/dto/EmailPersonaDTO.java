package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.EmailPersona} entity.
 */
public class EmailPersonaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long idPersonaRef;

    private Long etichetta;

    private Long numero;


    private Long idPersonaId;
    
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

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
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
        if (!(o instanceof EmailPersonaDTO)) {
            return false;
        }

        return id != null && id.equals(((EmailPersonaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmailPersonaDTO{" +
            "id=" + getId() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", etichetta=" + getEtichetta() +
            ", numero=" + getNumero() +
            ", idPersonaId=" + getIdPersonaId() +
            "}";
    }
}
