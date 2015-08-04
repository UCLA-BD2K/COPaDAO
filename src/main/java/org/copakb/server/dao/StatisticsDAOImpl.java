package org.copakb.server.dao;

import org.copakb.server.dao.model.WebStatistics;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 * StatisticsDAO implementation.
 * Created by Sneha on 6/30/2015.
 */
public class StatisticsDAOImpl extends DAOImpl implements StatisticsDAO {
    public WebStatistics getCurrentStats() {
        Session session = sessionFactory.openSession();
        Criteria c = session.createCriteria(WebStatistics.class);
        c.addOrder(Order.desc("ws_id"));
        c.setMaxResults(1);
        return (WebStatistics) c.uniqueResult();
    }

    public int addNewStats(int pageviews, double ppv, int uniquevisitors, int countries) {
        WebStatistics stat = new WebStatistics(pageviews, ppv, uniquevisitors, countries);
        int result = 0;

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            result = (int) session.save(stat);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
