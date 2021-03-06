package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Invito} entity.
 */
public class InvitoDTO implements Serializable {
    
    private Long id;

    @Max(value = 8L)
    private Long idStudioProfessionaleRef;

    private String dataInvito;

    private Long idUserInvitante;

    private String nomeUserInvitante;

    private String dataScadenzaInvito;

    private String testoInvito;

    private Long idPraticaRef;

    private Long idTaskRef;

    private String luogoFisico;

    private String indicazioniLuogo;

    private String dataInizio;

    private String oraInizio;

    private String dataFine;

    private String oraFine;

    private String urlStanzaVirtuale;

    private String discriminator;


    private Long idStudioProfessionaleId;

    private Long assegnazioneTaskId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdStudioProfessionaleRef() {
        return idStudioProfessionaleRef;
    }

    public void setIdStudioProfessionaleRef(Long idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
    }

    public String getDataInvito() {
        return dataInvito;
    }

    public void setDataInvito(String dataInvito) {
        this.dataInvito = dataInvito;
    }

    public Long getIdUserInvitante() {
        return idUserInvitante;
    }

    public void setIdUserInvitante(Long idUserInvitante) {
        this.idUserInvitante = idUserInvitante;
    }

    public String getNomeUserInvitante() {
        return nomeUserInvitante;
    }

    public void setNomeUserInvitante(String nomeUserInvitante) {
        this.nomeUserInvitante = nomeUserInvitante;
    }

    public String getDataScadenzaInvito() {
        return dataScadenzaInvito;
    }

    public void setDataScadenzaInvito(String dataScadenzaInvito) {
        this.dataScadenzaInvito = dataScadenzaInvito;
    }

    public String getTestoInvito() {
        return testoInvito;
    }

    public void setTestoInvito(String testoInvito) {
        this.testoInvito = testoInvito;
    }

    public Long getIdPraticaRef() {
        return idPraticaRef;
    }

    public void setIdPraticaRef(Long idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
    }

    public Long getIdTaskRef() {
        return idTaskRef;
    }

    public void setIdTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
    }

    public String getLuogoFisico() {
        return luogoFisico;
    }

    public void setLuogoFisico(String luogoFisico) {
        this.luogoFisico = luogoFisico;
    }

    public String getIndicazioniLuogo() {
        return indicazioniLuogo;
    }

    public void setIndicazioniLuogo(String indicazioniLuogo) {
        this.indicazioniLuogo = indicazioniLuogo;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(String oraInizio) {
        this.oraInizio = oraInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public String getOraFine() {
        return oraFine;
    }

    public void setOraFine(String oraFine) {
        this.oraFine = oraFine;
    }

    public String getUrlStanzaVirtuale() {
        return urlStanzaVirtuale;
    }

    public void setUrlStanzaVirtuale(String urlStanzaVirtuale) {
        this.urlStanzaVirtuale = urlStanzaVirtuale;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public Long getIdStudioProfessionaleId() {
        return idStudioProfessionaleId;
    }

    public void setIdStudioProfessionaleId(Long studioProfessionaleId) {
        this.idStudioProfessionaleId = studioProfessionaleId;
    }

    public Long getAssegnazioneTaskId() {
        return assegnazioneTaskId;
    }

    public void setAssegnazioneTaskId(Long assegnazioneTaskId) {
        this.assegnazioneTaskId = assegnazioneTaskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvitoDTO)) {
            return false;
        }

        return id != null && id.equals(((InvitoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvitoDTO{" +
            "id=" + getId() +
            ", idStudioProfessionaleRef=" + getIdStudioProfessionaleRef() +
            ", dataInvito='" + getDataInvito() + "'" +
            ", idUserInvitante=" + getIdUserInvitante() +
            ", nomeUserInvitante='" + getNomeUserInvitante() + "'" +
            ", dataScadenzaInvito='" + getDataScadenzaInvito() + "'" +
            ", testoInvito='" + getTestoInvito() + "'" +
            ", idPraticaRef=" + getIdPraticaRef() +
            ", idTaskRef=" + getIdTaskRef() +
            ", luogoFisico='" + getLuogoFisico() + "'" +
            ", indicazioniLuogo='" + getIndicazioniLuogo() + "'" +
            ", dataInizio='" + getDataInizio() + "'" +
            ", oraInizio='" + getOraInizio() + "'" +
            ", dataFine='" + getDataFine() + "'" +
            ", oraFine='" + getOraFine() + "'" +
            ", urlStanzaVirtuale='" + getUrlStanzaVirtuale() + "'" +
            ", discriminator='" + getDiscriminator() + "'" +
            ", idStudioProfessionaleId=" + getIdStudioProfessionaleId() +
            ", assegnazioneTaskId=" + getAssegnazioneTaskId() +
            "}";
    }
}
