package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.InvitoPratica} entity.
 */
public class InvitoPraticaDTO implements Serializable {
    
    private Long id;

    private Integer idPratica;


    private Long idPraticaId;

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

    public Long getIdPraticaId() {
        return idPraticaId;
    }

    public void setIdPraticaId(Long invitoId) {
        this.idPraticaId = invitoId;
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
        if (!(o instanceof InvitoPraticaDTO)) {
            return false;
        }

        return id != null && id.equals(((InvitoPraticaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvitoPraticaDTO{" +
            "id=" + getId() +
            ", idPratica=" + getIdPratica() +
            ", idPraticaId=" + getIdPraticaId() +
            ", praticaId=" + getPraticaId() +
            "}";
    }
}
