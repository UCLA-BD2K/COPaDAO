package org.copakb.server.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * Created by Ping PC1 on 6/4/2015.
 */
@Controller
public class DAOObject {

    private ClassPathXmlApplicationContext context;
    public PeptideDAO peptideDAO;
    public ProteinDAO proteinDAO;

    @PostConstruct
    public void init() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        peptideDAO = context.getBean(PeptideDAO.class);
        proteinDAO = context.getBean(ProteinDAO.class);
    }

}
