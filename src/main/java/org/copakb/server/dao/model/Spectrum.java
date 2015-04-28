package org.copakb.server.dao.model;

import javax.persistence.*;

/**
 * Created by vincekyi on 4/19/15.
 */

@Entity
@Table(name = "Spectrum")
public class Spectrum {


    private int spectrum_id;
    private String ptm_sequence;
    private int charge_state;
    private LibraryModule module;
    private double xcorr;
    private double delta_cn;
    private double zscore;
    private double fdr;
    private double precursor_mz;
    private double th_precursor_mz;
    private PTM_type ptm;
    private String rawfile_id;
    private Peptide peptide;

    public Spectrum(String ptm_sequence, int charge_state, LibraryModule module, double xcorr, double delta_cn, double zscore, double fdr, double precursor_mz, double th_precursor_mz, PTM_type ptm, String rawfile_id, Peptide peptide) {
        this.ptm_sequence = ptm_sequence;
        this.charge_state = charge_state;
        this.module = module;
        this.xcorr = xcorr;
        this.delta_cn = delta_cn;
        this.zscore = zscore;
        this.fdr = fdr;
        this.precursor_mz = precursor_mz;
        this.th_precursor_mz = th_precursor_mz;
        this.ptm = ptm;
        this.rawfile_id = rawfile_id;
        this.peptide = peptide;
    }

    public Spectrum(){
        //empty
    }


    @Id
    @Column(name="spectrum_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getSpectrum_id() {
        return spectrum_id;
    }

    public void setSpectrum_id(int spectrum_id) {
        this.spectrum_id = spectrum_id;
    }

    @Column(name = "ptm_sequence")
    public String getPtm_sequence() {
        return ptm_sequence;
    }

    public void setPtm_sequence(String ptm_sequence) {
        this.ptm_sequence = ptm_sequence;
    }

    @Column(name = "charge_state")
    public int getCharge_state() {
        return charge_state;
    }

    public void setCharge_state(int charge_state) {
        this.charge_state = charge_state;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mod_id", nullable = false)
    public LibraryModule getModule() {
        return module;
    }

    public void setModule(LibraryModule module) {
        this.module = module;
    }


    @Column(name = "xcorr")
    public double getXcorr() {
        return xcorr;
    }

    public void setXcorr(double xcorr) {
        this.xcorr = xcorr;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "peptide_id", nullable = false)
    public Peptide getPeptide() {
        return peptide;
    }

    public void setPeptide(Peptide peptide) {
        this.peptide = peptide;
    }

    @Column(name = "delta_cn")
    public double getDelta_cn() {
        return delta_cn;
    }

    public void setDelta_cn(double delta_cn) {
        this.delta_cn = delta_cn;
    }

    @Column(name = "zscore")
    public double getZscore() {
        return zscore;
    }

    public void setZscore(double zscore) {
        this.zscore = zscore;
    }

    @Column(name = "fdr")
    public double getFdr() {
        return fdr;
    }

    public void setFdr(double fdr) {
        this.fdr = fdr;
    }

    @Column(name = "precursor_mz")
    public double getPrecursor_mz() {
        return precursor_mz;
    }

    public void setPrecursor_mz(double precursor_mz) {
        this.precursor_mz = precursor_mz;
    }

    @Column(name = "th_precursor_mz")
    public double getTh_precursor_mz() {
        return th_precursor_mz;
    }

    public void setTh_precursor_mz(double th_precursor_mz) {
        this.th_precursor_mz = th_precursor_mz;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ptm_type", nullable = false)
    public PTM_type getPtm() {
        return ptm;
    }

    public void setPtm(PTM_type ptm) {
        this.ptm = ptm;
    }

    @Column(name = "rawfileid")
    public String getRawfile_id() {
        return rawfile_id;
    }

    public void setRawfile_id(String rawfile_id) {
        this.rawfile_id = rawfile_id;
    }

    @Override
    public String toString(){
        return "seq: "+this.ptm_sequence+"\npeptide sequence: "+this.peptide.getPeptide_sequence()+"/n--";
    }
}
