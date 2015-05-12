package org.copakb.server.dao;

import org.copakb.server.dao.model.GoTerms;
import org.copakb.server.dao.model.ProteinCurrent;

import java.util.Set;

/**
 * Created by vincekyi on 5/2/15.
 */
public interface ProteinDAO {

    public ProteinCurrent searchByID(String uniprotID);

    public ProteinCurrent getProteinWithGoTerms(String uniprotID);

    public ProteinCurrent getProteinWithGenes(String uniprotID);
}
