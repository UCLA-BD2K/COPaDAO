package org.copakb.server.dao.model;

import javax.persistence.*;

/**
 * Antibody model
 *
 * Created by Alan on 6/26/2015.
 */
@Entity
@Table(name = "Antibodies")
public class Antibody {
    private String antibodyID;
    private String ensemblID;
    private String summary;
    private String myocyteStaining;
    private String myocyteIntensity;

    private String samplePatientSex;
    private int samplePatientAge;
    private String sampleDesc;
    private String sampleImage;

    @Id
    @Column(name = "antibodyID")
    public String getAntibodyID() {
        return antibodyID;
    }

    public void setAntibodyID(String antibodyID) {
        this.antibodyID = antibodyID;
    }

    @Column(name = "ensemblID")
    public String getEnsemblID() {
        return ensemblID;
    }

    public void setEnsemblID(String ensemblID) {
        this.ensemblID = ensemblID;
    }

    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Column(name = "myocyteStaining")
    public String getMyocyteStaining() {
        return myocyteStaining;
    }

    public void setMyocyteStaining(String myocyteStaining) {
        this.myocyteStaining = myocyteStaining;
    }

    @Column(name = "myocyteIntensity")
    public String getMyocyteIntensity() {
        return myocyteIntensity;
    }

    public void setMyocyteIntensity(String myocyteIntensity) {
        this.myocyteIntensity = myocyteIntensity;
    }

    @Column(name = "samplePatientSex")
    public String getSamplePatientSex() {
        return samplePatientSex;
    }

    public void setSamplePatientSex(String samplePatientSex) {
        this.samplePatientSex = samplePatientSex;
    }

    @Column(name = "samplePatientAge")
    public int getSamplePatientAge() {
        return samplePatientAge;
    }

    public void setSamplePatientAge(int samplePatientAge) {
        this.samplePatientAge = samplePatientAge;
    }

    @Column(name = "sampleDesc")
    public String getSampleDesc() {
        return sampleDesc;
    }

    public void setSampleDesc(String sampleDesc) {
        this.sampleDesc = sampleDesc;
    }

    @Column(name = "sampleImage")
    public String getSampleImage() {
        return sampleImage;
    }

    public void setSampleImage(String sampleImage) {
        this.sampleImage = sampleImage;
    }

    @Override
    public String toString() {
        return ("Antibody ID: " + antibodyID + "\n") +
                "Ensembl ID: " + ensemblID + "\n" +
                "Staining: " + myocyteStaining + "\n" +
                "Intensity: " + myocyteIntensity + "\n" +
                "Patient Sex: " + samplePatientSex + "\n" +
                "Patient Age: " + samplePatientAge + "\n" +
                "Sample Description: " + sampleDesc + "\n" +
                "Sample Image: " + sampleImage + "\n";
    }
}
