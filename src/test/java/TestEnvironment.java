import org.copakb.server.dao.PeptideDAO;
import org.copakb.server.dao.model.LibraryModule;
import org.copakb.server.dao.model.PTM_type;
import org.copakb.server.dao.model.Peptide;
import org.copakb.server.dao.model.Spectrum;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by vincekyi on 4/16/15.
 */
public class TestEnvironment {

private static char AA[] = {
        'A', 'R', 'N', 'D', 'C', 'E', 'Q', 'G', 'H', 'O',
        'I', 'L', 'K', 'M', 'F', 'P', 'U', 'S', 'T', 'V', 'U'
    };

    public static String randomPeptideGen(int len){
        StringBuilder result = new StringBuilder("");

        for(int i = 0; i < len; i++){
            int index = (int)(Math.random()*21);
            result.append(AA[index]);
        }

        return result.toString();

    }
    public static void main(String []args){


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        PeptideDAO peptideDAO = context.getBean(PeptideDAO.class);

        Peptide peptide = new Peptide("AAAAA", 123.45, 5);

        //peptideDAO.addPeptide(peptide);

        //System.out.println("Peptide::"+peptide);

        List<Peptide> list = peptideDAO.list();
        System.out.println(list.size());

        for(Peptide p : list){
            System.out.println("Peptide "+p);
        }

        System.out.println(randomPeptideGen(20));

        LibraryModule libmod = new LibraryModule();
        libmod.setMod_id(1);
        PTM_type ptm = new PTM_type();
        ptm.setPtm_type(0);
        //Set<Spectrum> spectra = new HashSet<Spectrum>();
        //spectra.add(newSpec);

        Peptide newPep = new Peptide("TLGDUSMMILEATNMHQIUAAAAA", 0.01, 24, null);
        Spectrum newSpec = new Spectrum("TLGDUSMMILEATNMHQIUAAAAA", 3, libmod, 8.1, 0.2, 4.3, 0, 500.0, 501.0, ptm, "file", newPep);

        System.out.println("added new spectrum: "+peptideDAO.addSpectrum(newSpec));
        //close resources
        context.close();
    }
}
