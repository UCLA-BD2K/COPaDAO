package org.copakb.server.dao;

import org.copakb.server.dao.model.Disease;
import org.copakb.server.dao.model.DiseaseGene;
import org.copakb.server.dao.model.Gene;

import java.util.List;

/**
 * DiseaseDAO interface
 * Created by Ping PC1 on 7/1/2015.
 */
public interface DiseaseDAO {

    /**
     * Add disease information to the database
     *
     * @param d defined disease  object to be added
     * @return disease OMIM id if successful, -1 otherwise
     */
    int addDisease(Disease d);

    /**
     * Search for a disease object from the table
     *
     * @param doid OMIM disease id
     * @return completed disease object retrieved from the database
     */
    Disease searchDisease(int doid);

    /**
     * Returns a diseases with the given gene.
     *
     * @param geneName Name of gene to search.
     * @return List of diseases with the given gene.
     */
    List<Disease> searchDiseasesByGene(String geneName);

    /**
     * Add disease gene information to the database
     *
     * @param d defined diseasegene object to be added
     * @return disease OMIM id if successful, -1 otherwise
     */
    int addDiseaseGene(DiseaseGene d);

    /**
     * Searches for the DiseaseGene information from the database using the disease and gene symbol as the objects.
     * Mapped using the Disease and Gene objects through hibernate
     *
     * @param disease Disease to be matched
     * @param gene    Gene to be matched
     * @return defined DiseaseGene object with information from the database
     */
    DiseaseGene searchDiseaseGene(Disease disease, Gene gene);

    /**
     * Searches a limited list of Gene objects from the database
     *
     * @param start  beginning index for list
     * @param length number of genes to be returned
     * @return partial list of specified length of Gene objects beginning at the start index
     */
    List<Gene> limitedGeneList(int start, int length);
}
