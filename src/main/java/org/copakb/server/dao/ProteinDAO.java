package org.copakb.server.dao;

import org.copakb.server.dao.model.GoTerms;
import org.copakb.server.dao.model.ProteinCurrent;
import org.copakb.server.dao.model.Species;

import java.util.List;

import java.util.Set;

/**
 * Created by vincekyi on 5/2/15.
 */
public interface ProteinDAO {

    public List<ProteinCurrent> list();

    public void addProteinCurrent(ProteinCurrent prot);

    public int addSpecies(Species spec);

    public ProteinCurrent searchByID(String uniprotID);

    public ProteinCurrent searchByName(String proteinName);

    public ProteinCurrent searchByRef(String refKbId);

    public ProteinCurrent searchByEnsg(String ensgID);

    public ProteinCurrent getProteinWithGoTerms(String uniprotID);

    public ProteinCurrent getProteinWithGenes(String uniprotID);
}
