package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A EmailPersona.
 */
@Entity
@Table(name = "email_persona")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "emailpersona")
public class EmailPersona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_persona_ref", nullable = false)
    private Long idPersonaRef;

    @Column(name = "etichetta")
    private Long etichetta;

    @Column(name = "numero")
    private Long numero;

    @ManyToOne
    @JsonIgnoreProperties(value = "emailPersonas", allowSetters = true)
    private Persona idPersona;

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

    public EmailPersona idPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
        return this;
    }

    public void setIdPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Long getEtichetta() {
        return etichetta;
    }

    public EmailPersona etichetta(Long etichetta) {
        this.etichetta = etichetta;
        return this;
    }

    public void setEtichetta(Long etichetta) {
        this.etichetta = etichetta;
    }

    public Long getNumero() {
        return numero;
    }

    public EmailPersona numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public EmailPersona idPersona(Persona persona) {
        this.idPersona = persona;
        return this;
    }

    public void setIdPersona(Persona persona) {
        this.idPersona = persona;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailPersona)) {
            return false;
        }
        return id != null && id.equals(((EmailPersona) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmailPersona{" +
            "id=" + getId() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", etichetta=" + getEtichetta() +
            ", numero=" + getNumero() +
            "}";
    }
}
