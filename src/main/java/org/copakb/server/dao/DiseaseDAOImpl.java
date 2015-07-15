package org.copakb.server.dao;

import org.copakb.server.dao.model.Disease;
import org.copakb.server.dao.model.DiseaseGene;
import org.copakb.server.dao.model.Gene;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * DiseaseDAO implementation
 * Created by Ping PC1 on 7/1/2015.
 */
@SuppressWarnings("unchecked")
public class DiseaseDAOImpl implements DiseaseDAO {

    private SessionFactory sessionFactory;

    /**
     * Default sets the session factory
     *
     * @param sessionFactory SessionFactory to use
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int addDisease(Disease d) {
        Disease disease = searchDisease(d.getDOID());
        if (disease != null) {
            return disease.getDOID();
        }

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            int result = (int) session.save(d);
            tx.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

    @Override
    public Disease searchDisease(int doid) {
        Session session = sessionFactory.openSession();

        Disease disease = (Disease) session.get(Disease.class, doid);
        session.close();

        return disease;
    }

    @Override
    public List<Disease> searchDiseasesByGene(String geneName) {
        Session session = sessionFactory.openSession();

        List<Disease> diseases = session
                .createCriteria(Disease.class, "d")
                .createAlias("d.genes", "genes")
                .add(Restrictions.eq("genes.gene_name", geneName))
                .list();
        session.close();

        return diseases;
    }

    @Override
    public int addDiseaseGene(DiseaseGene d) {
        DiseaseGene diseaseGene = searchDiseaseGene(d.getDisease(), d.getGene());
        if (diseaseGene != null) {
            return diseaseGene.getDisease().getDOID();
        }

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(d);
            tx.commit();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

    @Override
    public DiseaseGene searchDiseaseGene(Disease disease, Gene gene) {
        Session session = sessionFactory.openSession();

        DiseaseGene diseaseGene = (DiseaseGene) session
                .createCriteria(DiseaseGene.class)
                .add(Restrictions.and(
                        Restrictions.eq("disease", disease),
                        Restrictions.eq("gene", gene)))
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        return diseaseGene;
    }

    @Override
    public List<Gene> limitedGeneList(int start, int length) {
        Session session = sessionFactory.openSession();
        List<Gene> genes = session
                .createCriteria(Gene.class)
                .setFirstResult(start)
                .setMaxResults(length)
                .list();
        session.close();

        return genes;
    }
}
