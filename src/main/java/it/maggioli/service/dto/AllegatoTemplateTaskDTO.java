package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.AllegatoTemplateTask} entity.
 */
public class AllegatoTemplateTaskDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8)
    private Integer idTemplateTaskRef;

    private Integer idTipoAllegatoRef;

    private Integer formato;

    private Integer idFileRef;

    private Integer pubPriv;


    private Long templateTaskId;

    private Long tipoAllegatoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTemplateTaskRef() {
        return idTemplateTaskRef;
    }

    public void setIdTemplateTaskRef(Integer idTemplateTaskRef) {
        this.idTemplateTaskRef = idTemplateTaskRef;
    }

    public Integer getIdTipoAllegatoRef() {
        return idTipoAllegatoRef;
    }

    public void setIdTipoAllegatoRef(Integer idTipoAllegatoRef) {
        this.idTipoAllegatoRef = idTipoAllegatoRef;
    }

    public Integer getFormato() {
        return formato;
    }

    public void setFormato(Integer formato) {
        this.formato = formato;
    }

    public Integer getIdFileRef() {
        return idFileRef;
    }

    public void setIdFileRef(Integer idFileRef) {
        this.idFileRef = idFileRef;
    }

    public Integer getPubPriv() {
        return pubPriv;
    }

    public void setPubPriv(Integer pubPriv) {
        this.pubPriv = pubPriv;
    }

    public Long getTemplateTaskId() {
        return templateTaskId;
    }

    public void setTemplateTaskId(Long templateTaskId) {
        this.templateTaskId = templateTaskId;
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
            ", templateTaskId=" + getTemplateTaskId() +
            ", tipoAllegatoId=" + getTipoAllegatoId() +
            "}";
    }
}
