package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.InvitoPratica} entity.
 */
public class InvitoPraticaDTO implements Serializable {
    
    private Long id;

    private Long idPraticaRef;


    private Long idPraticaRefId;

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

    public Long getIdPraticaRefId() {
        return idPraticaRefId;
    }

    public void setIdPraticaRefId(Long invitoId) {
        this.idPraticaRefId = invitoId;
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
            ", idPraticaRef=" + getIdPraticaRef() +
            ", idPraticaRefId=" + getIdPraticaRefId() +
            ", idPraticaId=" + getIdPraticaId() +
            "}";
    }
}
