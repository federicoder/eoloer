package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Invitato} entity.
 */
public class InvitatoDTO implements Serializable {
    
    private Long id;

    @Max(value = 8)
    private Integer idInvito;

    private String tokenInvito;

    private Integer canalePrimarioInvito;

    private Integer canaleBackupInvito;

    private Integer statoInvito;

    private Integer idUserInvitato;

    private Integer idPersonaInvitata;

    private String nomeUserInvitato;

    private String dataRispostaInvito;

    private Integer ruoloInvitato;

    private Integer indInvitati;


    private Long userPersonaId;

    private Long invitoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdInvito() {
        return idInvito;
    }

    public void setIdInvito(Integer idInvito) {
        this.idInvito = idInvito;
    }

    public String getTokenInvito() {
        return tokenInvito;
    }

    public void setTokenInvito(String tokenInvito) {
        this.tokenInvito = tokenInvito;
    }

    public Integer getCanalePrimarioInvito() {
        return canalePrimarioInvito;
    }

    public void setCanalePrimarioInvito(Integer canalePrimarioInvito) {
        this.canalePrimarioInvito = canalePrimarioInvito;
    }

    public Integer getCanaleBackupInvito() {
        return canaleBackupInvito;
    }

    public void setCanaleBackupInvito(Integer canaleBackupInvito) {
        this.canaleBackupInvito = canaleBackupInvito;
    }

    public Integer getStatoInvito() {
        return statoInvito;
    }

    public void setStatoInvito(Integer statoInvito) {
        this.statoInvito = statoInvito;
    }

    public Integer getIdUserInvitato() {
        return idUserInvitato;
    }

    public void setIdUserInvitato(Integer idUserInvitato) {
        this.idUserInvitato = idUserInvitato;
    }

    public Integer getIdPersonaInvitata() {
        return idPersonaInvitata;
    }

    public void setIdPersonaInvitata(Integer idPersonaInvitata) {
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

    public Integer getRuoloInvitato() {
        return ruoloInvitato;
    }

    public void setRuoloInvitato(Integer ruoloInvitato) {
        this.ruoloInvitato = ruoloInvitato;
    }

    public Integer getIndInvitati() {
        return indInvitati;
    }

    public void setIndInvitati(Integer indInvitati) {
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
            ", idInvito=" + getIdInvito() +
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
