package org.copakb.server.dao.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.cglib.beans.BeanCopier;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * DBRef object
 * Created by Alan on 6/30/2015.
 */
@Entity
@Table(name = "dbref")
public class DBRef implements Serializable {
    private String protein_acc;
    private String pdb;
    private String reactome;
    private String geneWiki;

    @Id
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
                //"uniprotID='" + proteinCurrent.getProtein_acc() + '\'' +
                ", pdb='" + pdb + '\'' +
                ", reactome='" + reactome + '\'' +
                ", geneWiki='" + geneWiki + '\'' +
                '}';
    }
}
