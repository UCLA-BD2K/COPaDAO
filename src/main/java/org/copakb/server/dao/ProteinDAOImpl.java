package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
import org.copakb.server.dao.model.Version;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.*;

/**
 * Created by vincekyi on 5/2/15.
 */

public class ProteinDAOImpl implements ProteinDAO {

    private SessionFactory sessionFactory;

    /**
     * Default sets the session factory
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Searches database for all objects in the database that matches the ProteinCurrent specifications
     * @return all the ProteinCurrent objects contained in the database
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProteinCurrent> list() throws HibernateException {
        Session session = this.sessionFactory.openSession();
        List<ProteinCurrent> proteinList = session.createCriteria(ProteinCurrent.class).list();
        session.close();
        return proteinList;
    }

    /**
     * Searches a limited list of ProteinCurrent objects from the database
     * @param start beginning index for list
     * @param length number of Protein Currents to be returned
     * @return partial list of specified length of ProteinCurrent objects beginning at the start index
     */
    public List<ProteinCurrent> limitedList(int start, int length) {
        Session session = this.sessionFactory.openSession();
        List<ProteinCurrent> proteinList = session.createCriteria(ProteinCurrent.class).setFirstResult(start).setMaxResults(length).list();
        session.close();
        return proteinList;
    }

    /**
     * Adds a ProteinCurrent object to the database
     * @param p object to be added
     * @return Uniprot ID of p if successful, empty string otherwise
     * @throws HibernateException
     */
    public String addProteinCurrent(ProteinCurrent p) throws HibernateException{
        String uniprot = "";

        ProteinCurrent existingProtein = searchByID(p.getProtein_acc()); // add param
        if (existingProtein != null)
            return "Existed";
        //return existingProtein.getProtein_acc();
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            uniprot = (String) session.save(p);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            String temp = "";
            if (e instanceof org.hibernate.exception.LockTimeoutException) {
                temp = addProteinCurrentLast(p);
            }
            if (temp.equals("")) {
                return "Failed";
            }
        }

