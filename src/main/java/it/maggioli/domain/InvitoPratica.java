package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A InvitoPratica.
 */
@Entity
@Table(name = "invito_pratica")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "invitopratica")
public class InvitoPratica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_pratica")
    private Integer idPratica;

    @OneToOne
    @JoinColumn(unique = true)
    private Invito idPratica;

    @ManyToOne
    @JsonIgnoreProperties(value = "ids", allowSetters = true)
    private Pratica pratica;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPratica() {
        return idPratica;
    }

    public InvitoPratica idPratica(Integer idPratica) {
        this.idPratica = idPratica;
        return this;
    }

    public void setIdPratica(Integer idPratica) {
        this.idPratica = idPratica;
    }

    public Invito getIdPratica() {
        return idPratica;
    }

    public InvitoPratica idPratica(Invito invito) {
        this.idPratica = invito;
        return this;
    }

    public void setIdPratica(Invito invito) {
        this.idPratica = invito;
    }

    public Pratica getPratica() {
        return pratica;
    }

    public InvitoPratica pratica(Pratica pratica) {
        this.pratica = pratica;
        return this;
    }

    public void setPratica(Pratica pratica) {
        this.pratica = pratica;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvitoPratica)) {
            return false;
        }
        return id != null && id.equals(((InvitoPratica) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvitoPratica{" +
            "id=" + getId() +
            ", idPratica=" + getIdPratica() +
            "}";
    }
}
