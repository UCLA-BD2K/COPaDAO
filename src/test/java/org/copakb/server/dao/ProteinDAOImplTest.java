package org.copakb.server.dao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alan on 6/29/2015.
 */
public class ProteinDAOImplTest {

    @Test
    public void testSearchByLikeID() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().searchByLikeID("A4F"));
    }

    @Test
    public void testSearchByPartialSequence() throws Exception {
        System.out.println(DAOObject.getInstance().getProteinDAO().searchByPartialSequence("AAA"));
    }
}
