package org.copakb.server.dao.model;

import javax.persistence.Table;
import javax.persistence.*;

/**
 * Created by Kevin on 4/30/2015.
 */

@Entity
@Table(name = "ptm")
public class PTM {
    private ProteinCurrent proteinCurrent;
    private int ptm_type;
    private int ptm_site;
    private char ptm_site_code;
    private String PMIDS;
    private String kinases;
    private String source;
    private String db_source;

    public PTM(int ptm_type, int ptm_site, char ptm_site_code, String PMIDS, String kinases, String source, String db_source, ProteinCurrent proteinCurrent) {
        this.ptm_type = ptm_type;
        this.ptm_site = ptm_site;
        this.ptm_site_code = ptm_site_code;
        this.PMIDS = PMIDS;
        this.kinases = kinases;
        this.source = source;
        this.db_source = db_source;
        this.proteinCurrent = proteinCurrent;
    }

    public PTM() {
        //empty
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "protein_acc", nullable = false)
    public ProteinCurrent getProteinCurrent() {
        return proteinCurrent;
    }
    public void setProteinCurrent(ProteinCurrent proteinCurrent) {
        this.proteinCurrent = proteinCurrent;
    }

    @Id
    @Column(name = "ptm_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPtm_type() {
        return ptm_type;
    }

    public void setPtm_type(int ptm_type) {
        this.ptm_type = ptm_type;
    }

    @Column(name = "ptm_site")
    public int getPtm_site() {
        return ptm_site;
    }
    public void setPtm_site(int ptm_site) {
        this.ptm_site = ptm_site;
    }

    @Column(name = "ptm_site_code")
    public char getPtm_site_code() {
        return ptm_site_code;
    }
    public void setPtm_site_code(char ptm_site_code) {
        this.ptm_site_code = ptm_site_code;
    }

    @Column(name = "PMIDS")
    public String getPMIDS() {
        return PMIDS;
    }
    public void setPMIDS(String PMIDS) {
        this.PMIDS = PMIDS;
    }

    @Column(name = "kinases")
    public String getKinases() {
        return kinases;
    }
    public void setKinases(String kinases) {
        this.kinases = kinases;
    }

    @Column(name = "source")
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    @Column(name = "db_source")
    public String getDb_source() {
        return db_source;
    }
    public void setDb_source(String db_source) {
        this.db_source = db_source;
    }

    @Override
    public String toString() {
        String result = "protein_acc: " + getProteinCurrent().getProtein_acc() + "\n" +
                "ptm_type: " + getPtm_type() + "\n" +
                "ptm_site_code: " + getPtm_site_code() + "\n" +
                "PMIDS: " + getPMIDS() + "\n" +
                "kinases: " + getKinases() + "\n" +
                "source: " + getSource() + "\n" +
                "db_source: " + getDb_source();

        return result;
    }
}
