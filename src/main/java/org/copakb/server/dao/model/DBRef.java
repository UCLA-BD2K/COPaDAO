package org.copakb.server.dao.model;

import javax.persistence.*;

/**
 * DBRef object
 *
 * Created by Alan on 6/30/2015.
 */
@Entity
@Table(name = "DBRef")
public class DBRef {
    private String protein_acc;
    private ProteinCurrent proteinCurrent;
    private String pdb;
    private String reactome;
    private String geneWiki;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public ProteinCurrent getProteinCurrent() {
        return proteinCurrent;
    }

    public void setProteinCurrent(ProteinCurrent proteinCurrent) {
        this.proteinCurrent = proteinCurrent;
    }

    @Id
    @Column(name = "protein_acc")
    public String getProtein_acc() {
        return protein_acc;
    }

    public void setProtein_acc(String protein_acc) {
        this.protein_acc = protein_acc;
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
                "uniprotID='" + proteinCurrent.getProtein_acc() + '\'' +
                ", pdb='" + pdb + '\'' +
                ", reactome='" + reactome + '\'' +
                ", geneWiki='" + geneWiki + '\'' +
                '}';
    }
}
