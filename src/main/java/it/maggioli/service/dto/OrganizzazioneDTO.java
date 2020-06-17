package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Organizzazione} entity.
 */
public class OrganizzazioneDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer idOrganizzazione;

    @NotNull
    private Integer idPersona;

    private String nome;

    private String tipo;


    private Long idPersonaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdOrganizzazione() {
        return idOrganizzazione;
    }

    public void setIdOrganizzazione(Integer idOrganizzazione) {
        this.idOrganizzazione = idOrganizzazione;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getIdPersonaId() {
        return idPersonaId;
    }

    public void setIdPersonaId(Long personaId) {
        this.idPersonaId = personaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganizzazioneDTO)) {
            return false;
        }

        return id != null && id.equals(((OrganizzazioneDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganizzazioneDTO{" +
            "id=" + getId() +
            ", idOrganizzazione=" + getIdOrganizzazione() +
            ", idPersona=" + getIdPersona() +
            ", nome='" + getNome() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", idPersonaId=" + getIdPersonaId() +
            "}";
    }
}
