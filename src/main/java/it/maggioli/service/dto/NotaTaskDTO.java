package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.NotaTask} entity.
 */
public class NotaTaskDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8L)
    private Long idNotaTask;

    @Max(value = 8L)
    private Long idTaskRef;

    private String data;

    private String nota;

    private String version;


    private Long idTaskId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdNotaTask() {
        return idNotaTask;
    }

    public void setIdNotaTask(Long idNotaTask) {
        this.idNotaTask = idNotaTask;
    }

    public Long getIdTaskRef() {
        return idTaskRef;
    }

    public void setIdTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getIdTaskId() {
        return idTaskId;
    }

    public void setIdTaskId(Long taskId) {
        this.idTaskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotaTaskDTO)) {
            return false;
        }

        return id != null && id.equals(((NotaTaskDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotaTaskDTO{" +
            "id=" + getId() +
            ", idNotaTask=" + getIdNotaTask() +
            ", idTaskRef=" + getIdTaskRef() +
            ", data='" + getData() + "'" +
            ", nota='" + getNota() + "'" +
            ", version='" + getVersion() + "'" +
            ", idTaskId=" + getIdTaskId() +
            "}";
    }
}
