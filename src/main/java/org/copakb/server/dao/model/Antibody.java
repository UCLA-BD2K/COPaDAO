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
    private String antibody_id;
    private String ensembl_id;
    private String summary;
    private String myocyteStaining;
    private String myocyteIntensity;

    private String samplePatientSex;
    private int samplePatientAge;
    private String sampleDesc;
    private String sampleImage;

    @Id
    @Column(name = "antibody_id")
    public String getAntibody_id() {
        return antibody_id;
    }

    public void setAntibody_id(String antibody_id) {
        this.antibody_id = antibody_id;
    }

    @Column(name = "ensembl_id")
    public String getEnsembl_id() {
        return ensembl_id;
    }

    public void setEnsembl_id(String ensembl_id) {
        this.ensembl_id = ensembl_id;
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
        return ("Antibody ID: " + antibody_id + "\n") +
                "Ensembl ID: " + ensembl_id + "\n" +
                "Staining: " + myocyteStaining + "\n" +
                "Intensity: " + myocyteIntensity + "\n" +
                "Patient Sex: " + samplePatientSex + "\n" +
                "Patient Age: " + samplePatientAge + "\n" +
                "Sample Description: " + sampleDesc + "\n" +
                "Sample Image: " + sampleImage + "\n";
    }
}
