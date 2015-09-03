package org.copakb.server.dao.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * DiseaseGene2 model.
 * Created by Kevin on 4/30/2015.
 */
@Entity
@Table(name = "Disease_Gene2")
public class DiseaseGene2 implements Serializable {
    private Disease disease;
    private Gene2 gene;
    private String perturbation;
    private String pubmed_id;
    private String pubmed_title;
    private String pubmed_author;

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
    @JoinColumn(name = "ensembl_id")
    public Gene2 getGene() {
        return gene;
    }

    public void setGene(Gene2 gene) {
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
