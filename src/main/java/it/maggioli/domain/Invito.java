package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * A Invito.
 */
@Entity
@Table(name = "invito")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "invito")
public class Invito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Max(value = 8)
    @Column(name = "id_invito", nullable = false)
    private Integer idInvito;

    @Max(value = 8)
    @Column(name = "id_studio_professionale_ref")
    private Integer idStudioProfessionaleRef;

    @Column(name = "data_invito")
    private String dataInvito;

    @Column(name = "id_user_invitante")
    private Integer idUserInvitante;

    @Column(name = "nome_user_invitante")
    private String nomeUserInvitante;

    @Column(name = "data_scadenza_invito")
    private String dataScadenzaInvito;

    @Column(name = "testo_invito")
    private String testoInvito;

    @Column(name = "id_pratica_ref")
    private Integer idPraticaRef;

    @Column(name = "id_task_ref")
    private Integer idTaskRef;

    @Column(name = "luogo_fisico")
    private String luogoFisico;

    @Column(name = "indicazioni_luogo")
    private String indicazioniLuogo;

    @Column(name = "data_inizio")
    private String dataInizio;

    @Column(name = "ora_inizio")
    private String oraInizio;

    @Column(name = "data_fine")
    private String dataFine;

    @Column(name = "ora_fine")
    private String oraFine;

    @Column(name = "url_stanza_virtuale")
    private String urlStanzaVirtuale;

    @Column(name = "discriminator")
    private String discriminator;

    @OneToOne
    @JoinColumn(unique = true)
    private StudioProfessionale idStudioProfessionaleRef;

    @OneToMany(mappedBy = "invito")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Invitato> idInvitos = new HashSet<>();

    @OneToOne(mappedBy = "idPraticaRef")
    @JsonIgnore
    private InvitoPratica idInvito;

    @OneToOne(mappedBy = "idTaskRef")
    @JsonIgnore
    private InvitoAttivita idInvito;

    @OneToOne(mappedBy = "idTaskRef")
    @JsonIgnore
    private InvitoEvento idInvito;

    @ManyToOne
    @JsonIgnoreProperties(value = "idUserConcedentes", allowSetters = true)
    private AssegnazioneTask assegnazioneTask;

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

    public Invito idInvito(Integer idInvito) {
        this.idInvito = idInvito;
        return this;
    }

    public void setIdInvito(Integer idInvito) {
        this.idInvito = idInvito;
    }

    public Integer getIdStudioProfessionaleRef() {
        return idStudioProfessionaleRef;
    }

    public Invito idStudioProfessionaleRef(Integer idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
        return this;
    }

    public void setIdStudioProfessionaleRef(Integer idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
    }

    public String getDataInvito() {
        return dataInvito;
    }

    public Invito dataInvito(String dataInvito) {
        this.dataInvito = dataInvito;
        return this;
    }

    public void setDataInvito(String dataInvito) {
        this.dataInvito = dataInvito;
    }

    public Integer getIdUserInvitante() {
        return idUserInvitante;
    }

    public Invito idUserInvitante(Integer idUserInvitante) {
        this.idUserInvitante = idUserInvitante;
        return this;
    }

    public void setIdUserInvitante(Integer idUserInvitante) {
        this.idUserInvitante = idUserInvitante;
    }

    public String getNomeUserInvitante() {
        return nomeUserInvitante;
    }

    public Invito nomeUserInvitante(String nomeUserInvitante) {
        this.nomeUserInvitante = nomeUserInvitante;
        return this;
    }

    public void setNomeUserInvitante(String nomeUserInvitante) {
        this.nomeUserInvitante = nomeUserInvitante;
    }

    public String getDataScadenzaInvito() {
        return dataScadenzaInvito;
    }

    public Invito dataScadenzaInvito(String dataScadenzaInvito) {
        this.dataScadenzaInvito = dataScadenzaInvito;
        return this;
    }

    public void setDataScadenzaInvito(String dataScadenzaInvito) {
        this.dataScadenzaInvito = dataScadenzaInvito;
    }

    public String getTestoInvito() {
        return testoInvito;
    }

    public Invito testoInvito(String testoInvito) {
        this.testoInvito = testoInvito;
        return this;
    }

    public void setTestoInvito(String testoInvito) {
        this.testoInvito = testoInvito;
    }

    public Integer getIdPraticaRef() {
        return idPraticaRef;
    }

    public Invito idPraticaRef(Integer idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
        return this;
    }

    public void setIdPraticaRef(Integer idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
    }

    public Integer getIdTaskRef() {
        return idTaskRef;
    }

    public Invito idTaskRef(Integer idTaskRef) {
        this.idTaskRef = idTaskRef;
        return this;
    }

    public void setIdTaskRef(Integer idTaskRef) {
        this.idTaskRef = idTaskRef;
    }

    public String getLuogoFisico() {
        return luogoFisico;
    }

    public Invito luogoFisico(String luogoFisico) {
        this.luogoFisico = luogoFisico;
        return this;
    }

    public void setLuogoFisico(String luogoFisico) {
        this.luogoFisico = luogoFisico;
    }

    public String getIndicazioniLuogo() {
        return indicazioniLuogo;
    }

    public Invito indicazioniLuogo(String indicazioniLuogo) {
        this.indicazioniLuogo = indicazioniLuogo;
        return this;
    }

    public void setIndicazioniLuogo(String indicazioniLuogo) {
        this.indicazioniLuogo = indicazioniLuogo;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public Invito dataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
        return this;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getOraInizio() {
        return oraInizio;
    }

    public Invito oraInizio(String oraInizio) {
        this.oraInizio = oraInizio;
        return this;
    }

    public void setOraInizio(String oraInizio) {
        this.oraInizio = oraInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public Invito dataFine(String dataFine) {
        this.dataFine = dataFine;
        return this;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public String getOraFine() {
        return oraFine;
    }

    public Invito oraFine(String oraFine) {
        this.oraFine = oraFine;
        return this;
    }

    public void setOraFine(String oraFine) {
        this.oraFine = oraFine;
    }

    public String getUrlStanzaVirtuale() {
        return urlStanzaVirtuale;
    }

    public Invito urlStanzaVirtuale(String urlStanzaVirtuale) {
        this.urlStanzaVirtuale = urlStanzaVirtuale;
        return this;
    }

    public void setUrlStanzaVirtuale(String urlStanzaVirtuale) {
        this.urlStanzaVirtuale = urlStanzaVirtuale;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public Invito discriminator(String discriminator) {
        this.discriminator = discriminator;
        return this;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public StudioProfessionale getIdStudioProfessionaleRef() {
        return idStudioProfessionaleRef;
    }

    public Invito idStudioProfessionaleRef(StudioProfessionale studioProfessionale) {
        this.idStudioProfessionaleRef = studioProfessionale;
        return this;
    }

    public void setIdStudioProfessionaleRef(StudioProfessionale studioProfessionale) {
        this.idStudioProfessionaleRef = studioProfessionale;
    }

    public Set<Invitato> getIdInvitos() {
        return idInvitos;
    }

    public Invito idInvitos(Set<Invitato> invitatoes) {
        this.idInvitos = invitatoes;
        return this;
    }

    public Invito addIdInvito(Invitato invitato) {
        this.idInvitos.add(invitato);
        invitato.setInvito(this);
        return this;
    }

    public Invito removeIdInvito(Invitato invitato) {
        this.idInvitos.remove(invitato);
        invitato.setInvito(null);
        return this;
    }

    public void setIdInvitos(Set<Invitato> invitatoes) {
        this.idInvitos = invitatoes;
    }

    public InvitoPratica getIdInvito() {
        return idInvito;
    }

    public Invito idInvito(InvitoPratica invitoPratica) {
        this.idInvito = invitoPratica;
        return this;
    }

    public void setIdInvito(InvitoPratica invitoPratica) {
        this.idInvito = invitoPratica;
    }

    public InvitoAttivita getIdInvito() {
        return idInvito;
    }

    public Invito idInvito(InvitoAttivita invitoAttivita) {
        this.idInvito = invitoAttivita;
        return this;
    }

    public void setIdInvito(InvitoAttivita invitoAttivita) {
        this.idInvito = invitoAttivita;
    }

    public InvitoEvento getIdInvito() {
        return idInvito;
    }

    public Invito idInvito(InvitoEvento invitoEvento) {
        this.idInvito = invitoEvento;
        return this;
    }

    public void setIdInvito(InvitoEvento invitoEvento) {
        this.idInvito = invitoEvento;
    }

    public AssegnazioneTask getAssegnazioneTask() {
        return assegnazioneTask;
    }

    public Invito assegnazioneTask(AssegnazioneTask assegnazioneTask) {
        this.assegnazioneTask = assegnazioneTask;
        return this;
    }

    public void setAssegnazioneTask(AssegnazioneTask assegnazioneTask) {
        this.assegnazioneTask = assegnazioneTask;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Invito)) {
            return false;
        }
        return id != null && id.equals(((Invito) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Invito{" +
            "id=" + getId() +
            ", idInvito=" + getIdInvito() +
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
            "}";
    }
}
