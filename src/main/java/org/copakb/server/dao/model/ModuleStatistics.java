package org.copakb.server.dao.model;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Sneha on 7/2/2015.
 */

@Entity
@Table(name = "module_statistics")
public class ModuleStatistics {
    private int mod_id;
    private String species;
    private String organelle;
    private int num_of_proteins;
    private int num_of_peptides;
    private int num_of_spectra;
    private Date last_modified;

    public ModuleStatistics(int mod_id, String species, String organelle) {
        this.mod_id = mod_id;
        this.species = species;
        this.organelle = organelle;
    }

    public ModuleStatistics() {
    }

    @Id
    @Column(name = "mod_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getMod_id() {
        return mod_id;
    }

    public void setMod_id(int mod_id) {
        this.mod_id = mod_id;
    }

    @Column(name = "species")
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Column(name = "organelle")
    public String getOrganelle() {
        return organelle;
    }

    public void setOrganelle(String organelle) {
        this.organelle = organelle;
    }

    @Column(name = "num_of_proteins")
    public int getNum_of_proteins() {
        return num_of_proteins;
    }

    public void setNum_of_proteins(int num_of_proteins) {
        this.num_of_proteins = num_of_proteins;
    }

    @Column(name = "num_of_peptides")
    public int getNum_of_peptides() {
        return num_of_peptides;
    }

    public void setNum_of_peptides(int num_of_peptides) {
        this.num_of_peptides = num_of_peptides;
    }

    @Column(name = "num_of_spectra")
    public int getNum_of_spectra() {
        return num_of_spectra;
    }

    public void setNum_of_spectra(int num_of_spectra) {
        this.num_of_spectra = num_of_spectra;
    }

    @Column(name = "last_modified")
    @Temporal(TemporalType.DATE)
    public Date getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }

}
