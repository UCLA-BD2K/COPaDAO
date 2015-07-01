package org.copakb.server.dao;

import org.junit.Test;

/**
 * Created by Alan on 6/29/2015.
 */
public class ProteinDAOImplTest {

    @Test
    public void testSearchByLikeID() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().searchByLikeID("A4F"));
    }

    @Test
    public void testSearchByPartialId() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().searchByPartialID("5E"));
    }

    @Test
    public void testSearchByPartialSequence() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().searchByPartialSequence("SVS"));
    }

    @Test
    public void testSearchByPDB() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().searchByPDB("2B05"));
    }
}
