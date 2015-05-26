package org.copakb.server.dao;

import org.copakb.server.dao.model.*;

import java.util.List;

import java.util.Set;

/**
 * Created by vincekyi on 5/2/15.
 */
public interface ProteinDAO {

    public List<ProteinCurrent> list();

    public List<ProteinCurrent> limitedList(int start, int length);

    public String addProteinCurrent(ProteinCurrent prot);

    public int addSpecies(Species spec);

    public ProteinCurrent searchByID(String uniprotID);

    public ProteinCurrent searchByName(String proteinName);

    public ProteinCurrent searchByRef(String refKbId);

    public ProteinCurrent searchByEnsg(String ensgID);

    public ProteinCurrent getProteinWithGenes(String uniprotID);
    public Gene searchByGeneName(String name);
    public String addGene(Gene g);

    public ProteinCurrent getProteinWithGoTerms(String uniprotID);
    public GoTerms searchByGOAccession(int GO_accession);
//  public String addGoTerm(GoTerms g);

    public ProteinCurrent getProteinWithPTMs(String uniprotID);
    public PTM searchByPTMType(String ptm_type);
    public String addPTM(PTM p);


}
