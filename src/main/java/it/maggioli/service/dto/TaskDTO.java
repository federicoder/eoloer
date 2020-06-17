package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Task} entity.
 */
public class TaskDTO implements Serializable {
    
    private Long id;

    @Max(value = 8)
    private Integer idPratica;

    private String nome;

    private Integer stato;

    private Integer prioritario;

    private Integer pubblico;

    private String version;

    private Integer condivisionePraticaId;

    @Max(value = 8)
    private Integer assegnazioneTaskId;

    @Max(value = 8)
    private Integer invitoId;


    private Long idId;

    private Long idId;

    private Long idId;

    private Long idId;

    private Long praticaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPratica() {
        return idPratica;
    }

    public void setIdPratica(Integer idPratica) {
        this.idPratica = idPratica;
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

    public Integer getCondivisionePraticaId() {
        return condivisionePraticaId;
    }

    public void setCondivisionePraticaId(Integer condivisionePraticaId) {
        this.condivisionePraticaId = condivisionePraticaId;
    }

    public Integer getAssegnazioneTaskId() {
        return assegnazioneTaskId;
    }

    public void setAssegnazioneTaskId(Integer assegnazioneTaskId) {
        this.assegnazioneTaskId = assegnazioneTaskId;
    }

    public Integer getInvitoId() {
        return invitoId;
    }

    public void setInvitoId(Integer invitoId) {
        this.invitoId = invitoId;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long consuntivoTaskId) {
        this.idId = consuntivoTaskId;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long previsioneTaskId) {
        this.idId = previsioneTaskId;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long assegnazioneTaskId) {
        this.idId = assegnazioneTaskId;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long invitoAttivitaId) {
        this.idId = invitoAttivitaId;
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
            ", idPratica=" + getIdPratica() +
            ", nome='" + getNome() + "'" +
            ", stato=" + getStato() +
            ", prioritario=" + getPrioritario() +
            ", pubblico=" + getPubblico() +
            ", version='" + getVersion() + "'" +
            ", condivisionePraticaId=" + getCondivisionePraticaId() +
            ", assegnazioneTaskId=" + getAssegnazioneTaskId() +
            ", invitoId=" + getInvitoId() +
            ", idId=" + getIdId() +
            ", idId=" + getIdId() +
            ", idId=" + getIdId() +
            ", idId=" + getIdId() +
            ", praticaId=" + getPraticaId() +
            "}";
    }
}
