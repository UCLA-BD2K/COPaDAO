package org.copakb.server.dao.model;

import org.hibernate.annotations.Type;
import javax.persistence.*;

import javax.persistence.*;

/**
 * Created by Kevin on 5/1/2015.
 */

@Entity
@Table(name = "Spectrum_Protein")
public class SpectrumProtein {
    private int spec_protein_id;
    private ProteinCurrent protein;
    private Spectrum spectrum;
    private LibraryModule libraryModule;
    private char prevAA;
    private char nextAA;
    private int location;
    private Peptide peptide;

    public SpectrumProtein(ProteinCurrent protein, Spectrum spectrum, LibraryModule libraryModule, char prevAA, char nextAA, int location, Peptide peptide) {
        this.protein = protein;
        this.spectrum = spectrum;
        this.libraryModule = libraryModule;
        this.prevAA = prevAA;
        this.nextAA = nextAA;
        this.location = location;
        this.peptide = peptide;
    }

    public SpectrumProtein() {
        //default
    }

    @Id
    @Column(name="spec_protein_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getSpectrumProtein_id() {
        return spec_protein_id;
    }
    public void setSpectrumProtein_id(int spec_protein_id) {
        this.spec_protein_id = spec_protein_id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "protein_acc", nullable = false)
    public ProteinCurrent getProtein() {
        return protein;
    }

    public void setProtein(ProteinCurrent protein) {
        this.protein = protein;
    }

    @ManyToOne(fetch = FetchType.EAGER)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "peptide_id", nullable = false)
    public Peptide getPeptide() {
        return peptide;
    }
    public void setPeptide(Peptide peptide) { this.peptide = peptide; }

    @Override
    public String toString() {
        String result = "protein_acc: " + getProtein().getProtein_acc() + "\n" +
                "spectrum_id " + getSpectrum().getSpectrum_id();

        return result;
    }
}
