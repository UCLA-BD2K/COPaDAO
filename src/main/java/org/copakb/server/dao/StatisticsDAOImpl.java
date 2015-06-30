package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
import org.copakb.server.dao.model.Version;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import java.util.*;

/**
 * Created by Sneha on 6/30/2015.
 */
public class StatisticsDAOImpl {

    public WebStatistics getCurrentStats()
    {
        WebStatistics w = new WebStatistics();
        return w;
    }

    public void addNewStats(int pageviews, double ppv, int uniquevisitors, int countries)
    {

    }


}
