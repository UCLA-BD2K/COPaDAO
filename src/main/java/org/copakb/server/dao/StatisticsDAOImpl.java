package org.copakb.server.dao;

import org.copakb.server.dao.model.LibraryModule;
import org.copakb.server.dao.model.ModuleStatistics;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.util.Date;
import java.util.Calendar;
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
        if (current.get(current.DAY_OF_WEEK) == 1 && current.get(current.DAY_OF_MONTH) != last.get(last.DAY_OF_MONTH)) {
            // Iterate through library modules
            for (LibraryModule module : DAOObject.getInstance().getPeptideDAO().getLibraryModules()) {
                // Count number of proteins in each module
                Transaction tx1 = session.beginTransaction();
                String s = "SELECT COUNT(DISTINCT (protein_acc)) FROM COPADB.spectrum_protein WHERE mod_id = " + module.getMod_id() + ";";
                SQLQuery query = session.createSQLQuery(s);
                BigInteger big = (BigInteger) query.list().get(0);
                int nProteins = big.intValue();
                tx1.commit();

                // Count number of peptides in each module
                Transaction tx2 = session.beginTransaction();
                s = "SELECT COUNT(DISTINCT (peptide_id)) FROM COPADB.spectrum WHERE mod_id = " + module.getMod_id() + ";";
                query = session.createSQLQuery(s);
                big = (BigInteger) query.list().get(0);
                int nPeptides = big.intValue();
                tx2.commit();

                // Count number of spectra in each module
                Transaction tx3 = session.beginTransaction();
                s = "SELECT COUNT(DISTINCT (spectrum_id)) FROM COPADB.spectrum WHERE mod_id = " + module.getMod_id() + ";";
                query = session.createSQLQuery(s);
                big = (BigInteger) query.list().get(0);
                int nSpectrum = big.intValue();
                tx3.commit();

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
