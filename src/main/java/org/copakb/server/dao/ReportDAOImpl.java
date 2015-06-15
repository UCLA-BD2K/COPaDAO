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

    /**
     * Sets the MongoOps to execute MongoDB operations
     * @param mongoOps
     */

    public ReportDAOImpl(MongoOperations mongoOps){
        this.mongoOps = mongoOps;
    }

    /**
     * generates a random token for Report identification
     * @return a token 24 characters long, and only contains lower-case alphanumeric characters
     */
    public String generateToken() {
        //ex: 5508bc9bfb8bcff4d7c0b597
        //length: 24, lower case alphanumeric
        String result = RandomStringUtils.random(24, true, true).toLowerCase();

        return result;
    }

    /**
     * Searches the MongoDB database for a specific Report
     * @param   token   the token of a specific Report
     * @return  model of the Report with the specified token
     */
    @Override
    public Report searchReport(String token) {
        return this.mongoOps.findById(token, Report.class, REPORT_COLLECTION);
    }

    /**
     * Searches the MongoDB database for a specific Protein in a specific Report
     * @param   token       the token of a specific Report
     * @param   uniprotID   the uniprotID of a specific Protein
     * @return  the model of the Protein with the specific uniprotID that is in the Report with the specific token
     */
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

    /**
     * Searches the MongoDB database for a specific Peptide in a specific Protein in a specific Report
     * @param   token       the token of a specific Report
     * @param   uniprotID   the uniprotID of a specific Protein
     * @param   sequence    the peptide sequence of a specific Peptide
     * @return  the model of a Peptide with the specified sequence
     */
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
