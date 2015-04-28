package org.copakb.server.dao.model;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vincekyi on 4/16/15.
 */

@Entity
@Table(name = "Peptide")
public class Peptide {
// http://www.mkyong.com/hibernate/hibernate-one-to-many-relationship-example-annotation/

    private int peptide_id;
    private String peptide_sequence;
    private double molecular_weight;
    private int sequence_length;
    private Set<Spectrum> spectra = new HashSet<Spectrum>(0);

    public Peptide(String peptide_sequence, double mol_weight, int sequence_length) {
        this.peptide_sequence = peptide_sequence;
        this.molecular_weight = mol_weight;
        this.sequence_length = sequence_length;
    }

    public Peptide(int peptide_id, String peptide_sequence, double mol_weight, int sequence_length, Set<Spectrum> spectra) {
        this.peptide_id = peptide_id;
        this.peptide_sequence = peptide_sequence;
        this.molecular_weight = mol_weight;
        this.sequence_length = sequence_length;
        this.spectra = spectra;
    }

    public Peptide() {
    }


    @Id
    @Column(name="peptide_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getPeptide_id() {
        return peptide_id;
    }

    public void setPeptide_id(int peptide_id) {
        this.peptide_id = peptide_id;
    }

    @Column(name = "peptide_sequence")
    public String getPeptide_sequence() {
        return peptide_sequence;
    }

    public void setPeptide_sequence(String peptide_sequence) {
        this.peptide_sequence = peptide_sequence;
    }

    @Column(name = "molecular_weight")
    public double getMolecular_weight() {
        return molecular_weight;
    }

    public void setMolecular_weight(double mol_weight) {
        this.molecular_weight = mol_weight;
    }

    @Column(name = "sequence_length")
    public int getSequence_length() {
        return sequence_length;
    }

    public void setSequence_length(int sequence_length) {
        this.sequence_length = sequence_length;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "peptide")
    public Set<Spectrum> getSpectra() {
        return spectra;
    }

    public void setSpectra(Set<Spectrum> spectra) {
        this.spectra = spectra;
    }

    @Override
    public String toString(){
        String spectraList = "spectra: ";
        for(Spectrum s: spectra){
            spectraList+="\n"+s.getPtm_sequence()+", xcorr: "+s.getXcorr()+", module: "+s.getModule().getLib_mod()+", species: "+s.getModule().getSpecies().getSpecies_name();
        }
        return "ID: "+Integer.toString(this.getPeptide_id())+"\n"+"sequence: "+this.getPeptide_sequence()+"\n"+spectraList+"\n**";
    }
}
