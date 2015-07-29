package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.*;

/**
 * ProteinDAO test class.
 * Created by Alan on 6/29/2015.
 */
public class ProteinDAOTest {
    private final ProteinDAO proteinDAO = DAOObject.getInstance().getProteinDAO();
    private final static String UNIPROT_ID = "P31946";

    @Test
    public void testAddProteinCurrent() throws Exception {
        // TODO
    }

    @Test
    public void testUpdateProteinCurrent() throws Exception {
        // TODO
    }

    @Test
    public void testDeleteProteinCurrent() throws Exception {
        // TODO
//        ProteinCurrent p = new ProteinCurrent();
//        p.setProtein_acc("FP1234");
//        p.setSequence("NEWSEQUENCEHERE");
//        p.setProtein_name("NEWNAMEHERE");
//        p.setMolecular_weight(1234.1234);
//        p.setSpecies(DAOObject.getInstance().getProteinDAO().searchSpecies("Human"));
//        p.setChromosome("X");
//        DBRef dbRef = new DBRef();
//        dbRef.setProtein_acc(p);
//        p.setDbRef(dbRef);
//
//        proteinDAO.addProteinCurrent(p);
//        assert proteinDAO.searchByID(p.getProtein_acc()) != null;
//        proteinDAO.deleteProteinCurrent(p.getProtein_acc());
//        assert proteinDAO.searchByID(p.getProtein_acc()) == null;
    }

    @Test
    public void testCompareProteinCurrent() throws Exception {
        ProteinCurrent a = proteinDAO.searchByID("P54216");
        ProteinCurrent b = proteinDAO.searchByID("P54216");
        assert a != null;
        assert b != null;
        b.setProtein_name(a.getProtein_name().substring(0, a.getProtein_name().length() - 3));
        assert !proteinDAO.compareProteinCurrent(a, b);
    }

    @Test
    public void testAddProteinHistory() throws Exception {
        ProteinHistory proteinHistory = new ProteinHistory();
        proteinHistory.setProtein_acc("G5EC63");
        proteinHistory.setProtein_name("protein name");
        proteinHistory.setChromosome("XX");
        proteinHistory.setMolecular_weight(1234.1234);
        proteinHistory.setSequence("OLDSEQUENCE");

        Version version = new Version();
        int v = proteinDAO.searchLatestVersion().getVersion() - 1; // set it to previous versions
        version.setVersion(v);
        version.setDescription("Update"); // todo: change the description values
        version.setDate(new Date());
        proteinHistory.setVersion(version);

        proteinDAO.addProteinHistory(proteinHistory);
        ProteinHistory ph = proteinDAO.searchProteinHistory("G5EC63");
        assert ph != null;
        assert ph.getProtein_acc().equals("G5EC63");
        assert ph.getVersion().getVersion() == v;
    }

    @Test
    public void testSearchProteinHistory() throws Exception {
        assert proteinDAO.searchProteinHistory("P1") == null;
        assert proteinDAO.searchProteinHistory("G5EC63") != null;
    }

    @Test
    public void testAddSpectrumProteinHistory() throws Exception {
        Version version = new Version();
        version.setVersion(proteinDAO.searchLatestVersion().getVersion() - 1); // set it to previous versions
        version.setDescription("Update"); // todo: change the description values
        version.setDate(new Date());

        SpectrumProteinHistory spectrumProteinHistory = new SpectrumProteinHistory();
        spectrumProteinHistory.setSpectrumProtein_id(9122);
        spectrumProteinHistory.setVersion(version);
        spectrumProteinHistory.setProtein_acc("FP123");
        spectrumProteinHistory.setFeature_peptide(true);
        spectrumProteinHistory.setLibraryModule(1);
        spectrumProteinHistory.setLocation(123);
        spectrumProteinHistory.setPrevAA('A');
        spectrumProteinHistory.setNextAA('B');

        proteinDAO.addSpectrumProteinHistory(spectrumProteinHistory);

        assert proteinDAO.searchSpectrumProteinHistory(
                spectrumProteinHistory.getProtein_acc(), spectrumProteinHistory.getSpectrum_id()) != null;
    }

