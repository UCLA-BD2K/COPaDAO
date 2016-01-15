package org.copakb.server.dao;

import org.copakb.server.dao.model.ModuleStatistics;

import java.util.List;

/**
 * ServiceDAO interface.
 * Created by Kevin on 9/22/2015.
 */
public interface StatisticsDAO {

    ModuleStatistics getModuleStatistics(int mod_id);

    List<ModuleStatistics> list();
}
