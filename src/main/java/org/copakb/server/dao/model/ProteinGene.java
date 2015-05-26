package org.copakb.server.dao.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kevin on 4/30/2015.
 */

//TODO get rid of Protein_Gene object
// No need for middle-man table

@Entity
@Table(name = "Protein_Gene")
@Deprecated
public class ProteinGene implements Serializable{
    private ProteinCurrent proteinCurrent;
    private Gene gene;

    public ProteinGene(ProteinCurrent proteinCurrent, Gene gene) {
        this.proteinCurrent = proteinCurrent;
        this.gene = gene;
    }

    public ProteinGene() {
        //default
    }

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name =  "protein_acc", nullable = false)
    public ProteinCurrent getProteinCurrent() {
        return proteinCurrent;
    }
    public void setProteinCurrent(ProteinCurrent proteinCurrent) {
        this.proteinCurrent = proteinCurrent;
    }

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gene_name", nullable = false)
    public Gene getGene() {
        return gene;
    }
    public void setGene(Gene gene) {
        this.gene = gene;
    }
}