    @Test
    public void testSearchSpectrumProteinHistory() throws Exception {
        assert proteinDAO.searchSpectrumProteinHistory("", -1) == null;
        assert proteinDAO.searchSpectrumProteinHistory("FP123", -1) == null;
        assert proteinDAO.searchSpectrumProteinHistory("FP124", 1) == null;
        assert proteinDAO.searchSpectrumProteinHistory("FP123", 1) != null;
    }

    @Test
    public void testDeleteSpectrumProtein() throws Exception {
        // TODO
    }

    @Test
    public void testAddVersion() throws Exception {
        Version version = new Version();
        version.setDate(new Date());
        version.setVersion(1);
        version.setDescription("First");

        proteinDAO.addVersion(version);
        assert proteinDAO.searchVersion(1) != null;
    }

    @Test
    public void testSearchVersion() throws Exception {
        assert proteinDAO.searchVersion(1) != null;
    }

    @Test
    public void testSearchLatestVersion() throws Exception {
        assert proteinDAO.searchLatestVersion() != null;
    }

    @Test
    public void testSearchByID() throws Exception {
        assert proteinDAO.searchByID("") == null;

        ProteinCurrent protein = proteinDAO.searchByID(UNIPROT_ID);
        assert protein != null;
        assert protein.getProtein_acc().equals(UNIPROT_ID);
    }

    @Test
    public void testGetInitializedProtein() throws Exception {
        assert proteinDAO.searchByID("") == null;

        ProteinCurrent protein = proteinDAO.getInitializedProtein(UNIPROT_ID);
        assert protein != null;
        assert protein.getProtein_acc().equals(UNIPROT_ID);
        assert protein.getDbRef().getProtein_acc().equals(UNIPROT_ID);
    }

    @Test
    public void testSearchByLikeID() throws Exception {
        assert proteinDAO.searchByLikeID("XXXXXXXX").isEmpty();
        assert proteinDAO.searchByLikeID(UNIPROT_ID + "XXXXX").isEmpty();

        String prefix = UNIPROT_ID.substring(0, 3);
        List<ProteinCurrent> proteins = proteinDAO.searchByLikeID(prefix);
        for (ProteinCurrent p : proteins) {
            assert p.getProtein_acc().startsWith(prefix);
        }
    }

    @Test
    public void testSearchByPartialID() throws Exception {
        assert proteinDAO.searchByPartialID("XXXXXXXX").isEmpty();
        assert !proteinDAO.searchByPartialID(UNIPROT_ID).isEmpty();

        String fragment = UNIPROT_ID.substring(2, 4);
        List<ProteinCurrent> proteins = proteinDAO.searchByPartialID(fragment);
        for (ProteinCurrent p : proteins) {
            assert p.getProtein_acc().contains(fragment);
        }
    }

    @Test
    public void testSearchByName() throws Exception {
        assert proteinDAO.searchByName("") == null;
        assert proteinDAO.searchByName("XXXXX") == null;

        String name = "14-3-3 protein beta/alpha";
        ProteinCurrent protein = proteinDAO.searchByName(name);
        assert protein.getProtein_name().equals(name);
    }

    @Test
    public void testSearchByPartialSequence() throws Exception {
        assert proteinDAO.searchByPartialSequence("XXXXX").isEmpty();
        assert !proteinDAO.searchByPartialSequence("").isEmpty();

        String sequence = "AAA";
        List<ProteinCurrent> proteins = proteinDAO.searchByPartialSequence(sequence);
        for (ProteinCurrent p : proteins) {
            assert p.getSequence().contains(sequence);
        }
    }

    @Test
    public void testList() throws Exception {
        assert !proteinDAO.list().isEmpty();
    }

    @Test
    public void testLimitedList() throws Exception {
        List<ProteinCurrent> proteins = proteinDAO.limitedList(0, 5);
        assert proteins != null;
        assert proteins.size() == 5;
    }

    @Test
    public void testSearchDbRefByID() throws Exception {
        assert proteinDAO.searchDbRefByID("") == null;
        assert proteinDAO.searchDbRefByID("XXXXXX") == null;

        DBRef dbRef = proteinDAO.searchDbRefByID(UNIPROT_ID);
        assert dbRef != null;
        assert dbRef.getProtein_acc().equals(UNIPROT_ID);
    }

    @Test
    public void testSearchByPDB() throws Exception {
        assert proteinDAO.searchByPDB("") != null;
        assert proteinDAO.searchByPDB("XXXXXXXXXXX") == null;

        ProteinCurrent protein = proteinDAO.searchByPDB("2BQ0");
        assert protein != null;
        assert protein.getProtein_acc().equals(UNIPROT_ID);
    }

