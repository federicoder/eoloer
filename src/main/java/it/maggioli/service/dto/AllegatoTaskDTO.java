package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.AllegatoTask} entity.
 */
public class AllegatoTaskDTO implements Serializable {
    
    private Long id;

    @Max(value = 8L)
    private Long idTipoAllegatoRef;

    @Max(value = 8L)
    private Long idTaskRef;

    private Long formato;

    private String note;

    private Long stato;

    private Long pubblico;

    private String version;

    @NotNull
    private Long idAllegatoMaster;


    private Long idTipoAllegatoRefId;

    private Long idTaskRefId;

    private Long allegatoTaskId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTipoAllegatoRef() {
        return idTipoAllegatoRef;
    }

    public void setIdTipoAllegatoRef(Long idTipoAllegatoRef) {
        this.idTipoAllegatoRef = idTipoAllegatoRef;
    }

    public Long getIdTaskRef() {
        return idTaskRef;
    }

    public void setIdTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
    }

    public Long getFormato() {
        return formato;
    }

    public void setFormato(Long formato) {
        this.formato = formato;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getStato() {
        return stato;
    }

    public void setStato(Long stato) {
        this.stato = stato;
    }

    public Long getPubblico() {
        return pubblico;
    }

    public void setPubblico(Long pubblico) {
        this.pubblico = pubblico;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getIdAllegatoMaster() {
        return idAllegatoMaster;
    }

    public void setIdAllegatoMaster(Long idAllegatoMaster) {
        this.idAllegatoMaster = idAllegatoMaster;
    }

    public Long getIdTipoAllegatoRefId() {
        return idTipoAllegatoRefId;
    }

    public void setIdTipoAllegatoRefId(Long tipoAllegatoId) {
        this.idTipoAllegatoRefId = tipoAllegatoId;
    }

    public Long getIdTaskRefId() {
        return idTaskRefId;
    }

    public void setIdTaskRefId(Long taskId) {
        this.idTaskRefId = taskId;
    }

    public Long getAllegatoTaskId() {
        return allegatoTaskId;
    }

    public void setAllegatoTaskId(Long allegatoTaskId) {
        this.allegatoTaskId = allegatoTaskId;
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
            ", idTipoAllegatoRef=" + getIdTipoAllegatoRef() +
            ", idTaskRef=" + getIdTaskRef() +
            ", formato=" + getFormato() +
            ", note='" + getNote() + "'" +
            ", stato=" + getStato() +
            ", pubblico=" + getPubblico() +
            ", version='" + getVersion() + "'" +
            ", idAllegatoMaster=" + getIdAllegatoMaster() +
            ", idTipoAllegatoRefId=" + getIdTipoAllegatoRefId() +
            ", idTaskRefId=" + getIdTaskRefId() +
            ", allegatoTaskId=" + getAllegatoTaskId() +
            "}";
    }
}
