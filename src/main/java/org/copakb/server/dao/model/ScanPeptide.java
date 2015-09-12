package org.copakb.server.dao.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ScanPeptide model.
 * Created by Kevin on 5/28/2015.
 */
@Document
public class ScanPeptide {
    private String mzFile;
    private int scan;
    private String peptideSequence;
    private String peptide;
    private double similarityScore;
    private double deltaMZ;
    private double spectrum;
    private double finalScore;
    private String unique;
    private int charge;
    private double precurMZ;
    private double thPrecurMZ;
    private String feature;

    public ScanPeptide(String feature, double thPrecurMZ, String mzFile, int scan,
                       String peptideSequence, String peptide, double similarityScore,
                       double deltaMZ, double spectrum, double finalScore, String unique,
                       int charge, double precurMZ) {
        this.feature = feature;
        this.thPrecurMZ = thPrecurMZ;
        this.mzFile = mzFile;
        this.scan = scan;
        this.peptideSequence = peptideSequence;
        this.peptide = peptide;
        this.similarityScore = similarityScore;
        this.deltaMZ = deltaMZ;
        this.spectrum = spectrum;
        this.finalScore = finalScore;
        this.unique = unique;
        this.charge = charge;
        this.precurMZ = precurMZ;
    }

    public ScanPeptide() {
        //default constructor
    }

    public String getMzFile() {
        return mzFile;
    }

    public void setMzFile(String mzFile) {
        this.mzFile = mzFile;
    }

    public int getScan() {
        return scan;
    }

    public void setScan(int scan) {
        this.scan = scan;
    }

    public String getPeptideSequence() {
        return peptideSequence;
    }

    public void setPeptideSequence(String peptideSequence) {
        this.peptideSequence = peptideSequence;
    }

    public String getPeptide() {
        return peptide;
    }

    public void setPeptide(String peptide) {
        this.peptide = peptide;
    }

    public double getSimilarityScore() {
        return similarityScore;
    }

    public void setSimilarityScore(double similarityScore) {
        this.similarityScore = similarityScore;
    }

    public double getDeltaMZ() {
        return deltaMZ;
    }

    public void setDeltaMZ(double deltaMZ) {
        this.deltaMZ = deltaMZ;
    }

    public double getSpectrum() {
        return spectrum;
    }

    public void setSpectrum(double spectrum) {
        this.spectrum = spectrum;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public double getPrecurMZ() {
        return precurMZ;
    }

    public void setPrecurMZ(double precurMZ) {
        this.precurMZ = precurMZ;
    }

    public double getThPrecurMZ() {
        return thPrecurMZ;
    }

    public void setThPrecurMZ(double thPrecurMZ) {
        this.thPrecurMZ = thPrecurMZ;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "mzFile: " + mzFile + "\n" +
                "scan: " + scan + "\n" +
                "peptideSequence: " + peptideSequence + "\n" +
                "peptide: " + peptide + "\n" +
                "similarityScore: " + similarityScore + "\n" +
                "deltaMZ: " + deltaMZ + "\n" +
                "spectrum: " + spectrum + "\n" +
                "finalScore: " + finalScore + "\n" +
                "unique: " + unique + "\n" +
                "charge: " + charge + "\n" +
                "precurMZ: " + precurMZ + "\n" +
                "thPrecurMZ: " + thPrecurMZ + "\n" +
                "feature: " + feature + "\n";
    }
}