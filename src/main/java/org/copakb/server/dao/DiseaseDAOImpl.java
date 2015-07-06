package org.copakb.server.dao;

import org.copakb.server.dao.model.Disease;
import org.copakb.server.dao.model.DiseaseGene;
import org.copakb.server.dao.model.Gene;
import org.copakb.server.dao.model.ProteinCurrent;
import org.hibernate.*;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;

/**
 * Created by Ping PC1 on 7/1/2015.
 */
public class DiseaseDAOImpl implements DiseaseDAO {

    private SessionFactory sessionFactory;

    /**
     * Default sets the session factory
     *
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Search for a disease object from the table
     *
     * @param doid OMIM disease id
     * @return completed disease object retrieved from the database
     */
    public Disease searchDisease(int doid) {
        Session session = this.sessionFactory.openSession();
        Disease disease = null;

        Transaction tx = session.beginTransaction();
        try {
            disease = (Disease) session.get(Disease.class, doid);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
        return disease;
    }

    public List<Disease> searchDiseaseByGene(String geneName) {
        Session session = sessionFactory.openSession();

        List<Disease> diseases = session
                .createCriteria(Disease.class, "d")
                .createAlias("d.genes", "genes")
                .add(Restrictions.eq("genes.gene_name", geneName))
                .list();

        session.beginTransaction().commit();
        session.close();

        return diseases;
    }

    /**
     * Add disease information to the database
     *
     * @param d defined disease  object to be added
     * @return disease OMIM id if successful, -1 otherwise
     * @throws HibernateException
     */
    public int addDisease(Disease d) throws HibernateException {
        int result = -1;

        Disease disease = searchDisease(d.getDOID());
        if (disease != null)
            return disease.getDOID();

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            result = (int) (session.save(d));
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Add disease gene information to the database
     *
     * @param d defined diseasegene object to be added
     * @return disease OMIM id if successful, -1 otherwise
     */
    public int addDiseaseGene(DiseaseGene d) {
        int result = 0;

        DiseaseGene diseaseGene = searchDiseaseGene(d.getDisease(), d.getGene());
        if (diseaseGene != null)
            return diseaseGene.getDisease().getDOID();

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(d);
            //result = (int) session.save(d);
            tx.commit();
            session.close();
        } catch (Exception e) {
            result = -1;
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Searches for the DiseaseGene information from the database using the disease and gene symbol as the objects.
     * Mapped using the Disease and Gene objects through hibernate
     *
     * @param disease Disease to be matched
     * @param gene    Gene to be matched
     * @return defined DiseaseGene object with information from the database
     */
    public DiseaseGene searchDiseaseGene(Disease disease, Gene gene) {
        DiseaseGene diseaseGene = null;

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        org.hibernate.Criteria criteria = session.createCriteria(DiseaseGene.class);
        try {
            Criterion diseaseRestriction = Restrictions.eq("disease", disease);
            Criterion geneRestriction = Restrictions.eq("gene", gene);

            criteria.add(Restrictions.and(diseaseRestriction, geneRestriction));

            List<DiseaseGene> results = criteria.list();
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

    }

    /**
     * Searches a limited list of Gene objects from the database
     *
     * @param start  beginning index for list
     * @param length number of genes to be returned
     * @return partial list of specified length of Gene objects beginning at the start index
     */
    public List<Gene> limitedGeneList(int start, int length) {
        Session session = this.sessionFactory.openSession();
        List<Gene> genes = session.createCriteria(Gene.class).setFirstResult(start).setMaxResults(length).list();
        session.close();
        return genes;
    }
}