    @Test
    public void testAddGene() throws Exception {
        // TODO
    }

    @Test
    public void testSearchByGeneName() throws Exception {
        assert proteinDAO.searchGeneByName("XXXXXXXXXXX") == null;

        String geneName = "YWHAB";
        Gene gene = proteinDAO.searchGeneByName(geneName);
        assert gene != null;
        assert gene.getGene_name().equals(geneName);
    }

    @Test
    public void testSearchByEnsg() throws Exception {
        assert proteinDAO.searchByEnsg("XXXXXXX") == null;

        String ensemblID = "ENSG00000166913";
        ProteinCurrent protein = proteinDAO.searchByEnsg(ensemblID);
        assert protein != null;
        assert protein.getProtein_acc().equals(UNIPROT_ID);
    }

    @Test
    public void testGetProteinWithGenes() throws Exception {
        assert proteinDAO.getProteinWithGenes("") == null;

        ProteinCurrent protein = proteinDAO.getProteinWithGenes(UNIPROT_ID);
        assert protein != null;
        assert protein.getProtein_acc().equals(UNIPROT_ID);
        assert protein.getGenes() != null;
        assert protein.getGenes().size() == 1;
    }

    @Test
    public void testAddGoTerms() throws Exception {
        // TODO
    }

    @Test
    public void testSearchByGOAccession() throws Exception {
        assert proteinDAO.searchByGOAccession(-1) == null;

        int go_acc = 51220;
        GoTerms goTerm = proteinDAO.searchByGOAccession(go_acc);
        assert goTerm != null;
        assert goTerm.getGO_accession() == go_acc;
    }

    @Test
    public void testGetProteinWithGoTerms() throws Exception {
        assert proteinDAO.getProteinWithGoTerms("") == null;

        ProteinCurrent protein = proteinDAO.getProteinWithGoTerms(UNIPROT_ID);
        assert protein != null;
        assert protein.getProtein_acc().equals(UNIPROT_ID);
        assert protein.getGoTerms() != null;
    }

    @Test
    public void testAddSpecies() throws Exception {
        // TODO
    }

    @Test
    public void testSearchSpecies() throws Exception {
        assert proteinDAO.searchSpecies("Minion") == null;

        Species species = proteinDAO.searchSpecies("Human");
        assert species != null;
        assert species.getSpecies_id() == 1;
    }

    @Test
    public void testAddSpectrumProtein() throws Exception {
        // TODO
    }

    @Test
    public void testGetProteinWithSpectra() throws Exception {
        assert proteinDAO.getProteinWithSpectra("") == null;

        ProteinCurrent protein = proteinDAO.getProteinWithSpectra(UNIPROT_ID);
        assert protein != null;
        assert protein.getProtein_acc().equals(UNIPROT_ID);
        Set<SpectrumProtein> spectra = protein.getSpectra();
        assert spectra != null;
        for (SpectrumProtein s : spectra) {
            assert s.getProtein().getProtein_acc().equals(UNIPROT_ID);
        }
    }

    @Test
    public void testSearchSpectrumProtein() throws Exception {
        Spectrum spectrum = DAOObject.getInstance().getPeptideDAO().searchBySpecId(9209);
        assert spectrum != null;
        ProteinCurrent protein = proteinDAO.searchByID("P49720");
        assert protein != null;

        assert proteinDAO.searchSpectrumProtein(spectrum, protein) != null;
    }

    @Test
    public void testSearchSpectrumProteins() throws Exception {
        assert proteinDAO.searchSpectrumProteins((ProteinCurrent) null).isEmpty();
        ProteinCurrent protein = proteinDAO.searchByID("P49720");
        assert protein != null;
        assert !proteinDAO.searchSpectrumProteins(protein).isEmpty();

        Spectrum spectrum = DAOObject.getInstance().getPeptideDAO().searchBySpecId(9209);
        assert spectrum != null;
        assert !proteinDAO.searchSpectrumProteins(spectrum).isEmpty();
    }

    @Test
    public void testSearchProteinsByPeptide() throws Exception {
        assert proteinDAO.searchProteinsByPeptide(null).isEmpty();
        Peptide peptide = DAOObject.getInstance().getPeptideDAO().searchById(13460);
        assert peptide != null;
        assert !proteinDAO.searchProteinsByPeptide(peptide).isEmpty();
    }

