package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Pratica} entity.
 */
public class PraticaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long idStudioProfessionaleRef;

    private String numero;

    private String nome;

    private String dataApertura;

    private String dataChiusura;

    private String dataScadenza;

    private Long stato;

    private String motivoChiusura;

    @Max(value = 8L)
    private Long idTitolare;

    private Long prcAvanzato;

    private String version;

    private String valuta;

    private Long idTemplatePraticaRef;


    private Long idTemplatePraticaRefId;
    
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataApertura() {
        return dataApertura;
    }

    public void setDataApertura(String dataApertura) {
        this.dataApertura = dataApertura;
    }

    public String getDataChiusura() {
        return dataChiusura;
    }

    public void setDataChiusura(String dataChiusura) {
        this.dataChiusura = dataChiusura;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Long getStato() {
        return stato;
    }

    public void setStato(Long stato) {
        this.stato = stato;
    }

    public String getMotivoChiusura() {
        return motivoChiusura;
    }

    public void setMotivoChiusura(String motivoChiusura) {
        this.motivoChiusura = motivoChiusura;
    }

    public Long getIdTitolare() {
        return idTitolare;
    }

    public void setIdTitolare(Long idTitolare) {
        this.idTitolare = idTitolare;
    }

    public Long getPrcAvanzato() {
        return prcAvanzato;
    }

    public void setPrcAvanzato(Long prcAvanzato) {
        this.prcAvanzato = prcAvanzato;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public Long getIdTemplatePraticaRef() {
        return idTemplatePraticaRef;
    }

    public void setIdTemplatePraticaRef(Long idTemplatePraticaRef) {
        this.idTemplatePraticaRef = idTemplatePraticaRef;
    }

    public Long getIdTemplatePraticaRefId() {
        return idTemplatePraticaRefId;
    }

    public void setIdTemplatePraticaRefId(Long templatePraticaId) {
        this.idTemplatePraticaRefId = templatePraticaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PraticaDTO)) {
            return false;
        }

        return id != null && id.equals(((PraticaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PraticaDTO{" +
            "id=" + getId() +
            ", idStudioProfessionaleRef=" + getIdStudioProfessionaleRef() +
            ", numero='" + getNumero() + "'" +
            ", nome='" + getNome() + "'" +
            ", dataApertura='" + getDataApertura() + "'" +
            ", dataChiusura='" + getDataChiusura() + "'" +
            ", dataScadenza='" + getDataScadenza() + "'" +
            ", stato=" + getStato() +
            ", motivoChiusura='" + getMotivoChiusura() + "'" +
            ", idTitolare=" + getIdTitolare() +
            ", prcAvanzato=" + getPrcAvanzato() +
            ", version='" + getVersion() + "'" +
            ", valuta='" + getValuta() + "'" +
            ", idTemplatePraticaRef=" + getIdTemplatePraticaRef() +
            ", idTemplatePraticaRefId=" + getIdTemplatePraticaRefId() +
            "}";
    }
}
