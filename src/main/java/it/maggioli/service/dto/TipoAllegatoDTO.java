package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.TipoAllegato} entity.
 */
public class TipoAllegatoDTO implements Serializable {
    
    private Long id;

    @Max(value = 8)
    private Integer idTipoAllegato;

    private String nome;

    private String formatiAmmessi;

    private String maxDimensioneAmmessa;

    private String version;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTipoAllegato() {
        return idTipoAllegato;
    }

    public void setIdTipoAllegato(Integer idTipoAllegato) {
        this.idTipoAllegato = idTipoAllegato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormatiAmmessi() {
        return formatiAmmessi;
    }

    public void setFormatiAmmessi(String formatiAmmessi) {
        this.formatiAmmessi = formatiAmmessi;
    }

    public String getMaxDimensioneAmmessa() {
        return maxDimensioneAmmessa;
    }

    public void setMaxDimensioneAmmessa(String maxDimensioneAmmessa) {
        this.maxDimensioneAmmessa = maxDimensioneAmmessa;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoAllegatoDTO)) {
            return false;
        }

        return id != null && id.equals(((TipoAllegatoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoAllegatoDTO{" +
            "id=" + getId() +
            ", idTipoAllegato=" + getIdTipoAllegato() +
            ", nome='" + getNome() + "'" +
            ", formatiAmmessi='" + getFormatiAmmessi() + "'" +
            ", maxDimensioneAmmessa='" + getMaxDimensioneAmmessa() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
