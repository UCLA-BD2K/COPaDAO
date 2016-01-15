package org.copakb.server.dao;

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
        Session session = sessionFactory.openSession();

        ModuleStatistics moduleStatistics = (ModuleStatistics) session.get(ModuleStatistics.class, mod_id);
        session.close();

        // Check if update is necessary
        if (moduleStatistics != null) {
            Calendar last = Calendar.getInstance();
            last.setTime(moduleStatistics.getLast_modified());

            Calendar current = Calendar.getInstance();
            if (!(current.get(current.DAY_OF_WEEK) == Calendar.SUNDAY &&
                    current.get(current.DAY_OF_MONTH) != last.get(last.DAY_OF_MONTH))) {
                // No update necessary
                return moduleStatistics;
            }
        }

        // Update and return module statistics
        return update(mod_id);
    }

    @Override
    public List<ModuleStatistics> list() {
        Session session = sessionFactory.openSession();
        List<ModuleStatistics> list = session.createCriteria(ModuleStatistics.class).list();

        session.close();
        return list;
    }

    private ModuleStatistics update(int mod_id) {
        Session session = sessionFactory.openSession();
        int numProteins = ((Number) session
                .createCriteria(SpectrumProtein.class, "spectrum")
                .add(Restrictions.eq("libraryModule.mod_id", mod_id))
                .setProjection(Projections.countDistinct("protein.protein_acc"))
                .uniqueResult())
                .intValue();
        int numPeptides = ((Number) session
                .createCriteria(Spectrum.class)
                .add(Restrictions.eq("module.mod_id", mod_id))
                .setProjection(Projections.countDistinct("peptide.peptide_id"))
                .uniqueResult())
                .intValue();
        int numSpectra = ((Number) session
                .createCriteria(Spectrum.class)
                .add(Restrictions.eq("module.mod_id", mod_id))
                .setProjection(Projections.countDistinct("spectrum_id"))
                .uniqueResult())
                .intValue();

        Transaction tx = session.beginTransaction();
        ModuleStatistics statistics = (ModuleStatistics) session.get(ModuleStatistics.class, mod_id);
        if (statistics == null) {
            statistics = new ModuleStatistics();
        }
        statistics.setNum_of_proteins(numProteins);
        statistics.setNum_of_peptides(numPeptides);
        statistics.setNum_of_spectra(numSpectra);
        statistics.setLast_modified(new Date());
        session.saveOrUpdate(statistics);
        tx.commit();

        session.close();
        return statistics;
    }
}
