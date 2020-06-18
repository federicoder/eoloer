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

    @Max(value = 8L)
    @Column(name = "id_user_ammesso")
    private Long idUserAmmesso;

    @Column(name = "ruolo")
    private Long ruolo;

    @Column(name = "id_user_concedente")
    private Long idUserConcedente;

    @Column(name = "stato_invito")
    private Long statoInvito;

    @Column(name = "id_pratica_ref")
    private Long idPraticaRef;

    @OneToOne
    @JoinColumn(unique = true)
    private RappresentanzaPratica idRuoloPersona;

    @OneToOne
    @JoinColumn(unique = true)
    private Persona idPersona;

    @ManyToOne
    @JsonIgnoreProperties(value = "condivisionePraticas", allowSetters = true)
    private UserPersona idUserPersona;

    @ManyToOne
    @JsonIgnoreProperties(value = "condivisionePraticas", allowSetters = true)
    private Pratica idPratica;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUserAmmesso() {
        return idUserAmmesso;
    }

    public CondivisionePratica idUserAmmesso(Long idUserAmmesso) {
        this.idUserAmmesso = idUserAmmesso;
        return this;
    }

    public void setIdUserAmmesso(Long idUserAmmesso) {
        this.idUserAmmesso = idUserAmmesso;
    }

    public Long getRuolo() {
        return ruolo;
    }

    public CondivisionePratica ruolo(Long ruolo) {
        this.ruolo = ruolo;
        return this;
    }

    public void setRuolo(Long ruolo) {
        this.ruolo = ruolo;
    }

    public Long getIdUserConcedente() {
        return idUserConcedente;
    }

    public CondivisionePratica idUserConcedente(Long idUserConcedente) {
        this.idUserConcedente = idUserConcedente;
        return this;
    }

    public void setIdUserConcedente(Long idUserConcedente) {
        this.idUserConcedente = idUserConcedente;
    }

    public Long getStatoInvito() {
        return statoInvito;
    }

    public CondivisionePratica statoInvito(Long statoInvito) {
        this.statoInvito = statoInvito;
        return this;
    }

    public void setStatoInvito(Long statoInvito) {
        this.statoInvito = statoInvito;
    }

    public Long getIdPraticaRef() {
        return idPraticaRef;
    }

    public CondivisionePratica idPraticaRef(Long idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
        return this;
    }

    public void setIdPraticaRef(Long idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
    }

    public RappresentanzaPratica getIdRuoloPersona() {
        return idRuoloPersona;
    }

    public CondivisionePratica idRuoloPersona(RappresentanzaPratica rappresentanzaPratica) {
        this.idRuoloPersona = rappresentanzaPratica;
        return this;
    }

    public void setIdRuoloPersona(RappresentanzaPratica rappresentanzaPratica) {
        this.idRuoloPersona = rappresentanzaPratica;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public CondivisionePratica idPersona(Persona persona) {
        this.idPersona = persona;
        return this;
    }

    public void setIdPersona(Persona persona) {
        this.idPersona = persona;
    }

    public UserPersona getIdUserPersona() {
        return idUserPersona;
    }

    public CondivisionePratica idUserPersona(UserPersona userPersona) {
        this.idUserPersona = userPersona;
        return this;
    }

    public void setIdUserPersona(UserPersona userPersona) {
        this.idUserPersona = userPersona;
    }

    public Pratica getIdPratica() {
        return idPratica;
    }

    public CondivisionePratica idPratica(Pratica pratica) {
        this.idPratica = pratica;
        return this;
    }

    public void setIdPratica(Pratica pratica) {
        this.idPratica = pratica;
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
            ", idPraticaRef=" + getIdPraticaRef() +
            "}";
    }
}
