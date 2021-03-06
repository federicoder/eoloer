package it.maggioli.domain;

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

    @Column(name = "id_task_ref")
    private Long idTaskRef;

    @OneToOne
    @JoinColumn(unique = true)
    private Task idTask;

    @OneToOne
    @JoinColumn(unique = true)
    private Invito idTask;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTaskRef() {
        return idTaskRef;
    }

    public InvitoAttivita idTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
        return this;
    }

    public void setIdTaskRef(Long idTaskRef) {
        this.idTaskRef = idTaskRef;
    }

    public Task getIdTask() {
        return idTask;
    }

    public InvitoAttivita idTask(Task task) {
        this.idTask = task;
        return this;
    }

    public void setIdTask(Task task) {
        this.idTask = task;
    }

    public Invito getIdTask() {
        return idTask;
    }

    public InvitoAttivita idTask(Invito invito) {
        this.idTask = invito;
        return this;
    }

    public void setIdTask(Invito invito) {
        this.idTask = invito;
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
            ", idTaskRef=" + getIdTaskRef() +
            "}";
    }
}
