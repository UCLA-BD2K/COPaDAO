package org.copakb.server.dao;

import org.copakb.server.dao.model.LibraryModule;
import org.copakb.server.dao.model.Peptide;
import org.copakb.server.dao.model.Spectrum;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.internal.ast.tree.RestrictableStatement;

import java.util.List;

/**
 * Created by vincekyi on 4/16/15.
 */
public class PeptideDAOImpl implements PeptideDAO {

    private SessionFactory sessionFactory;

    /**
     * Initializes the sessionFactory to run database operations
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Adds a peptide into the MySQL database
     * @param   p   model of the Peptide that needs to be added
     * @return  On success: the ID of the peptide that is added
     *          On failure: returns -1
     * @throws HibernateException
     */
    public int addPeptide(Peptide p) throws HibernateException{
        int result = -1;
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        result = (int)session.save(p);
        tx.commit();
        session.close();

        return result;
    }

    /**
     * Adds a spectrum into the MySQL database
     * @param   s   model of the Spectrum that needs to be added
     * @return  On success: returns the ID of the spectrum that is added
     *          On failure: returns -1
     * @throws HibernateException
     */
    public int addSpectrum(Spectrum s) throws HibernateException{
        int result = -1;

        Spectrum existingSpectrum = searchSpectrum(s.getPtm_sequence(), s.getModule().getMod_id(), s.getCharge_state());
        if(existingSpectrum!=null)
            return existingSpectrum.getSpectrum_id();

        Peptide peptide = this.searchBySequence(s.getPeptide().getPeptide_sequence());
        if(peptide==null) {
            int peptide_id = this.addPeptide(s.getPeptide());
            s.getPeptide().setPeptide_id(peptide_id);
        }else{
            s.setPeptide(peptide);
        }

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        result = (int)session.save(s);

        tx.commit();
        session.close();

        return result;
    }

    /**
     * Searches the MySQL database for a specific Spectrum
     * @param ptm_seq   ptm_seq of the Spectrum
     * @param mod_id    mod_id of the Spectrum
     * @param charge    charge of the Spectrum
     * @return  The spectrum with the specified ptm_seq, mod_id, and charge
     */
    public Spectrum searchSpectrum(String ptm_seq, int mod_id, int charge){
        Session session = this.sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Spectrum.class);

        Transaction tx = session.beginTransaction();
        try {
            LibraryModule mod = new LibraryModule();
            mod.setMod_id(mod_id);
            Criterion pmtRestriction = Restrictions.eq("ptm_sequence", ptm_seq);
            Criterion modRestriction = Restrictions.eq("module", mod);

            Criterion chargeRestriction = Restrictions.eq("charge_state", charge);

            Criterion pmtAndMod = Restrictions.and(pmtRestriction, modRestriction);


            criteria.add(Restrictions.and(pmtAndMod, chargeRestriction));
            List<Spectrum> results = criteria.list();
            tx.commit();
            if(results.isEmpty())
                return null;
            return results.get(0);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }

    /**
     * Searches the MySQL database for all Peptides
     * @return list of all Peptides in the database
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Peptide> list() throws HibernateException{
        Session session = this.sessionFactory.openSession();
        List<Peptide> peptideList = session.createCriteria(Peptide.class).list();
        session.close();
        return peptideList;
    }

    /**
     * Searches the MySQL database for a limited number of Peptides
     * @param start     the starting index of the desired list
     * @param length    the length of the desired list
     * @return          List of Peptides with desired starting index and length
     */
    public List<Peptide> limitedList(int start, int length){
        Session session = this.sessionFactory.openSession();
        List<Peptide> peptideList = session.createCriteria(Peptide.class).setFirstResult(start).setMaxResults(length).list();
        session.close();
        return peptideList;
    }

    /**
     * Searches the database for a specific Peptide
     * @param   peptide_id  the ID of the desired Peptide
     * @return  the specific Peptide with the provided ID
     * @throws HibernateException
     */
    public Peptide searchById(Integer peptide_id) throws HibernateException{
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

    /**
     * Searches the database for a specific Peptide
     * @param   peptide_sequence  the peptide sequence of the desired Peptide
     * @return  the specific Peptide with the provided peptide sequence
     * @throws HibernateException
     */
    public Peptide searchBySequence(String peptide_sequence) throws HibernateException{
        Session session = this.sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Peptide.class);

        Transaction tx = session.beginTransaction();
        try {
            //peptide = (Peptide)session.get(Peptide.class, peptide_sequence);
            Criterion c = Restrictions.eq("peptide_sequence", peptide_sequence);
            criteria.add(c);
            List<Peptide> results = criteria.list();
            tx.commit();
            if(results.isEmpty())
                return null;
            return results.get(0);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }

    /**
     * Searches the database for a specific Spectrum
     * @param   id  the ID of the desired Spectrum
     * @return  the Spectrum with the provided ID
     * @throws HibernateException
     */
    public Spectrum searchBySpecId(Integer id) throws HibernateException{
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
