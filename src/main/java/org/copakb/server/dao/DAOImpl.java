package org.copakb.server.dao;

import org.hibernate.SessionFactory;

/**
 * DAO Implementation base class.
 * Created by Alan on 8/3/2015.
 */
public abstract class DAOImpl {
    protected SessionFactory sessionFactory;

    /**
     * Default sets the session factory.
     *
     * @param sessionFactory Session factory to use.
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
