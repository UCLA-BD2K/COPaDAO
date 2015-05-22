import org.copakb.server.dao.PeptideDAO;
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

        /*PeptideDAO peptideDAO = context.getBean(PeptideDAO.class);
        //Peptide peptide = new Peptide(7, "AAAAABCD", 123.45, 5, null);
        //peptideDAO.addPeptide(peptide);
        List<Peptide> list = peptideDAO.list();
        System.out.println(peptideDAO.searchBySequence("DAVSGMGVIVHIIEK"));
        //System.out.println(peptideDAO.searchBySpecId(1));*/


        ProteinDAO proteinDAO = context.getBean(ProteinDAO.class);

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

        // Test code for GoProtein search
        List<GOProtein> listOfGO= proteinDAO.getProteinListWithGoTerms("P1");
        for (GOProtein goProtein : listOfGO) {
            System.out.println("" + goProtein.getProteinCurrent().getProtein_acc() + "\t" + goProtein.getGoTerm());
        }

        // Test code for ProteinGene search
        /*List<ProteinGene> listOfGene = proteinDAO.getProteinWithGenes("P22");
        for (ProteinGene proteinGene : listOfGene) {
            System.out.println("" + proteinGene.getProteinCurrent().getProtein_acc() + "\t" + proteinGene.getGene());
        }*/

        //close resources
        context.close();
    }
}
