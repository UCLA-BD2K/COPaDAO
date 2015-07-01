package org.copakb.server.dao;

import org.copakb.server.dao.model.Disease;
import org.copakb.server.dao.model.DiseaseGene;
import org.copakb.server.dao.model.Gene;

import java.util.List;

/**
 * Created by Ping PC1 on 7/1/2015.
 */
public interface DiseaseDAO {

    public int addDisease(Disease d);

    public Disease searchDisease(int doid);

    public int addDiseaseGene(DiseaseGene d);

    public DiseaseGene searchDiseaseGene(Disease disease, Gene gene);

    public List<Gene> limitedGeneList(int start, int length);

}
