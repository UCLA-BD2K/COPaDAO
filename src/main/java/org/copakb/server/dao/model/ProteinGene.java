package org.copakb.server.dao.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kevin on 4/30/2015.
 */

@Entity
@Table(name = "Protein_Gene")
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
