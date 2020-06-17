package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.AllegatoTemplateTask} entity.
 */
public class AllegatoTemplateTaskDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8L)
    private Long idTemplateTaskRef;

    private Long idTipoAllegatoRef;

    private Long formato;

    private Long idFileRef;

    private Long pubPriv;


    private Long idTemplateTaskRefId;

    private Long tipoAllegatoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTemplateTaskRef() {
        return idTemplateTaskRef;
    }

    public void setIdTemplateTaskRef(Long idTemplateTaskRef) {
        this.idTemplateTaskRef = idTemplateTaskRef;
    }

    public Long getIdTipoAllegatoRef() {
        return idTipoAllegatoRef;
    }

    public void setIdTipoAllegatoRef(Long idTipoAllegatoRef) {
        this.idTipoAllegatoRef = idTipoAllegatoRef;
    }

    public Long getFormato() {
        return formato;
    }

    public void setFormato(Long formato) {
        this.formato = formato;
    }

    public Long getIdFileRef() {
        return idFileRef;
    }

    public void setIdFileRef(Long idFileRef) {
        this.idFileRef = idFileRef;
    }

    public Long getPubPriv() {
        return pubPriv;
    }

    public void setPubPriv(Long pubPriv) {
        this.pubPriv = pubPriv;
    }

    public Long getIdTemplateTaskRefId() {
        return idTemplateTaskRefId;
    }

    public void setIdTemplateTaskRefId(Long templateTaskId) {
        this.idTemplateTaskRefId = templateTaskId;
    }

    public Long getTipoAllegatoId() {
        return tipoAllegatoId;
    }

    public void setTipoAllegatoId(Long tipoAllegatoId) {
        this.tipoAllegatoId = tipoAllegatoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AllegatoTemplateTaskDTO)) {
            return false;
        }

        return id != null && id.equals(((AllegatoTemplateTaskDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AllegatoTemplateTaskDTO{" +
            "id=" + getId() +
            ", idTemplateTaskRef=" + getIdTemplateTaskRef() +
            ", idTipoAllegatoRef=" + getIdTipoAllegatoRef() +
            ", formato=" + getFormato() +
            ", idFileRef=" + getIdFileRef() +
            ", pubPriv=" + getPubPriv() +
            ", idTemplateTaskRefId=" + getIdTemplateTaskRefId() +
            ", tipoAllegatoId=" + getTipoAllegatoId() +
            "}";
    }
}
