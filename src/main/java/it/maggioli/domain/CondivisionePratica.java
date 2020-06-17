package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A CondivisionePratica.
 */
@Entity
@Table(name = "condivisione_pratica")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "condivisionepratica")
public class CondivisionePratica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Max(value = 8)
    @Column(name = "id_user_ammesso")
    private Integer idUserAmmesso;

    @Column(name = "ruolo")
    private Integer ruolo;

    @Column(name = "id_user_concedente")
    private Integer idUserConcedente;

    @Column(name = "stato_invito")
    private Integer statoInvito;

    @Column(name = "id_pratica")
    private Integer idPratica;

    @OneToOne
    @JoinColumn(unique = true)
    private RappresentanzaPratica ruolo;

    @OneToOne
    @JoinColumn(unique = true)
    private Persona idUserConcedente;

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private Pratica pratica;

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private UserPersona userPersona;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUserAmmesso() {
        return idUserAmmesso;
    }

    public CondivisionePratica idUserAmmesso(Integer idUserAmmesso) {
        this.idUserAmmesso = idUserAmmesso;
        return this;
    }

    public void setIdUserAmmesso(Integer idUserAmmesso) {
        this.idUserAmmesso = idUserAmmesso;
    }

    public Integer getRuolo() {
        return ruolo;
    }

    public CondivisionePratica ruolo(Integer ruolo) {
        this.ruolo = ruolo;
        return this;
    }

    public void setRuolo(Integer ruolo) {
        this.ruolo = ruolo;
    }

    public Integer getIdUserConcedente() {
        return idUserConcedente;
    }

    public CondivisionePratica idUserConcedente(Integer idUserConcedente) {
        this.idUserConcedente = idUserConcedente;
        return this;
    }

    public void setIdUserConcedente(Integer idUserConcedente) {
        this.idUserConcedente = idUserConcedente;
    }

    public Integer getStatoInvito() {
        return statoInvito;
    }

    public CondivisionePratica statoInvito(Integer statoInvito) {
        this.statoInvito = statoInvito;
        return this;
    }

    public void setStatoInvito(Integer statoInvito) {
        this.statoInvito = statoInvito;
    }

    public Integer getIdPratica() {
        return idPratica;
    }

    public CondivisionePratica idPratica(Integer idPratica) {
        this.idPratica = idPratica;
        return this;
    }

    public void setIdPratica(Integer idPratica) {
        this.idPratica = idPratica;
    }

    public RappresentanzaPratica getRuolo() {
        return ruolo;
    }

    public CondivisionePratica ruolo(RappresentanzaPratica rappresentanzaPratica) {
        this.ruolo = rappresentanzaPratica;
        return this;
    }

    public void setRuolo(RappresentanzaPratica rappresentanzaPratica) {
        this.ruolo = rappresentanzaPratica;
    }

    public Persona getIdUserConcedente() {
        return idUserConcedente;
    }

    public CondivisionePratica idUserConcedente(Persona persona) {
        this.idUserConcedente = persona;
        return this;
    }

    public void setIdUserConcedente(Persona persona) {
        this.idUserConcedente = persona;
    }

    public Pratica getPratica() {
        return pratica;
    }

    public CondivisionePratica pratica(Pratica pratica) {
        this.pratica = pratica;
        return this;
    }

    public void setPratica(Pratica pratica) {
        this.pratica = pratica;
    }

    public UserPersona getUserPersona() {
        return userPersona;
    }

    public CondivisionePratica userPersona(UserPersona userPersona) {
        this.userPersona = userPersona;
        return this;
    }

    public void setUserPersona(UserPersona userPersona) {
        this.userPersona = userPersona;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CondivisionePratica)) {
            return false;
        }
        return id != null && id.equals(((CondivisionePratica) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CondivisionePratica{" +
            "id=" + getId() +
            ", idUserAmmesso=" + getIdUserAmmesso() +
            ", ruolo=" + getRuolo() +
            ", idUserConcedente=" + getIdUserConcedente() +
            ", statoInvito=" + getStatoInvito() +
            ", idPratica=" + getIdPratica() +
            "}";
    }
}
