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

    @Max(value = 8L)
    @Column(name = "id_invito_ref")
    private Long idInvitoRef;

    @Column(name = "token_invito")
    private String tokenInvito;

    @Column(name = "canale_primario_invito")
    private Long canalePrimarioInvito;

    @Column(name = "canale_backup_invito")
    private Long canaleBackupInvito;

    @Column(name = "stato_invito")
    private Long statoInvito;

    @Column(name = "id_user_invitato")
    private Long idUserInvitato;

    @Column(name = "id_persona_invitata")
    private Long idPersonaInvitata;

    @Column(name = "nome_user_invitato")
    private String nomeUserInvitato;

    @Column(name = "data_risposta_invito")
    private String dataRispostaInvito;

    @Column(name = "ruolo_invitato")
    private Long ruoloInvitato;

    @Column(name = "ind_invitati")
    private Long indInvitati;

    @ManyToOne
    @JsonIgnoreProperties(value = "invitatoes", allowSetters = true)
    private UserPersona idUserPersona;

    @ManyToOne
    @JsonIgnoreProperties(value = "invitatoes", allowSetters = true)
    private Invito idInvito;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdInvitoRef() {
        return idInvitoRef;
    }

    public Invitato idInvitoRef(Long idInvitoRef) {
        this.idInvitoRef = idInvitoRef;
        return this;
    }

    public void setIdInvitoRef(Long idInvitoRef) {
        this.idInvitoRef = idInvitoRef;
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

    public Long getCanalePrimarioInvito() {
        return canalePrimarioInvito;
    }

    public Invitato canalePrimarioInvito(Long canalePrimarioInvito) {
        this.canalePrimarioInvito = canalePrimarioInvito;
        return this;
    }

    public void setCanalePrimarioInvito(Long canalePrimarioInvito) {
        this.canalePrimarioInvito = canalePrimarioInvito;
    }

    public Long getCanaleBackupInvito() {
        return canaleBackupInvito;
    }

    public Invitato canaleBackupInvito(Long canaleBackupInvito) {
        this.canaleBackupInvito = canaleBackupInvito;
        return this;
    }

    public void setCanaleBackupInvito(Long canaleBackupInvito) {
        this.canaleBackupInvito = canaleBackupInvito;
    }

    public Long getStatoInvito() {
        return statoInvito;
    }

    public Invitato statoInvito(Long statoInvito) {
        this.statoInvito = statoInvito;
        return this;
    }

    public void setStatoInvito(Long statoInvito) {
        this.statoInvito = statoInvito;
    }

    public Long getIdUserInvitato() {
        return idUserInvitato;
    }

    public Invitato idUserInvitato(Long idUserInvitato) {
        this.idUserInvitato = idUserInvitato;
        return this;
    }

    public void setIdUserInvitato(Long idUserInvitato) {
        this.idUserInvitato = idUserInvitato;
    }

    public Long getIdPersonaInvitata() {
        return idPersonaInvitata;
    }

    public Invitato idPersonaInvitata(Long idPersonaInvitata) {
        this.idPersonaInvitata = idPersonaInvitata;
        return this;
    }

    public void setIdPersonaInvitata(Long idPersonaInvitata) {
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

    public Long getRuoloInvitato() {
        return ruoloInvitato;
    }

    public Invitato ruoloInvitato(Long ruoloInvitato) {
        this.ruoloInvitato = ruoloInvitato;
        return this;
    }

    public void setRuoloInvitato(Long ruoloInvitato) {
        this.ruoloInvitato = ruoloInvitato;
    }

    public Long getIndInvitati() {
        return indInvitati;
    }

    public Invitato indInvitati(Long indInvitati) {
        this.indInvitati = indInvitati;
        return this;
    }

    public void setIndInvitati(Long indInvitati) {
        this.indInvitati = indInvitati;
    }

    public UserPersona getIdUserPersona() {
        return idUserPersona;
    }

    public Invitato idUserPersona(UserPersona userPersona) {
        this.idUserPersona = userPersona;
        return this;
    }

    public void setIdUserPersona(UserPersona userPersona) {
        this.idUserPersona = userPersona;
    }

    public Invito getIdInvito() {
        return idInvito;
    }

    public Invitato idInvito(Invito invito) {
        this.idInvito = invito;
        return this;
    }

    public void setIdInvito(Invito invito) {
        this.idInvito = invito;
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
            ", idInvitoRef=" + getIdInvitoRef() +
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
