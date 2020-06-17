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
    @Column(name = "id_persona_fisica", nullable = false)
    private Integer idPersonaFisica;

    @NotNull
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Column(name = "id_ruolo_persona")
    private Integer idRuoloPersona;

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
    private Persona idPersona;

    @OneToMany(mappedBy = "personaFisica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<UserPersona> idPersonaFisicas = new HashSet<>();

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

    public Integer getIdPersonaFisica() {
        return idPersonaFisica;
    }

    public PersonaFisica idPersonaFisica(Integer idPersonaFisica) {
        this.idPersonaFisica = idPersonaFisica;
        return this;
    }

    public void setIdPersonaFisica(Integer idPersonaFisica) {
        this.idPersonaFisica = idPersonaFisica;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public PersonaFisica idPersona(Integer idPersona) {
        this.idPersona = idPersona;
        return this;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdRuoloPersona() {
        return idRuoloPersona;
    }

    public PersonaFisica idRuoloPersona(Integer idRuoloPersona) {
        this.idRuoloPersona = idRuoloPersona;
        return this;
    }

    public void setIdRuoloPersona(Integer idRuoloPersona) {
        this.idRuoloPersona = idRuoloPersona;
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

    public Persona getIdPersona() {
        return idPersona;
    }

    public PersonaFisica idPersona(Persona persona) {
        this.idPersona = persona;
        return this;
    }

    public void setIdPersona(Persona persona) {
        this.idPersona = persona;
    }

    public Set<UserPersona> getIdPersonaFisicas() {
        return idPersonaFisicas;
    }

    public PersonaFisica idPersonaFisicas(Set<UserPersona> userPersonas) {
        this.idPersonaFisicas = userPersonas;
        return this;
    }

    public PersonaFisica addIdPersonaFisica(UserPersona userPersona) {
        this.idPersonaFisicas.add(userPersona);
        userPersona.setPersonaFisica(this);
        return this;
    }

    public PersonaFisica removeIdPersonaFisica(UserPersona userPersona) {
        this.idPersonaFisicas.remove(userPersona);
        userPersona.setPersonaFisica(null);
        return this;
    }

    public void setIdPersonaFisicas(Set<UserPersona> userPersonas) {
        this.idPersonaFisicas = userPersonas;
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
            ", idPersonaFisica=" + getIdPersonaFisica() +
            ", idPersona=" + getIdPersona() +
            ", idRuoloPersona=" + getIdRuoloPersona() +
            ", titolo='" + getTitolo() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", nome='" + getNome() + "'" +
            ", dataDiNascita='" + getDataDiNascita() + "'" +
            ", luogoDiNascita='" + getLuogoDiNascita() + "'" +
            ", professione='" + getProfessione() + "'" +
            "}";
    }
}
