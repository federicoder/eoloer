package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Persona.
 */
@Entity
@Table(name = "persona")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Max(value = 8L)
    @Column(name = "id_studio_professionale_ref")
    private Long idStudioProfessionaleRef;

    @Column(name = "codice_fiscale")
    private String codiceFiscale;

    @Column(name = "area_di_interesse")
    private String areaDiInteresse;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_di_nascita")
    private String dataDiNascita;

    @Column(name = "luogo_di_nascita")
    private String luogoDiNascita;

    @Column(name = "professione")
    private String professione;

    @Column(name = "tipo")
    private Long tipo;

    @Column(name = "discriminator")
    private String discriminator;

    @Column(name = "id_ruolo_persona_ref")
    private Long idRuoloPersonaRef;

    @Column(name = "tipo_ruolo_utente")
    private Long tipoRuoloUtente;

    @OneToOne
    @JoinColumn(unique = true)
    private IndirizzoPersona id;

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DatiContabili> ids = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<EmailPersona> ids = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TagPersona> ids = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TelefonoPersona> ids = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<NotePersona> ids = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<StudioProfessionale> ids = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<RappresentanzaPratica> ids = new HashSet<>();

    @OneToOne(mappedBy = "idPersonaRef")
    @JsonIgnore
    private PersonaFisica id;

    @OneToOne(mappedBy = "idPersonaRef")
    @JsonIgnore
    private Organizzazione id;

    @OneToOne(mappedBy = "idUserConcedente")
    @JsonIgnore
    private CondivisionePratica id;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdStudioProfessionaleRef() {
        return idStudioProfessionaleRef;
    }

    public Persona idStudioProfessionaleRef(Long idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
        return this;
    }

    public void setIdStudioProfessionaleRef(Long idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public Persona codiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
        return this;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getAreaDiInteresse() {
        return areaDiInteresse;
    }

    public Persona areaDiInteresse(String areaDiInteresse) {
        this.areaDiInteresse = areaDiInteresse;
        return this;
    }

    public void setAreaDiInteresse(String areaDiInteresse) {
        this.areaDiInteresse = areaDiInteresse;
    }

    public String getTitolo() {
        return titolo;
    }

    public Persona titolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCognome() {
        return cognome;
    }

    public Persona cognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public Persona nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public Persona dataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
        return this;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getLuogoDiNascita() {
        return luogoDiNascita;
    }

    public Persona luogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
        return this;
    }

    public void setLuogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
    }

    public String getProfessione() {
        return professione;
    }

    public Persona professione(String professione) {
        this.professione = professione;
        return this;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public Long getTipo() {
        return tipo;
    }

    public Persona tipo(Long tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public Persona discriminator(String discriminator) {
        this.discriminator = discriminator;
        return this;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public Long getIdRuoloPersonaRef() {
        return idRuoloPersonaRef;
    }

    public Persona idRuoloPersonaRef(Long idRuoloPersonaRef) {
        this.idRuoloPersonaRef = idRuoloPersonaRef;
        return this;
    }

    public void setIdRuoloPersonaRef(Long idRuoloPersonaRef) {
        this.idRuoloPersonaRef = idRuoloPersonaRef;
    }

    public Long getTipoRuoloUtente() {
        return tipoRuoloUtente;
    }

    public Persona tipoRuoloUtente(Long tipoRuoloUtente) {
        this.tipoRuoloUtente = tipoRuoloUtente;
        return this;
    }

    public void setTipoRuoloUtente(Long tipoRuoloUtente) {
        this.tipoRuoloUtente = tipoRuoloUtente;
    }

    public IndirizzoPersona getId() {
        return id;
    }

    public Persona id(IndirizzoPersona indirizzoPersona) {
        this.id = indirizzoPersona;
        return this;
    }

    public void setId(IndirizzoPersona indirizzoPersona) {
        this.id = indirizzoPersona;
    }

    public Set<DatiContabili> getIds() {
        return ids;
    }

    public Persona ids(Set<DatiContabili> datiContabilis) {
        this.ids = datiContabilis;
        return this;
    }

    public Persona addId(DatiContabili datiContabili) {
        this.ids.add(datiContabili);
        datiContabili.setPersona(this);
        return this;
    }

    public Persona removeId(DatiContabili datiContabili) {
        this.ids.remove(datiContabili);
        datiContabili.setPersona(null);
        return this;
    }

    public void setIds(Set<DatiContabili> datiContabilis) {
        this.ids = datiContabilis;
    }

    public Set<EmailPersona> getIds() {
        return ids;
    }

    public Persona ids(Set<EmailPersona> emailPersonas) {
        this.ids = emailPersonas;
        return this;
    }

    public Persona addId(EmailPersona emailPersona) {
        this.ids.add(emailPersona);
        emailPersona.setPersona(this);
        return this;
    }

    public Persona removeId(EmailPersona emailPersona) {
        this.ids.remove(emailPersona);
        emailPersona.setPersona(null);
        return this;
    }

    public void setIds(Set<EmailPersona> emailPersonas) {
        this.ids = emailPersonas;
    }

    public Set<TagPersona> getIds() {
        return ids;
    }

    public Persona ids(Set<TagPersona> tagPersonas) {
        this.ids = tagPersonas;
        return this;
    }

    public Persona addId(TagPersona tagPersona) {
        this.ids.add(tagPersona);
        tagPersona.setPersona(this);
        return this;
    }

    public Persona removeId(TagPersona tagPersona) {
        this.ids.remove(tagPersona);
        tagPersona.setPersona(null);
        return this;
    }

    public void setIds(Set<TagPersona> tagPersonas) {
        this.ids = tagPersonas;
    }

    public Set<TelefonoPersona> getIds() {
        return ids;
    }

    public Persona ids(Set<TelefonoPersona> telefonoPersonas) {
        this.ids = telefonoPersonas;
        return this;
    }

    public Persona addId(TelefonoPersona telefonoPersona) {
        this.ids.add(telefonoPersona);
        telefonoPersona.setPersona(this);
        return this;
    }

    public Persona removeId(TelefonoPersona telefonoPersona) {
        this.ids.remove(telefonoPersona);
        telefonoPersona.setPersona(null);
        return this;
    }

    public void setIds(Set<TelefonoPersona> telefonoPersonas) {
        this.ids = telefonoPersonas;
    }

    public Set<NotePersona> getIds() {
        return ids;
    }

    public Persona ids(Set<NotePersona> notePersonas) {
        this.ids = notePersonas;
        return this;
    }

    public Persona addId(NotePersona notePersona) {
        this.ids.add(notePersona);
        notePersona.setPersona(this);
        return this;
    }

    public Persona removeId(NotePersona notePersona) {
        this.ids.remove(notePersona);
        notePersona.setPersona(null);
        return this;
    }

    public void setIds(Set<NotePersona> notePersonas) {
        this.ids = notePersonas;
    }

    public Set<StudioProfessionale> getIds() {
        return ids;
    }

    public Persona ids(Set<StudioProfessionale> studioProfessionales) {
        this.ids = studioProfessionales;
        return this;
    }

    public Persona addId(StudioProfessionale studioProfessionale) {
        this.ids.add(studioProfessionale);
        studioProfessionale.setPersona(this);
        return this;
    }

    public Persona removeId(StudioProfessionale studioProfessionale) {
        this.ids.remove(studioProfessionale);
        studioProfessionale.setPersona(null);
        return this;
    }

    public void setIds(Set<StudioProfessionale> studioProfessionales) {
        this.ids = studioProfessionales;
    }

    public Set<RappresentanzaPratica> getIds() {
        return ids;
    }

    public Persona ids(Set<RappresentanzaPratica> rappresentanzaPraticas) {
        this.ids = rappresentanzaPraticas;
        return this;
    }

    public Persona addId(RappresentanzaPratica rappresentanzaPratica) {
        this.ids.add(rappresentanzaPratica);
        rappresentanzaPratica.setPersona(this);
        return this;
    }

    public Persona removeId(RappresentanzaPratica rappresentanzaPratica) {
        this.ids.remove(rappresentanzaPratica);
        rappresentanzaPratica.setPersona(null);
        return this;
    }

    public void setIds(Set<RappresentanzaPratica> rappresentanzaPraticas) {
        this.ids = rappresentanzaPraticas;
    }

    public PersonaFisica getId() {
        return id;
    }

    public Persona id(PersonaFisica personaFisica) {
        this.id = personaFisica;
        return this;
    }

    public void setId(PersonaFisica personaFisica) {
        this.id = personaFisica;
    }

    public Organizzazione getId() {
        return id;
    }

    public Persona id(Organizzazione organizzazione) {
        this.id = organizzazione;
        return this;
    }

    public void setId(Organizzazione organizzazione) {
        this.id = organizzazione;
    }

    public CondivisionePratica getId() {
        return id;
    }

    public Persona id(CondivisionePratica condivisionePratica) {
        this.id = condivisionePratica;
        return this;
    }

    public void setId(CondivisionePratica condivisionePratica) {
        this.id = condivisionePratica;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Persona)) {
            return false;
        }
        return id != null && id.equals(((Persona) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Persona{" +
            "id=" + getId() +
            ", idStudioProfessionaleRef=" + getIdStudioProfessionaleRef() +
            ", codiceFiscale='" + getCodiceFiscale() + "'" +
            ", areaDiInteresse='" + getAreaDiInteresse() + "'" +
            ", titolo='" + getTitolo() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", nome='" + getNome() + "'" +
            ", dataDiNascita='" + getDataDiNascita() + "'" +
            ", luogoDiNascita='" + getLuogoDiNascita() + "'" +
            ", professione='" + getProfessione() + "'" +
            ", tipo=" + getTipo() +
            ", discriminator='" + getDiscriminator() + "'" +
            ", idRuoloPersonaRef=" + getIdRuoloPersonaRef() +
            ", tipoRuoloUtente=" + getTipoRuoloUtente() +
            "}";
    }
}
