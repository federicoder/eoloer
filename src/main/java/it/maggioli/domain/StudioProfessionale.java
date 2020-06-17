package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

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

    @ManyToOne
    @JsonIgnoreProperties(value = "studioProfessionales", allowSetters = true)
    private Persona idUserAmministratore;

    @OneToOne(mappedBy = "idStudioProfessionaleRef")
    @JsonIgnore
    private Invito id;

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

    public Persona getIdUserAmministratore() {
        return idUserAmministratore;
    }

    public StudioProfessionale idUserAmministratore(Persona persona) {
        this.idUserAmministratore = persona;
        return this;
    }

    public void setIdUserAmministratore(Persona persona) {
        this.idUserAmministratore = persona;
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
