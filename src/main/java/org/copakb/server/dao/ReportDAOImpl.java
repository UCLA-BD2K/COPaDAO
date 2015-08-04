package org.copakb.server.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.copakb.server.dao.model.AnalysisTask;
import org.copakb.server.dao.model.Report;
import org.copakb.server.dao.model.ReportProtein;
import org.copakb.server.dao.model.ScanPeptide;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * ReportDAO implementation.
 * Created by Kevin on 5/28/2015.
 */
public class ReportDAOImpl extends DAOImpl implements ReportDAO {
    //http://www.journaldev.com/4144/spring-data-mongodb-example-tutorial
    private MongoOperations mongoOps;

    private static final String REPORT_COLLECTION = "Reports";

    /**
     * Sets the MongoOps to execute MongoDB operations
     *
     * @param mongoOps
     */
    public ReportDAOImpl(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    /**
     * generates a random token for Report identification
     *
     * @return a token 24 characters long, and only contains lower-case alphanumeric characters
     */
    public String generateToken() {
        //ex: 5508bc9bfb8bcff4d7c0b597
        //length: 24, lower case alphanumeric

        return RandomStringUtils.random(24, true, true).toLowerCase();
    }

    /**
     * Searches the MongoDB database for a specific Report
     *
     * @param token the token of a specific Report
     * @return model of the Report with the specified token
     */
    @Override
    public Report searchReport(String token) {
        return this.mongoOps.findById(token, Report.class, REPORT_COLLECTION);
    }

    /**
     * Searches the MongoDB database for a specific Protein in a specific Report
     *
     * @param token     the token of a specific Report
     * @param uniprotID the uniprotID of a specific Protein
     * @return the model of the Protein with the specific uniprotID that is in the Report with the specific token
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
     *
     * @param token     the token of a specific Report
     * @param uniprotID the uniprotID of a specific Protein
     * @param sequence  the peptide sequence of a specific Peptide
     * @return the model of a Peptide with the specified sequence
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

    /**
     * Add a species to the database
     *
     * @param task defined species object with name, id, and list of relevant proteins
     * @return task id if successful, -1 otherwise
     * @throws HibernateException
     */
    public int addTask(AnalysisTask task) throws HibernateException {
        int result = -1;
        try {
            if (task.getToken() != null) {
                AnalysisTask existingTask = searchTask(task.getToken()); // add param
                if (existingTask != null)
                    return existingTask.getTask_id();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            result = (int) session.save(task);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Searches for a task object by checking task id
     *
     * @param tok name of task
     * @return species object that matches the given name
     */
    public AnalysisTask searchTask(String tok) {
        Session session = this.sessionFactory.openSession();
        //AnalysisTask task = null;
        org.hibernate.Criteria criteria = session.createCriteria(AnalysisTask.class);
        Transaction tx = session.beginTransaction();
        try {
            //task = (AnalysisTask) session.get(AnalysisTask.class, token);
            //tx.commit();

            Criterion nameRestriction = Restrictions.eq("token", tok);
            criteria.add(nameRestriction);//Restrictions.and(nameRestriction));
            List<AnalysisTask> results = criteria.list();
            tx.commit();
            if (results.isEmpty())
                return null;
            return results.get(0);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
        //return task;
    }

    public long count() {
        return 1;
    }
}
