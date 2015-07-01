package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
import org.copakb.server.dao.model.Version;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Created by Sneha on 6/30/2015.
 */

public class StatisticsDAOImpl {

    private SessionFactory sessionFactory;

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
