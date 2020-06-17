package it.maggioli.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A NotaPratica.
 */
@Entity
@Table(name = "nota_pratica")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "notapratica")
public class NotaPratica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Max(value = 8)
    @Column(name = "id_nota_pratica", nullable = false)
    private Integer idNotaPratica;

    @Max(value = 8)
    @Column(name = "id_pratica_ref")
    private Integer idPraticaRef;

    @Column(name = "data")
    private String data;

    @Column(name = "nota")
    private String nota;

    @Column(name = "version")
    private String version;

    @ManyToOne
    @JsonIgnoreProperties(value = "idPraticas", allowSetters = true)
    private Pratica pratica;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdNotaPratica() {
        return idNotaPratica;
    }

    public NotaPratica idNotaPratica(Integer idNotaPratica) {
        this.idNotaPratica = idNotaPratica;
        return this;
    }

    public void setIdNotaPratica(Integer idNotaPratica) {
        this.idNotaPratica = idNotaPratica;
    }

    public Integer getIdPraticaRef() {
        return idPraticaRef;
    }

    public NotaPratica idPraticaRef(Integer idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
        return this;
    }

    public void setIdPraticaRef(Integer idPraticaRef) {
        this.idPraticaRef = idPraticaRef;
    }

    public String getData() {
        return data;
    }

    public NotaPratica data(String data) {
        this.data = data;
        return this;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNota() {
        return nota;
    }

    public NotaPratica nota(String nota) {
        this.nota = nota;
        return this;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getVersion() {
        return version;
    }

    public NotaPratica version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Pratica getPratica() {
        return pratica;
    }

    public NotaPratica pratica(Pratica pratica) {
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
        if (!(o instanceof NotaPratica)) {
            return false;
        }
        return id != null && id.equals(((NotaPratica) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotaPratica{" +
            "id=" + getId() +
            ", idNotaPratica=" + getIdNotaPratica() +
            ", idPraticaRef=" + getIdPraticaRef() +
            ", data='" + getData() + "'" +
            ", nota='" + getNota() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
