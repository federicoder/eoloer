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
    private Integer idRuoloPersona;

    @NotNull
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Enumerated(EnumType.STRING)
    @Column(name = "ruoli")
    private Ruoli ruoli;

    @OneToOne(mappedBy = "ruolo")
    @JsonIgnore
    private CondivisionePratica idRuoloPersona;

    @OneToOne(mappedBy = "ruolo")
    @JsonIgnore
    private AssegnazioneTask idRuoloPersona;

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private Persona persona;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdRuoloPersona() {
        return idRuoloPersona;
    }

    public RappresentanzaPratica idRuoloPersona(Integer idRuoloPersona) {
        this.idRuoloPersona = idRuoloPersona;
        return this;
    }

    public void setIdRuoloPersona(Integer idRuoloPersona) {
        this.idRuoloPersona = idRuoloPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public RappresentanzaPratica idPersona(Integer idPersona) {
        this.idPersona = idPersona;
        return this;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
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

    public Persona getPersona() {
        return persona;
    }

    public RappresentanzaPratica persona(Persona persona) {
        this.persona = persona;
        return this;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
            ", idPersona=" + getIdPersona() +
            ", ruoli='" + getRuoli() + "'" +
            "}";
    }
}
