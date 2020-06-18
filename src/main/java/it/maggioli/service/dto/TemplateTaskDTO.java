package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.TemplateTask} entity.
 */
public class TemplateTaskDTO implements Serializable {
    
    private Long id;

    private Long ordineEsecuzione;

    private Long nome;

    private Long note;

    private Long pubPriv;

    private Long idTemplatePraticaRef;


    private Long idTemplatePraticaId;

    private Long idTemplateTaskId;

    private Long templateTaskId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdineEsecuzione() {
        return ordineEsecuzione;
    }

    public void setOrdineEsecuzione(Long ordineEsecuzione) {
        this.ordineEsecuzione = ordineEsecuzione;
    }

    public Long getNome() {
        return nome;
    }

    public void setNome(Long nome) {
        this.nome = nome;
    }

    public Long getNote() {
        return note;
    }

    public void setNote(Long note) {
        this.note = note;
    }

    public Long getPubPriv() {
        return pubPriv;
    }

    public void setPubPriv(Long pubPriv) {
        this.pubPriv = pubPriv;
    }

    public Long getIdTemplatePraticaRef() {
        return idTemplatePraticaRef;
    }

    public void setIdTemplatePraticaRef(Long idTemplatePraticaRef) {
        this.idTemplatePraticaRef = idTemplatePraticaRef;
    }

    public Long getIdTemplatePraticaId() {
        return idTemplatePraticaId;
    }

    public void setIdTemplatePraticaId(Long templatePraticaId) {
        this.idTemplatePraticaId = templatePraticaId;
    }

    public Long getIdTemplateTaskId() {
        return idTemplateTaskId;
    }

    public void setIdTemplateTaskId(Long templateTaskId) {
        this.idTemplateTaskId = templateTaskId;
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
            ", ordineEsecuzione=" + getOrdineEsecuzione() +
            ", nome=" + getNome() +
            ", note=" + getNote() +
            ", pubPriv=" + getPubPriv() +
            ", idTemplatePraticaRef=" + getIdTemplatePraticaRef() +
            ", idTemplatePraticaId=" + getIdTemplatePraticaId() +
            ", idTemplateTaskId=" + getIdTemplateTaskId() +
            ", templateTaskId=" + getTemplateTaskId() +
            "}";
    }
}
