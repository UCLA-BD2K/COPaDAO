package org.copakb.server.dao;

import org.copakb.server.dao.model.ProteinCurrent;

/**
 * Created by vincekyi on 5/2/15.
 */
public interface ProteinDAO {

    public ProteinCurrent searchByID(String uniprotID);

}
