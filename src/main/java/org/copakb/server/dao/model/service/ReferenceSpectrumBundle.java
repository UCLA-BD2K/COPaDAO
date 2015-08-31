package org.copakb.server.dao.model.service;

import org.copakb.server.dao.model.SpectraDataEntry;
import org.copakb.server.dao.model.SpectraDataEntry;

/**
 * Created by vincekyi on 8/16/15.
 */
public class ReferenceSpectrumBundle {

    private SpectraDataEntry specData;


    public ReferenceSpectrumBundle(SpectraDataEntry specData) {
        this.specData = specData;
    }

    public int getSpectrum_id() {
        return specData.getSpectrum_id();
    }

    public String getPtm_sequence() {
        return specData.getPtm_sequence();
    }


    public int getCharge_state() {
        return specData.getCharge_state();
    }


    public double getXcorr() {
        return specData.getXcorr();
    }


    public double getDelta_cn() {
        return specData.getDelta_cn();
    }


    public double getZscore() {
        return specData.getZscore();
    }


    public double getFdr() {
        return specData.getFdr();
    }


    public double getPrecursor_mz() {
        return specData.getPrecursor_mz();
    }


    public double getTh_precursor_mz() {
        return specData.getTh_precursor_mz();
    }


    public double[][] getPeaks() {
        return specData.getPeaks();
    }


    public String getPeptide_sequence() {
        return specData.getPeptide_sequence();
    }


    public int getMod_id() {
        return specData.getMod_id();
    }


    public String getOrganelle() {
        return specData.getOrganelle();
    }


    public String getSpecies_name() {
        return specData.getSpecies_name();
    }


    @Override
    public String toString() {
        return "ReferenceSpectrumBundle{" +
                "specData=" + specData +
                '}';
    }
}
