package org.copakb.server.dao.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * DBRef object
 * Created by Alan on 6/30/2015.
 */
@Entity
@Table(name = "DBRef")
public class DBRef implements Serializable {
    private ProteinCurrent protein;
    private String pdb;
    private String reactome;
    private String geneWiki;

    @Id
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "protein_acc", nullable = false)
    public ProteinCurrent getProtein() {
        return protein;
    }

    public void setProtein(ProteinCurrent protein) {
        this.protein = protein;
    }

    @Column(name = "pdb")
    public String getPdb() {
        return pdb;
    }

    public void setPdb(String pdb) {
        this.pdb = pdb;
    }

    @Column(name = "reactome")
    public String getReactome() {
        return reactome;
    }

    public void setReactome(String reactome) {
        this.reactome = reactome;
    }

    @Column(name = "geneWiki")
    public String getGeneWiki() {
        return geneWiki;
    }

    public void setGeneWiki(String geneWiki) {
        this.geneWiki = geneWiki;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DBRef) {
            // TODO Implement actual equals
            return protein.equals(((DBRef) obj).getProtein());
        }

        return false;
    }

    @Override
    public int hashCode() {
        // TODO Implement actual hashCode
        return protein.hashCode();
    }

    @Override
    public String toString() {
        return "DBRef{" +
                "uniprotID='" + protein.getProtein_acc() + '\'' +
                ", pdb='" + pdb + '\'' +
                ", reactome='" + reactome + '\'' +
                ", geneWiki='" + geneWiki + '\'' +
                '}';
    }
}
