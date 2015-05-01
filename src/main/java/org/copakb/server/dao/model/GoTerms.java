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
    private Set<GOProtein> GOProteins = new HashSet<GOProtein>(0);

    public GoTerms(int GO_accession, String terms, Set<GOProtein> GOProteins) {
        this.GO_accession = GO_accession;
        Terms = terms;
        this.GOProteins = GOProteins;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "goTerm")
    public Set<GOProtein> getGOProteins() {
        return GOProteins;
    }
    public void setGOProteins(Set<GOProtein> GOProteins) {
        this.GOProteins = GOProteins;
    }
}
