package it.maggioli.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.InvitoEvento} entity.
 */
public class InvitoEventoDTO implements Serializable {
    
    private Long id;

    private Long idTaskRef;

    private String luogoFisico;

    private String indicazioniLuogo;

    private String dataInizio;

    private String oraInizio;

    private String dataFine;

    private String oraFine;

    private String urlStanzaVirtuale;


    private Long idTaskRefId;

    private Long previsioneEventoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getIdTaskRefId() {
        return idTaskRefId;
    }

    public void setIdTaskRefId(Long invitoId) {
        this.idTaskRefId = invitoId;
    }

    public Long getPrevisioneEventoId() {
        return previsioneEventoId;
    }

    public void setPrevisioneEventoId(Long previsioneEventoId) {
        this.previsioneEventoId = previsioneEventoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvitoEventoDTO)) {
            return false;
        }

        return id != null && id.equals(((InvitoEventoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvitoEventoDTO{" +
            "id=" + getId() +
            ", idTaskRef=" + getIdTaskRef() +
            ", luogoFisico='" + getLuogoFisico() + "'" +
            ", indicazioniLuogo='" + getIndicazioniLuogo() + "'" +
            ", dataInizio='" + getDataInizio() + "'" +
            ", oraInizio='" + getOraInizio() + "'" +
            ", dataFine='" + getDataFine() + "'" +
            ", oraFine='" + getOraFine() + "'" +
            ", urlStanzaVirtuale='" + getUrlStanzaVirtuale() + "'" +
            ", idTaskRefId=" + getIdTaskRefId() +
            ", previsioneEventoId=" + getPrevisioneEventoId() +
            "}";
    }
}
