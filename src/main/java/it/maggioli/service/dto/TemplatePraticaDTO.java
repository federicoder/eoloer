package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.TemplatePratica} entity.
 */
public class TemplatePraticaDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8)
    private Integer idTemplatePratica;

    private Integer nomeTemplate;

    private Integer elencoTagAmbito;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTemplatePratica() {
        return idTemplatePratica;
    }

    public void setIdTemplatePratica(Integer idTemplatePratica) {
        this.idTemplatePratica = idTemplatePratica;
    }

    public Integer getNomeTemplate() {
        return nomeTemplate;
    }

    public void setNomeTemplate(Integer nomeTemplate) {
        this.nomeTemplate = nomeTemplate;
    }

    public Integer getElencoTagAmbito() {
        return elencoTagAmbito;
    }

    public void setElencoTagAmbito(Integer elencoTagAmbito) {
        this.elencoTagAmbito = elencoTagAmbito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TemplatePraticaDTO)) {
            return false;
        }

        return id != null && id.equals(((TemplatePraticaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TemplatePraticaDTO{" +
            "id=" + getId() +
            ", idTemplatePratica=" + getIdTemplatePratica() +
            ", nomeTemplate=" + getNomeTemplate() +
            ", elencoTagAmbito=" + getElencoTagAmbito() +
            "}";
    }
}
