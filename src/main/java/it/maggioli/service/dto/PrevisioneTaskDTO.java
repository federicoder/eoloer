package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.PrevisioneTask} entity.
 */
public class PrevisioneTaskDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8)
    private Integer idTask;

    @Max(value = 8)
    private Integer qntOrdine;

    @Max(value = 1)
    private Integer prcPrevisione;

    private Integer checkList;

    private Integer idTaskMilestone;

    private String tipoTask;

    private String version;


    private Long previsioneTaskId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public Integer getQntOrdine() {
        return qntOrdine;
    }

    public void setQntOrdine(Integer qntOrdine) {
        this.qntOrdine = qntOrdine;
    }

    public Integer getPrcPrevisione() {
        return prcPrevisione;
    }

    public void setPrcPrevisione(Integer prcPrevisione) {
        this.prcPrevisione = prcPrevisione;
    }

    public Integer getCheckList() {
        return checkList;
    }

    public void setCheckList(Integer checkList) {
        this.checkList = checkList;
    }

    public Integer getIdTaskMilestone() {
        return idTaskMilestone;
    }

    public void setIdTaskMilestone(Integer idTaskMilestone) {
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
            ", idTask=" + getIdTask() +
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
