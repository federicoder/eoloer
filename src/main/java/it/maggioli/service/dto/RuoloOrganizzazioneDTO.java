package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.RuoloOrganizzazione} entity.
 */
public class RuoloOrganizzazioneDTO implements Serializable {
    
    private Long id;

    private Long ruoloInOrg;


    private Long idOrganizzazioneId;

    private Long idPersonaFisicaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuoloInOrg() {
        return ruoloInOrg;
    }

    public void setRuoloInOrg(Long ruoloInOrg) {
        this.ruoloInOrg = ruoloInOrg;
    }

    public Long getIdOrganizzazioneId() {
        return idOrganizzazioneId;
    }

    public void setIdOrganizzazioneId(Long organizzazioneId) {
        this.idOrganizzazioneId = organizzazioneId;
    }

    public Long getIdPersonaFisicaId() {
        return idPersonaFisicaId;
    }

    public void setIdPersonaFisicaId(Long personaFisicaId) {
        this.idPersonaFisicaId = personaFisicaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RuoloOrganizzazioneDTO)) {
            return false;
        }

        return id != null && id.equals(((RuoloOrganizzazioneDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RuoloOrganizzazioneDTO{" +
            "id=" + getId() +
            ", ruoloInOrg=" + getRuoloInOrg() +
            ", idOrganizzazioneId=" + getIdOrganizzazioneId() +
            ", idPersonaFisicaId=" + getIdPersonaFisicaId() +
            "}";
    }
}
