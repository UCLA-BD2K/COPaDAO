package org.copakb.server.dao;

import org.copakb.server.dao.model.Peptide;
import org.copakb.server.dao.model.ProteinCurrent;
import org.copakb.server.dao.model.Spectrum;
import org.junit.Test;

import java.util.List;

/**
 * PeptideDAO tests
 * Created by Alan on 7/1/2015.
 */
public class PeptideDAOTest {
    private final PeptideDAO peptideDAO = DAOObject.getInstance().getPeptideDAO();

    @Test
    public void testAddPeptide() throws Exception {
        // TODO
    }

    @Test
    public void testList() throws Exception {
        assert !peptideDAO.list().isEmpty();
    }

    @Test
    public void testLimitedList() throws Exception {
        assert peptideDAO.limitedList(-1, 0).isEmpty();
        assert peptideDAO.limitedList(-1, -1).isEmpty();
        List<Peptide> peptides;
        peptides = peptideDAO.limitedList(0, 0);
        assert peptides != null;
        assert peptides.size() > 1;
        peptides = peptideDAO.limitedList(1, 0);
        assert peptides != null;
        assert peptides.size() > 1;
        peptides = peptideDAO.limitedList(1, 5);
        assert peptides != null;
        assert peptides.size() == 5;
    }

    @Test
    public void testGetLocation() throws Exception {
        Peptide peptide = peptideDAO.searchById(13460);
        assert peptide != null;
        ProteinCurrent protein;
        protein = DAOObject.getInstance().getProteinDAO().searchByID("P49720");
        assert protein != null;
        assert peptideDAO.getLocation(peptide, protein) == -1;
        protein = DAOObject.getInstance().getProteinDAO().searchByID("P21953");
        assert protein != null;
        assert peptideDAO.getLocation(peptide, protein) == 245;
    }

    @Test
    public void testSearchById() throws Exception {
        assert peptideDAO.searchById(-1) == null;
        assert peptideDAO.searchById(1) != null;
    }

    @Test
    public void testSearchBySequence() throws Exception {
        assert peptideDAO.searchBySequence("") == null;
        assert peptideDAO.searchBySequence("COPAKB") == null;
        assert peptideDAO.searchBySequence("DAVSGMGVIVHIIEK") != null;
    }

    @Test
    public void testSearchBySpecId() throws Exception {
        assert peptideDAO.searchBySpecId(-1) == null;
        assert peptideDAO.searchBySpecId(9200) != null;
    }

    @Test
    public void testSearchByPartialSequence() throws Exception {
        assert peptideDAO.searchByPartialSequence(null).isEmpty();
        assert peptideDAO.searchByPartialSequence("XXXXXXXX").isEmpty();
        assert !peptideDAO.searchByPartialSequence("AAA").isEmpty();
    }

    @Test
    public void testSearchSpectrum() throws Exception {
        assert peptideDAO.searchBySpecId(-1) == null;
        assert peptideDAO.searchBySpecId(9200) != null;
    }

    @Test
    public void testAddSpectrum() throws Exception {
        // TODO
    }

    @Test
    public void testGetSpectrum() throws Exception {
        // TODO
    }

    @Test
    public void testUpdateSpectrumSpecies() throws Exception {
        int id = 9200;
        Spectrum spectrum = peptideDAO.searchBySpecId(id);
        assert spectrum != null;
        spectrum.setSpecies_unique(!spectrum.isSpecies_unique());
        spectrum.setFeature_peptide(!spectrum.isFeature_peptide());
        peptideDAO.updateSpectrumSpecies(id, spectrum);

        Spectrum updatedSpectrum = peptideDAO.searchBySpecId(id);
        assert updatedSpectrum != null;
        assert updatedSpectrum.isSpecies_unique() == spectrum.isSpecies_unique();
    }

    @Test
    public void testUpdateSpectrumFeature() throws Exception {
        int id = 9200;
        Spectrum spectrum = peptideDAO.searchBySpecId(id);
        assert spectrum != null;
        spectrum.setFeature_peptide(!spectrum.isFeature_peptide());
        peptideDAO.updateSpectrumFeature(id, spectrum);

        Spectrum updatedSpectrum = peptideDAO.searchBySpecId(id);
        assert updatedSpectrum != null;
        assert updatedSpectrum.isFeature_peptide() == spectrum.isFeature_peptide();
    }

    @Test
    public void testAddSpecies() throws Exception {
        // TODO
    }

    @Test
    public void testSearchSpecies() throws Exception {
        assert peptideDAO.searchSpecies("elves") == null;
        assert peptideDAO.searchSpecies("Homo sapiens") != null;
    }

    @Test
    public void testAddLibraryModule() throws Exception {
        // TODO
    }

    @Test
    public void testSearchLibraryModuleWithId() throws Exception {
        assert peptideDAO.searchLibraryModuleWithId(-1) == null;
        assert peptideDAO.searchLibraryModuleWithId(8) != null;
    }

    @Test
    public void testSearchLibraryModuleWithModule() throws Exception {
        assert peptideDAO.searchLibraryModuleWithModule(null) == null;
        assert peptideDAO.searchLibraryModuleWithModule("") == null;
        assert peptideDAO.searchLibraryModuleWithModule("human_heart_proteasome") != null;
    }

    @Test
    public void testAddPtmType() throws Exception {
        // TODO
    }

    @Test
    public void testSearchPtmType() throws Exception {
        assert peptideDAO.searchPtmType(-1) == null;
        assert peptideDAO.searchPtmType(0) != null;
    }
}
