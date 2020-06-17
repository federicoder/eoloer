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

    @NotNull
    @Max(value = 8)
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Max(value = 8)
    @Column(name = "id_studio_professionale_ref")
    private Integer idStudioProfessionaleRef;

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
    private Integer tipo;

    @Column(name = "discriminator")
    private String discriminator;

    @Column(name = "id_ruolo_persona_ref")
    private Integer idRuoloPersonaRef;

    @Column(name = "tipo_ruolo_utente")
    private Integer tipoRuoloUtente;

    @OneToOne
    @JoinColumn(unique = true)
    private IndirizzoPersona idPersona;

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DatiContabili> idPersonas = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<EmailPersona> idPersonas = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TagPersona> idPersonas = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TelefonoPersona> idPersonas = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<NotePersona> idPersonas = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<StudioProfessionale> idPersonas = new HashSet<>();

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<RappresentanzaPratica> idPersonas = new HashSet<>();

    @OneToOne(mappedBy = "idPersonaRef")
    @JsonIgnore
    private PersonaFisica idPersona;

    @OneToOne(mappedBy = "idPersonaRef")
    @JsonIgnore
    private Organizzazione idPersona;

    @OneToOne(mappedBy = "idUserConcedente")
    @JsonIgnore
    private CondivisionePratica idPersona;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public Persona idPersona(Integer idPersona) {
        this.idPersona = idPersona;
        return this;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdStudioProfessionaleRef() {
        return idStudioProfessionaleRef;
    }

    public Persona idStudioProfessionaleRef(Integer idStudioProfessionaleRef) {
        this.idStudioProfessionaleRef = idStudioProfessionaleRef;
        return this;
    }

    public void setIdStudioProfessionaleRef(Integer idStudioProfessionaleRef) {
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

    public Integer getTipo() {
        return tipo;
    }

    public Persona tipo(Integer tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(Integer tipo) {
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

    public Integer getIdRuoloPersonaRef() {
        return idRuoloPersonaRef;
    }

    public Persona idRuoloPersonaRef(Integer idRuoloPersonaRef) {
        this.idRuoloPersonaRef = idRuoloPersonaRef;
        return this;
    }

    public void setIdRuoloPersonaRef(Integer idRuoloPersonaRef) {
        this.idRuoloPersonaRef = idRuoloPersonaRef;
    }

    public Integer getTipoRuoloUtente() {
        return tipoRuoloUtente;
    }

    public Persona tipoRuoloUtente(Integer tipoRuoloUtente) {
        this.tipoRuoloUtente = tipoRuoloUtente;
        return this;
    }

    public void setTipoRuoloUtente(Integer tipoRuoloUtente) {
        this.tipoRuoloUtente = tipoRuoloUtente;
    }

    public IndirizzoPersona getIdPersona() {
        return idPersona;
    }

    public Persona idPersona(IndirizzoPersona indirizzoPersona) {
        this.idPersona = indirizzoPersona;
        return this;
    }

    public void setIdPersona(IndirizzoPersona indirizzoPersona) {
        this.idPersona = indirizzoPersona;
    }

    public Set<DatiContabili> getIdPersonas() {
        return idPersonas;
    }

    public Persona idPersonas(Set<DatiContabili> datiContabilis) {
        this.idPersonas = datiContabilis;
        return this;
    }

    public Persona addIdPersona(DatiContabili datiContabili) {
        this.idPersonas.add(datiContabili);
        datiContabili.setPersona(this);
        return this;
    }

    public Persona removeIdPersona(DatiContabili datiContabili) {
        this.idPersonas.remove(datiContabili);
        datiContabili.setPersona(null);
        return this;
    }

    public void setIdPersonas(Set<DatiContabili> datiContabilis) {
        this.idPersonas = datiContabilis;
    }

    public Set<EmailPersona> getIdPersonas() {
        return idPersonas;
    }

    public Persona idPersonas(Set<EmailPersona> emailPersonas) {
        this.idPersonas = emailPersonas;
        return this;
    }

    public Persona addIdPersona(EmailPersona emailPersona) {
        this.idPersonas.add(emailPersona);
        emailPersona.setPersona(this);
        return this;
    }

    public Persona removeIdPersona(EmailPersona emailPersona) {
        this.idPersonas.remove(emailPersona);
        emailPersona.setPersona(null);
        return this;
    }

    public void setIdPersonas(Set<EmailPersona> emailPersonas) {
        this.idPersonas = emailPersonas;
    }

    public Set<TagPersona> getIdPersonas() {
        return idPersonas;
    }

    public Persona idPersonas(Set<TagPersona> tagPersonas) {
        this.idPersonas = tagPersonas;
        return this;
    }

    public Persona addIdPersona(TagPersona tagPersona) {
        this.idPersonas.add(tagPersona);
        tagPersona.setPersona(this);
        return this;
    }

    public Persona removeIdPersona(TagPersona tagPersona) {
        this.idPersonas.remove(tagPersona);
        tagPersona.setPersona(null);
        return this;
    }

    public void setIdPersonas(Set<TagPersona> tagPersonas) {
        this.idPersonas = tagPersonas;
    }

    public Set<TelefonoPersona> getIdPersonas() {
        return idPersonas;
    }

    public Persona idPersonas(Set<TelefonoPersona> telefonoPersonas) {
        this.idPersonas = telefonoPersonas;
        return this;
    }

    public Persona addIdPersona(TelefonoPersona telefonoPersona) {
        this.idPersonas.add(telefonoPersona);
        telefonoPersona.setPersona(this);
        return this;
    }

    public Persona removeIdPersona(TelefonoPersona telefonoPersona) {
        this.idPersonas.remove(telefonoPersona);
        telefonoPersona.setPersona(null);
        return this;
    }

    public void setIdPersonas(Set<TelefonoPersona> telefonoPersonas) {
        this.idPersonas = telefonoPersonas;
    }

    public Set<NotePersona> getIdPersonas() {
        return idPersonas;
    }

    public Persona idPersonas(Set<NotePersona> notePersonas) {
        this.idPersonas = notePersonas;
        return this;
    }

    public Persona addIdPersona(NotePersona notePersona) {
        this.idPersonas.add(notePersona);
        notePersona.setPersona(this);
        return this;
    }

    public Persona removeIdPersona(NotePersona notePersona) {
        this.idPersonas.remove(notePersona);
        notePersona.setPersona(null);
        return this;
    }

    public void setIdPersonas(Set<NotePersona> notePersonas) {
        this.idPersonas = notePersonas;
    }

    public Set<StudioProfessionale> getIdPersonas() {
        return idPersonas;
    }

    public Persona idPersonas(Set<StudioProfessionale> studioProfessionales) {
        this.idPersonas = studioProfessionales;
        return this;
    }

    public Persona addIdPersona(StudioProfessionale studioProfessionale) {
        this.idPersonas.add(studioProfessionale);
        studioProfessionale.setPersona(this);
        return this;
    }

    public Persona removeIdPersona(StudioProfessionale studioProfessionale) {
        this.idPersonas.remove(studioProfessionale);
        studioProfessionale.setPersona(null);
        return this;
    }

    public void setIdPersonas(Set<StudioProfessionale> studioProfessionales) {
        this.idPersonas = studioProfessionales;
    }

    public Set<RappresentanzaPratica> getIdPersonas() {
        return idPersonas;
    }

    public Persona idPersonas(Set<RappresentanzaPratica> rappresentanzaPraticas) {
        this.idPersonas = rappresentanzaPraticas;
        return this;
    }

    public Persona addIdPersona(RappresentanzaPratica rappresentanzaPratica) {
        this.idPersonas.add(rappresentanzaPratica);
        rappresentanzaPratica.setPersona(this);
        return this;
    }

    public Persona removeIdPersona(RappresentanzaPratica rappresentanzaPratica) {
        this.idPersonas.remove(rappresentanzaPratica);
        rappresentanzaPratica.setPersona(null);
        return this;
    }

    public void setIdPersonas(Set<RappresentanzaPratica> rappresentanzaPraticas) {
        this.idPersonas = rappresentanzaPraticas;
    }

    public PersonaFisica getIdPersona() {
        return idPersona;
    }

    public Persona idPersona(PersonaFisica personaFisica) {
        this.idPersona = personaFisica;
        return this;
    }

    public void setIdPersona(PersonaFisica personaFisica) {
        this.idPersona = personaFisica;
    }

    public Organizzazione getIdPersona() {
        return idPersona;
    }

    public Persona idPersona(Organizzazione organizzazione) {
        this.idPersona = organizzazione;
        return this;
    }

    public void setIdPersona(Organizzazione organizzazione) {
        this.idPersona = organizzazione;
    }

    public CondivisionePratica getIdPersona() {
        return idPersona;
    }

    public Persona idPersona(CondivisionePratica condivisionePratica) {
        this.idPersona = condivisionePratica;
        return this;
    }

    public void setIdPersona(CondivisionePratica condivisionePratica) {
        this.idPersona = condivisionePratica;
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
            ", idPersona=" + getIdPersona() +
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
