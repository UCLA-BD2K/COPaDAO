package org.copakb.server.dao.model;

import org.copakb.server.dao.DAOObject;

import javax.persistence.*;
import java.util.Set;

/**
 * Disease model
 * Created by Kevin on 4/30/2015.
 */
@Entity
@Table(name = "disease")
public class Disease extends Model {
    private String disease_id;
    private String name;
    private String description;
    private boolean heart_disease;
    private Set<Gene> genes;
    private String disease_url;

    public Disease() {

    }

    public Disease(String DOID, String name, String description, boolean heart_disease, Set<Gene> genes,
                    String disease_url) {
        this.disease_id = DOID;
        this.name = name;
        this.description = description;
        this.heart_disease = heart_disease;
        this.genes = genes;
        this.disease_url = disease_url;
    }

    @Id
    @Column(name = "disease_id")
    public String getDOID() {
        return disease_id;
    }

    public void setDOID(String DOID) {
        this.disease_id = DOID;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "heart_disease")
    public boolean getHeart_disease() {
        return heart_disease;
    }

    public void setHeart_disease(boolean heart_disease) {
        this.heart_disease = heart_disease;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "disease_gene", joinColumns = {
            @JoinColumn(name = "disease_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ensembl_id",
                    nullable = false, updatable = false)})
    public Set<Gene> getGenes() {
        return genes;
    }

    public void setGenes(Set<Gene> genes) {
        this.genes = genes;
    }

    @Column(name = "disease_url")
    public String getDiseaseUrl() {
        return this.disease_url;
    }

    public void setDiseaseUrl(String diseaseUrl) {
        this.disease_url = diseaseUrl;
    }

    /**
     * Initializes the model's lazy loaded objects.
     */
    @Override
    public Disease initialize() {
        Disease initialized = DAOObject.getInstance().getDiseaseDAO().getInitializedDisease(disease_id);
        if (initialized != null) {
            setGenes(initialized.getGenes());
        }

        return initialized;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "disease_id=" + disease_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", heart_disease='" + heart_disease + '\'' +
                //", genes=" + getGenes() +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        // two diseases are equal if they have the same disease_id
        if (o instanceof Disease) {
            if (((Disease) o).getDOID().equals(this.getDOID())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {

        // cheating, but for our purposes collisions should be rare
        return disease_id.hashCode();
    }
}
