package org.copakb.server.dao.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * DBRef object
 *
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
    @OneToOne(fetch = FetchType.EAGER)
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
    public String toString() {
        return "DBRef{" +
                "uniprotID='" + protein.getProtein_acc() + '\'' +
                ", pdb='" + pdb + '\'' +
                ", reactome='" + reactome + '\'' +
                ", geneWiki='" + geneWiki + '\'' +
                '}';
    }
}
