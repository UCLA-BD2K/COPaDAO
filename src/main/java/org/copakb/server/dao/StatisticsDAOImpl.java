package org.copakb.server.dao;

import org.copakb.server.dao.model.LibraryModule;
import org.copakb.server.dao.model.ModuleStatistics;
import org.copakb.server.dao.model.Spectrum;
import org.copakb.server.dao.model.SpectrumProtein;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * StatisticsDAO implementation.
 */
@SuppressWarnings("unchecked")
public class StatisticsDAOImpl extends DAOImpl implements StatisticsDAO {
    @Override
    public ModuleStatistics getModuleStatistics(int mod_id) throws HibernateException {
        update();
        Session session = sessionFactory.openSession();

        ModuleStatistics moduleStatistics = (ModuleStatistics) session.get(ModuleStatistics.class, mod_id);
        session.close();

        return moduleStatistics;
    }

    @Override
    public List<ModuleStatistics> list() {
        Session session = sessionFactory.openSession();
        List<ModuleStatistics> list = session.createCriteria(ModuleStatistics.class).list();

        session.close();
        return list;
    }

    private void update() {
        Session session = sessionFactory.openSession();

        ModuleStatistics moduleStatistics = (ModuleStatistics) session.get(ModuleStatistics.class, 1);

        Calendar last = Calendar.getInstance();
        last.setTime(moduleStatistics.getLast_modified());

        Calendar current = Calendar.getInstance();
        // Check if update is necessary
        if (current.get(current.DAY_OF_WEEK) == 1 && current.get(current.DAY_OF_MONTH) != last.get(last.DAY_OF_MONTH)) {
            // Iterate through library modules
            for (LibraryModule module : DAOObject.getInstance().getPeptideDAO().getLibraryModules()) {
                int numProteins = ((Number) session
                        .createCriteria(SpectrumProtein.class)
                        .add(Restrictions.eq("mod_id", module.getMod_id()))
                        .setProjection(Projections.countDistinct("protein_acc")))
                        .intValue();
                int numPeptides = ((Number) session
                        .createCriteria(Spectrum.class)
                        .add(Restrictions.eq("mod_id", module.getMod_id()))
                        .setProjection(Projections.countDistinct("peptide_id")))
                        .intValue();
                int numSpectra = ((Number) session
                        .createCriteria(Spectrum.class)
                        .add(Restrictions.eq("mod_id", module.getMod_id()))
                        .setProjection(Projections.countDistinct("spectrum_id")))
                        .intValue();

                Transaction tx = session.beginTransaction();
                ModuleStatistics ms = (ModuleStatistics) session.get(ModuleStatistics.class, module.getMod_id());
                ms.setNum_of_proteins(nProteins);
                ms.setNum_of_peptides(nPeptides);
                ms.setNum_of_spectra(nSpectrum);
                ms.setLast_modified(new Date());
                session.merge(ms);
                tx.commit();
            }
        }

        session.close();
    }
}
