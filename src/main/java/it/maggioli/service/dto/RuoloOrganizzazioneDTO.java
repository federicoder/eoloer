package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.RuoloOrganizzazione} entity.
 */
public class RuoloOrganizzazioneDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idRuoloOrganizzazione;

    private Integer ruoloInOrg;


    private Long idRuoloOrganizzazioneId;

    private Long idRuoloOrganizzazioneId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdRuoloOrganizzazione() {
        return idRuoloOrganizzazione;
    }

    public void setIdRuoloOrganizzazione(Integer idRuoloOrganizzazione) {
        this.idRuoloOrganizzazione = idRuoloOrganizzazione;
    }

    public Integer getRuoloInOrg() {
        return ruoloInOrg;
    }

    public void setRuoloInOrg(Integer ruoloInOrg) {
        this.ruoloInOrg = ruoloInOrg;
    }

    public Long getIdRuoloOrganizzazioneId() {
        return idRuoloOrganizzazioneId;
    }

    public void setIdRuoloOrganizzazioneId(Long organizzazioneId) {
        this.idRuoloOrganizzazioneId = organizzazioneId;
    }

    public Long getIdRuoloOrganizzazioneId() {
        return idRuoloOrganizzazioneId;
    }

    public void setIdRuoloOrganizzazioneId(Long personaFisicaId) {
        this.idRuoloOrganizzazioneId = personaFisicaId;
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
            ", idRuoloOrganizzazione=" + getIdRuoloOrganizzazione() +
            ", ruoloInOrg=" + getRuoloInOrg() +
            ", idRuoloOrganizzazioneId=" + getIdRuoloOrganizzazioneId() +
            ", idRuoloOrganizzazioneId=" + getIdRuoloOrganizzazioneId() +
            "}";
    }
}
