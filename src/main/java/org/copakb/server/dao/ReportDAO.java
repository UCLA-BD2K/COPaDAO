package org.copakb.server.dao;

import org.copakb.server.dao.model.Report;
import org.copakb.server.dao.model.ReportProtein;
import org.copakb.server.dao.model.ScanPeptide;

public interface ReportDAO {
    //http://www.journaldev.com/4144/spring-data-mongodb-example-tutorial

    public String generateToken();

    public Report searchReport(String token);

    public ReportProtein searchProtein(String token, String uniprotID);

    public ScanPeptide searchScanPeptide(String token, String uniprotID, String sequence);
}

