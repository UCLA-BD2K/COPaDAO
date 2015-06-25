package org.copakb.server.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * Created by Ping PC1 on 6/4/2015.
 */
@Controller
public class DAOObject {

    private static ClassPathXmlApplicationContext context;
    public static PeptideDAO peptideDAO;
    public static ProteinDAO proteinDAO;

    @PostConstruct
    public void init() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        peptideDAO = context.getBean(PeptideDAO.class);
        proteinDAO = context.getBean(ProteinDAO.class);
    }

    public static PeptideDAO getPeptideDAO() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("spring.xml");
        }
        if (peptideDAO == null) {
            peptideDAO = context.getBean(PeptideDAO.class);
        }
        return peptideDAO;
    }

    public static ProteinDAO getProteinDAO() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("spring.xml");
        }
        if (proteinDAO == null) {
            proteinDAO = context.getBean(ProteinDAO.class);
        }
        return proteinDAO;
    }

    protected DAOObject() {
        // Protect against instantiation
    }
}
