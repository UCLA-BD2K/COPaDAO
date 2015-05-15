package org.copakb.server.dao;

import org.copakb.server.dao.model.Peptide;
import org.copakb.server.dao.model.ProteinCurrent;
import org.hibernate.HibernateException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by vincekyi on 4/28/15.
 */
public class TestPeptideDAO {

    private static ClassPathXmlApplicationContext context = null;

    @BeforeClass
    public static void setupTest(){
        if(context==null)
            context = new ClassPathXmlApplicationContext("spring.xml");
    }

    @AfterClass
    public static void closeResources(){
        if(context!=null)
            context.close();
    }

    @Test
    public void testSearchFirstEntry(){


        PeptideDAO peptideDAO = context.getBean(PeptideDAO.class);
        ProteinDAO proteinDAO = context.getBean(ProteinDAO.class);

        Peptide pep1 = null;
        ProteinCurrent prot1 = null;
        try{
            pep1 = peptideDAO.searchById(1);
            assertEquals(pep1.toString(), "ID: 1\n" +
                    "sequence: DAVSGMGVIVHIIEK\n" +
                    "spectra: \n" +
                    "DAVSGMGVI(19.0)VHIIEK, xcorr: 7.2632, module: human_heart_proteasome, species: Human, ptm: 1\n" +
                    "DAVSGMGVIVHIIEK, xcorr: 6.2632, module: human_heart_proteasome, species: Human, ptm: 0\n" +
                    "**");
            /*prot1 = proteinDAO.searchByID("P1");
            System.out.println(prot1.getSequence());
            System.out.println(prot1.getENSG_ID());
            System.out.println(prot1.getSpecies().getProteinCurrents().size()); */
        }catch(HibernateException ex){
            assert(false);
        }

    }

    @Test
    public void testAddExistingPeptide(){
        PeptideDAO peptideDAO = context.getBean(PeptideDAO.class);

        Peptide newPep = new Peptide("DAVSGMGVIVHIIEK", 0.01, 15);
        try {
            peptideDAO.addPeptide(newPep);
            assert(false);
        }catch(HibernateException ex){
            assert(true);
        }
    }
}