        return uniprot;
    }

    /**
     * Add ProteinCurrent object as a last resort in case of Hibernate's LockTimeoutException.
     *
     * @param p object to be added
     * @return Uniprot ID of p if successful, empty string otherwise
     */
    public String addProteinCurrentLast(ProteinCurrent p) {
        String uniprot = "";

        ProteinCurrent existingProtein = searchByID(p.getProtein_acc()); // add param
        if (existingProtein != null)
            return "Existed";
        //return existingProtein.getProtein_acc();

        try {
            Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            uniprot = (String) session.save(p);
            tx.commit();
            session.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Failed";
        }

        return uniprot;
    }

    /**
     * Add a species to the database
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
     * Searches for a species object by checking species names
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
     * Searches for an object in the database with the given Uniprot ID
     * @param protein_acc Uniprot ID of the protein as given by www.uniprot.org
     * @return ProteinCurrent object that contains the given Uniprot ID
     */
    public ProteinCurrent searchByID(String protein_acc){
        Session session = this.sessionFactory.openSession();
        ProteinCurrent protein = null;

        Transaction tx = session.beginTransaction();
        try {
            protein = (ProteinCurrent)session.get(ProteinCurrent.class, protein_acc);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
        return protein;
    }

    /**
     * Returns a list of all proteins that start with the given UniProt ID prefix.
     *
     * @param protein_acc   UniProt ID prefix
     * @return              A list of proteins objects that start with the given UniProt ID prefix.
     */
    @Override
    public List<ProteinCurrent> searchByLikeID(String protein_acc) {
        Session session = sessionFactory.openSession();

        // Create query
        List<ProteinCurrent> proteins = session
                .createCriteria(ProteinCurrent.class)
                .add(Restrictions.like("protein_acc", protein_acc + "%"))
                .list();

        session.beginTransaction().commit();
        session.close();

        return proteins;
    }

    /**
     * Searches for an object in the database with the given protein name
     * @param protein_name name of the protein
     * @return ProteinCurrent object that contains the given protein name
     */
    public ProteinCurrent searchByName(String protein_name){
        Session session = this.sessionFactory.openSession();
        ProteinCurrent protein = null;

        Criteria criteria = session.createCriteria(ProteinCurrent.class);

        Transaction tx = session.beginTransaction();
        try {
            Criterion c = Restrictions.eq("protein_name", protein_name);
            criteria.add(c);
            List<ProteinCurrent> results = criteria.list();
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
     *
     * @param ref_kb_id
     * @return
     */
    public ProteinCurrent searchByRef(String ref_kb_id){
        Session session = this.sessionFactory.openSession();

        Criteria criteria = session.createCriteria(ProteinCurrent.class);

        Transaction tx = session.beginTransaction();
        try {
            Criterion c = Restrictions.eq("ref_kb_id", ref_kb_id);
            criteria.add(c);
            List<ProteinCurrent> results = criteria.list();
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
     * Searches for an object in the database with the given Ensembl ID
     * @param ENSG_ID Ensembl id of the protein as given by the www.ensembl.org
     * @return ProteinCurrent object that contains the given Ensembl ID
     */
    public ProteinCurrent searchByEnsg(String ENSG_ID){

        //ENSG is no longer part of ProteinCurrent table
        //Todo rewrite function accordingly
        return null;
//        Session session = this.sessionFactory.openSession();
//
//        Criteria criteria = session.createCriteria(ProteinCurrent.class);
//
//        Transaction tx = session.beginTransaction();
//        try {
//            Criterion c = Restrictions.eq("ENSG_ID", ENSG_ID);
//            criteria.add(c);
//            List<ProteinCurrent> results = criteria.list();
//            tx.commit();
//            if(results.isEmpty())
//                return null;
//            return results.get(0);
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//            return null;
//        }finally{
//            session.close();
//        }
    }

    /**
     * Returns a list of proteins containing the partial ID.
     *
     * @param idFragment Partial sequence to search.
     * @return List of proteins containing the partial ID.
     */
    @Override
    public List<ProteinCurrent> searchByPartialID(String idFragment) {
        Session session = sessionFactory.openSession();

        // Create query
        List<ProteinCurrent> proteins = session
                .createCriteria(ProteinCurrent.class)
                .add(Restrictions.like("protein_acc", "%" + idFragment + "%"))
                .list();

        session.beginTransaction().commit();
        session.close();

        return proteins;
    }

    /**
     * Returns a list of proteins containing the partial sequence.
     *
     * @param sequence Partial sequence to search.
     * @return List of proteins containing the partial sequence.
     */
    @Override
    public List<ProteinCurrent> searchByPartialSequence(String sequence) {
        Session session = sessionFactory.openSession();

        // Create query
        List<ProteinCurrent> proteins = session
                .createCriteria(ProteinCurrent.class)
                .add(Restrictions.like("sequence", "%" + sequence + "%"))
                .list();

        session.beginTransaction().commit();
        session.close();

        return proteins;
    }

    /**
     * Searches for proteins in the database with the given uniprot ID and
     * matches it with all the relevant GO terms
     * @param uniprotID Uniprot ID of the protein as given by www.uniprot.org
     * @return Project object with defined GO terms
     */
    public ProteinCurrent getProteinWithGoTerms(String uniprotID){
        Session session = this.sessionFactory.openSession();
        ProteinCurrent protein = null;
        Transaction tx = session.beginTransaction();

        try {
            protein = (ProteinCurrent)session.get(ProteinCurrent.class, uniprotID);
            Hibernate.initialize(protein.getGoTerms());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

        return protein;
    }

    /**
     * Searches for proteins in the database with the given GO accession ID
     * @param GO_accession GO accession of the protein as automatically defined in the database entries
     * @return GOTerms object which includes all of the relevant proteins and terms
     */
    public GoTerms searchByGOAccession(int GO_accession) {
        Session session = this.sessionFactory.openSession();
        GoTerms go = null;

        Transaction tx = session.beginTransaction();
        try {
            go = (GoTerms)session.get(GoTerms.class, GO_accession);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
        return go;
    }

    public int addGoTerms(GoTerms goTerms) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        int result = -1;

        GoTerms existingGoTerms = searchByGOAccession(goTerms.getGO_accession());
        if (existingGoTerms != null)
            return existingGoTerms.getGO_accession();

        Set<ProteinCurrent> listofProteinCurrent = goTerms.getProteins();

        result = (int) session.save(goTerms);

        tx.commit();
        session.close();

        return result;
    }
//
//    public String addGoTerm(GoTerms g) throws HibernateException {
//        String result = "";
//
//        PTM existingPTM = searchByPTMType(g.getGO_accession());
//        if(existingPTM != null)
//            return existingPTM.getPtm_type();
//
//        ProteinCurrent protein = this.searchByID(p.getProteinCurrent().getProtein_acc());
//        if(protein == null) {
//            String protein_id = this.addProteinCurrent(p.getProteinCurrent());
//            p.getProteinCurrent().setProtein_acc(protein_id);
//        } else{
//            p.setProteinCurrent(protein);
//        }
//
//        Session session = this.sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//        result = String.valueOf(session.save(p));
//
//        tx.commit();
//        session.close();
//
//        return result;
//    }


    /**
     * Searches for proteins in the database with the given uniprot ID and
     * matches it with all the relevant gene information
     * @param uniprotID Uniprot ID of the protein as given by www.uniprot.org
     * @return Protein object with defined gene information
     */
    public ProteinCurrent getProteinWithGenes(String uniprotID){
        Session session = this.sessionFactory.openSession();
        ProteinCurrent protein = null;
        Transaction tx = session.beginTransaction();

        try {
            protein = (ProteinCurrent)session.get(ProteinCurrent.class, uniprotID);
            Hibernate.initialize(protein.getGenes());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

        return protein;
    }

    /**
     * Searches for an object in the database with the given gene name
     * @param name Gene name of the protein
     * @return Gene object that contains the gene information, disease relevance, and relevant proteins
     */
    public Gene searchByGeneName(String name) {
        Session session = this.sessionFactory.openSession();
        Gene gene = null;

        Transaction tx = session.beginTransaction();
        try {
            gene = (Gene)session.get(Gene.class, name);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
        return gene;
    }

    /**
     * Add gene information to all relevant protein objects
     * @param g defined Gene to be added
     * @return gene name if successful, empty string otherwise
     * @throws HibernateException
     */
    public String addGene(Gene g) throws HibernateException {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String result = "";

        Gene existingGene = searchByGeneName(g.getGene_name());
        if(existingGene != null)
            return existingGene.getGene_name();

        result = (String) session.save(g);

        tx.commit();
        session.close();

        return result;
    }

    /**
     * Search for protein and PTM information
     * @param uniprotID Uniprot ID of the protein as given by www.uniprot.org
     * @return ProteinCurrent object with the PTM defined
     */
    public ProteinCurrent getProteinWithPTMs(String uniprotID) {
        Session session = this.sessionFactory.openSession();
        ProteinCurrent protein = null;
        Transaction tx = session.beginTransaction();

        try {
            protein = (ProteinCurrent)session.get(ProteinCurrent.class, uniprotID);
            Hibernate.initialize(protein.getPTMs());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

        return protein;
    }

    /**
     * Search for protein and spectrum information
     * @param uniprotID Uniprot ID of the protein as given by www.uniprot.org
     * @return ProteinCurrent object with the set of spectra defined
     */
    public ProteinCurrent getProteinWithSpectra(String uniprotID) {
        Session session = this.sessionFactory.openSession();
        ProteinCurrent protein = null;
        Transaction tx = session.beginTransaction();

        try
        {
            protein = (ProteinCurrent)session.get(ProteinCurrent.class, uniprotID);
            Hibernate.initialize(protein.getSpectra());
            for(SpectrumProtein s: protein.getSpectra()){
                Hibernate.initialize(s.getSpectrum().getPeptide());
            }

            tx.commit();
        }
        catch (Exception e)
        {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }

        return protein;
    }

    /**
     * Add SpectrumProtein object
     * @param p defined SpectrumProtein object to be added
     * @return Auto-generated ID if successful, -1 otherwise
     * @throws HibernateException
     */
    public int addSpectrumProtein(SpectrumProtein p) throws HibernateException{
        int result = -1;

        //SpectrumProtein existingSpecProtein = searchSpectrumProtein(p.getSpectrum().getSpectrum_id(), p.getProtein_acc()); // add param
        //if (existingSpecProtein != null)
        //    return existingSpecProtein.getSpectrumProtein_id();

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            result = (int) session.save(p);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Searches for SpectrumProtein object from the database
     * @param spectrum_id ID of the spectrum as autogenerated by the database
     * @param protein_acc protein Uniprot ID as used from uniprot.org
     * @return defined SpectrumProtein object
     */
    public SpectrumProtein searchSpectrumProtein(int spectrum_id, String protein_acc) {
        Session session = this.sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SpectrumProtein.class);

        Transaction tx = session.beginTransaction();
        try {
            Criterion specIdRestriction = Restrictions.eq("spectrum_id", spectrum_id);
            Criterion proteinAccRestriction = Restrictions.eq("protein_acc", protein_acc);

            criteria.add(Restrictions.and(specIdRestriction, proteinAccRestriction));
            List<SpectrumProtein> results = criteria.list();
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

    @Override
    public String addHPAProtein(HPAProtein protein) {
        if (searchHPAByID(protein.getEnsemblID()) != null) {
            return "Existed";
        }

        Session session = this.sessionFactory.openSession();
        String result = (String) session.save(protein);

        session.beginTransaction().commit();
        session.close();

        return result;
    }

    @Override
    public HPAProtein searchHPAByID(String ensemblID) {
        Session session = sessionFactory.openSession();

        // Create query
        HPAProtein protein = (HPAProtein) session.get(HPAProtein.class, ensemblID);

        session.beginTransaction().commit();
        session.close();

        return protein;
    }

    @Override
    public String addAntibody(Antibody antibody) {
        if (searchAntibodyByID(antibody.getAntibodyID()) != null) {
            return "Existed";
        }

        Session session = this.sessionFactory.openSession();
        String result = (String) session.save(antibody);

        session.beginTransaction().commit();
        session.close();

        return result;
    }

    @Override
    public Antibody searchAntibodyByID(String antibodyID) {
        Session session = sessionFactory.openSession();

        // Create query
        Antibody antibody = (Antibody) session.get(Antibody.class, antibodyID);

        session.beginTransaction().commit();
        session.close();

        return antibody;
    }

    @Override
    public String addDbRef(DBRef dbRef) {
        if (searchDbRefByID(dbRef.getProteinCurrent().getProtein_acc()) != null) {
            return "Existed";
        }

        Session session = this.sessionFactory.openSession();
        String result = (String) session.save(dbRef);

        session.beginTransaction().commit();
        session.close();

        return result;
    }

    @Override
    public DBRef searchDbRefByID(String uniprotID) {
        Session session = sessionFactory.openSession();
        DBRef dbRef = (DBRef) session.get(DBRef.class, uniprotID);
        session.beginTransaction().commit();
        session.close();
        return dbRef;
    }

    /**
     * Returns the first DBRef which references the given PDB ID.
     *
     * @param pdbID The PDB ID to search for.
     * @return      A protein that references the given PDB IDl
     */
    public DBRef searchDbRefByPDB(String pdbID) {
        Session session = sessionFactory.openSession();

        // Create query
        List<DBRef> dbRefs = session
                .createCriteria(DBRef.class)
                .add(Restrictions.like("pdb", "%" + pdbID + "%"))
                .list();

        session.beginTransaction().commit();
        session.close();

        if (dbRefs == null || dbRefs.isEmpty()) {
            return null;
        }

        return dbRefs.get(0);
    }

    /**
     * Returns the first protein which references the given PDB ID.
     *
     * @param pdbID The PDB ID to search for.
     * @return      A protein that references the given PDB IDl
     */
    @Override
    public ProteinCurrent searchByPDB(String pdbID) {
        DBRef dbRef = searchDbRefByPDB(pdbID);
        if (dbRef == null) {
            return null;
        }

        return searchByID(dbRef.getProtein_acc());
    }

    @Override
    public List<ProteinCurrent> searchProteinsByPeptide(Peptide peptide) {
        Session session = sessionFactory.openSession();

        List<ProteinCurrent> proteins = null; // TODO

        session.beginTransaction().commit();
        session.close();
        return proteins;
    }
}
