package org.copakb.server.dao;

import org.copakb.server.dao.model.AnalysisTask;
import org.copakb.server.dao.model.Report;
import org.copakb.server.dao.model.ReportProtein;
import org.copakb.server.dao.model.ScanPeptide;

public interface ReportDAO {
    //http://www.journaldev.com/4144/spring-data-mongodb-example-tutorial

    public String generateToken();


    /* Report searches */

    public Report searchReport(String token);
    public ReportProtein searchProtein(String token, String uniprotID);


    /* Scan peptide search */

    public ScanPeptide searchScanPeptide(String token, String uniprotID, String sequence);


    /* Task add & search */

    public int addTask(AnalysisTask task);

    public AnalysisTask searchTask(String token);

}

