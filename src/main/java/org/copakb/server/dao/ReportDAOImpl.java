package org.copakb.server.dao;

import org.copakb.server.dao.model.Report;
import org.copakb.server.dao.model.ReportProtein;
import org.copakb.server.dao.model.ScanPeptide;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.apache.commons.lang3.RandomStringUtils;

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
        Query query = new Query(Criteria.where("_id").is(token));
        return this.mongoOps.findOne(query, Report.class, REPORT_COLLECTION);
    }

    @Override
    public ReportProtein searchProtein(String token, String uniprotID) {
        //http://docs.spring.io/spring-data/data-mongo/docs/1.4.0.RC1/reference/html/mongo.repositories.html
        return null;
    }

    @Override
    public ScanPeptide searchScanPeptide(String token, String uniprotID, String sequence) {
        return null;
    }

}
