package org.copakb.server.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Ping PC1 on 8/13/2015.
 */

@Document(collection = "Spectra")
public class SpectraDataEntry {
    @Id
    private int spectrum_id;
    private double precursor_mz;
    private String ptm_sequence;
    private int charge_state;
    private String peptide;
    private double fdr;
    private String species_name;
    private String organelle;
    private double[][] peaks;

    public SpectraDataEntry(int spectrum_id, double precursor_mz, String ptm_sequence, int charge_state, String peptide, double fdr, String species_name, String organelle, double[][] peaks) {
        this.spectrum_id = spectrum_id;
        this.precursor_mz = precursor_mz;
        this.ptm_sequence = ptm_sequence;
        this.charge_state = charge_state;
        this.peptide = peptide;
        this.fdr = fdr;
        this.species_name = species_name;
        this.organelle = organelle;
        this.peaks = peaks;
    }

    public int getSpectrum_id() {
        return spectrum_id;
    }

    public void setSpectrum_id(int spectrum_id) {
        this.spectrum_id = spectrum_id;
    }

    public double getPrecursor_mz() {
        return precursor_mz;
    }

    public void setPrecursor_mz(double precursor_mz) {
        this.precursor_mz = precursor_mz;
    }

    public String getPtm_sequence() {
        return ptm_sequence;
    }

    public void setPtm_sequence(String ptm_sequence) {
        this.ptm_sequence = ptm_sequence;
    }

    public String getPeptide() {
        return peptide;
    }

    public void setPeptide(String peptide) {
        this.peptide = peptide;
    }

    public double[][] getPeaks() {
        return peaks;
    }

    public void setPeaks(double[][] peaks) {
        this.peaks = peaks;
    }

    public String getSpecies_name() {
        return species_name;
    }

    public void setSpecies_name(String species_name) {
        this.species_name = species_name;
    }

    public double getFdr() {
        return fdr;
    }

    public void setFdr(double fdr) {
        this.fdr = fdr;
    }

    public int getCharge_state() {
        return charge_state;
    }

    public void setCharge_state(int charge_state) {
        this.charge_state = charge_state;
    }

    public String getOrganelle() {
        return organelle;
    }

    public void setOrganelle(String organelle) {
        this.organelle = organelle;
    }

    @Override
    public String toString() {
        String spectraString = "";
        for (double[] doubles : peaks) {
            spectraString+="["+Double.toString(doubles[0])+", "+Double.toString(doubles[1])+"]";
        }
        return "SpectraDataEntry{" +
                "spectrum_id=" + spectrum_id +
                ", precursor_mz=" + precursor_mz +
                ", ptm_sequence='" + ptm_sequence + '\'' +
                ", charge_state=" + charge_state +
                ", peptide='" + peptide + '\'' +
                ", fdr=" + fdr +
                ", species_name='" + species_name + '\'' +
                ", organelle='" + organelle + '\'' +
                ", peaks=" + spectraString +
                '}';
    }
}