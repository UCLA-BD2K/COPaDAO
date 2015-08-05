package org.copakb.server.dao.model.service;

import org.copakb.server.dao.model.*;

import java.util.List;

/**
 * ReferenceProtein bundle.
 * Created by Alan on 8/3/2015.
 */
public class ReferenceProteinBundle {
    private String uniprotID;
    private List<Disease> diseases;
    private List<SpectrumProtein> spectrumProteins;
    private List<GoTerms> goTerms;
    private HPAProtein hpa;

    public String getUniprotID() {
        return uniprotID;
    }

    public void setUniprotID(String uniprotID) {
        this.uniprotID = uniprotID;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public List<SpectrumProtein> getSpectrumProteins() {
        return spectrumProteins;
    }

    public void setSpectrumProteins(List<SpectrumProtein> spectrumProteins) {
        this.spectrumProteins = spectrumProteins;
    }

    public List<GoTerms> getGoTerms() {
        return goTerms;
    }

    public void setGoTerms(List<GoTerms> goTerms) {
        this.goTerms = goTerms;
    }

    public HPAProtein getHPA() {
        return hpa;
    }

    public void setHPA(HPAProtein hpa) {
        this.hpa = hpa;
    }
}
