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

    @Max(value = 8)
    @Column(name = "id_studio_professionale")
    private Integer idStudioProfessionale;

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

    @Column(name = "id_pratica")
    private Integer idPratica;

    @Column(name = "id_attivita")
    private Integer idAttivita;

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
    private StudioProfessionale idStudioProfessionale;

    @OneToMany(mappedBy = "invito")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Invitato> ids = new HashSet<>();

    @OneToOne(mappedBy = "idPratica")
    @JsonIgnore
    private InvitoPratica id;

    @OneToOne(mappedBy = "idAttivita")
    @JsonIgnore
    private InvitoAttivita id;

    @OneToOne(mappedBy = "idAttivita")
    @JsonIgnore
    private InvitoEvento id;

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

    public Integer getIdStudioProfessionale() {
        return idStudioProfessionale;
    }

    public Invito idStudioProfessionale(Integer idStudioProfessionale) {
        this.idStudioProfessionale = idStudioProfessionale;
        return this;
    }

    public void setIdStudioProfessionale(Integer idStudioProfessionale) {
        this.idStudioProfessionale = idStudioProfessionale;
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

    public Integer getIdPratica() {
        return idPratica;
    }

    public Invito idPratica(Integer idPratica) {
        this.idPratica = idPratica;
        return this;
    }

    public void setIdPratica(Integer idPratica) {
        this.idPratica = idPratica;
    }

    public Integer getIdAttivita() {
        return idAttivita;
    }

    public Invito idAttivita(Integer idAttivita) {
        this.idAttivita = idAttivita;
        return this;
    }

    public void setIdAttivita(Integer idAttivita) {
        this.idAttivita = idAttivita;
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

    public StudioProfessionale getIdStudioProfessionale() {
        return idStudioProfessionale;
    }

    public Invito idStudioProfessionale(StudioProfessionale studioProfessionale) {
        this.idStudioProfessionale = studioProfessionale;
        return this;
    }

    public void setIdStudioProfessionale(StudioProfessionale studioProfessionale) {
        this.idStudioProfessionale = studioProfessionale;
    }

    public Set<Invitato> getIds() {
        return ids;
    }

    public Invito ids(Set<Invitato> invitatoes) {
        this.ids = invitatoes;
        return this;
    }

    public Invito addId(Invitato invitato) {
        this.ids.add(invitato);
        invitato.setInvito(this);
        return this;
    }

    public Invito removeId(Invitato invitato) {
        this.ids.remove(invitato);
        invitato.setInvito(null);
        return this;
    }

    public void setIds(Set<Invitato> invitatoes) {
        this.ids = invitatoes;
    }

    public InvitoPratica getId() {
        return id;
    }

    public Invito id(InvitoPratica invitoPratica) {
        this.id = invitoPratica;
        return this;
    }

    public void setId(InvitoPratica invitoPratica) {
        this.id = invitoPratica;
    }

    public InvitoAttivita getId() {
        return id;
    }

    public Invito id(InvitoAttivita invitoAttivita) {
        this.id = invitoAttivita;
        return this;
    }

    public void setId(InvitoAttivita invitoAttivita) {
        this.id = invitoAttivita;
    }

    public InvitoEvento getId() {
        return id;
    }

    public Invito id(InvitoEvento invitoEvento) {
        this.id = invitoEvento;
        return this;
    }

    public void setId(InvitoEvento invitoEvento) {
        this.id = invitoEvento;
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
            ", idStudioProfessionale=" + getIdStudioProfessionale() +
            ", dataInvito='" + getDataInvito() + "'" +
            ", idUserInvitante=" + getIdUserInvitante() +
            ", nomeUserInvitante='" + getNomeUserInvitante() + "'" +
            ", dataScadenzaInvito='" + getDataScadenzaInvito() + "'" +
            ", testoInvito='" + getTestoInvito() + "'" +
            ", idPratica=" + getIdPratica() +
            ", idAttivita=" + getIdAttivita() +
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
