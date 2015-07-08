package org.copakb.server.dao.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ping PC1 on 7/6/2015.
 */
@Entity
@Table(name = "Spectrum_Protein_History")
public class SpectrumProteinHistory {
    private int spec_protein_id;
    private Version version;
    private String protein_acc;
    private int spectrum_id;
    private int libraryModule;
    @Type(type = "numeric_boolean")
    private boolean feature_peptide;
    @Type(type = "numeric_boolean")
    private boolean species_unique;
    private char prevAA;
    private char nextAA;
    private int location;
    private Date delete_date;

    public SpectrumProteinHistory(String protein_acc, Version version, int spectrum_id, int libraryModule, boolean feature_peptide, boolean species_unique, char prevAA, char nextAA, int location) {
        this.protein_acc = protein_acc;
        this.version = version;
        this.spectrum_id = spectrum_id;
        this.libraryModule = libraryModule;
        this.feature_peptide = feature_peptide;
        this.species_unique = species_unique;
        this.prevAA = prevAA;
        this.nextAA = nextAA;
        this.location = location;
    }

    public SpectrumProteinHistory() {
        //default
    }

    @Id
    @Column(name="spec_protein_id")
    public int getSpectrumProtein_id() {
        return spec_protein_id;
    }
    public void setSpectrumProtein_id(int spec_protein_id) {
        this.spec_protein_id = spec_protein_id;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "version", nullable = false)
    public Version getVersion() {
        return version;
    }
    public void setVersion(Version version) {
        this.version = version;
    }


    @Column(name = "protein_acc")
    public String getProtein_acc() {return protein_acc;}
    public void setProtein_acc(String protein_acc) {this.protein_acc = protein_acc;}

    @Column(name = "spectrum_id")
    public int getSpectrum_id() {
        return spectrum_id;
    }
    public void setSpectrum_id(int spectrum_id) {
        this.spectrum_id = spectrum_id;
    }

    @Column(name = "mod_id")
    public int getLibraryModule() {
        return libraryModule;
    }
    public void setLibraryModule(int libraryModule) {
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

    @Column(name = "delete_date")
    public Date getDelete_date() {
        return delete_date;
    }
    public void setDelete_date(Date delete_date) {
        this.delete_date = delete_date;
    }

}
