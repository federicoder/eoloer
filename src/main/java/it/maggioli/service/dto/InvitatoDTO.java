package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Invitato} entity.
 */
public class InvitatoDTO implements Serializable {
    
    private Long id;

    @Max(value = 8L)
    private Long idInvitoRef;

    private String tokenInvito;

    private Long canalePrimarioInvito;

    private Long canaleBackupInvito;

    private Long statoInvito;

    private Long idUserInvitato;

    private Long idPersonaInvitata;

    private String nomeUserInvitato;

    private String dataRispostaInvito;

    private Long ruoloInvitato;

    private Long indInvitati;


    private Long userPersonaId;

    private Long invitoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdInvitoRef() {
        return idInvitoRef;
    }

    public void setIdInvitoRef(Long idInvitoRef) {
        this.idInvitoRef = idInvitoRef;
    }

    public String getTokenInvito() {
        return tokenInvito;
    }

    public void setTokenInvito(String tokenInvito) {
        this.tokenInvito = tokenInvito;
    }

    public Long getCanalePrimarioInvito() {
        return canalePrimarioInvito;
    }

    public void setCanalePrimarioInvito(Long canalePrimarioInvito) {
        this.canalePrimarioInvito = canalePrimarioInvito;
    }

    public Long getCanaleBackupInvito() {
        return canaleBackupInvito;
    }

    public void setCanaleBackupInvito(Long canaleBackupInvito) {
        this.canaleBackupInvito = canaleBackupInvito;
    }

    public Long getStatoInvito() {
        return statoInvito;
    }

    public void setStatoInvito(Long statoInvito) {
        this.statoInvito = statoInvito;
    }

    public Long getIdUserInvitato() {
        return idUserInvitato;
    }

    public void setIdUserInvitato(Long idUserInvitato) {
        this.idUserInvitato = idUserInvitato;
    }

    public Long getIdPersonaInvitata() {
        return idPersonaInvitata;
    }

    public void setIdPersonaInvitata(Long idPersonaInvitata) {
        this.idPersonaInvitata = idPersonaInvitata;
    }

    public String getNomeUserInvitato() {
        return nomeUserInvitato;
    }

    public void setNomeUserInvitato(String nomeUserInvitato) {
        this.nomeUserInvitato = nomeUserInvitato;
    }

    public String getDataRispostaInvito() {
        return dataRispostaInvito;
    }

    public void setDataRispostaInvito(String dataRispostaInvito) {
        this.dataRispostaInvito = dataRispostaInvito;
    }

    public Long getRuoloInvitato() {
        return ruoloInvitato;
    }

    public void setRuoloInvitato(Long ruoloInvitato) {
        this.ruoloInvitato = ruoloInvitato;
    }

    public Long getIndInvitati() {
        return indInvitati;
    }

    public void setIndInvitati(Long indInvitati) {
        this.indInvitati = indInvitati;
    }

    public Long getUserPersonaId() {
        return userPersonaId;
    }

    public void setUserPersonaId(Long userPersonaId) {
        this.userPersonaId = userPersonaId;
    }

    public Long getInvitoId() {
        return invitoId;
    }

    public void setInvitoId(Long invitoId) {
        this.invitoId = invitoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvitatoDTO)) {
            return false;
        }

        return id != null && id.equals(((InvitatoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvitatoDTO{" +
            "id=" + getId() +
            ", idInvitoRef=" + getIdInvitoRef() +
            ", tokenInvito='" + getTokenInvito() + "'" +
            ", canalePrimarioInvito=" + getCanalePrimarioInvito() +
            ", canaleBackupInvito=" + getCanaleBackupInvito() +
            ", statoInvito=" + getStatoInvito() +
            ", idUserInvitato=" + getIdUserInvitato() +
            ", idPersonaInvitata=" + getIdPersonaInvitata() +
            ", nomeUserInvitato='" + getNomeUserInvitato() + "'" +
            ", dataRispostaInvito='" + getDataRispostaInvito() + "'" +
            ", ruoloInvitato=" + getRuoloInvitato() +
            ", indInvitati=" + getIndInvitati() +
            ", userPersonaId=" + getUserPersonaId() +
            ", invitoId=" + getInvitoId() +
            "}";
    }
}
