package org.copakb.server.dao.model;

import org.copakb.server.dao.DAOObject;

import javax.persistence.*;
import java.util.Set;

/**
 * HPAProtein model
 *
 * Created by Alan on 6/26/2015.
 */
@Entity
@Table(name = "hpaproteins")
public class HPAProtein extends Model {
    private String ensembl_id;
    private String proteinName;
    private String expressionSummary;
    private String proteinClass;

    private String subcellSummary;
    private String subcellImage;
    private String mainLocations;
    private String altLocations;
    private String assaySummary;

    private Set<Antibody> antibodies;

    public HPAProtein() {

    }

    public HPAProtein(String ensembl_id, String proteinName, String expressionSummary, String proteinClass,
                      String subcellSummary, String subcellImage, String mainLocations, String altLocations,
                      String assaySummary, Set<Antibody> antibodies) {
        this.ensembl_id = ensembl_id;
        this.proteinName = proteinName;
        this.expressionSummary = expressionSummary;
        this.proteinClass = proteinClass;
        this.subcellSummary = subcellSummary;
        this.subcellImage = subcellImage;
        this.mainLocations = mainLocations;
        this.altLocations = altLocations;
        this.assaySummary = assaySummary;
        this.antibodies = antibodies;
    }

    @Id
    @Column(name = "ensembl_id")
    public String getEnsembl_id() {
        return ensembl_id;
    }

    public void setEnsembl_id(String ensembl_id) {
        this.ensembl_id = ensembl_id;
    }

    @Column(name = "gene_name")
    public String getProteinName() {
        return proteinName;
    }

    public void setProteinName(String proteinName) {
        this.proteinName = proteinName;
    }

    @Column(name = "expressionSummary")
    public String getExpressionSummary() {
        return expressionSummary;
    }

    public void setExpressionSummary(String expressionSummary) {
        this.expressionSummary = expressionSummary;
    }

    @Column(name = "class")
    public String getProteinClass() {
        return proteinClass;
    }

    public void setProteinClass(String proteinClass) {
        this.proteinClass = proteinClass;
    }

    @Column(name = "subcellSummary")
    public String getSubcellSummary() {
        return subcellSummary;
    }

    public void setSubcellSummary(String subcellSummary) {
        this.subcellSummary = subcellSummary;
    }

    @Column(name = "subcellImage")
    public String getSubcellImage() {
        return subcellImage;
    }

    public void setSubcellImage(String subcellImage) {
        this.subcellImage = subcellImage;
    }

    @Column(name = "mainLocations")
    public String getMainLocations() {
        return mainLocations;
    }

    public void setMainLocations(String mainLocations) {
        this.mainLocations = mainLocations;
    }

    @Column(name = "altLocations")
    public String getAltLocations() {
        return altLocations;
    }

    public void setAltLocations(String altLocations) {
        this.altLocations = altLocations;
    }

    @Column(name = "assaySummary")
    public String getAssaySummary() {
        return assaySummary;
    }

    public void setAssaySummary(String assaySummary) {
        this.assaySummary = assaySummary;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ensembl_id")
    public Set<Antibody> getAntibodies() {
        return antibodies;
    }

    public void setAntibodies(Set<Antibody> antibodies) {
        this.antibodies = antibodies;
    }

    /**
     * Initializes the model's lazy loaded objects.
     */
    @Override
    public HPAProtein initialize() {
        HPAProtein initialized = DAOObject.getInstance().getProteinDAO().getInitializedHPAProtein(ensembl_id);
        if (initialized != null) {
            setAntibodies(initialized.getAntibodies());
        }

        return this;
    }

    @Override
    public String toString() {
        return ("Ensembl ID: " + ensembl_id + "\n") +
                "Protein Name: " + proteinName + "\n" +
                "Protein Expression Summary:\n" + expressionSummary + "\n" +
                "Protein Class: " + proteinClass + "\n" +
                "Subcellular Summary:\n" + subcellSummary + "\n" +
                "Subcellular Image: " + subcellImage + "\n" +
                "Main Locations: " + mainLocations + "\n" +
                "Alternative Locations: " + altLocations + "\n" +
                "Assay Summary: " + assaySummary + "\n" +
                "Antibodies:\n" + antibodies + "\n";
    }
}
