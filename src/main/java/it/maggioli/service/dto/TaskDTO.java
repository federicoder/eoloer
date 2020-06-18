package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Task} entity.
 */
public class TaskDTO implements Serializable {
    
    private Long id;

    @Max(value = 8L)
    private Long idPraticaRef;

    private String nome;

    private Long stato;

    private Long prioritario;

    private Long pubblico;

    private String version;

    private Long idCondivisionePraticaRef;

    @Max(value = 8L)
    private Long idAssegnazioneTaskRef;

    @Max(value = 8L)
    private Long idInvitoRef;


    private Long idTaskId;

    private Long idTaskId;

    private Long idPraticaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPraticaRef() {
        return idPraticaRef;
    }

    public void setIdPraticaRef(Long idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getStato() {
        return stato;
    }

    public void setStato(Long stato) {
        this.stato = stato;
    }

    public Long getPrioritario() {
        return prioritario;
    }

    public void setPrioritario(Long prioritario) {
        this.prioritario = prioritario;
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

    public Long getIdCondivisionePraticaRef() {
        return idCondivisionePraticaRef;
    }

    public void setIdCondivisionePraticaRef(Long idCondivisionePraticaRef) {
        this.idCondivisionePraticaRef = idCondivisionePraticaRef;
    }

    public Long getIdAssegnazioneTaskRef() {
        return idAssegnazioneTaskRef;
    }

    public void setIdAssegnazioneTaskRef(Long idAssegnazioneTaskRef) {
        this.idAssegnazioneTaskRef = idAssegnazioneTaskRef;
    }

    public Long getIdInvitoRef() {
        return idInvitoRef;
    }

    public void setIdInvitoRef(Long idInvitoRef) {
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

    public Long getIdPraticaId() {
        return idPraticaId;
    }

    public void setIdPraticaId(Long praticaId) {
        this.idPraticaId = praticaId;
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
            ", idPraticaId=" + getIdPraticaId() +
            "}";
    }
}
