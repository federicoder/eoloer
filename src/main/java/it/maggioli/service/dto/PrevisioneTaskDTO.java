package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.PrevisioneTask} entity.
 */
public class PrevisioneTaskDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8L)
    private Long idTaskRef;

    @Max(value = 8L)
    private Long qntOrdine;

    @Max(value = 1L)
    private Long prcPrevisione;

    private Long checkList;

    private Long idTaskMilestone;

    private String tipoTask;

    private String version;


    private Long previsioneTaskId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTaskRef() {
        return idTaskRef;
    }

    public void setIdTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
    }

    public Long getQntOrdine() {
        return qntOrdine;
    }

    public void setQntOrdine(Long qntOrdine) {
        this.qntOrdine = qntOrdine;
    }

    public Long getPrcPrevisione() {
        return prcPrevisione;
    }

    public void setPrcPrevisione(Long prcPrevisione) {
        this.prcPrevisione = prcPrevisione;
    }

    public Long getCheckList() {
        return checkList;
    }

    public void setCheckList(Long checkList) {
        this.checkList = checkList;
    }

    public Long getIdTaskMilestone() {
        return idTaskMilestone;
    }

    public void setIdTaskMilestone(Long idTaskMilestone) {
        this.idTaskMilestone = idTaskMilestone;
    }

    public String getTipoTask() {
        return tipoTask;
    }

    public void setTipoTask(String tipoTask) {
        this.tipoTask = tipoTask;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getPrevisioneTaskId() {
        return previsioneTaskId;
    }

    public void setPrevisioneTaskId(Long previsioneTaskId) {
        this.previsioneTaskId = previsioneTaskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrevisioneTaskDTO)) {
            return false;
        }

        return id != null && id.equals(((PrevisioneTaskDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrevisioneTaskDTO{" +
            "id=" + getId() +
            ", idTaskRef=" + getIdTaskRef() +
            ", qntOrdine=" + getQntOrdine() +
            ", prcPrevisione=" + getPrcPrevisione() +
            ", checkList=" + getCheckList() +
            ", idTaskMilestone=" + getIdTaskMilestone() +
            ", tipoTask='" + getTipoTask() + "'" +
            ", version='" + getVersion() + "'" +
            ", previsioneTaskId=" + getPrevisioneTaskId() +
            "}";
    }
}
