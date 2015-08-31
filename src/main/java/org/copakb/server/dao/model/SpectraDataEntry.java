package org.copakb.server.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

/**
 * Spectra Mongo data entry.
 * Created by vincekyi on 8/13/2015.
 */
@Document(collection = "Spectra")
public class SpectraDataEntry {
    @Id
    private int spectrum_id;
    private String ptm_sequence;
    private int charge_state;
    private double xcorr;
    private double delta_cn;
    private double zscore;
    private double fdr;
    @Indexed
    private double precursor_mz;
    private double th_precursor_mz;
    private double[][] peaks;
    private String peptide_sequence;
    private int mod_id;
    private String organelle;
    private String species_name;

    public SpectraDataEntry() {

    }

    public SpectraDataEntry(Spectrum spectrum, double[][] peaks, String peptide_sequence) {
        this.spectrum_id = spectrum.getSpectrum_id();
        this.ptm_sequence = spectrum.getPtm_sequence();
        this.charge_state = spectrum.getCharge_state();
        this.xcorr = spectrum.getXcorr();
        this.delta_cn = spectrum.getDelta_cn();
        this.zscore = spectrum.getZscore();
        this.fdr = spectrum.getFdr();
        this.precursor_mz = spectrum.getPrecursor_mz();
        this.th_precursor_mz = spectrum.getTh_precursor_mz();
        this.peaks = peaks;
        this.peptide_sequence = peptide_sequence;
        this.mod_id = spectrum.getModule().getMod_id();
        this.organelle = spectrum.getModule().getOrganelle();
        this.species_name = spectrum.getModule().getSpecies().getSpecies_name();
    }

    public int getSpectrum_id() {
        return spectrum_id;
    }

    public void setSpectrum_id(int spectrum_id) {
        this.spectrum_id = spectrum_id;
    }

    public String getPtm_sequence() {
        return ptm_sequence;
    }

    public void setPtm_sequence(String ptm_sequence) {
        this.ptm_sequence = ptm_sequence;
    }

    public int getCharge_state() {
        return charge_state;
    }

    public void setCharge_state(int charge_state) {
        this.charge_state = charge_state;
    }

    public double getXcorr() {
        return xcorr;
    }

    public void setXcorr(double xcorr) {
        this.xcorr = xcorr;
    }

    public double getDelta_cn() {
        return delta_cn;
    }

    public void setDelta_cn(double delta_cn) {
        this.delta_cn = delta_cn;
    }

    public double getZscore() {
        return zscore;
    }

    public void setZscore(double zscore) {
        this.zscore = zscore;
    }

    public double getFdr() {
        return fdr;
    }

    public void setFdr(double fdr) {
        this.fdr = fdr;
    }

    public double getPrecursor_mz() {
        return precursor_mz;
    }

    public void setPrecursor_mz(double precursor_mz) {
        this.precursor_mz = precursor_mz;
    }

    public double getTh_precursor_mz() {
        return th_precursor_mz;
    }

    public void setTh_precursor_mz(double th_precursor_mz) {
        this.th_precursor_mz = th_precursor_mz;
    }

    public double[][] getPeaks() {
        return peaks;
    }

    public void setPeaks(double[][] peaks) {
        this.peaks = peaks;
    }

    public String getPeptide_sequence() {
        return peptide_sequence;
    }

    public void setPeptide_sequence(String peptide_sequence) {
        this.peptide_sequence = peptide_sequence;
    }

    public int getMod_id() {
        return mod_id;
    }

    public void setMod_id(int mod_id) {
        this.mod_id = mod_id;
    }

    public String getOrganelle() {
        return organelle;
    }

    public void setOrganelle(String organelle) {
        this.organelle = organelle;
    }

    public String getSpecies_name() {
        return species_name;
    }

    public void setSpecies_name(String species_name) {
        this.species_name = species_name;
    }

    @Override
    public String toString() {
        return "SpectraDataEntry{" +
                "spectrum_id=" + spectrum_id +
                ", ptm_sequence='" + ptm_sequence + '\'' +
                ", charge_state=" + charge_state +
                ", xcorr=" + xcorr +
                ", delta_cn=" + delta_cn +
                ", zscore=" + zscore +
                ", fdr=" + fdr +
                ", precursor_mz=" + precursor_mz +
                ", th_precursor_mz=" + th_precursor_mz +
                ", peaks=" + Arrays.toString(peaks) +
                ", peptide_sequence='" + peptide_sequence + '\'' +
                ", mod_id=" + mod_id +
                ", organelle='" + organelle + '\'' +
                ", species_name='" + species_name + '\'' +
                '}';
    }
}
