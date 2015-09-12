package org.copakb.server.dao.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kevin on 4/30/2015.
 */

@Entity
@Table(name = "disease_gene")
public class DiseaseGene implements Serializable{
    private Disease disease;
    private Gene gene;
    private String perturbation;
    private String pubmed_id;
    private String pubmed_title;
    private String pubmed_author;

    public DiseaseGene(Disease disease, Gene gene, String perturbation, String pubmed_id, String pubmed_title, String pubmed_author) {
        this.disease = disease;
        this.gene = gene;
        this.perturbation = perturbation;
        this.pubmed_id = pubmed_id;
        this.pubmed_title = pubmed_title;
        this.pubmed_author = pubmed_author;
    }

    public DiseaseGene() {
    }

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DOID", nullable = false)
    public Disease getDisease() {
        return disease;
    }
    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gene_name")
    public Gene getGene() {
        return gene;
    }
    public void setGene(Gene gene) {
        this.gene = gene;
    }

    @Column(name = "perturbation")
    public String getPerturbation() {
        return perturbation;
    }
    public void setPerturbation(String perturbation) {
        this.perturbation = perturbation;
    }

    @Column(name = "pubmed_id")
    public String getPubmed_id() {
        return pubmed_id;
    }
    public void setPubmed_id(String pubmed_id) {
        this.pubmed_id = pubmed_id;
    }

    @Column(name = "pubmed_title")
    public String getPubmed_title() {
        return pubmed_title;
    }
    public void setPubmed_title(String pubmed_title) {
        this.pubmed_title = pubmed_title;
    }

    @Column(name = "pubmed_author")
    public String getPubmed_author() {
        return pubmed_author;
    }
    public void setPubmed_author(String pubmed_author) {
        this.pubmed_author = pubmed_author;
    }
}
