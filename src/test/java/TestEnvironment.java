import org.copakb.server.dao.PeptideDAO;
import org.copakb.server.dao.ProteinDAO;
import org.copakb.server.dao.model.*;
import org.hibernate.Hibernate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

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

        PeptideDAO peptideDAO = context.getBean(PeptideDAO.class);

        //Peptide peptide = new Peptide("AAAAA", 123.45, 5);

        //peptideDAO.addPeptide(peptide);

        //System.out.println("Peptide::"+peptide);
        //Protein_Current s = new Protein_Current();
        //PTM d = new PTM("w",3,'e',"w","w","w","w",s);
        List<Peptide> list = peptideDAO.limitedList(0, 3);

//        for(Peptide p : list){
//            System.out.println("Peptide "+p);
//        }

        for(SpectrumProtein s: peptideDAO.searchBySpecId(1).getSpectrumProtein())
            System.out.println("Protein: "+s.getProtein_acc());
        //Peptide p1 = peptideDAO.searchById(1);
        //System.out.println(p1);
        //System.out.println(peptideDAO.searchBySequence("DAVSGMGVIVHIIEK"));
        //System.out.println(peptideDAO.searchBySpecId(1));

        ProteinDAO proteinDAO = context.getBean(ProteinDAO.class);
        ProteinCurrent searchProtein = proteinDAO.getProteinWithGenes("P1");
        System.out.println("protein sequence: "+searchProtein.getSequence());

        for(Gene gene: searchProtein.getGenes()) {
            System.out.println("Go Term: " + gene.getGene_name());
        }
        //close resources
        context.close();
    }
}
