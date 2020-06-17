package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Invitato.
 */
@Entity
@Table(name = "invitato")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "invitato")
public class Invitato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Max(value = 8)
    @Column(name = "id_invito")
    private Integer idInvito;

    @Column(name = "token_invito")
    private String tokenInvito;

    @Column(name = "canale_primario_invito")
    private Integer canalePrimarioInvito;

    @Column(name = "canale_backup_invito")
    private Integer canaleBackupInvito;

    @Column(name = "stato_invito")
    private Integer statoInvito;

    @Column(name = "id_user_invitato")
    private Integer idUserInvitato;

    @Column(name = "id_persona_invitata")
    private Integer idPersonaInvitata;

    @Column(name = "nome_user_invitato")
    private String nomeUserInvitato;

    @Column(name = "data_risposta_invito")
    private String dataRispostaInvito;

    @Column(name = "ruolo_invitato")
    private Integer ruoloInvitato;

    @Column(name = "ind_invitati")
    private Integer indInvitati;

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private UserPersona userPersona;

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private Invito invito;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdInvito() {
        return idInvito;
    }

    public Invitato idInvito(Integer idInvito) {
        this.idInvito = idInvito;
        return this;
    }

    public void setIdInvito(Integer idInvito) {
        this.idInvito = idInvito;
    }

    public String getTokenInvito() {
        return tokenInvito;
    }

    public Invitato tokenInvito(String tokenInvito) {
        this.tokenInvito = tokenInvito;
        return this;
    }

    public void setTokenInvito(String tokenInvito) {
        this.tokenInvito = tokenInvito;
    }

    public Integer getCanalePrimarioInvito() {
        return canalePrimarioInvito;
    }

    public Invitato canalePrimarioInvito(Integer canalePrimarioInvito) {
        this.canalePrimarioInvito = canalePrimarioInvito;
        return this;
    }

    public void setCanalePrimarioInvito(Integer canalePrimarioInvito) {
        this.canalePrimarioInvito = canalePrimarioInvito;
    }

    public Integer getCanaleBackupInvito() {
        return canaleBackupInvito;
    }

    public Invitato canaleBackupInvito(Integer canaleBackupInvito) {
        this.canaleBackupInvito = canaleBackupInvito;
        return this;
    }

    public void setCanaleBackupInvito(Integer canaleBackupInvito) {
        this.canaleBackupInvito = canaleBackupInvito;
    }

    public Integer getStatoInvito() {
        return statoInvito;
    }

    public Invitato statoInvito(Integer statoInvito) {
        this.statoInvito = statoInvito;
        return this;
    }

    public void setStatoInvito(Integer statoInvito) {
        this.statoInvito = statoInvito;
    }

    public Integer getIdUserInvitato() {
        return idUserInvitato;
    }

    public Invitato idUserInvitato(Integer idUserInvitato) {
        this.idUserInvitato = idUserInvitato;
        return this;
    }

    public void setIdUserInvitato(Integer idUserInvitato) {
        this.idUserInvitato = idUserInvitato;
    }

    public Integer getIdPersonaInvitata() {
        return idPersonaInvitata;
    }

    public Invitato idPersonaInvitata(Integer idPersonaInvitata) {
        this.idPersonaInvitata = idPersonaInvitata;
        return this;
    }

    public void setIdPersonaInvitata(Integer idPersonaInvitata) {
        this.idPersonaInvitata = idPersonaInvitata;
    }

    public String getNomeUserInvitato() {
        return nomeUserInvitato;
    }

    public Invitato nomeUserInvitato(String nomeUserInvitato) {
        this.nomeUserInvitato = nomeUserInvitato;
        return this;
    }

    public void setNomeUserInvitato(String nomeUserInvitato) {
        this.nomeUserInvitato = nomeUserInvitato;
    }

    public String getDataRispostaInvito() {
        return dataRispostaInvito;
    }

    public Invitato dataRispostaInvito(String dataRispostaInvito) {
        this.dataRispostaInvito = dataRispostaInvito;
        return this;
    }

    public void setDataRispostaInvito(String dataRispostaInvito) {
        this.dataRispostaInvito = dataRispostaInvito;
    }

    public Integer getRuoloInvitato() {
        return ruoloInvitato;
    }

    public Invitato ruoloInvitato(Integer ruoloInvitato) {
        this.ruoloInvitato = ruoloInvitato;
        return this;
    }

    public void setRuoloInvitato(Integer ruoloInvitato) {
        this.ruoloInvitato = ruoloInvitato;
    }

    public Integer getIndInvitati() {
        return indInvitati;
    }

    public Invitato indInvitati(Integer indInvitati) {
        this.indInvitati = indInvitati;
        return this;
    }

    public void setIndInvitati(Integer indInvitati) {
        this.indInvitati = indInvitati;
    }

    public UserPersona getUserPersona() {
        return userPersona;
    }

    public Invitato userPersona(UserPersona userPersona) {
        this.userPersona = userPersona;
        return this;
    }

    public void setUserPersona(UserPersona userPersona) {
        this.userPersona = userPersona;
    }

    public Invito getInvito() {
        return invito;
    }

    public Invitato invito(Invito invito) {
        this.invito = invito;
        return this;
    }

    public void setInvito(Invito invito) {
        this.invito = invito;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Invitato)) {
            return false;
        }
        return id != null && id.equals(((Invitato) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Invitato{" +
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
            "}";
    }
}
