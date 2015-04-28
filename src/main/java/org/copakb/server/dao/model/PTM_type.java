package org.copakb.server.dao.model;

/**
 * Created by vincekyi on 4/28/15.
 */

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PTM_type")
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
    }

    @Id
    @Column(name="ptm_type")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getPtm_type() {
        return ptm_type;
    }

    public void setPtm_type(int ptm_type) {
        this.ptm_type = ptm_type;
    }

    @Column(name="modification")
    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    @Column(name="residues")
    public String getResidues() {
        return residues;
    }

    public void setResidues(String residues) {
        this.residues = residues;
    }

    @Column(name="mass")
    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ptm")
    public Set<Spectrum> getSpectra() {
        return spectra;
    }

    public void setSpectra(Set<Spectrum> spectra) {
        this.spectra = spectra;
    }
}
