import org.copakb.server.dao.*;
import org.copakb.server.dao.model.*;
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
        ServiceDAO serviceDAO = context.getBean(ServiceDAO.class);
        SpectrumDAO spectrumDAO = context.getBean(SpectrumDAO.class);


        /*Date date = new Date();
        LibraryModule mod = new LibraryModule("module_name", "LTQ", "proteasome", date, "Trypsin", proteinDAO.searchSpecies("Human"));

        peptideDAO.addLibraryModule(mod);

        Version version = new Version(1, date, "description", null, null);
        AnalysisTask task = new AnalysisTask("directory", "email", mod, version);
        //task.setToken("QSAD");

        reportDAO.addTask(task);*/

        //System.out.println(reportDAO.searchReport("f916bae895b146629a3cb32f64370287"));
        System.out.println(spectrumDAO.searchByID(1));


        context.close();

    }
}
