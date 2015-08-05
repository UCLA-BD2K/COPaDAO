package org.copakb.server.dao.model.service;

import org.copakb.server.dao.model.SpectrumProtein;

import java.util.List;

/**
 * ReferencePeptide bundle.
 * Created by Alan on 8/5/2015.
 */
public class ReferencePeptideBundle {
    private int peptideID;
    private List<SpectrumProtein> spectrumProteins;

    public int getPeptideID() {
        return peptideID;
    }

    public void setPeptideID(int peptideID) {
        this.peptideID = peptideID;
    }

    public List<SpectrumProtein> getSpectrumProteins() {
        return spectrumProteins;
    }

    public void setSpectrumProteins(List<SpectrumProtein> spectrumProteins) {
        this.spectrumProteins = spectrumProteins;
    }
}