    @Test
    public void testAddHPAProtein() throws Exception {
        // TODO
    }

    @Test
    public void testSearchHPAByID() throws Exception {
        assert proteinDAO.searchHPAByID("") == null;

        String ensemblID = "ENSG00000000003";
        HPAProtein hpaProtein = proteinDAO.searchHPAByID(ensemblID);
        assert hpaProtein != null;
        assert hpaProtein.getEnsembl_id().equals(ensemblID);
    }

    @Test
    public void testAddAntibody() throws Exception {
        // TODO
    }

    @Test
    public void testSearchAntibodyByID() throws Exception {
        assert proteinDAO.searchAntibodyByID("") == null;

        String antibodyID = "HPA004109";
        Antibody antibody = proteinDAO.searchAntibodyByID(antibodyID);
        assert antibody != null;
        assert antibody.getAntibody_id().equals(antibodyID);
    }

    @Test
    public void testGetProteinWithPTMs() throws Exception {
        assert proteinDAO.getProteinWithPTMs("") == null;

        ProteinCurrent protein = proteinDAO.getProteinWithPTMs(UNIPROT_ID);
        assert protein != null;
        assert protein.getProtein_acc().equals(UNIPROT_ID);
        assert protein.getPTMs() != null;
    }

    @Test
    public void testSpeed() throws Exception {
        Date beg = new Date();
        //List<ProteinCurrent> list = proteinDAO.list();
        List<ProteinCurrent> proteins = proteinDAO.searchByPartialID("P9");
        Date end = new Date();
        System.out.println("Started: " + beg);
        System.out.println("Ended: " + end);
        System.out.println();
    }

    @Test
    public void test() throws Exception {
        long start_time = System.currentTimeMillis();

        //Peptide peptide = DAOObject.getInstance().getPeptideDAO().searchById(1);
        //DAOObject.getInstance().getProteinDAO().searchProteinsByPeptide(peptide);
        DAOObject.getInstance().getProteinDAO().list();

        long end_time = System.currentTimeMillis();
        System.out.println("Time Elapsed: " + (end_time - start_time));

        ProteinCurrent p = new ProteinCurrent();
        p.setProtein_acc("FP1234");
        p.setSequence("NEWSEQUENCEHERE");
        p.setProtein_name("NEWNAMEHERE");
        p.setMolecular_weight(1234.1234);
        p.setSpecies(DAOObject.getInstance().getProteinDAO().searchSpecies("Human"));
        p.setChromosome("X");
        DBRef dbRef = new DBRef();
        dbRef.setProtein_acc(p.getProtein_acc());
        p.setDbRef(dbRef);

        proteinDAO.addProteinCurrent(p);

        long end_time2 = System.currentTimeMillis();
        System.out.println("Time Elapsed: " + (end_time2 - end_time));
    }

