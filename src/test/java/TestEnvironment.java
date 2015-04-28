import org.copakb.server.dao.DBAccess;
import org.copakb.server.dao.PeptideDAO;
import org.copakb.server.dao.model.Peptide;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by vincekyi on 4/16/15.
 */
public class TestEnvironment {

    public static void main(String []args){


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        PeptideDAO peptideDAO = context.getBean(PeptideDAO.class);

        Peptide peptide = new Peptide("AAAAA", 123.45, 5);

        //peptideDAO.addPeptide(peptide);

        //System.out.println("Peptide::"+peptide);

        List<Peptide> list = peptideDAO.list();

        for(Peptide p : list){
            System.out.println("Peptide "+p);
        }


        //close resources
        context.close();
    }
}
