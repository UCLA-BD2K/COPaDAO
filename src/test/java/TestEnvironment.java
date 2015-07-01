import org.copakb.server.dao.DAOObject;
import org.copakb.server.dao.PeptideDAO;
import org.copakb.server.dao.ReportDAO;
import org.copakb.server.dao.model.*;
import org.copakb.server.dao.ProteinDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * Created by vincekyi on 4/16/15.
 */
public class TestEnvironment {

    public static void main(String []args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ProteinDAO proteinDAO = context.getBean(ProteinDAO.class);
        PeptideDAO peptideDAO = context.getBean(PeptideDAO.class);
        ReportDAO reportDAO = context.getBean(ReportDAO.class);

        Date date = new Date();
        LibraryModule mod = new LibraryModule("module_name", "LTQ", "proteasome", date, "Trypsin", proteinDAO.searchSpecies("Human"));

        peptideDAO.addLibraryModule(mod);

        Version version = new Version(1, date, "description", null, null);
        AnalysisTask task = new AnalysisTask("directory", "email", mod, version);
        //task.setToken("QSAD");

        reportDAO.addTask(task);


        context.close();

    }
}
