package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



/**
 * Created by Sneha on 6/30/2015.
 */

public class StatisticsDAOImpl implements StatisticsDAO {

    private SessionFactory sessionFactory;

    /**
     * Default sets the session factory
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public WebStatistics getCurrentStats()
    {
        Session session = sessionFactory.openSession();
        Criteria c = session.createCriteria(WebStatistics.class);
        c.addOrder(Order.desc("ws_id"));
        c.setMaxResults(1);
        return (WebStatistics)c.uniqueResult();
    }


    public int addNewStats(int pageviews, double ppv, int uniquevisitors, int countries)
    {
        WebStatistics stat = new WebStatistics(pageviews, ppv, uniquevisitors, countries);
        int result = 0;

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try{
            result = (int)session.save(stat);
            tx.commit();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }


}
