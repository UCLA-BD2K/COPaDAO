package org.copakb.server.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by Kevin on 5/1/2015.
 */
@Entity
@Table(name = "protein_history")
public class ProteinHistory {
    private String protein_acc;
    private Version version;
    private String sequence;
    private String protein_name;
    private double molecular_weight;
    private Date delete_date;

    public ProteinHistory(String protein_acc, Version version, String sequence, String protein_name, String chromosome, double molecular_weight, Date delete_date) {
        this.protein_acc = protein_acc;
        this.version = version;
        this.sequence = sequence;
        this.protein_name = protein_name;
        this.molecular_weight = molecular_weight;
        this.delete_date = delete_date;
    }

    public ProteinHistory() {
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
    @JoinColumn(name = "version", nullable = false)
    public Version getVersion() {
        return version;
    }
    public void setVersion(Version version) {
        this.version = version;
    }

    @Column(name = "sequence")
    public String getSequence() {
        return sequence;
    }
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Column(name = "protein_name")
    public String getProtein_name() {
        return protein_name;
    }
    public void setProtein_name(String protein_name) {
        this.protein_name = protein_name;
    }

    @Column(name = "molecular_weight")
    public double getMolecular_weight() {
        return molecular_weight;
    }
    public void setMolecular_weight(double molecular_weight) {
        this.molecular_weight = molecular_weight;
    }

    @Column(name = "delete_date ")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDelete_date() {
        return delete_date;
    }
    public void setDelete_date(Date delete_date) {
        this.delete_date = delete_date;
    }
}
