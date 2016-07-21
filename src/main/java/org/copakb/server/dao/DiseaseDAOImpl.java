package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * DiseaseDAO implementation
 * Created by Ping PC1 on 7/1/2015.
 */
@SuppressWarnings("unchecked")
public class DiseaseDAOImpl extends DAOImpl implements DiseaseDAO {
    @Override
    public String addDisease(Disease d) {
        Disease disease = searchDisease(d.getDOID());
        if (disease != null) {
            return disease.getDOID();
        }

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            String result = (String) session.save(d);
            tx.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        } finally {
            session.close();
        }
    }

    @Override
    public Disease searchDisease(String doid) {
        Session session = sessionFactory.openSession();

        Disease disease = (Disease) session.get(Disease.class, doid);
        session.close();

        return disease;
    }

    @Override
    public Disease getInitializedDisease(String doid) {
        Session session = sessionFactory.openSession();

        Disease disease = (Disease) session.get(Disease.class, doid);
        if (disease != null) {
            Hibernate.initialize(disease.getGenes());
        }
        session.close();

        return disease;
    }

    @Override
    public String addDiseaseGene(DiseaseGene d) {
        DiseaseGene diseaseGene = searchDiseaseGene(d.getDisease(), d.getGene());
        if (diseaseGene != null) {
            return diseaseGene.getDisease().getDOID();
        }

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            String result = (String) session.save(d);
            tx.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
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
    public DiseaseGene getInitializedDiseaseGene(Disease d, Gene g) {
        Session session = sessionFactory.openSession();

        DiseaseGene dg = (DiseaseGene) session
                .createCriteria(DiseaseGene.class)
                .add(Restrictions.and(
                        Restrictions.eq("disease", d),
                        Restrictions.eq("gene", g)
                ))
                .setMaxResults(1)   // should only be one match
                .uniqueResult();

        if (dg != null) {
            Hibernate.initialize(dg.getDiseaseGenePublications());
        }

        session.close();

        return dg;
    }

    @Override
    public List<Gene> limitedGeneList(int start, int length) {
        Session session = sessionFactory.openSession();
        List<Gene> genes = session
                .createCriteria(Gene.class)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .setFirstResult(start)
                .setMaxResults(length)
                .list();
        session.close();

        return genes;
    }
}
