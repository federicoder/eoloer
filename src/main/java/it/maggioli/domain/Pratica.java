package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Pratica.
 */
@Entity
@Table(name = "pratica")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "pratica")
public class Pratica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Max(value = 8)
    @Column(name = "id_pratica")
    private Integer idPratica;

    @Max(value = 8)
    @Column(name = "id_studio_professionale_ref")
    private Integer idStudioProfessionaleRef;

    @Column(name = "numero")
    private String numero;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_apertura")
    private String dataApertura;

    @Column(name = "data_chiusura")
    private String dataChiusura;

    @Column(name = "data_scadenza")
    private String dataScadenza;

    @Column(name = "stato")
    private Integer stato;

    @Column(name = "motivo_chiusura")
    private String motivoChiusura;

    @Max(value = 8)
    @Column(name = "id_titolare")
    private Integer idTitolare;

    @Column(name = "prc_avanzato")
    private Integer prcAvanzato;

    @Column(name = "version")
    private String version;

    @Column(name = "valuta")
    private String valuta;

    @Column(name = "id_template_pratica_ref")
    private Integer idTemplatePraticaRef;

    @OneToMany(mappedBy = "pratica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Task> idPraticas = new HashSet<>();

    @OneToMany(mappedBy = "pratica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<NotaPratica> idPraticas = new HashSet<>();

    @OneToMany(mappedBy = "pratica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CondivisionePratica> idPraticas = new HashSet<>();

    @OneToMany(mappedBy = "pratica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<InvitoPratica> idPraticas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "praticas", allowSetters = true)
    private TemplatePratica idTemplatePraticaRef;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPratica() {
        return idPratica;
    }

    public Pratica idPratica(Integer idPratica) {
        this.idPratica = idPratica;
        return this;
    }

    public void setIdPratica(Integer idPratica) {
        this.idPratica = idPratica;
    }

    public Integer getIdStudioProfessionaleRef() {
        return idStudioProfessionaleRef;
    }

    public Pratica idStudioProfessionaleRef(Integer idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
        return this;
    }

    public void setIdStudioProfessionaleRef(Integer idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
    }

    public String getNumero() {
        return numero;
    }

    public Pratica numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public Pratica nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataApertura() {
        return dataApertura;
    }

    public Pratica dataApertura(String dataApertura) {
        this.dataApertura = dataApertura;
        return this;
    }

    public void setDataApertura(String dataApertura) {
        this.dataApertura = dataApertura;
    }

    public String getDataChiusura() {
        return dataChiusura;
    }

    public Pratica dataChiusura(String dataChiusura) {
        this.dataChiusura = dataChiusura;
        return this;
    }

    public void setDataChiusura(String dataChiusura) {
        this.dataChiusura = dataChiusura;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public Pratica dataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
        return this;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Integer getStato() {
        return stato;
    }

    public Pratica stato(Integer stato) {
        this.stato = stato;
        return this;
    }

    public void setStato(Integer stato) {
        this.stato = stato;
    }

    public String getMotivoChiusura() {
        return motivoChiusura;
    }

    public Pratica motivoChiusura(String motivoChiusura) {
        this.motivoChiusura = motivoChiusura;
        return this;
    }

    public void setMotivoChiusura(String motivoChiusura) {
        this.motivoChiusura = motivoChiusura;
    }

    public Integer getIdTitolare() {
        return idTitolare;
    }

    public Pratica idTitolare(Integer idTitolare) {
        this.idTitolare = idTitolare;
        return this;
    }

    public void setIdTitolare(Integer idTitolare) {
        this.idTitolare = idTitolare;
    }

    public Integer getPrcAvanzato() {
        return prcAvanzato;
    }

    public Pratica prcAvanzato(Integer prcAvanzato) {
        this.prcAvanzato = prcAvanzato;
        return this;
    }

    public void setPrcAvanzato(Integer prcAvanzato) {
        this.prcAvanzato = prcAvanzato;
    }

    public String getVersion() {
        return version;
    }

    public Pratica version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getValuta() {
        return valuta;
    }

    public Pratica valuta(String valuta) {
        this.valuta = valuta;
        return this;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public Integer getIdTemplatePraticaRef() {
        return idTemplatePraticaRef;
    }

    public Pratica idTemplatePraticaRef(Integer idTemplatePraticaRef) {
        this.idTemplatePraticaRef = idTemplatePraticaRef;
        return this;
    }

    public void setIdTemplatePraticaRef(Integer idTemplatePraticaRef) {
        this.idTemplatePraticaRef = idTemplatePraticaRef;
    }

    public Set<Task> getIdPraticas() {
        return idPraticas;
    }

    public Pratica idPraticas(Set<Task> tasks) {
        this.idPraticas = tasks;
        return this;
    }

    public Pratica addIdPratica(Task task) {
        this.idPraticas.add(task);
        task.setPratica(this);
        return this;
    }

    public Pratica removeIdPratica(Task task) {
        this.idPraticas.remove(task);
        task.setPratica(null);
        return this;
    }

    public void setIdPraticas(Set<Task> tasks) {
        this.idPraticas = tasks;
    }

    public Set<NotaPratica> getIdPraticas() {
        return idPraticas;
    }

    public Pratica idPraticas(Set<NotaPratica> notaPraticas) {
        this.idPraticas = notaPraticas;
        return this;
    }

    public Pratica addIdPratica(NotaPratica notaPratica) {
        this.idPraticas.add(notaPratica);
        notaPratica.setPratica(this);
        return this;
    }

    public Pratica removeIdPratica(NotaPratica notaPratica) {
        this.idPraticas.remove(notaPratica);
        notaPratica.setPratica(null);
        return this;
    }

    public void setIdPraticas(Set<NotaPratica> notaPraticas) {
        this.idPraticas = notaPraticas;
    }

    public Set<CondivisionePratica> getIdPraticas() {
        return idPraticas;
    }

    public Pratica idPraticas(Set<CondivisionePratica> condivisionePraticas) {
        this.idPraticas = condivisionePraticas;
        return this;
    }

    public Pratica addIdPratica(CondivisionePratica condivisionePratica) {
        this.idPraticas.add(condivisionePratica);
        condivisionePratica.setPratica(this);
        return this;
    }

    public Pratica removeIdPratica(CondivisionePratica condivisionePratica) {
        this.idPraticas.remove(condivisionePratica);
        condivisionePratica.setPratica(null);
        return this;
    }

    public void setIdPraticas(Set<CondivisionePratica> condivisionePraticas) {
        this.idPraticas = condivisionePraticas;
    }

    public Set<InvitoPratica> getIdPraticas() {
        return idPraticas;
    }

    public Pratica idPraticas(Set<InvitoPratica> invitoPraticas) {
        this.idPraticas = invitoPraticas;
        return this;
    }

    public Pratica addIdPratica(InvitoPratica invitoPratica) {
        this.idPraticas.add(invitoPratica);
        invitoPratica.setPratica(this);
        return this;
    }

    public Pratica removeIdPratica(InvitoPratica invitoPratica) {
        this.idPraticas.remove(invitoPratica);
        invitoPratica.setPratica(null);
        return this;
    }

    public void setIdPraticas(Set<InvitoPratica> invitoPraticas) {
        this.idPraticas = invitoPraticas;
    }

    public TemplatePratica getIdTemplatePraticaRef() {
        return idTemplatePraticaRef;
    }

    public Pratica idTemplatePraticaRef(TemplatePratica templatePratica) {
        this.idTemplatePraticaRef = templatePratica;
        return this;
    }

    public void setIdTemplatePraticaRef(TemplatePratica templatePratica) {
        this.idTemplatePraticaRef = templatePratica;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pratica)) {
            return false;
        }
        return id != null && id.equals(((Pratica) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pratica{" +
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
            "}";
    }
}
