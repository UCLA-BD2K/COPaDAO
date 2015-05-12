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
    private Set<Gene> genes;

    public Disease(int DOID, String name, String description, String heart_disease, Set<Gene> genes) {
        this.DOID = DOID;
        this.name = name;
        this.description = description;
        this.heart_disease = heart_disease;
        this.genes = genes;
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


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Disease_Gene", joinColumns = {
            @JoinColumn(name = "DOID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "gene_name",
                    nullable = false, updatable = false) })
    public Set<Gene> getGenes() {
        return genes;
    }

    public void setGenes(Set<Gene> genes) {
        this.genes = genes;
    }
}
