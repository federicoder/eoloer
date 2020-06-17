package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.TemplateTask} entity.
 */
public class TemplateTaskDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8)
    private Integer idTemplateTask;

    private Integer ordineEsecuzione;

    private Integer nome;

    private Integer note;

    private Integer pubPriv;

    private Integer idTemplatePraticaRef;


    private Long templatePraticaId;

    private Long templateTaskId;
    
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

    public Integer getOrdineEsecuzione() {
        return ordineEsecuzione;
    }

    public void setOrdineEsecuzione(Integer ordineEsecuzione) {
        this.ordineEsecuzione = ordineEsecuzione;
    }

    public Integer getNome() {
        return nome;
    }

    public void setNome(Integer nome) {
        this.nome = nome;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Integer getPubPriv() {
        return pubPriv;
    }

    public void setPubPriv(Integer pubPriv) {
        this.pubPriv = pubPriv;
    }

    public Integer getIdTemplatePraticaRef() {
        return idTemplatePraticaRef;
    }

    public void setIdTemplatePraticaRef(Integer idTemplatePraticaRef) {
        this.idTemplatePraticaRef = idTemplatePraticaRef;
    }

    public Long getTemplatePraticaId() {
        return templatePraticaId;
    }

    public void setTemplatePraticaId(Long templatePraticaId) {
        this.templatePraticaId = templatePraticaId;
    }

    public Long getTemplateTaskId() {
        return templateTaskId;
    }

    public void setTemplateTaskId(Long templateTaskId) {
        this.templateTaskId = templateTaskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TemplateTaskDTO)) {
            return false;
        }

        return id != null && id.equals(((TemplateTaskDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TemplateTaskDTO{" +
            "id=" + getId() +
            ", idTemplateTask=" + getIdTemplateTask() +
            ", ordineEsecuzione=" + getOrdineEsecuzione() +
            ", nome=" + getNome() +
            ", note=" + getNote() +
            ", pubPriv=" + getPubPriv() +
            ", idTemplatePraticaRef=" + getIdTemplatePraticaRef() +
            ", templatePraticaId=" + getTemplatePraticaId() +
            ", templateTaskId=" + getTemplateTaskId() +
            "}";
    }
}
