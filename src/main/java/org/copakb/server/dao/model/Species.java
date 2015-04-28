package org.copakb.server.dao.model;

/**
 * Created by vincekyi on 4/27/15.
 */

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Species")
public class Species {

    private int species_id;
    private String species_name;
    private Set<LibraryModule> modules;

    public Species(int species_id, String species_name, Set<LibraryModule> modules) {
        this.species_id = species_id;
        this.species_name = species_name;
        this.modules = modules;
    }

    public Species(){

    }

    @Id
    @Column(name="species_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getSpecies_id() {
        return species_id;
    }

    public void setSpecies_id(int species_id) {
        this.species_id = species_id;
    }

    @Column(name="species_name")
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
}
