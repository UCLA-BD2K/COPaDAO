package org.copakb.server.dao.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kevin on 4/30/2015.
 */
@Entity
@Table(name = "Gene")
public class Gene {
    private String gene_name;
    private Set<Disease> diseases;
    private Set<ProteinCurrent> proteins;

    public Gene(String gene_name, Set<Disease> diseases, Set<ProteinCurrent> proteins) {
        this.gene_name = gene_name;
        this.diseases = diseases;
        this.proteins = proteins;
    }

    public Gene() {
        //empty
    }

    @Id
    @Column(name = "gene_name")
    public String getGene_name() {
        return gene_name;
    }
    public void setGene_name(String gene_name) {
        this.gene_name = gene_name;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Disease_Gene", joinColumns = {
            @JoinColumn(name = "gene_name", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "DOID",
                    nullable = false, updatable = false) })
    public Set<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<Disease> diseases) {
        this.diseases = diseases;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Protein_Gene", joinColumns = {
            @JoinColumn(name = "gene_name", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "protein_acc",
                    nullable = false, updatable = false) })
    public Set<ProteinCurrent> getProteins() {
        return proteins;
    }

    public void setProteins(Set<ProteinCurrent> proteins) {
        this.proteins = proteins;
    }
}
