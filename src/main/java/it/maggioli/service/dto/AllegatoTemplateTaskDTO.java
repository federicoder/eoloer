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
    private Integer idTemplateTask;

    private Integer tipoAllegato;

    private Integer formato;

    private Integer idFile;

    private Integer pubPriv;


    private Long templateTaskId;

    private Long tipoAllegatoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTemplateTask() {
        return idTemplateTask;
    }

    public void setIdTemplateTask(Integer idTemplateTask) {
        this.idTemplateTask = idTemplateTask;
    }

    public Integer getTipoAllegato() {
        return tipoAllegato;
    }

    public void setTipoAllegato(Integer tipoAllegato) {
        this.tipoAllegato = tipoAllegato;
    }

    public Integer getFormato() {
        return formato;
    }

    public void setFormato(Integer formato) {
        this.formato = formato;
    }

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
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
            ", idTemplateTask=" + getIdTemplateTask() +
            ", tipoAllegato=" + getTipoAllegato() +
            ", formato=" + getFormato() +
            ", idFile=" + getIdFile() +
            ", pubPriv=" + getPubPriv() +
            ", templateTaskId=" + getTemplateTaskId() +
            ", tipoAllegatoId=" + getTipoAllegatoId() +
            "}";
    }
}
