package org.copakb.server.dao.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kevin on 5/1/2015.
 */

//TODO get rid of GO_Protein object
    // No need for middle-man table

@Entity
@Table(name = "GO_Protein")
public class GOProtein implements Serializable{
    private ProteinCurrent proteinCurrent;
    private GoTerms goTerm;

    public GOProtein(ProteinCurrent proteinCurrent, GoTerms goTerm) {
        this.proteinCurrent = proteinCurrent;
        this.goTerm = goTerm;
    }

    public GOProtein() {
        //default
    }
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "protein_acc")
    public ProteinCurrent getProteinCurrent() {
        return proteinCurrent;
    }
    public void setProteinCurrent(ProteinCurrent proteinCurrent) {
        this.proteinCurrent = proteinCurrent;
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
