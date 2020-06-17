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
 * A StudioProfessionale.
 */
@Entity
@Table(name = "studio_professionale")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "studioprofessionale")
public class StudioProfessionale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_studio_professionale", nullable = false)
    private Integer idStudioProfessionale;

    @NotNull
    @Column(name = "id_user_amministratore", nullable = false)
    private Integer idUserAmministratore;

    @OneToMany(mappedBy = "studioProfessionale")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<RisorseDisponibili> idStudioProfessionales = new HashSet<>();

    @OneToMany(mappedBy = "studioProfessionale")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Ordine> idStudioProfessionales = new HashSet<>();

    @OneToOne(mappedBy = "idStudioProfessionaleRef")
    @JsonIgnore
    private Invito idStudioProfessionale;

    @ManyToOne
    @JsonIgnoreProperties(value = "idPersonas", allowSetters = true)
    private Persona persona;

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

    public StudioProfessionale idStudioProfessionale(Integer idStudioProfessionale) {
        this.idStudioProfessionale = idStudioProfessionale;
        return this;
    }

    public void setIdStudioProfessionale(Integer idStudioProfessionale) {
        this.idStudioProfessionale = idStudioProfessionale;
    }

    public Integer getIdUserAmministratore() {
        return idUserAmministratore;
    }

    public StudioProfessionale idUserAmministratore(Integer idUserAmministratore) {
        this.idUserAmministratore = idUserAmministratore;
        return this;
    }

    public void setIdUserAmministratore(Integer idUserAmministratore) {
        this.idUserAmministratore = idUserAmministratore;
    }

    public Set<RisorseDisponibili> getIdStudioProfessionales() {
        return idStudioProfessionales;
    }

    public StudioProfessionale idStudioProfessionales(Set<RisorseDisponibili> risorseDisponibilis) {
        this.idStudioProfessionales = risorseDisponibilis;
        return this;
    }

    public StudioProfessionale addIdStudioProfessionale(RisorseDisponibili risorseDisponibili) {
        this.idStudioProfessionales.add(risorseDisponibili);
        risorseDisponibili.setStudioProfessionale(this);
        return this;
    }

    public StudioProfessionale removeIdStudioProfessionale(RisorseDisponibili risorseDisponibili) {
        this.idStudioProfessionales.remove(risorseDisponibili);
        risorseDisponibili.setStudioProfessionale(null);
        return this;
    }

    public void setIdStudioProfessionales(Set<RisorseDisponibili> risorseDisponibilis) {
        this.idStudioProfessionales = risorseDisponibilis;
    }

    public Set<Ordine> getIdStudioProfessionales() {
        return idStudioProfessionales;
    }

    public StudioProfessionale idStudioProfessionales(Set<Ordine> ordines) {
        this.idStudioProfessionales = ordines;
        return this;
    }

    public StudioProfessionale addIdStudioProfessionale(Ordine ordine) {
        this.idStudioProfessionales.add(ordine);
        ordine.setStudioProfessionale(this);
        return this;
    }

    public StudioProfessionale removeIdStudioProfessionale(Ordine ordine) {
        this.idStudioProfessionales.remove(ordine);
        ordine.setStudioProfessionale(null);
        return this;
    }

    public void setIdStudioProfessionales(Set<Ordine> ordines) {
        this.idStudioProfessionales = ordines;
    }

    public Invito getIdStudioProfessionale() {
        return idStudioProfessionale;
    }

    public StudioProfessionale idStudioProfessionale(Invito invito) {
        this.idStudioProfessionale = invito;
        return this;
    }

    public void setIdStudioProfessionale(Invito invito) {
        this.idStudioProfessionale = invito;
    }

    public Persona getPersona() {
        return persona;
    }

    public StudioProfessionale persona(Persona persona) {
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
        if (!(o instanceof StudioProfessionale)) {
            return false;
        }
        return id != null && id.equals(((StudioProfessionale) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StudioProfessionale{" +
            "id=" + getId() +
            ", idStudioProfessionale=" + getIdStudioProfessionale() +
            ", idUserAmministratore=" + getIdUserAmministratore() +
            "}";
    }
}
