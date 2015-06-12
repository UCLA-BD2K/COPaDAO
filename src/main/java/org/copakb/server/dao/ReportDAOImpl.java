package org.copakb.server.dao;

import org.copakb.server.dao.model.Report;
import org.copakb.server.dao.model.ReportProtein;
import org.copakb.server.dao.model.ScanPeptide;

import org.hibernate.criterion.Criterion;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.lang.String;

/**
 * Created by Kevin on 5/28/2015.
 */

public class ReportDAOImpl implements ReportDAO {
    //http://www.journaldev.com/4144/spring-data-mongodb-example-tutorial

    private MongoOperations mongoOps;

    private static final String REPORT_COLLECTION = "Reports";

    public ReportDAOImpl(MongoOperations mongoOps){
        this.mongoOps = mongoOps;
    }

    public String generateToken() {
        //ex: 5508bc9bfb8bcff4d7c0b597
        //length: 24, lower case alphanumeric
        String result = RandomStringUtils.random(24, true, true).toLowerCase();

        return result;
    }

    @Override
    public Report searchReport(String token) {
        return this.mongoOps.findById(token, Report.class, REPORT_COLLECTION);
    }

    @Override
    public ReportProtein searchProtein(String token, String uniprotID) {
        //http://docs.spring.io/spring-data/data-mongo/docs/1.4.0.RC1/reference/html/mongo.repositories.html


        Report report = searchReport(token);

//        Criteria criteria = Criteria.where("proteins.iPI").is(uniprotID);
//        return mongoOps.findOne(Query.query(criteria), ReportProtein.class);

        ArrayList<ReportProtein> proteins = report.getReportProteins();

        for (ReportProtein protein : proteins) {
            if (protein.getcOPaID().equals(uniprotID))
                return protein;
        }

        return null;

    }

    @Override
    public ScanPeptide searchScanPeptide(String token, String uniprotID, String sequence) {
        ReportProtein reportProtein = searchProtein(token, uniprotID);
        ArrayList<ScanPeptide> peptides = reportProtein.getScanPeptides();

        for (ScanPeptide peptide : peptides) {
            if (peptide.getPeptideSequence().equals(sequence))
                return peptide;
        }

        return null;
    }

    public long count() {
        return 1;
    }

}
