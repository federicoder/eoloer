package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.AllegatoTask} entity.
 */
public class AllegatoTaskDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8)
    private Integer idAllegatoTask;

    @Max(value = 8)
    private Integer idTipo;

    @Max(value = 8)
    private Integer idTask;

    private Integer formato;

    private String note;

    private Integer stato;

    private Integer pubblico;

    private String version;

    @NotNull
    private Integer idAllegatoMaster;


    private Long tipoAllegatoId;

    private Long allegatoTaskId;

    private Long taskId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdAllegatoTask() {
        return idAllegatoTask;
    }

    public void setIdAllegatoTask(Integer idAllegatoTask) {
        this.idAllegatoTask = idAllegatoTask;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public Integer getFormato() {
        return formato;
    }

    public void setFormato(Integer formato) {
        this.formato = formato;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStato() {
        return stato;
    }

    public void setStato(Integer stato) {
        this.stato = stato;
    }

    public Integer getPubblico() {
        return pubblico;
    }

    public void setPubblico(Integer pubblico) {
        this.pubblico = pubblico;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getIdAllegatoMaster() {
        return idAllegatoMaster;
    }

    public void setIdAllegatoMaster(Integer idAllegatoMaster) {
        this.idAllegatoMaster = idAllegatoMaster;
    }

    public Long getTipoAllegatoId() {
        return tipoAllegatoId;
    }

    public void setTipoAllegatoId(Long tipoAllegatoId) {
        this.tipoAllegatoId = tipoAllegatoId;
    }

    public Long getAllegatoTaskId() {
        return allegatoTaskId;
    }

    public void setAllegatoTaskId(Long allegatoTaskId) {
        this.allegatoTaskId = allegatoTaskId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AllegatoTaskDTO)) {
            return false;
        }

        return id != null && id.equals(((AllegatoTaskDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AllegatoTaskDTO{" +
            "id=" + getId() +
            ", idAllegatoTask=" + getIdAllegatoTask() +
            ", idTipo=" + getIdTipo() +
            ", idTask=" + getIdTask() +
            ", formato=" + getFormato() +
            ", note='" + getNote() + "'" +
            ", stato=" + getStato() +
            ", pubblico=" + getPubblico() +
            ", version='" + getVersion() + "'" +
            ", idAllegatoMaster=" + getIdAllegatoMaster() +
            ", tipoAllegatoId=" + getTipoAllegatoId() +
            ", allegatoTaskId=" + getAllegatoTaskId() +
            ", taskId=" + getTaskId() +
            "}";
    }
}
