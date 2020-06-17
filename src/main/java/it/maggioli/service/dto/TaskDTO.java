package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Task} entity.
 */
public class TaskDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Max(value = 8)
    private Integer idTask;

    @Max(value = 8)
    private Integer idPraticaRef;

    private String nome;

    private Integer stato;

    private Integer prioritario;

    private Integer pubblico;

    private String version;

    private Integer idCondivisionePraticaRef;

    @Max(value = 8)
    private Integer idAssegnazioneTaskRef;

    @Max(value = 8)
    private Integer idInvitoRef;


    private Long idTaskId;

    private Long idTaskId;

    private Long idTaskId;

    private Long idTaskId;

    private Long praticaId;
    
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

    public Integer getIdPraticaRef() {
        return idPraticaRef;
    }

    public void setIdPraticaRef(Integer idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getStato() {
        return stato;
    }

    public void setStato(Integer stato) {
        this.stato = stato;
    }

    public Integer getPrioritario() {
        return prioritario;
    }

    public void setPrioritario(Integer prioritario) {
        this.prioritario = prioritario;
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

    public Integer getIdCondivisionePraticaRef() {
        return idCondivisionePraticaRef;
    }

    public void setIdCondivisionePraticaRef(Integer idCondivisionePraticaRef) {
        this.idCondivisionePraticaRef = idCondivisionePraticaRef;
    }

    public Integer getIdAssegnazioneTaskRef() {
        return idAssegnazioneTaskRef;
    }

    public void setIdAssegnazioneTaskRef(Integer idAssegnazioneTaskRef) {
        this.idAssegnazioneTaskRef = idAssegnazioneTaskRef;
    }

    public Integer getIdInvitoRef() {
        return idInvitoRef;
    }

    public void setIdInvitoRef(Integer idInvitoRef) {
        this.idInvitoRef = idInvitoRef;
    }

    public Long getIdTaskId() {
        return idTaskId;
    }

    public void setIdTaskId(Long consuntivoTaskId) {
        this.idTaskId = consuntivoTaskId;
    }

    public Long getIdTaskId() {
        return idTaskId;
    }

    public void setIdTaskId(Long previsioneTaskId) {
        this.idTaskId = previsioneTaskId;
    }

    public Long getIdTaskId() {
        return idTaskId;
    }

    public void setIdTaskId(Long assegnazioneTaskId) {
        this.idTaskId = assegnazioneTaskId;
    }

    public Long getIdTaskId() {
        return idTaskId;
    }

    public void setIdTaskId(Long invitoAttivitaId) {
        this.idTaskId = invitoAttivitaId;
    }

    public Long getPraticaId() {
        return praticaId;
    }

    public void setPraticaId(Long praticaId) {
        this.praticaId = praticaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskDTO)) {
            return false;
        }

        return id != null && id.equals(((TaskDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaskDTO{" +
            "id=" + getId() +
            ", idTask=" + getIdTask() +
            ", idPraticaRef=" + getIdPraticaRef() +
            ", nome='" + getNome() + "'" +
            ", stato=" + getStato() +
            ", prioritario=" + getPrioritario() +
            ", pubblico=" + getPubblico() +
            ", version='" + getVersion() + "'" +
            ", idCondivisionePraticaRef=" + getIdCondivisionePraticaRef() +
            ", idAssegnazioneTaskRef=" + getIdAssegnazioneTaskRef() +
            ", idInvitoRef=" + getIdInvitoRef() +
            ", idTaskId=" + getIdTaskId() +
            ", idTaskId=" + getIdTaskId() +
            ", idTaskId=" + getIdTaskId() +
            ", idTaskId=" + getIdTaskId() +
            ", praticaId=" + getPraticaId() +
            "}";
    }
}
