package org.copakb.server.dao;

import org.copakb.server.dao.model.*;

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
    String addDisease(Disease d);

    /**
     * Search for a disease object from the table
     *
     * @param doid OMIM disease id
     * @return completed disease object retrieved from the database
     */
    Disease searchDisease(String doid);

    /**
     * Gets a fully initialized Disease.
     *
     * @param doid Disease OMIM ID to search.
     * @return Fully initialized Disease; null if not found.
     */
    Disease getInitializedDisease(String doid);

    /**
     * Add disease gene information to the database
     *
     * @param d defined diseasegene object to be added
     * @return disease OMIM id if successful, -1 otherwise
     */
    String addDiseaseGene(DiseaseGene d);

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
     * Searches for the DiseaseGene information from the database using the disease and gene symbol as the objects.
     * Mapped using the Disease and Gene objects through hibernate, and with all Lazy
     * associations initialized.
     *
     * @param disease Disease to be matched
     * @param gene Gene to be matched
     * @return defined DiseaseGene object with information from the database
     */
    DiseaseGene getInitializedDiseaseGene(Disease disease, Gene gene);

    /**
     * Searches a limited list of Gene objects from the database
     *
     * @param start  beginning index for list
     * @param length number of genes to be returned
     * @return partial list of specified length of Gene objects beginning at the start index
     */
    List<Gene> limitedGeneList(int start, int length);
}
