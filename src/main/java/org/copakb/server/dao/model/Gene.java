package org.copakb.server.dao.model;

import org.copakb.server.dao.DAOObject;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.Set;

/**
 * Gene model.
 * Created by Alan on 8/6/2015.
 */
@Entity
@Table(name = "gene")
public class Gene {
    private String ensembl_id;
    private String gene_symbol;
    private String chromosome;
    private Species species;
    private Set<ProteinCurrent> proteins;
    private Set<Disease> diseases;
    private Set<DiseaseGene> diseaseGenes;
    private Set<HPAProtein> hpaProteins;

    @Id
    @Column(name = "ensembl_id")
    public String getEnsembl_id() {
        return ensembl_id;
    }

    public void setEnsembl_id(String ensembl_id) {
        this.ensembl_id = ensembl_id;
    }

    @Column(name = "gene_symbol")
    public String getGene_symbol() {
        return gene_symbol;
    }

    public void setGene_symbol(String gene_symbol) {
        this.gene_symbol = gene_symbol;
    }

    @Column(name = "chromosome")
    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "species_id", nullable = false)
    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "protein_gene", joinColumns = {
            @JoinColumn(name = "ensembl_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "protein_acc",
                    nullable = false, updatable = false)})
    public Set<ProteinCurrent> getProteins() {
        return proteins;
    }

    public void setProteins(Set<ProteinCurrent> proteins) {
        this.proteins = proteins;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "disease_gene", joinColumns = {
            @JoinColumn(name = "ensembl_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "disease_id",
                    nullable = false, updatable = false)})
    public Set<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<Disease> diseases) {
        this.diseases = diseases;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gene")
    public Set<DiseaseGene> getDiseaseGenes() { return this.diseaseGenes; }

    public void setDiseaseGenes(Set<DiseaseGene> diseaseGenes) {
        this.diseaseGenes = diseaseGenes;
    }

    public void initializeDiseaseGenes() {
        Gene g = DAOObject.getInstance().getProteinDAO().searchGeneInitialized(ensembl_id);
        setDiseaseGenes(g.getDiseaseGenes());
    }

    @Override
    public String toString() {
        return "Gene{" +
                "ensembl_id='" + ensembl_id + '\'' +
                ", gene_symbol='" + gene_symbol + '\'' +
                ", chromosome='" + chromosome + '\'' +
                ", species=" + species +
                ", diseases=" + diseases +
                '}';
    }
}
