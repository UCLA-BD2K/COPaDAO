package org.copakb.server.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * DiseaseGene model.
 * Created by Kevin on 4/30/2015.
 */
@Entity
@Table(name = "disease_gene")
@IdClass(DiseaseGenePK.class)
public class DiseaseGene implements Serializable {
    private Disease disease;    /* demo db migration, see getter for disease */
    private Gene gene;
    private String perturbation;
    private String relationship;
    private String weblink;
    private String data_source;

    private Set<DiseaseGenePublication> diseaseGenePublications;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disease_id", nullable = false)
    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ensembl_id")
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

    @Column(name = "relationship")
    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Column(name = "weblink")
    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    @Column(name = "data_source")
    public String getData_source() { return data_source; }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "diseaseGene")
    public Set<DiseaseGenePublication> getDiseaseGenePublications() { return diseaseGenePublications; }

    public void setDiseaseGenePublications(Set<DiseaseGenePublication> dgp) { this.diseaseGenePublications = dgp; }
}
