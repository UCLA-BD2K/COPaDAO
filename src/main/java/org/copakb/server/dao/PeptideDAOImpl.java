package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

/**
 * PeptideDAO implementation.
 * Created by vincekyi on 4/16/15.
 */
@SuppressWarnings("unchecked")
public class PeptideDAOImpl implements PeptideDAO {

    private SessionFactory sessionFactory;

    /**
     * Initializes the sessionFactory to run database operations
     *
     * @param sessionFactory Session factory to use.
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int addPeptide(Peptide p) {
        Session session = sessionFactory.openSession();
        int result = (int) session.save(p);
        session.close();

        return result;
    }

    @Override
    public int addSpectrum(Spectrum s) {
        Spectrum existingSpectrum = searchSpectrum(s.getPtm_sequence(), s.getModule().getMod_id(), s.getCharge_state());
        if (existingSpectrum != null) {
            return existingSpectrum.getSpectrum_id();
        }

        Peptide peptide = searchBySequence(s.getPeptide().getPeptide_sequence());
        if (peptide == null) {
            int peptide_id = addPeptide(s.getPeptide());
            s.getPeptide().setPeptide_id(peptide_id);
        } else {
            s.setPeptide(peptide);
        }

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        int result = (int) session.save(s);
        tx.commit();
        session.close();

        return result;
    }

    @Override
    public String getSpectrum(int spec_id) {
        // TODO Decide location for spectra files
        String fileName = "target/" + spec_id + ".txt";

        String content = "";
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while ((line = bufferedReader.readLine()) != null) {
                content += line;
            }

            bufferedReader.close();
        } catch (Exception ex) {
            System.out.println("Unable to open spectrum file '" + spec_id + "'");
            ex.printStackTrace();
        }

        return content;
    }

    @Override
    public Spectrum getInitializedSpectrum(int spec_id) {
        Session session = sessionFactory.openSession();

        Spectrum spectrum = (Spectrum) session.get(Spectrum.class, spec_id);
        if (spectrum != null) {
            Hibernate.initialize(spectrum.getModule());
            Hibernate.initialize(spectrum.getPtm());
            Hibernate.initialize(spectrum.getPeptide());
            Hibernate.initialize(spectrum.getSpectrumProtein());
        }
        session.close();

        return spectrum;
    }

    @Override
    public Spectrum searchSpectrum(String ptm_seq, int mod_id, int charge) {
        Session session = sessionFactory.openSession();

        LibraryModule mod = new LibraryModule();
        mod.setMod_id(mod_id);
        Spectrum spectrum = (Spectrum) session
                .createCriteria(Spectrum.class)
                .add(Restrictions.and(
                        Restrictions.eq("ptm_sequence", ptm_seq),
                        Restrictions.and(Restrictions.eq("module", mod),
                                Restrictions.eq("charge_state", charge))))
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        return spectrum;
    }

    @Override
    public List<Spectrum> searchSpectrumBySequence(String ptm_seq) {
        Session session = sessionFactory.openSession();

        List<Spectrum> results = session
                .createCriteria(Spectrum.class)
                .add(Restrictions.eq("ptm_sequence", ptm_seq))
                .list();
        session.close();

        return results;
    }

    @Override
    public List<Spectrum> searchSpectrumBySequenceAndCharge(String ptm_seq, int charge) {
        Session session = sessionFactory.openSession();

        List<Spectrum> results = session
                .createCriteria(Spectrum.class)
                .add(Restrictions.eq("ptm_sequence", ptm_seq))
                .add(Restrictions.eq("charge_state", charge))
                .list();
        session.close();

        return results;
    }

    @Override
    public Spectrum searchSpectrumByAll(String ptm_seq, int mod_id, int charge, double xcorr, String rawfileid) {
        Session session = sessionFactory.openSession();

        Spectrum result = (Spectrum) session
                .createCriteria(Spectrum.class)
                .add(Restrictions.eq("ptm_sequence", ptm_seq))
                .add(Restrictions.eq("charge_state", charge))
                .add(Restrictions.eq("xcorr", xcorr))
                .add(Restrictions.eq("rawfileid", rawfileid))
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        return result;
    }

    @Override
    public void updateSpectrumSpecies(int spec_id, Spectrum spectrum) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Spectrum newSpectrum = (Spectrum) session.get(Spectrum.class, spec_id);
        // Update spectrum values
        newSpectrum.setSpecies_unique(spectrum.isSpecies_unique());
        session.update(newSpectrum);
        tx.commit();
        session.close();
    }

    @Override
    public void updateSpectrumFeature(int spec_id, Spectrum spectrum) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Spectrum newSpectrum = (Spectrum) session.get(Spectrum.class, spec_id);
        // Update spectrum values
        newSpectrum.setFeature_peptide(spectrum.isFeature_peptide());
        session.update(newSpectrum);
        tx.commit();
        session.close();
    }

    @Override
    public List<Peptide> list() {
        Session session = sessionFactory.openSession();

        List<Peptide> peptideList = session
                .createCriteria(Peptide.class)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .list();
        session.close();

        return peptideList;
    }

    @Override
    public List<Peptide> limitedList(int start, int length) {
        Session session = sessionFactory.openSession();

        List<Peptide> peptideList = session
                .createCriteria(Peptide.class)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .setFirstResult(start)
                .setMaxResults(length).list();
        session.close();

        return peptideList;
    }

    @Override
    public Peptide searchById(int peptide_id) {
        Session session = sessionFactory.openSession();

        Peptide peptide = (Peptide) session.get(Peptide.class, peptide_id);
        session.close();

        return peptide;
    }

    @Override
    public Peptide getInitializedPeptide(int peptide_id) {
        Session session = sessionFactory.openSession();

        Peptide peptide = (Peptide) session.get(Peptide.class, peptide_id);
        if (peptide != null) {
            Hibernate.initialize(peptide.getSpectra());
        }
        session.close();

        return peptide;
    }

    @Override
    public List<Peptide> searchPeptidesByProtein(ProteinCurrent protein) {
        Session session = sessionFactory.openSession();

        List<Peptide> results = session.createCriteria(Peptide.class, "peptide")
                .createAlias("peptide.spectra", "spectra")
                .createAlias("spectra.spectrumProtein", "sp")
                .add(Restrictions.eq("sp.protein", protein))
                .list();
        session.close();

        return results;
    }

    @Override
    public Peptide searchBySequence(String peptide_sequence) {
        Session session = sessionFactory.openSession();

        Peptide result = (Peptide) session
                .createCriteria(Peptide.class)
                .add(Restrictions.eq("peptide_sequence", peptide_sequence))
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        return result;
    }

    @Override
    public Spectrum searchBySpecId(Integer id) {
        Session session = sessionFactory.openSession();

        Spectrum spectrum = (Spectrum) session.get(Spectrum.class, id);
        session.close();

        return spectrum;
    }

    @Override
    public List<Peptide> searchByPartialSequence(String sequence) {
        Session session = sessionFactory.openSession();
        List<Peptide> peptides = session
                .createCriteria(Peptide.class)
                .add(Restrictions.like("peptide_sequence", "%" + sequence + "%"))
                .list();
        session.close();

        return peptides;
    }

    @Override
    public int addSpecies(Species spec) {
        Species existingSpecies = searchSpecies(spec.getSpecies_name());
        if (existingSpecies != null)
            return existingSpecies.getSpecies_id();

        Session session = sessionFactory.openSession();
        int result = (int) session.save(spec);
        session.close();

        return result;
    }

    @Override
    public Species searchSpecies(String name) {
        Session session = sessionFactory.openSession();

        Species result = (Species) session.createCriteria(Species.class)
                .add(Restrictions.eq("species_name", name))
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        return result;
    }

    @Override
    public Species searchSpecies(int id) {
        Session session = sessionFactory.openSession();

        Species result = (Species) session.createCriteria(Species.class)
                .add(Restrictions.eq("species_id", id))
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        return result;
    }

    @Override
    public int addLibraryModule(LibraryModule libmod) {
        LibraryModule existingLibraryModule = searchLibraryModuleWithModule(libmod.getLib_mod());
        if (existingLibraryModule != null) {
            return existingLibraryModule.getMod_id();
        }

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        int result = (int) session.save(libmod);
        tx.commit();
        session.close();

        return result;
    }

    @Override
    public LibraryModule searchLibraryModuleWithId(int id) {
        Session session = sessionFactory.openSession();

        LibraryModule result = (LibraryModule) session.get(LibraryModule.class, id);
        session.close();

        return result;
    }

    @Override
    public LibraryModule searchLibraryModuleWithModule(String lib_mod) {
        Session session = sessionFactory.openSession();

        LibraryModule result = (LibraryModule) session
                .createCriteria(LibraryModule.class)
                .add(Restrictions.eq("lib_mod", lib_mod))
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        return result;
    }

    @Override
    public int addPtmType(PTM_type type) {
        PTM_type existingPtm_type = searchPtmType(type.getPtm_type());
        if (existingPtm_type != null) {
            return existingPtm_type.getPtm_type();
        }

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        int result = (int) session.save(type);
        tx.commit();
        session.close();

        return result;
    }

    @Override
    public PTM_type searchPtmType(int id) {
        Session session = sessionFactory.openSession();

        PTM_type result = (PTM_type) session.createCriteria(PTM_type.class)
                .add(Restrictions.eq("ptm_type", id))
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        return result;
    }

    @Override
    public int getLocation(Peptide peptide, ProteinCurrent protein) {
        Session session = sessionFactory.openSession();

        SpectrumProtein result = (SpectrumProtein) session
                .createCriteria(SpectrumProtein.class)
                .add(Restrictions.and(
                        Restrictions.eq("peptide", peptide),
                        Restrictions.eq("protein", protein)))
                .setMaxResults(1)
                .uniqueResult();
        session.close();

        if (result == null) {
            return -1;
        }

        return result.getLocation();
    }
}
