package org.copakb.server.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Spectra Mongo data entry.
 * Created by vincekyi on 8/13/2015.
 */
@Document(collection = "Spectra")
public class SpectraDataEntry {
    @Id
    int spectrum_id;
    String ptm_sequence;
    int charge_state;
    double xcorr;
    double delta_cn;
    double zscore;
    double fdr;
    @Indexed
    double precursor_mz;
    double th_precursor_mz;
    double[][] peaks;
    String peptide_sequence;
    int mod_id;
    String organelle;
    String species_name;

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
}
