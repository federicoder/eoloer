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
    @Column(name = "id_studio")
    private Integer idStudio;

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

    @Column(name = "id_template_pratica")
    private Integer idTemplatePratica;

    @OneToMany(mappedBy = "pratica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Task> ids = new HashSet<>();

    @OneToMany(mappedBy = "pratica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<NotaPratica> ids = new HashSet<>();

    @OneToMany(mappedBy = "pratica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CondivisionePratica> ids = new HashSet<>();

    @OneToMany(mappedBy = "pratica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<InvitoPratica> ids = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "praticas", allowSetters = true)
    private TemplatePratica idTemplate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdStudio() {
        return idStudio;
    }

    public Pratica idStudio(Integer idStudio) {
        this.idStudio = idStudio;
        return this;
    }

    public void setIdStudio(Integer idStudio) {
        this.idStudio = idStudio;
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

    public Integer getIdTemplatePratica() {
        return idTemplatePratica;
    }

    public Pratica idTemplatePratica(Integer idTemplatePratica) {
        this.idTemplatePratica = idTemplatePratica;
        return this;
    }

    public void setIdTemplatePratica(Integer idTemplatePratica) {
        this.idTemplatePratica = idTemplatePratica;
    }

    public Set<Task> getIds() {
        return ids;
    }

    public Pratica ids(Set<Task> tasks) {
        this.ids = tasks;
        return this;
    }

    public Pratica addId(Task task) {
        this.ids.add(task);
        task.setPratica(this);
        return this;
    }

    public Pratica removeId(Task task) {
        this.ids.remove(task);
        task.setPratica(null);
        return this;
    }

    public void setIds(Set<Task> tasks) {
        this.ids = tasks;
    }

    public Set<NotaPratica> getIds() {
        return ids;
    }

    public Pratica ids(Set<NotaPratica> notaPraticas) {
        this.ids = notaPraticas;
        return this;
    }

    public Pratica addId(NotaPratica notaPratica) {
        this.ids.add(notaPratica);
        notaPratica.setPratica(this);
        return this;
    }

    public Pratica removeId(NotaPratica notaPratica) {
        this.ids.remove(notaPratica);
        notaPratica.setPratica(null);
        return this;
    }

    public void setIds(Set<NotaPratica> notaPraticas) {
        this.ids = notaPraticas;
    }

    public Set<CondivisionePratica> getIds() {
        return ids;
    }

    public Pratica ids(Set<CondivisionePratica> condivisionePraticas) {
        this.ids = condivisionePraticas;
        return this;
    }

    public Pratica addId(CondivisionePratica condivisionePratica) {
        this.ids.add(condivisionePratica);
        condivisionePratica.setPratica(this);
        return this;
    }

    public Pratica removeId(CondivisionePratica condivisionePratica) {
        this.ids.remove(condivisionePratica);
        condivisionePratica.setPratica(null);
        return this;
    }

    public void setIds(Set<CondivisionePratica> condivisionePraticas) {
        this.ids = condivisionePraticas;
    }

    public Set<InvitoPratica> getIds() {
        return ids;
    }

    public Pratica ids(Set<InvitoPratica> invitoPraticas) {
        this.ids = invitoPraticas;
        return this;
    }

    public Pratica addId(InvitoPratica invitoPratica) {
        this.ids.add(invitoPratica);
        invitoPratica.setPratica(this);
        return this;
    }

    public Pratica removeId(InvitoPratica invitoPratica) {
        this.ids.remove(invitoPratica);
        invitoPratica.setPratica(null);
        return this;
    }

    public void setIds(Set<InvitoPratica> invitoPraticas) {
        this.ids = invitoPraticas;
    }

    public TemplatePratica getIdTemplate() {
        return idTemplate;
    }

    public Pratica idTemplate(TemplatePratica templatePratica) {
        this.idTemplate = templatePratica;
        return this;
    }

    public void setIdTemplate(TemplatePratica templatePratica) {
        this.idTemplate = templatePratica;
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
            ", idStudio=" + getIdStudio() +
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
            ", idTemplatePratica=" + getIdTemplatePratica() +
            "}";
    }
}
