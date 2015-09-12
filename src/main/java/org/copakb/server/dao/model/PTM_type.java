package org.copakb.server.dao.model;

import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;
import java.util.Set;

/**
 * PTM_type model.
 * Created by vincekyi on 4/28/15.
 */
@Entity
@Table(name = "ptm_type")
@SQLInsert(sql = "INSERT IGNORE INTO ptm_type (mass, modification, residues, ptm_type) VALUES (?, ?, ?, ?)")
public class PTM_type {
    private int ptm_type;
    private String modification;
    private String residues;
    private double mass;

    Set<Spectrum> spectra;

    public PTM_type(int ptm_type, String modification, String residues, double mass, Set<Spectrum> spectra) {
        this.ptm_type = ptm_type;
        this.modification = modification;
        this.residues = residues;
        this.mass = mass;
        this.spectra = spectra;
    }

    public PTM_type() {
        this.ptm_type = 0;
        this.modification = null;
        this.residues = null;
        this.mass = 0;
    }

    @Id
    @Column(name = "ptm_type")
    public int getPtm_type() {
        return ptm_type;
    }

    public void setPtm_type(int ptm_type) {
        this.ptm_type = ptm_type;
    }

    @Column(name = "modification")
    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    @Column(name = "residues")
    public String getResidues() {
        return residues;
    }

    public void setResidues(String residues) {
        this.residues = residues;
    }

    @Column(name = "mass")
    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ptm", cascade = CascadeType.PERSIST)
    public Set<Spectrum> getSpectra() {
        return spectra;
    }

    public void setSpectra(Set<Spectrum> spectra) {
        this.spectra = spectra;
    }
}