    @Test
    public void testSpeciesAndFeature() throws Exception {
        // UPDATE SPECIES UNIQUE
        List<Peptide> peptides = DAOObject.getInstance().getPeptideDAO().list();
        for (Peptide peptide : peptides) {
            // arraylist of arraylist of peptides within that species
            // each contained list is relevant to a species, where index = species id
            System.out.println("Processing: " + peptide.getPeptide_id());
            List<Spectrum> pepSpectrums = DAOObject.getInstance().getPeptideDAO().searchSpectrumBySequence(peptide.getPeptide_sequence());
            if (pepSpectrums == null || pepSpectrums.size() == 0) {
                System.out.println("\tSkipping " + peptide.getPeptide_id());
                continue;
            }
            HashMap<Integer, ArrayList<Spectrum>> sortedPeptides = new HashMap<>();
            for (int i = 0; i <= 10; i++) { // todo: assuming 10 species, fix; could reuse for every peptide
                // initialize the species keys
                if (DAOObject.getInstance().getPeptideDAO().searchSpecies(i) != null) {
                    ArrayList<Spectrum> temp = new ArrayList<>();
                    sortedPeptides.put(i, temp);
                }
            }

            for (Spectrum spectrum : pepSpectrums) { // sort
                int species_id = spectrum.getModule().getSpecies().getSpecies_id();
                sortedPeptides.get(species_id).add(spectrum);
            }

            for (ArrayList<Spectrum> tempArr : sortedPeptides.values()) {
                if (tempArr.size() == 1) {
                    Spectrum spec = tempArr.get(0);
                    spec.setSpecies_unique(true);
                    DAOObject.getInstance().getPeptideDAO().updateSpectrumSpecies(spec.getSpectrum_id(), spec);
                    System.out.println("Species unique: " + spec.getSpectrum_id());
                }
            }

        }


        // UPDATE FEATURE PEPTIDE

        List<Peptide> peptides2 = DAOObject.getInstance().getPeptideDAO().list();
        for (Peptide peptide : peptides2) {
            System.out.println("Processing: " + peptide.getPeptide_id());
            List<Spectrum> pepSpectrums = DAOObject.getInstance().getPeptideDAO().searchSpectrumBySequence(peptide.getPeptide_sequence());
            if (pepSpectrums == null || pepSpectrums.size() == 0) {
                System.out.println("\tSkipping " + peptide.getPeptide_id());
                continue;
            }


            // filter through charges for each
            for (int i = 0; i < 5; i++) { // todo: random range of 0-5 charge
                List<Spectrum> pepChargeSpectrums = DAOObject.getInstance().getPeptideDAO().searchSpectrumBySequenceAndCharge(peptide.getPeptide_sequence(), i);
                // find all the spectrum for a certain sequence and charge
                if (pepChargeSpectrums != null) { // list of all spectra with certain sequence and charge
                    // if only one of sequence + charge combo, then it is unique to one module (key id for spectrum is seq + charge + mod)
                    if (pepChargeSpectrums.size() == 1) {
                        Spectrum spec = pepChargeSpectrums.get(0);
                        spec.setFeature_peptide(true);
                        DAOObject.getInstance().getPeptideDAO().updateSpectrumFeature(spec.getSpectrum_id(), spec);
                        System.out.println("Feature peptide: " + spec.getSpectrum_id());
                    }
                }
            }
        }

    }

    @Test
    public void testSeparateGeneEnsembl() throws Exception {

    }

    @Test
    public void testFixSpeciesIds() throws Exception {
        List<ProteinCurrent> proteinCurrents = proteinDAO.searchBySpecies(5);
        Species celegans = proteinDAO.searchSpecies("Caenorhabditis elegans");
        for (ProteinCurrent p : proteinCurrents) {
            p.setSpecies(celegans);
            proteinDAO.updateProteinCurrent(p.getProtein_acc(), p);
        }

        proteinCurrents = proteinDAO.searchBySpecies(6);
        Species fruitfly = proteinDAO.searchSpecies("Fruit fly");
        for (ProteinCurrent p : proteinCurrents) {
            p.setSpecies(fruitfly);
            proteinDAO.updateProteinCurrent(p.getProtein_acc(), p);
        }
    }

    @Test
    public void testParseName() throws Exception {
        String file = "./src/main/resources/copa_to_do/Mouse_Whole_heart_lysate.copa";
        String organelle = "";
        String libmod = "";
        String[] shortFileName = file.split("/");
        String shortName = shortFileName[shortFileName.length - 1];
        shortName = shortName.substring(0, shortName.length() - 5);
        String[] parsedShortName = shortName.split("_");
        String parseShortName = parsedShortName[parsedShortName.length - 1];
        System.out.println("Short Name: " + shortName);
        System.out.println("Parsed Short Name: " + parseShortName);

        try { // last section of file name is an integer, truncate
            Integer.parseInt(parseShortName);
            organelle = parsedShortName[parsedShortName.length - 2];
            for (int i = 0; i < parsedShortName.length - 1; i++) {
                libmod += parsedShortName[i] + "_";
            }
            libmod = libmod.substring(0, libmod.length() - 1);
        } catch (NumberFormatException e) { // last section of file name is not an integer, must be organelle
            organelle = parseShortName;
            libmod = shortName;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Filename formatted incorrectly");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String species = parsedShortName[0];
        if (species.length() < 2) {
            String temp = " " + parsedShortName[1];
            species = species.concat(temp);
        }
        // match to formatted species name
        species = species.substring(0, 1).toUpperCase() + species.substring(1).toLowerCase();

        System.out.println("Module Name: " + libmod);
        System.out.println("Organelle: " + organelle);
        System.out.println("Species: " + species);
    }
}
