package org.copakb.server.dao.model;

import javax.persistence.*;

/**
 * Represents a record in the disease_gene_publication table.
 *
 * Created by allengong on 7/19/16.
 */
@Entity
@Table(name = "disease_gene_publication")
public class DiseaseGenePublication {

    private int id;
    private Disease disease;
    private Gene gene;
    private String pubmed_id;
    private String pubmed_title;
    private String pubmed_author;

    private DiseaseGene diseaseGene;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disease_id", nullable = false, insertable = false, updatable = false)
    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ensembl_id", nullable = false, insertable = false, updatable = false)
    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="disease_id", referencedColumnName = "disease_id", nullable = false),
            @JoinColumn(name="ensembl_id", referencedColumnName = "ensembl_id", nullable = false)
    })
    public DiseaseGene getDiseaseGene() { return this.diseaseGene; }

    public void setDiseaseGene(DiseaseGene dg) { this.diseaseGene = dg; }

    @Override
    public String toString() {
        return "DiseaseGenePublication{" +
                "id=" + id +
                ", disease=" + disease +
                ", gene=" + gene +
                ", pubmed_id='" + pubmed_id + '\'' +
                ", pubmed_title='" + pubmed_title + '\'' +
                ", pubmed_author='" + pubmed_author + '\'' +
                '}';
    }
}
