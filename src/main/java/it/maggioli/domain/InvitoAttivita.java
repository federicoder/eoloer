package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A InvitoAttivita.
 */
@Entity
@Table(name = "invito_attivita")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "invitoattivita")
public class InvitoAttivita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_attivita")
    private Integer idAttivita;

    @OneToOne
    @JoinColumn(unique = true)
    private Invito idAttivita;

    @OneToOne(mappedBy = "id")
    @JsonIgnore
    private Task idAttivita;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdAttivita() {
        return idAttivita;
    }

    public InvitoAttivita idAttivita(Integer idAttivita) {
        this.idAttivita = idAttivita;
        return this;
    }

    public void setIdAttivita(Integer idAttivita) {
        this.idAttivita = idAttivita;
    }

    public Invito getIdAttivita() {
        return idAttivita;
    }

    public InvitoAttivita idAttivita(Invito invito) {
        this.idAttivita = invito;
        return this;
    }

    public void setIdAttivita(Invito invito) {
        this.idAttivita = invito;
    }

    public Task getIdAttivita() {
        return idAttivita;
    }

    public InvitoAttivita idAttivita(Task task) {
        this.idAttivita = task;
        return this;
    }

    public void setIdAttivita(Task task) {
        this.idAttivita = task;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvitoAttivita)) {
            return false;
        }
        return id != null && id.equals(((InvitoAttivita) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvitoAttivita{" +
            "id=" + getId() +
            ", idAttivita=" + getIdAttivita() +
            "}";
    }
}
