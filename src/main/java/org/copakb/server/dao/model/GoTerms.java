package org.copakb.server.dao.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Created by Kevin on 5/1/2015.
 */
@Entity
@Table(name = "Go_Terms")
public class GoTerms {
    private int GO_accession;
    private String Terms;
    private Set<ProteinCurrent> proteins;

    public GoTerms(int GO_accession, String terms, Set<ProteinCurrent> proteins) {
        this.GO_accession = GO_accession;
        Terms = terms;
        this.proteins = proteins;
    }

    public GoTerms() {
    }

    @Id
    @Column(name = "GO_accession")
    public int getGO_accession() {
        return GO_accession;
    }
    public void setGO_accession(int GO_accession) {
        this.GO_accession = GO_accession;
    }

    @Column(name = "Terms")
    public String getTerms() {
        return Terms;
    }
    public void setTerms(String terms) {
        Terms = terms;
    }


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "GO_Protein", joinColumns = {
            @JoinColumn(name = "GO_accession", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "protein_acc",
                    nullable = false, updatable = false) })
    public Set<ProteinCurrent> getProteins() {
        return proteins;
    }

    public void setProteins(Set<ProteinCurrent> proteins) {
        this.proteins = proteins;
    }
}
