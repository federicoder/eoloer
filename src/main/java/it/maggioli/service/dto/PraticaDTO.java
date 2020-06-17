package it.maggioli.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.maggioli.domain.Pratica} entity.
 */
public class PraticaDTO implements Serializable {
    
    private Long id;

    @Max(value = 8)
    private Integer idPratica;

    @Max(value = 8)
    private Integer idStudioProfessionaleRef;

    private String numero;

    private String nome;

    private String dataApertura;

    private String dataChiusura;

    private String dataScadenza;

    private Integer stato;

    private String motivoChiusura;

    @Max(value = 8)
    private Integer idTitolare;

    private Integer prcAvanzato;

    private String version;

    private String valuta;

    private Integer idTemplatePraticaRef;


    private Long idTemplatePraticaRefId;
    
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

    public Integer getIdStudioProfessionaleRef() {
        return idStudioProfessionaleRef;
    }

    public void setIdStudioProfessionaleRef(Integer idStudioProfessionaleRef) {
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

    public Integer getStato() {
        return stato;
    }

    public void setStato(Integer stato) {
        this.stato = stato;
    }

    public String getMotivoChiusura() {
        return motivoChiusura;
    }

    public void setMotivoChiusura(String motivoChiusura) {
        this.motivoChiusura = motivoChiusura;
    }

    public Integer getIdTitolare() {
        return idTitolare;
    }

    public void setIdTitolare(Integer idTitolare) {
        this.idTitolare = idTitolare;
    }

    public Integer getPrcAvanzato() {
        return prcAvanzato;
    }

    public void setPrcAvanzato(Integer prcAvanzato) {
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

    public Integer getIdTemplatePraticaRef() {
        return idTemplatePraticaRef;
    }

    public void setIdTemplatePraticaRef(Integer idTemplatePraticaRef) {
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
            ", idPratica=" + getIdPratica() +
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
