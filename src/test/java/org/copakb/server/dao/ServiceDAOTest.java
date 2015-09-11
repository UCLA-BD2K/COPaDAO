package org.copakb.server.dao;

import org.copakb.server.dao.model.Peptide;
import org.copakb.server.dao.model.ProteinCurrent;
import org.copakb.server.dao.model.service.ReferencePeptideBundle;
import org.copakb.server.dao.model.service.ReferenceProteinBundle;
import org.junit.Test;

/**
 * ServiceDAO tests.
 * Created by Alan on 8/3/2015.
 */
public class ServiceDAOTest {
    private final ServiceDAO serviceDAO = DAOObject.getInstance().getServiceDAO();

    @Test
    public void testGetReferenceProteinBundle() throws Exception {
        ProteinCurrent protein = DAOObject.getInstance().getProteinDAO().getInitializedProtein("A5A8P9");
        long start_time = System.currentTimeMillis();
        ReferenceProteinBundle bundle = serviceDAO.getReferenceProteinBundle(protein);
        long end_time = System.currentTimeMillis();
        System.out.println("Time Elapsed: " + (end_time - start_time));

        assert bundle.getDiseases().size() == 1;
        assert bundle.getSpectrumProteins().get(0).getNextAA() == 'N';
        assert bundle.getSpectrumProteins().get(0).getPeptide().getPeptide_id() == 50511;
        assert bundle.getGoTerms().size() == 1;
    }

    @Test
    public void testGetReferencePeptideBundle() throws Exception {
        Peptide peptide = DAOObject.getInstance().getPeptideDAO().searchById(1);
        long start_time = System.currentTimeMillis();
        ReferencePeptideBundle bundle = serviceDAO.getReferencePeptideBundle(peptide);
        long end_time = System.currentTimeMillis();
        System.out.println("Time Elapsed: " + (end_time - start_time));

        assert bundle != null;
    }

    @Test
    public void testGetLibModNames() throws Exception {
        System.out.println(serviceDAO.getLibModNames());
    }
}
