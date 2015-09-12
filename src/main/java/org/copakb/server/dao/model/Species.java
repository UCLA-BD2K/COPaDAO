package org.copakb.server.dao.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Species model.
 * Created by vincekyi on 4/27/15.
 */
@Entity
@Table(name = "species")
public class Species {
    private int species_id;
    private String species_name;
    private Set<LibraryModule> modules;
    private Set<ProteinCurrent> proteinCurrents;

    public Species(int species_id, String species_name, Set<LibraryModule> modules, Set<ProteinCurrent> proteinCurrents) {
        this.species_id = species_id;
        this.species_name = species_name;
        this.modules = modules;
        this.proteinCurrents = proteinCurrents;
    }

    public Species(int species_id, String species_name) {
        this.species_id = species_id;
        this.species_name = species_name;
    }

    public Species() {
        // default
    }

    @Id
    @Column(name = "species_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getSpecies_id() {
        return species_id;
    }

    public void setSpecies_id(int species_id) {
        this.species_id = species_id;
    }

    @Column(name = "species_name")
    public String getSpecies_name() {
        return species_name;
    }

    public void setSpecies_name(String species_name) {
        this.species_name = species_name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "species")
    public Set<LibraryModule> getModules() {
        return modules;
    }

    public void setModules(Set<LibraryModule> modules) {
        this.modules = modules;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "species")
    public Set<ProteinCurrent> getProteinCurrents() {
        return proteinCurrents;
    }

    public void setProteinCurrents(Set<ProteinCurrent> proteinCurrents) {
        this.proteinCurrents = proteinCurrents;
    }

    @Override
    public String toString() {
        return ("Species ID: " + species_id + "\n") + "Species Name: " + species_name + "\n";
    }
}
