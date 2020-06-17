package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.NotaPratica} entity.
 */
public class NotaPraticaDTO implements Serializable {
    
    private Long id;

    @Max(value = 8L)
    private Long idPraticaRef;

    private String data;

    private String nota;

    private String version;


    private Long idPraticaRefId;
    
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

    public Long getIdPraticaRefId() {
        return idPraticaRefId;
    }

    public void setIdPraticaRefId(Long praticaId) {
        this.idPraticaRefId = praticaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotaPraticaDTO)) {
            return false;
        }

        return id != null && id.equals(((NotaPraticaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotaPraticaDTO{" +
            "id=" + getId() +
            ", idPraticaRef=" + getIdPraticaRef() +
            ", data='" + getData() + "'" +
            ", nota='" + getNota() + "'" +
            ", version='" + getVersion() + "'" +
            ", idPraticaRefId=" + getIdPraticaRefId() +
            "}";
    }
}
