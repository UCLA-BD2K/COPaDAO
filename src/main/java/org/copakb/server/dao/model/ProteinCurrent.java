package org.copakb.server.dao.model;

import org.copakb.server.dao.DAOObject;

import javax.persistence.*;
import java.util.Set;

/**
 * ProteinCurrent model
 * Created by Kevin on 4/30/2015.
 */
@Entity
@Table(name = "Protein_Current")
public class ProteinCurrent {
    private String protein_acc;
    private String sequence;
    private String protein_name;
    private double molecular_weight;
    private String transmembrane_domain;
    private String cytoplasmatic_domain;
    private String noncytoplasmatic_domain;
    private String signal_peptide;
    private DBRef dbRef;
    private String keywords;
    private String feature_table;
    private Species species;
    private String chromosome;
    private Set<Gene> genes;
    private Set<GoTerms> goTerms;
    private Set<PTM> PTMs;
    private Set<SpectrumProtein> spectra;

    @Id
    @Column(name = "protein_acc")
    public String getProtein_acc() {
        return protein_acc;
    }

    public void setProtein_acc(String protein_acc) {
        this.protein_acc = protein_acc;
    }

    @Column(name = "sequence")
    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Column(name = "protein_name")
    public String getProtein_name() {
        return protein_name;
    }

    public void setProtein_name(String protein_name) {
        this.protein_name = protein_name;
    }

    @Column(name = "molecular_weight")
    public double getMolecular_weight() {
        return molecular_weight;
    }

    public void setMolecular_weight(double molecular_weight) {
        this.molecular_weight = molecular_weight;
    }

    @Column(name = "transmembrane_domain")
    public String getTransmembrane_domain() {
        return transmembrane_domain;
    }

    public void setTransmembrane_domain(String transmembrane_domain) {
        this.transmembrane_domain = transmembrane_domain;
    }

    @Column(name = "cytoplasmatic_domain")
    public String getCytoplasmatic_domain() {
        return cytoplasmatic_domain;
    }

    public void setCytoplasmatic_domain(String cytoplasmatic_domain) {
        this.cytoplasmatic_domain = cytoplasmatic_domain;
    }

    @Column(name = "noncytoplasmatic_domain")
    public String getNoncytoplasmatic_domain() {
        return noncytoplasmatic_domain;
    }

    public void setNoncytoplasmatic_domain(String noncytoplasmatic_domain) {
        this.noncytoplasmatic_domain = noncytoplasmatic_domain;
    }

    @Column(name = "signal_peptide")
    public String getSignal_peptide() {
        return signal_peptide;
    }

    public void setSignal_peptide(String signal_peptide) {
        this.signal_peptide = signal_peptide;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "proteinCurrent", cascade = CascadeType.ALL, optional = false)
    public DBRef getDbRef() {
        return dbRef;
    }

    public void setDbRef(DBRef dbRef) {
        this.dbRef = dbRef;
    }

    @Column(name = "keywords")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Column(name = "feature_table")
    public String getFeature_table() {
        return feature_table;
    }

    public void setFeature_table(String feature_table) {
        this.feature_table = feature_table;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "species_id", nullable = false)
    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    @Column(name = "chromosome")
    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Protein_Gene", joinColumns = {
            @JoinColumn(name = "protein_acc", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "gene_name",
                    nullable = false, updatable = false)})
    public Set<Gene> getGenes() {
        return genes;
    }

    public void setGenes(Set<Gene> genes) {
        this.genes = genes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proteinCurrent")
    public Set<PTM> getPTMs() {
        return PTMs;
    }

    public void setPTMs(Set<PTM> PTMs) {
        this.PTMs = PTMs;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "GO_Protein", joinColumns = {
            @JoinColumn(name = "protein_acc", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "GO_accession",
                    nullable = false, updatable = false)})
    public Set<GoTerms> getGoTerms() {
        return goTerms;
    }

    public void setGoTerms(Set<GoTerms> goTerms) {
        this.goTerms = goTerms;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "protein")
    public Set<SpectrumProtein> getSpectra() {
        return spectra;
    }

    public void setSpectra(Set<SpectrumProtein> spectra) {
        this.spectra = spectra;
    }

    public void initialize() {
        ProteinCurrent initializedProtein = DAOObject.getInstance().getProteinDAO().getInitializedProtein(protein_acc);
        if (initializedProtein != null) {
            setDbRef(initializedProtein.getDbRef());
            setSpecies(initializedProtein.getSpecies());
            setGenes(initializedProtein.getGenes());
            setGoTerms(initializedProtein.getGoTerms());
            setPTMs(initializedProtein.getPTMs());
            setSpectra(initializedProtein.getSpectra());
        }
    }

    @Override
    public String toString() {
        return "ProteinCurrent{" +
                "signal_peptide='" + signal_peptide + '\'' +
                ", protein_acc='" + protein_acc + '\'' +
                ", sequence='" + sequence + '\'' +
                ", molecular_weight=" + molecular_weight +
                ", transmembrane_domain='" + transmembrane_domain + '\'' +
                ", cytoplasmatic_domain='" + cytoplasmatic_domain + '\'' +
                ", noncytoplasmatic_domain='" + noncytoplasmatic_domain + '\'' +
                ", keywords='" + keywords + '\'' +
                ", feature_table='" + feature_table + '\'' +
                ", chromosome='" + chromosome + '\'' +
                ", protein_name='" + protein_name + '\'' +
                '}';
    }
}
