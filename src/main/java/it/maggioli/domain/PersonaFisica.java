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
 * A PersonaFisica.
 */
@Entity
@Table(name = "persona_fisica")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "personafisica")
public class PersonaFisica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_persona_ref", nullable = false)
    private Long idPersonaRef;

    @Column(name = "id_ruolo_persona_ref")
    private Long idRuoloPersonaRef;

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

    @OneToOne
    @JoinColumn(unique = true)
    private Persona idPersonaRef;

    @OneToMany(mappedBy = "personaFisica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<UserPersona> ids = new HashSet<>();

    @OneToOne(mappedBy = "id")
    @JsonIgnore
    private RuoloOrganizzazione idRuoloPersona;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPersonaRef() {
        return idPersonaRef;
    }

    public PersonaFisica idPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
        return this;
    }

    public void setIdPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Long getIdRuoloPersonaRef() {
        return idRuoloPersonaRef;
    }

    public PersonaFisica idRuoloPersonaRef(Long idRuoloPersonaRef) {
        this.idRuoloPersonaRef = idRuoloPersonaRef;
        return this;
    }

    public void setIdRuoloPersonaRef(Long idRuoloPersonaRef) {
        this.idRuoloPersonaRef = idRuoloPersonaRef;
    }

    public String getTitolo() {
        return titolo;
    }

    public PersonaFisica titolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCognome() {
        return cognome;
    }

    public PersonaFisica cognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public PersonaFisica nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public PersonaFisica dataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
        return this;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getLuogoDiNascita() {
        return luogoDiNascita;
    }

    public PersonaFisica luogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
        return this;
    }

    public void setLuogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
    }

    public String getProfessione() {
        return professione;
    }

    public PersonaFisica professione(String professione) {
        this.professione = professione;
        return this;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public Persona getIdPersonaRef() {
        return idPersonaRef;
    }

    public PersonaFisica idPersonaRef(Persona persona) {
        this.idPersonaRef = persona;
        return this;
    }

    public void setIdPersonaRef(Persona persona) {
        this.idPersonaRef = persona;
    }

    public Set<UserPersona> getIds() {
        return ids;
    }

    public PersonaFisica ids(Set<UserPersona> userPersonas) {
        this.ids = userPersonas;
        return this;
    }

    public PersonaFisica addId(UserPersona userPersona) {
        this.ids.add(userPersona);
        userPersona.setPersonaFisica(this);
        return this;
    }

    public PersonaFisica removeId(UserPersona userPersona) {
        this.ids.remove(userPersona);
        userPersona.setPersonaFisica(null);
        return this;
    }

    public void setIds(Set<UserPersona> userPersonas) {
        this.ids = userPersonas;
    }

    public RuoloOrganizzazione getIdRuoloPersona() {
        return idRuoloPersona;
    }

    public PersonaFisica idRuoloPersona(RuoloOrganizzazione ruoloOrganizzazione) {
        this.idRuoloPersona = ruoloOrganizzazione;
        return this;
    }

    public void setIdRuoloPersona(RuoloOrganizzazione ruoloOrganizzazione) {
        this.idRuoloPersona = ruoloOrganizzazione;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonaFisica)) {
            return false;
        }
        return id != null && id.equals(((PersonaFisica) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonaFisica{" +
            "id=" + getId() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", idRuoloPersonaRef=" + getIdRuoloPersonaRef() +
            ", titolo='" + getTitolo() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", nome='" + getNome() + "'" +
            ", dataDiNascita='" + getDataDiNascita() + "'" +
            ", luogoDiNascita='" + getLuogoDiNascita() + "'" +
            ", professione='" + getProfessione() + "'" +
            "}";
    }
}
