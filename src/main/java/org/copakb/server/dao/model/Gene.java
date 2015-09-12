package org.copakb.server.dao.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Gene model
 * Created by Kevin on 4/30/2015.
 */
@Entity
@Table(name = "gene")
public class Gene {
    private String gene_name;
    private String ensembl_id;
    private Set<HPAProtein> hpaProteins;
    private Set<Disease> diseases;
    private Set<ProteinCurrent> proteins;

    public Gene(String gene_name, String ensembl_id, Set<HPAProtein> hpaProteins, Set<Disease> diseases,
                Set<ProteinCurrent> proteins) {
        this.gene_name = gene_name;
        this.ensembl_id = ensembl_id;
        this.hpaProteins = hpaProteins;
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

    @Column(name = "ensembl_id")
    public String getEnsembl_id() {
        return ensembl_id;
    }

    public void setEnsembl_id(String ensembl_id) {
        this.ensembl_id = ensembl_id;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ensembl_id")
    public Set<HPAProtein> getHpaProteins() {
        return hpaProteins;
    }

    public void setHpaProteins(Set<HPAProtein> hpas1) {
        this.hpaProteins = hpas1;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "disease_gene", joinColumns = {
            @JoinColumn(name = "gene_name", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "DOID",
                    nullable = false, updatable = false)})
    public Set<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<Disease> diseases) {
        this.diseases = diseases;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "protein_gene", joinColumns = {
            @JoinColumn(name = "gene_name", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "protein_acc",
                    nullable = false, updatable = false)})
    public Set<ProteinCurrent> getProteins() {
        return proteins;
    }

    public void setProteins(Set<ProteinCurrent> proteins) {
        this.proteins = proteins;
    }

    @Override
    public String toString() {
        return "gene_name: " + gene_name + "\n" +
                "ensembl_id: " + ensembl_id + "\n" + "disease: ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gene gene = (Gene) o;

        if (!gene_name.equals(gene.gene_name)) return false;
        return ensembl_id.equals(gene.ensembl_id);

    }

    @Override
    public int hashCode() {
        int result = gene_name.hashCode();
        result = 31 * result + ensembl_id.hashCode();
        return result;
    }
}
