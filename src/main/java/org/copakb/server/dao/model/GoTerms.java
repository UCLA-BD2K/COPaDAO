package org.copakb.server.dao.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Created by Kevin on 5/1/2015.
 */
@Entity
@Table(name = "GO_Terms")
public class GoTerms {
    private int GO_accession;
    private String Terms;
    private String Evidence;
    private String Reference;
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

    @Column(name = "Evidence")
    public String getEvidence() {
        return Evidence;
    }
    public void setEvidence(String evidence) {
        Evidence = evidence;
    }

    @Column(name = "Reference")
    public String getReference() {
        return Reference;
    }
    public void setReference(String reference) {
        Reference = reference;
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

    @Override
    public String toString() {
        return "GoTerms{" +
                "GO_accession=" + GO_accession +
                ", Terms='" + Terms + '\'' +
                ", Evidence='" + Evidence + '\'' +
                ", Reference='" + Reference + '\'' +
                '}';
    }
}
