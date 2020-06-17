package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.TemplatePratica} entity.
 */
public class TemplatePraticaDTO implements Serializable {
    
    private Long id;

    private Long nomeTemplate;

    private Long elencoTagAmbito;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomeTemplate() {
        return nomeTemplate;
    }

    public void setNomeTemplate(Long nomeTemplate) {
        this.nomeTemplate = nomeTemplate;
    }

    public Long getElencoTagAmbito() {
        return elencoTagAmbito;
    }

    public void setElencoTagAmbito(Long elencoTagAmbito) {
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
            ", nomeTemplate=" + getNomeTemplate() +
            ", elencoTagAmbito=" + getElencoTagAmbito() +
            "}";
    }
}
