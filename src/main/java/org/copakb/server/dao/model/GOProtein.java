package org.copakb.server.dao.model;

import javax.persistence.*;

/**
 * Created by Kevin on 5/1/2015.
 */

@Entity
@Table(name = "GO_Protein")
public class GOProtein {
    private String protein_acc;
    private GoTerms goTerm;

    public GOProtein(String protein_acc, GoTerms goTerm) {
        this.protein_acc = protein_acc;
        this.goTerm = goTerm;
    }

    public GOProtein() {
        //default
    }

    @Id
    @Column(name = "protein_acc")
    public String getProtein_acc() {
        return protein_acc;
    }
    public void setProtein_acc(String protein_acc) {
        this.protein_acc = protein_acc;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GO_accession")
    public GoTerms getGoTerm() {
        return goTerm;
    }
    public void setGoTerm(GoTerms goTerm) {
        this.goTerm = goTerm;
    }
}
