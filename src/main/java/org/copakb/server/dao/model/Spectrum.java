package org.copakb.server.dao.model;

import org.copakb.server.dao.DAOObject;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Spectrum model.
 * Created by vincekyi on 4/19/15.
 */
@Entity
@Table(name = "Spectrum")
@Document(collection = "SpectraInfo")
public class Spectrum extends Model {
    private int spectrum_id;
    private String ptm_sequence;
    private int charge_state;
    private LibraryModule module;
    private double xcorr;
    private double delta_cn;
    private double zscore;
    private double fdr;
    @Indexed
    private double precursor_mz;
    private double th_precursor_mz;
    private boolean species_unique;
    private boolean feature_peptide;
    private PTM_type ptm;
    private String rawfile_id;
    private Peptide peptide;
    private Set<SpectrumProtein> spectrumProtein;
    private double[][] peaks;
    private String peptide_sequence;
    private String mod_lib;
    private String species;

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

    public Spectrum() {
        //empty
    }

    @Id
    @Column(name = "spectrum_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.springframework.data.annotation.Id
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
    @org.springframework.data.annotation.Transient
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peptide_id")
    @org.springframework.data.annotation.Transient
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
    @org.springframework.data.annotation.Transient
    public double getTh_precursor_mz() {
        return th_precursor_mz;
    }

    public void setTh_precursor_mz(double th_precursor_mz) {
        this.th_precursor_mz = th_precursor_mz;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ptm_type")
    @org.springframework.data.annotation.Transient
    public PTM_type getPtm() {
        return ptm;
    }

    public void setPtm(PTM_type ptm) {
        this.ptm = ptm;
    }

    @Column(name = "rawfileid")
    @org.springframework.data.annotation.Transient
    public String getRawfile_id() {
        return rawfile_id;
    }

    public void setRawfile_id(String rawfile_id) {
        this.rawfile_id = rawfile_id;
    }

    @Column(name = "feature_peptide")
    @org.springframework.data.annotation.Transient
    public boolean isFeature_peptide() {
        return feature_peptide;
    }

    public void setFeature_peptide(boolean feature_peptide) {
        this.feature_peptide = feature_peptide;
    }

    @Column(name = "species_unique")
    @org.springframework.data.annotation.Transient
    public boolean isSpecies_unique() {
        return species_unique;
    }

    public void setSpecies_unique(boolean species_unique) {
        this.species_unique = species_unique;
    }


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "spectrum")
    public Set<SpectrumProtein> getSpectrumProtein() {
        return spectrumProtein;
    }

    public void setSpectrumProtein(Set<SpectrumProtein> spectrumProtein) {
        this.spectrumProtein = spectrumProtein;
    }

    @Transient
    public double[][] getPeaks() {
        return peaks;
    }

    public void setPeaks(double[][] peaks) {
        this.peaks = peaks;
    }

    @Transient
    public String getPeptide_sequence() {
        return peptide_sequence;
    }

    public void setPeptide_sequence(String peptide_sequence) {
        this.peptide_sequence = peptide_sequence;
    }

    @Transient
    public String getMod_lib() {
        return mod_lib;
    }

    public void setMod_lib(String mod_lib) {
        this.mod_lib = mod_lib;
    }

    @Transient
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * Initializes the model's lazy loaded objects.
     */
    @Override
    public Spectrum initialize() {
        Spectrum initialized = DAOObject.getInstance().getPeptideDAO().getInitializedSpectrum(spectrum_id);
        if (initialized != null) {
            setModule(initialized.getModule());
            setPtm(initialized.getPtm());
            setPeptide(initialized.getPeptide());
            setSpectrumProtein(initialized.getSpectrumProtein());
        }

        return initialized;
    }

    @Override
    public String toString() {
        return "seq: " + this.ptm_sequence + "\npeptide sequence: " + this.peptide.getPeptide_sequence() + "/n--";
    }

    public int compare(Spectrum spectrum) {
        if (this.getCharge_state() == spectrum.getCharge_state() &&
                this.getDelta_cn() == spectrum.getDelta_cn() &&
                this.getModule().getMod_id() == spectrum.getModule().getMod_id() &&
                this.getPeptide().getPeptide_id() == spectrum.getPeptide().getPeptide_id() &&
                this.getPrecursor_mz() == spectrum.getPrecursor_mz() &&
                this.getPtm().getPtm_type() == spectrum.getPtm().getPtm_type() &&
                Objects.equals(this.getRawfile_id(), spectrum.getRawfile_id()))
            return 0;
        else
            return -1;
    }
}
