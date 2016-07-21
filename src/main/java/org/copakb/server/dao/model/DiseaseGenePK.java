package org.copakb.server.dao.model;


import java.io.ObjectInput;
import java.io.Serializable;

/**
 * Compound primary key representation for disease_gene.
 *
 * Created by allengong on 7/20/16.
 */

public class DiseaseGenePK implements Serializable {

    private Gene gene;
    private Disease disease;

    public DiseaseGenePK() {}

    public DiseaseGenePK(Gene gene, Disease disease) {
        this.gene = gene;
        this.disease = disease;
    }

    public Gene getGene() {
        return gene;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    @Override
    public int hashCode() {
        // temp
        return (this.getGene().getEnsembl_id()+this.getDisease().getDOID()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }

        if (!(obj instanceof DiseaseGenePK)) {
            return false;
        } else {    // else it is an instance of DiseaseGenePK
            DiseaseGenePK other = (DiseaseGenePK) obj;
            if (other.getGene().getEnsembl_id().equals(this.getGene().getEnsembl_id())
                    && other.getDisease().getDOID().equals(this.getDisease().getDOID())) {

                return true; // only equal if disease_id and ensembl_id match
            }
        }

        return false;
    }
}
