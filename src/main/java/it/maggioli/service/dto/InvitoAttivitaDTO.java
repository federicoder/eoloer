package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.InvitoAttivita} entity.
 */
public class InvitoAttivitaDTO implements Serializable {
    
    private Long id;

    private Integer idAttivita;


    private Long idAttivitaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdAttivita() {
        return idAttivita;
    }

    public void setIdAttivita(Integer idAttivita) {
        this.idAttivita = idAttivita;
    }

    public Long getIdAttivitaId() {
        return idAttivitaId;
    }

    public void setIdAttivitaId(Long invitoId) {
        this.idAttivitaId = invitoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvitoAttivitaDTO)) {
            return false;
        }

        return id != null && id.equals(((InvitoAttivitaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvitoAttivitaDTO{" +
            "id=" + getId() +
            ", idAttivita=" + getIdAttivita() +
            ", idAttivitaId=" + getIdAttivitaId() +
            "}";
    }
}
