package org.copakb.server.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * Created by Ping PC1 on 6/4/2015.
 * Modified on 6/29/2015 by Daniel Yao to a Singleton class
 */

@Controller
public class DAOObject {

    private volatile static DAOObject uniqueInstance;
    private static PeptideDAO peptideDAO;
    private static ProteinDAO proteinDAO;
    private static DiseaseDAO diseaseDAO;
    private static ServiceDAO serviceDAO;
    private static SpectrumDAO spectrumDAO;

    protected DAOObject() {
    }

    /**
     * Used to get the unique instance.
     * Returns the unique instance or construct a new one if doesn't exist.
     */
    public static DAOObject getInstance() {
        if(uniqueInstance == null) {
            synchronized (DAOObject.class) {
                if(uniqueInstance == null) {
                    uniqueInstance = new DAOObject();
                    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
                    peptideDAO = context.getBean(PeptideDAO.class);
                    proteinDAO = context.getBean(ProteinDAO.class);
                    diseaseDAO = context.getBean(DiseaseDAO.class);
                    serviceDAO = context.getBean(ServiceDAO.class);
                    spectrumDAO = context.getBean(SpectrumDAO.class);
                }
            }
        }
        return uniqueInstance;
    }

    public PeptideDAO getPeptideDAO() {
        return peptideDAO;
    }

    public ProteinDAO getProteinDAO() {
        return proteinDAO;
    }

    public DiseaseDAO getDiseaseDAO() {
        return diseaseDAO;
    }

    public ServiceDAO getServiceDAO() {
        return serviceDAO;
    }

    public SpectrumDAO getSpectrumDAO() { return spectrumDAO; }
}
