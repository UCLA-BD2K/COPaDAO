package org.copakb.server.dao;

import org.copakb.server.dao.model.ProteinCurrent;
import org.copakb.server.dao.model.ProteinHistory;
import org.copakb.server.dao.model.SpectrumProteinHistory;
import org.copakb.server.dao.model.Version;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Alan on 6/29/2015.
 */
public class ProteinDAOTest {

/*    @Test
    public void testSearchByLikeID() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().searchByLikeID("A4F"));
    }

    @Test
    public void testSearchByPartialId() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().searchByPartialID("5E"));
    }

    @Test
    public void testSearchByPartialSequence() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().searchByPartialSequence("SVS"));
    }

    @Test
    public void testSearchByPDB() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().searchByPDB("2B05"));
    }

    @Test
    public void testGetProteinWithGOTerms() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().getProteinWithGoTerms("P41932"));
    }

    @Test
    public void testGetProteinWithSpectra() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().getProteinWithSpectra("P41932"));
    }*/

    /*@Test
    public void testSearchProteinsByPeptide() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().searchProteinsByPeptide(
                DAOObject.getInstance().getPeptideDAO().searchById(6401)));
    }*/

    /*@Test
    public void testCompareProteinCurrent() throws Exception {
        ProteinCurrent a = DAOObject.getInstance().getProteinDAO().searchByID("P54216");
        ProteinCurrent b = DAOObject.getInstance().getProteinDAO().searchByID("P54216");
        if(a != null && b != null) {
            b.setProtein_name(a.getProtein_name().substring(0, a.getProtein_name().length() - 3));
            System.out.println(DAOObject.getInstance().getProteinDAO().compareProteinCurrent(a, b));
        }
    }

    @Test
    public void testSearchProteinHistory() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().searchProteinHistory("P1"));
    }*/

    @Test
    public void testAddProteinToHistory() throws Exception {
        /*Version version = new Version();
        version.setVersion(DAOObject.getInstance().getProteinDAO().searchLatestVersion().getVersion() - 1); // set it to previous versions
        version.setDescription("Update"); // todo: change the description values
        version.setDate(new Date());

        ProteinHistory proteinHistory = new ProteinHistory();
        proteinHistory.setProtein_acc("G5EC63");
        proteinHistory.setProtein_name("protein name");
        proteinHistory.setChromosome("XX");
        proteinHistory.setMolecular_weight(1234.1234);
        proteinHistory.setSequence("OLDSEQUENCE");
        proteinHistory.setVersion(version);

        DAOObject.getInstance().getProteinDAO().addProteinHistory(proteinHistory);*/
        return;
    }

    @Test
    public void testDeleteProteinCurrent() throws Exception {
        /*ProteinCurrent p = new ProteinCurrent();
        p.setProtein_acc("FP123");
        p.setSequence("NEWSEQUENCEHERE");
        p.setProtein_name("NEWNAMEHERE");
        p.setMolecular_weight(1234.1234);
        p.setSpecies(DAOObject.getInstance().getProteinDAO().searchSpecies("Human"));
        p.setChromosome("X");

        DAOObject.getInstance().getProteinDAO().addProteinCurrent(p);*/

        //DAOObject.getInstance().getProteinDAO().deleteProteinCurrent("P99999");
        return;
    }

    @Test
    public void testAddVersion() throws Exception {
        Version version = new Version();
        version.setDate(new Date());
        version.setVersion(1);
        version.setDescription("First");

        DAOObject.getInstance().getProteinDAO().addVersion(version);
    }

    @Test
    public void testAddSpectrumProteinHistory() throws Exception {
        Version version = new Version();
        version.setVersion(DAOObject.getInstance().getProteinDAO().searchLatestVersion().getVersion() - 1); // set it to previous versions
        version.setDescription("Update"); // todo: change the description values
        version.setDate(new Date());

        SpectrumProteinHistory spectrumProteinHistory = new SpectrumProteinHistory();
        spectrumProteinHistory.setSpectrumProtein_id(1);
        spectrumProteinHistory.setVersion(version);
        spectrumProteinHistory.setProtein_acc("FP123");
        spectrumProteinHistory.setFeature_peptide(true);
        spectrumProteinHistory.setLibraryModule(1);
        spectrumProteinHistory.setLocation(123);
        spectrumProteinHistory.setPrevAA('A');
        spectrumProteinHistory.setNextAA('B');

        DAOObject.getInstance().getProteinDAO().addSpectrumProteinHistory(spectrumProteinHistory);
    }

    @Test
    public void testSearchSpectrumProteins() throws Exception {
        //System.out.println(DAOObject.getInstance().getProteinDAO().searchSpectrumProteins().size());
        return;
    }

}
