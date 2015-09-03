package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public Disease getInitializedDisease(int doid) {
        Session session = sessionFactory.openSession();

        Disease disease = (Disease) session.get(Disease.class, doid);
        if (disease != null) {
            Hibernate.initialize(disease.getGenes());
        }
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
    public int addDiseaseGene2(DiseaseGene2 d) {
        DiseaseGene2 diseaseGene = searchDiseaseGene2(d.getDisease(), d.getGene());
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
    public DiseaseGene2 searchDiseaseGene2(Disease disease, Gene2 gene) {
        Session session = sessionFactory.openSession();

        DiseaseGene2 diseaseGene = (DiseaseGene2) session
                .createCriteria(DiseaseGene2.class)
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
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .setFirstResult(start)
                .setMaxResults(length)
                .list();
        session.close();

        return genes;
    }

    @Override
    public List<Gene2> limitedGene2List(int start, int length) {
        Session session = sessionFactory.openSession();
        List<Gene2> genes = session
                .createCriteria(Gene2.class)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .setFirstResult(start)
                .setMaxResults(length)
                .list();
        session.close();

        return genes;
    }
}
