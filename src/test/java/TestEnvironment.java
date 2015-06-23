import org.copakb.server.dao.DAOObject;
import org.copakb.server.dao.PeptideDAO;
import org.copakb.server.dao.ReportDAO;
import org.copakb.server.dao.model.*;
import org.copakb.server.dao.ProteinDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vincekyi on 4/16/15.
 */
public class TestEnvironment {

    public static void main(String []args){
//        System.out.println("Hello World!");
//        System.out.println(System.getProperty("user.dir"));
//        DBAccess db = DBAccess.getInstance();
//        System.out.println(db.query("select count(*) from peptide"));

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ProteinDAO proteinDAO = context.getBean(ProteinDAO.class);
        //System.out.println("" +proteinDAO.searchByID("P1"));

        // If duplicate, gives error: MySQLIntegrityConstraintViolationException
        ProteinCurrent result = new ProteinCurrent();
        result.setProtein_acc("P5");
        result.setSequence("QWERTYUIOPASDFGHJKL");
        result.setProtein_name("14-3-3 protein beta/alpha");
        result.setMolecular_weight(28082.0);
        result.setTransmembrane_domain("1");
        result.setCytoplasmatic_domain("1");
        result.setNoncytoplasmatic_domain("1");
        result.setSignal_peptide("1");
        result.setRef_kb_id("Ref1");
        result.setKeywords("3D-structure|Acetylation");
        result.setFeature_table("CHAIN\t1\t246");

        //Species tempSpecies = proteinDAO.searchSpecies("Mouse");
        Species newSpecies = new Species(2, "Mouse", null, null);
        proteinDAO.addSpecies(newSpecies);
        result.setSpecies(newSpecies);

        result.setWiki_link("www.wiki.com");

        Gene tempGene = new Gene("YWHAB", "ENSG000000166913", "15", null, null, null);
        proteinDAO.addGene(tempGene);
        Set<Gene> geneSet = new HashSet<>();
        geneSet.add(tempGene);
        result.setGenes(geneSet);

        // todo: figure out go term commit issue -> might be because there is not connection in db between uniprot and go_accession
        // ^ PROTEIN CURRENT still uses GoProtein (but it is not initialized!)
        /* debugging makes it seem that goterms is causing the infinite loop in commit */

        GoTerms tempGoTerm = new GoTerms(51220, "A:Phosphorous,C:", null);
        proteinDAO.addGoTerms(tempGoTerm);
        Set<GoTerms> goTermSet = new HashSet<>();
        goTermSet.add(tempGoTerm);
        result.setGoTerms(goTermSet);

        result.setPTMs(null);
        result.setSpectra(null);

        /*PTM tempPTM = new PTM("1", 1234, 'A', "18I32D", "AAAAA", "EFGH", "database", null);
        proteinDAO.addPTM(tempPTM);
        Set<PTM> ptmSet = new HashSet<>();
        ptmSet.add(tempPTM);
        result.setPTMs(ptmSet);

        PTM_type tempPtmType = new PTM_type(1, "(100.00)", "residue", 123.0, null);
        Spectrum tempSpectrum = new Spectrum("QWERTY", 2, null, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, tempPtmType, "rawfile1", null);
        Set<Spectrum> spectrumSet = new HashSet<>();
        spectrumSet.add(tempSpectrum);
        result.setSpectra(spectrumSet);*/


        proteinDAO.addProteinCurrent(result);

        context.close();

        //PeptideDAO peptideDAO = context.getBean(PeptideDAO.class);
        /*//Peptide peptide = new Peptide(7, "AAAAABCD", 123.45, 5, null);
        //peptideDAO.addPeptide(peptide);
        List<Peptide> list = peptideDAO.list();
        System.out.println(peptideDAO.searchBySequence("DAVSGMGVIVHIIEK"));*/
        //System.out.println(peptideDAO.searchBySpecId(2));


        //ProteinDAO proteinDAO = context.getBean(ProteinDAO.class);

        Set<LibraryModule> modules = new HashSet<>(Arrays.asList(new LibraryModule(), new LibraryModule()));
        //Set<ProteinCurrent> proteinCurrents = new HashSet<>(Arrays.asList(new ProteinCurrent(), new ProteinCurrent()));
        Species spec = new Species(1, "", null, null);
        Gene gene1 = new Gene();
        gene1.setGene_name("name123");
        /*Set<Gene> genes = new HashSet<>(Arrays.asList(gene1));
        Set<GoTerms> goTerms = new HashSet<>(Arrays.asList(new GoTerms(1, "", null), new GoTerms(2, "", null)));
        PTM ptm1 = new PTM("type", 14, 'C', "", "", "", "", new ProteinCurrent());
        PTM ptm2 = new PTM("type2", 16, 'K', "", "", "", "", new ProteinCurrent());
        Set<PTM> PTMs = new HashSet<>(Arrays.asList(ptm1, ptm2));*/
        //ProteinCurrent protein = new ProteinCurrent("P47", "", "", null, 0.0, null, null, null, null, null, null, null, null, spec, null, null, null, null);

        //ProteinCurrent protein = new ProteinCurrent("identification");
        //proteinDAO.addProteinCurrent(protein);

        //System.out.println("Peptide::"+peptide);
        //ProteinCurrent s = new ProteinCurrent();
        //PTM d = new PTM("w",3,'e',"w","w","w","w",s);

        /*List<ProteinCurrent> list = proteinDAO.list();

        for(ProteinCurrent p : list){
            System.out.println("Protein "+p);
        }*/

        //Peptide p1 = peptideDAO.searchById(1);
        //System.out.println(p1);

        //System.out.println("" + proteinDAO.searchByID("P1"));
        //System.out.println("" + proteinDAO.searchByName("Example"));
        //System.out.println("" + proteinDAO.searchByRef("test8"));

//        // Test code for GoTerms search
//        ProteinCurrent protein= proteinDAO.getProteinWithGoTerms("P1");
//        Set<GoTerms> listofGo= protein.getGoTerms();
//        for (GoTerms goTerm : listofGo) {
//            System.out.println("" + goTerm.getGO_accession() + "\t" + goTerm.getTerms());
//        }
//
//        // Test code for ProteinGene search
//        ProteinCurrent prote = proteinDAO.getProteinWithGenes("P1");
//        Set<Gene> listofGene = prote.getGenes();
//        for (Gene gene : listofGene) {
//            System.out.println("" + gene.toString());
//        }
//
//        // Test code for SpectrumProtein search
//        ProteinCurrent prot = proteinDAO.getProteinWithSpectra("P1");
//        Set<SpectrumProtein> listofSpectra = prot.getSpectra();
//        System.out.println(listofSpectra.size());
//        for (SpectrumProtein specProt : listofSpectra) {
//            System.out.println("" + specProt.getProtein_acc() + "\t" + specProt.getPrevAA() + "\t" + specProt.getNextAA());
//        }
        // Test code for GoTerms search
        /*ProteinCurrent protein= proteinDAO.getProteinWithGoTerms("P1");
        Set<GoTerms> listofGo= protein.getGoTerms();
        for (GoTerms goTerm : listofGo) {
            System.out.println("" + goTerm.getGO_accession() + "\t" + goTerm.getTerms());
        }

        // Test code for ProteinGene search
        ProteinCurrent prote = proteinDAO.getProteinWithGenes("P1");
        Set<Gene> listofGene = prote.getGenes();
        for (Gene gene : listofGene) {
            System.out.println("" + gene.toString());
        }

        ReportDAO reportDAO = context.getBean("reportDAO", ReportDAO.class);

        System.out.println(reportDAO.generateToken());

        //http://stackoverflow.com/questions/17893293/spring-mongodb-xml-configuration-with-credentials-fails
        Report report = reportDAO.searchReport("5508bc9bfb8bcff4d7c0b597");
        System.out.println(report);
        // Test code for SpectrumProtein search
        ProteinCurrent prot = proteinDAO.getProteinWithSpectra("P1");
        Set<SpectrumProtein> listofSpectra = prot.getSpectra();
        System.out.println(listofSpectra.size());
        for (SpectrumProtein specProt : listofSpectra) {
            System.out.println("" + specProt.getProtein_acc() + "\t" + specProt.getPrevAA() + "\t" + specProt.getNextAA());
        }*/

        //close resources
        //context.close();
    }
}
