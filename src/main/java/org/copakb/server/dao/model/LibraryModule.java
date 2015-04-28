package org.copakb.server.dao.model;

/**
 * Created by vincekyi on 4/27/15.
 */


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Library_Module")
public class LibraryModule {

    private int mod_id;
    private String lib_mod;
    private String instrument;
    private String organelle;
    private Date upload_date;
    private Set<Spectrum> spectra;
    private Species species;

    public LibraryModule(String lib_mod, String instrument, String organelle, Date upload_date, Species species) {
        this.lib_mod = lib_mod;
        this.instrument = instrument;
        this.organelle = organelle;
        this.upload_date = upload_date;
        this.species = species;
    }

    public LibraryModule(){

    }

    @Id
    @Column(name="mod_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getMod_id() {
        return mod_id;
    }

    public void setMod_id(int mod_id) {
        this.mod_id = mod_id;
    }

    @Column(name = "lib_mod")
    public String getLib_mod() {
        return lib_mod;
    }

    public void setLib_mod(String lib_mod) {
        this.lib_mod = lib_mod;
    }

    @Column(name = "instrument")
    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    @Column(name = "organelle")
    public String getOrganelle() {
        return organelle;
    }

    public void setOrganelle(String organelle) {
        this.organelle = organelle;
    }

    @Column(name = "upload_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(Date upload_date) {
        this.upload_date = upload_date;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
    public Set<Spectrum> getSpectra() {
        return spectra;
    }

    public void setSpectra(Set<Spectrum> spectra) {
        this.spectra = spectra;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "species_id", nullable = false)
    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}
