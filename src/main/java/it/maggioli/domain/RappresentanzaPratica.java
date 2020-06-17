package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

import it.maggioli.domain.enumeration.Ruoli;

/**
 * A RappresentanzaPratica.
 */
@Entity
@Table(name = "rappresentanza_pratica")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "rappresentanzapratica")
public class RappresentanzaPratica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_ruolo_persona", nullable = false)
    private Long idRuoloPersona;

    @NotNull
    @Column(name = "id_persona_ref", nullable = false)
    private Long idPersonaRef;

    @Enumerated(EnumType.STRING)
    @Column(name = "ruoli")
    private Ruoli ruoli;

    @ManyToOne
    @JsonIgnoreProperties(value = "rappresentanzaPraticas", allowSetters = true)
    private Persona idPersonaRef;

    @OneToOne(mappedBy = "ruolo")
    @JsonIgnore
    private CondivisionePratica idRuoloPersona;

    @OneToOne(mappedBy = "ruolo")
    @JsonIgnore
    private AssegnazioneTask idRuoloPersona;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdRuoloPersona() {
        return idRuoloPersona;
    }

    public RappresentanzaPratica idRuoloPersona(Long idRuoloPersona) {
        this.idRuoloPersona = idRuoloPersona;
        return this;
    }

    public void setIdRuoloPersona(Long idRuoloPersona) {
        this.idRuoloPersona = idRuoloPersona;
    }

    public Long getIdPersonaRef() {
        return idPersonaRef;
    }

    public RappresentanzaPratica idPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
        return this;
    }

    public void setIdPersonaRef(Long idPersonaRef) {
        this.idPersonaRef = idPersonaRef;
    }

    public Ruoli getRuoli() {
        return ruoli;
    }

    public RappresentanzaPratica ruoli(Ruoli ruoli) {
        this.ruoli = ruoli;
        return this;
    }

    public void setRuoli(Ruoli ruoli) {
        this.ruoli = ruoli;
    }

    public Persona getIdPersonaRef() {
        return idPersonaRef;
    }

    public RappresentanzaPratica idPersonaRef(Persona persona) {
        this.idPersonaRef = persona;
        return this;
    }

    public void setIdPersonaRef(Persona persona) {
        this.idPersonaRef = persona;
    }

    public CondivisionePratica getIdRuoloPersona() {
        return idRuoloPersona;
    }

    public RappresentanzaPratica idRuoloPersona(CondivisionePratica condivisionePratica) {
        this.idRuoloPersona = condivisionePratica;
        return this;
    }

    public void setIdRuoloPersona(CondivisionePratica condivisionePratica) {
        this.idRuoloPersona = condivisionePratica;
    }

    public AssegnazioneTask getIdRuoloPersona() {
        return idRuoloPersona;
    }

    public RappresentanzaPratica idRuoloPersona(AssegnazioneTask assegnazioneTask) {
        this.idRuoloPersona = assegnazioneTask;
        return this;
    }

    public void setIdRuoloPersona(AssegnazioneTask assegnazioneTask) {
        this.idRuoloPersona = assegnazioneTask;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RappresentanzaPratica)) {
            return false;
        }
        return id != null && id.equals(((RappresentanzaPratica) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RappresentanzaPratica{" +
            "id=" + getId() +
            ", idRuoloPersona=" + getIdRuoloPersona() +
            ", idPersonaRef=" + getIdPersonaRef() +
            ", ruoli='" + getRuoli() + "'" +
            "}";
    }
}
