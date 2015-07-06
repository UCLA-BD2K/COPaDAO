package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
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

    /**
     * Returns a list of peptides containing the partial sequence.
     *
     * @param sequence Partial sequence to search.
     * @return  List of peptides containing the partial sequence.
     */
    @Override
    public List<Peptide> searchByPartialSequence(String sequence) {
        Session session = sessionFactory.openSession();

        // Create query
        List<Peptide> peptides = session
                .createCriteria(Peptide.class)
                .add(Restrictions.like("peptide_sequence", "%" + sequence + "%"))
                .list();

        session.beginTransaction().commit();
        session.close();

        return peptides;
    }

    /**
     * Searches for a species object by checking species names
     *
     * @param name name of species
     * @return species object that matches the given name
     */
    public Species searchSpecies(String name) {
        Session session = this.sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Species.class);

        Transaction tx = session.beginTransaction();
        try {
            Criterion nameRestriction = Restrictions.eq("species_name", name);

            criteria.add(Restrictions.and(nameRestriction));
            List<Species> results = criteria.list();
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
     * Add a species to the database
     *
     * @param sp defined species object with name, id, and list of relevant proteins
     * @return species id if successful, -1 otherwise
     * @throws HibernateException
     */
    public int addSpecies(Species sp) throws HibernateException {
        int result = -1;

        Species existingSpecies = searchSpecies(sp.getSpecies_name()); // add param
        if (existingSpecies != null)
            return existingSpecies.getSpecies_id();

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            result = (int) session.save(sp);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Searches for a library module in the database
     *
     * @param id ID of library module as autogenerated by the database
     * @return defined LibraryModule object
     */
    public LibraryModule searchLibraryModuleWithId(int id) {
        Session session = this.sessionFactory.openSession();

        Criteria criteria = session.createCriteria(LibraryModule.class);

        Transaction tx = session.beginTransaction();
        try {
            Criterion nameRestriction = Restrictions.eq("mod_id", id);

            criteria.add(Restrictions.and(nameRestriction));
            List<LibraryModule> results = criteria.list();
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
     * Searches for library module in the database
     * @param lib_mod name of the module; formatted all lower case with underscores between words (ex. human_heart_proteasome)
     * @return defined LibraryModule object
     */
    public LibraryModule searchLibraryModuleWithModule(String lib_mod) {
        Session session = this.sessionFactory.openSession();

        Criteria criteria = session.createCriteria(LibraryModule.class);

        Transaction tx = session.beginTransaction();
        try {
            Criterion nameRestriction = Restrictions.eq("lib_mod", lib_mod);

            criteria.add(Restrictions.and(nameRestriction));
            List<LibraryModule> results = criteria.list();
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
     * Adds a library module to the database
     * @param libmod defined LibraryModule object to be added
     * @return autogenerated library module id if successful, -1 otherwise
     */
    public int addLibraryModule(LibraryModule libmod) {
        int result = -1;

        LibraryModule existingLibraryModule = searchLibraryModuleWithId(libmod.getMod_id()); // add param
        if (existingLibraryModule != null)
            return existingLibraryModule.getMod_id();

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            result = (int) session.save(libmod);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Returns PTM object
     * @param id PTM type as defined in the database. Ranges from 1-255 using the following code:
     *       1	Carbamidomethylation	C,K,H	57.02000
     *         2	Acetylation	K,N-term	42.01000
     *         4	Oxidation	M	15.99000
     *         8	Phosphorylation	S,T	79.97000
     *         16	Succinylation	K	100.01860
     *         32	Propionamide	C	71.03712
     *         64	Pyro-carbamidomethyl	C	39.99492
     *         128	Pyro-glu	E	-17.03000
     * @return PTM object
     */
    public PTM_type searchPtmType(int id) {
        Session session = this.sessionFactory.openSession();

        Criteria criteria = session.createCriteria(PTM_type.class);

        Transaction tx = session.beginTransaction();
        try {
            Criterion nameRestriction = Restrictions.eq("ptm_type", id);

            criteria.add(Restrictions.and(nameRestriction));
            List<PTM_type> results = criteria.list();
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
     * Add PTM information to all relevant protein objects
     * @param type defined PTM object to be added
     * @return PTM type if successful, empty string otherwise
     */
    public int addPtmType(PTM_type type) {
        int result = -1;

        PTM_type existingPtm_type = searchPtmType(type.getPtm_type()); // add param
        if (existingPtm_type != null)
            return existingPtm_type.getPtm_type();

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            result = (int) session.save(type);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String getSpectrum(int spec_id) {
        return "";
    }

    public int getLocation(Peptide peptide, ProteinCurrent protein) {

        Session session = this.sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SpectrumProtein.class);

        Transaction tx = session.beginTransaction();
        try {
            Criterion peptideRestriction = Restrictions.eq("peptide", peptide);
            Criterion proteinRestriction = Restrictions.eq("protein_acc", protein.getProtein_acc());
            criteria.add(Restrictions.and(peptideRestriction));
            criteria.add(Restrictions.and(proteinRestriction));
            List<SpectrumProtein> results = criteria.list();
            tx.commit();
            if (results.isEmpty())
                return -1;
            return results.get(0).getLocation();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

}
