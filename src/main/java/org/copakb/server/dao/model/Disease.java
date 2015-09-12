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
    private int DOID;
    private String name;
    private String description;
    private boolean heart_disease;
    private Set<Gene> genes;
    private Set<Gene2> gene;

    public Disease() {

    }

    public Disease(int DOID, String name, String description, boolean heart_disease, Set<Gene> genes) {
        this.DOID = DOID;
        this.name = name;
        this.description = description;
        this.heart_disease = heart_disease;
        this.genes = genes;
    }

    @Id
    @Column(name = "DOID")
    public int getDOID() {
        return DOID;
    }

    public void setDOID(int DOID) {
        this.DOID = DOID;
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
            @JoinColumn(name = "DOID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "gene_name",
                    nullable = false, updatable = false)})
    public Set<Gene> getGenes() {
        return genes;
    }

    public void setGenes(Set<Gene> genes) {
        this.genes = genes;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "disease_gene2", joinColumns = {
            @JoinColumn(name = "DOID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ensembl_id",
                    nullable = false, updatable = false)})
    public Set<Gene2> getGene() {
        return gene;
    }

    public void setGene(Set<Gene2> genes) {
        this.gene = gene;
    }

    /**
     * Initializes the model's lazy loaded objects.
     */
    @Override
    public Disease initialize() {
        Disease initialized = DAOObject.getInstance().getDiseaseDAO().getInitializedDisease(DOID);
        if (initialized != null) {
            setGenes(initialized.getGenes());
        }

        return initialized;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "DOID=" + DOID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", heart_disease='" + heart_disease + '\'' +
                //", genes=" + getGenes() +
                '}';
    }
}
