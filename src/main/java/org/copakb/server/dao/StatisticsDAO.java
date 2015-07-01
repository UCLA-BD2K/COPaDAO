package org.copakb.server.dao;

import org.copakb.server.dao.model.*;

import java.util.List;

/**
 * Created by Sneha on 6/30/2015.
 */
public interface StatisticsDAO {

    public WebStatistics getCurrentStats();

    public int addNewStats(int pageviews, double ppv, int uniqueVisitors, int countries);
}
