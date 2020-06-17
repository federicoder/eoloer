package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Organizzazione.
 */
@Entity
@Table(name = "organizzazione")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "organizzazione")
public class Organizzazione implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_organizzazione", nullable = false)
    private Integer idOrganizzazione;

    @NotNull
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private String tipo;

    @OneToOne
    @JoinColumn(unique = true)
    private Persona idPersona;

    @OneToOne(mappedBy = "id")
    @JsonIgnore
    private RuoloOrganizzazione idOrganizzazione;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdOrganizzazione() {
        return idOrganizzazione;
    }

    public Organizzazione idOrganizzazione(Integer idOrganizzazione) {
        this.idOrganizzazione = idOrganizzazione;
        return this;
    }

    public void setIdOrganizzazione(Integer idOrganizzazione) {
        this.idOrganizzazione = idOrganizzazione;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public Organizzazione idPersona(Integer idPersona) {
        this.idPersona = idPersona;
        return this;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNome() {
        return nome;
    }

    public Organizzazione nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Organizzazione tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public Organizzazione idPersona(Persona persona) {
        this.idPersona = persona;
        return this;
    }

    public void setIdPersona(Persona persona) {
        this.idPersona = persona;
    }

    public RuoloOrganizzazione getIdOrganizzazione() {
        return idOrganizzazione;
    }

    public Organizzazione idOrganizzazione(RuoloOrganizzazione ruoloOrganizzazione) {
        this.idOrganizzazione = ruoloOrganizzazione;
        return this;
    }

    public void setIdOrganizzazione(RuoloOrganizzazione ruoloOrganizzazione) {
        this.idOrganizzazione = ruoloOrganizzazione;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organizzazione)) {
            return false;
        }
        return id != null && id.equals(((Organizzazione) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Organizzazione{" +
            "id=" + getId() +
            ", idOrganizzazione=" + getIdOrganizzazione() +
            ", idPersona=" + getIdPersona() +
            ", nome='" + getNome() + "'" +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
