package org.copakb.server.dao;

import org.copakb.server.dao.PeptideDAO;
import org.copakb.server.dao.model.Peptide;
import org.copakb.server.dao.model.Spectrum;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by vincekyi on 4/16/15.
 */
public class PeptideDAOImpl implements PeptideDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addPeptide(Peptide p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Peptide> list() {
        Session session = this.sessionFactory.openSession();
        List<Peptide> peptideList = session.createCriteria(Peptide.class).list();
        session.close();
        return peptideList;
    }

    public Peptide searchById(Integer peptide_id) {
        Session session = this.sessionFactory.openSession();
        Peptide peptide = null;

        Transaction tx = session.beginTransaction();
        try {
            peptide = (Peptide)session.get(Peptide.class, peptide_id);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
        return peptide;
    }

    public Peptide searchBySequence(String peptide_sequence){
        Session session = this.sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Peptide.class);

        Transaction tx = session.beginTransaction();
        try {
            //peptide = (Peptide)session.get(Peptide.class, peptide_sequence);
            Criterion c = Restrictions.eq("peptide_sequence", peptide_sequence);
            criteria.add(c);
            List<Peptide> results = criteria.list();
            tx.commit();
            return results.get(0);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }

    public Spectrum searchBySpecId(Integer id){
        Session session = this.sessionFactory.openSession();
        Spectrum spectrum = null;

        Transaction tx = session.beginTransaction();
        try {
            spectrum = (Spectrum)session.get(Spectrum.class, id);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
        return spectrum;
    }

}
