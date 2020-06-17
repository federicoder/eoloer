package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.RuoloOrganizzazione} entity.
 */
public class RuoloOrganizzazioneDTO implements Serializable {
    
    private Long id;

    private Integer ruoloInOrg;


    private Long idId;

    private Long idId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRuoloInOrg() {
        return ruoloInOrg;
    }

    public void setRuoloInOrg(Integer ruoloInOrg) {
        this.ruoloInOrg = ruoloInOrg;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long organizzazioneId) {
        this.idId = organizzazioneId;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long personaFisicaId) {
        this.idId = personaFisicaId;
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
            ", idId=" + getIdId() +
            ", idId=" + getIdId() +
            "}";
    }
}
