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
    private Set<DiseaseGene> disease_genes = new HashSet<DiseaseGene>(0);
    private Set<ProteinGene> protein_genes = new HashSet<ProteinGene>(0);

    public Gene(Set<ProteinGene> protein_genes, Set<DiseaseGene> disease_genes) {
        this.protein_genes = protein_genes;
        this.disease_genes = disease_genes;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gene")
    public Set<DiseaseGene> getDisease_genes() {
        return disease_genes;
    }
    public void setDisease_genes(Set<DiseaseGene> disease_genes) {
        this.disease_genes = disease_genes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proteinCurrent")
    public Set<ProteinGene> getProtein_genes() {
        return protein_genes;
    }
    public void setProtein_genes(Set<ProteinGene> protein_genes) {
        this.protein_genes = protein_genes;
    }
}
