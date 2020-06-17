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
    @Column(name = "id_user_amministratore", nullable = false)
    private Long idUserAmministratore;

    @OneToMany(mappedBy = "studioProfessionale")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<RisorseDisponibili> ids = new HashSet<>();

    @OneToMany(mappedBy = "studioProfessionale")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Ordine> ids = new HashSet<>();

    @OneToOne(mappedBy = "idStudioProfessionaleRef")
    @JsonIgnore
    private Invito id;

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

    public Long getIdUserAmministratore() {
        return idUserAmministratore;
    }

    public StudioProfessionale idUserAmministratore(Long idUserAmministratore) {
        this.idUserAmministratore = idUserAmministratore;
        return this;
    }

    public void setIdUserAmministratore(Long idUserAmministratore) {
        this.idUserAmministratore = idUserAmministratore;
    }

    public Set<RisorseDisponibili> getIds() {
        return ids;
    }

    public StudioProfessionale ids(Set<RisorseDisponibili> risorseDisponibilis) {
        this.ids = risorseDisponibilis;
        return this;
    }

    public StudioProfessionale addId(RisorseDisponibili risorseDisponibili) {
        this.ids.add(risorseDisponibili);
        risorseDisponibili.setStudioProfessionale(this);
        return this;
    }

    public StudioProfessionale removeId(RisorseDisponibili risorseDisponibili) {
        this.ids.remove(risorseDisponibili);
        risorseDisponibili.setStudioProfessionale(null);
        return this;
    }

    public void setIds(Set<RisorseDisponibili> risorseDisponibilis) {
        this.ids = risorseDisponibilis;
    }

    public Set<Ordine> getIds() {
        return ids;
    }

    public StudioProfessionale ids(Set<Ordine> ordines) {
        this.ids = ordines;
        return this;
    }

    public StudioProfessionale addId(Ordine ordine) {
        this.ids.add(ordine);
        ordine.setStudioProfessionale(this);
        return this;
    }

    public StudioProfessionale removeId(Ordine ordine) {
        this.ids.remove(ordine);
        ordine.setStudioProfessionale(null);
        return this;
    }

    public void setIds(Set<Ordine> ordines) {
        this.ids = ordines;
    }

    public Invito getId() {
        return id;
    }

    public StudioProfessionale id(Invito invito) {
        this.id = invito;
        return this;
    }

    public void setId(Invito invito) {
        this.id = invito;
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
            ", idUserAmministratore=" + getIdUserAmministratore() +
            "}";
    }
}
