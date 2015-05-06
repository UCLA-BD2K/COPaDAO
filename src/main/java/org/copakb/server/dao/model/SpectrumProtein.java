package org.copakb.server.dao.model;

import org.hibernate.annotations.Type;
import javax.persistence.*;

import javax.persistence.*;

/**
 * Created by Kevin on 5/1/2015.
 */

//Todo reevaluate need for this table
    //can be merged with spectrum table
@Entity
@Table(name = "Spectrum_Protein")
public class SpectrumProtein {
    private String protein_acc;
    private Spectrum spectrum;
    private LibraryModule libraryModule;
    @Type(type = "numeric_boolean")
    private boolean feature_peptide;
    @Type(type = "numeric_boolean")
    private boolean species_unique;
    private String enzyme_specificity;
    private char prevAA;
    private char nextAA;
    private int location;

    public SpectrumProtein(String protein_acc, Spectrum spectrum, LibraryModule libraryModule, boolean feature_peptide, boolean species_unique, String enzyme_specificity, char prevAA, char nextAA, int location) {
        this.protein_acc = protein_acc;
        this.spectrum = spectrum;
        this.libraryModule = libraryModule;
        this.feature_peptide = feature_peptide;
        this.species_unique = species_unique;
        this.enzyme_specificity = enzyme_specificity;
        this.prevAA = prevAA;
        this.nextAA = nextAA;
        this.location = location;
    }

    public SpectrumProtein() {
        //default
    }

    @Id
    @Column(name = "protein_acc")
    public String getProtein_acc() {
        return protein_acc;
    }
    public void setProtein_acc(String protein_acc) {
        this.protein_acc = protein_acc;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spectrum_id", nullable = false)
    public Spectrum getSpectrum() {
        return spectrum;
    }
    public void setSpectrum(Spectrum spectrum) {
        this.spectrum = spectrum;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mod_id", nullable = false)
    public LibraryModule getLibraryModule() {
        return libraryModule;
    }
    public void setLibraryModule(LibraryModule libraryModule) {
        this.libraryModule = libraryModule;
    }

    @Column(name = "feature_peptide")
    public boolean isFeature_peptide() {
        return feature_peptide;
    }
    public void setFeature_peptide(boolean feature_peptide) {
        this.feature_peptide = feature_peptide;
    }

    @Column(name = "species_unique")
    public boolean isSpecies_unique() {
        return species_unique;
    }
    public void setSpecies_unique(boolean species_unique) {
        this.species_unique = species_unique;
    }

    @Column(name = "enzyme_specificity")
    public String getEnzyme_specificity() {
        return enzyme_specificity;
    }
    public void setEnzyme_specificity(String enzyme_specificity) {
        this.enzyme_specificity = enzyme_specificity;
    }

    @Column(name = "prevAA")
    public char getPrevAA() {
        return prevAA;
    }
    public void setPrevAA(char prevAA) {
        this.prevAA = prevAA;
    }

    @Column(name = "nextAA")
    public char getNextAA() {
        return nextAA;
    }
    public void setNextAA(char nextAA) {
        this.nextAA = nextAA;
    }

    @Column(name = "location")
    public int getLocation() {
        return location;
    }
    public void setLocation(int location) {
        this.location = location;
    }
}
