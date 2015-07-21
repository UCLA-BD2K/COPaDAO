package org.copakb.server.dao;

import org.copakb.server.dao.model.Disease;
import org.copakb.server.dao.model.Gene;
import org.junit.Test;

import java.util.List;

/**
 * DiseaseDAO tests
 * Created by Alan on 7/2/2015.
 */
public class DiseaseDAOTest {
    private final DiseaseDAO diseaseDAO = DAOObject.getInstance().getDiseaseDAO();

    @Test
    public void testAddDisease() throws Exception {
        // TODO
    }

    @Test
    public void testSearchDisease() throws Exception {
        assert diseaseDAO.searchDisease(-1) == null;
        assert diseaseDAO.searchDisease(153480) != null;
    }

    @Test
    public void testSearchDiseasesByGene() throws Exception {
        assert diseaseDAO.searchDiseasesByGene(null).isEmpty();
        assert diseaseDAO.searchDiseasesByGene("XXXX").isEmpty();
        assert !diseaseDAO.searchDiseasesByGene("PTEN").isEmpty();
    }

    @Test
    public void testAddDiseaseGene() throws Exception {
        // TODO
    }

    @Test
    public void testSearchDiseaseGene() throws Exception {
        assert diseaseDAO.searchDiseaseGene(null, null) == null;
        Disease disease = diseaseDAO.searchDisease(153480);
        assert disease != null;
        Gene gene = DAOObject.getInstance().getProteinDAO().searchGeneByName("PTEN");
        assert gene != null;
        assert diseaseDAO.searchDiseaseGene(disease, null) == null;
        assert diseaseDAO.searchDiseaseGene(null, gene) == null;
        assert diseaseDAO.searchDiseaseGene(disease, gene) != null;
    }

    @Test
    public void testLimitedGeneList() throws Exception {
        assert diseaseDAO.limitedGeneList(-1, 0).isEmpty();
        List<Gene> genes = diseaseDAO.limitedGeneList(1, 0);
        assert genes != null;
        assert genes.size() > 1;
        genes = diseaseDAO.limitedGeneList(1, 1);
        assert genes != null;
        assert genes.size() == 1;
        genes = diseaseDAO.limitedGeneList(1, 5);
        assert genes != null;
        assert genes.size() == 5;
    }
}
