package org.copakb.server.dao.model;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by Kevin on 4/30/2015.
 */

@Entity
@Table(name = "Disease")
public class Disease {
    private int DOID;
    private String name;
    private String description;
    private String heart_disease;
    private Set<DiseaseGene> disease_genes = new HashSet<DiseaseGene>(0);

    public Disease(String name, String description, String heart_disease, Set<DiseaseGene> diseaseGenes) {
        this.name = name;
        this.description = description;
        this.heart_disease = heart_disease;
        this.disease_genes = disease_genes;
    }

    public Disease() {
        //empty
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
    public String getHeart_disease() {
        return heart_disease;
    }
    public void setHeart_disease(String heart_disease) {
        this.heart_disease = heart_disease;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "disease")
    public Set<DiseaseGene> getDisease_genes() {
        return disease_genes;
    }
    public void setDisease_genes(Set<DiseaseGene> disease_genes) {
        this.disease_genes = disease_genes;
    }
}
