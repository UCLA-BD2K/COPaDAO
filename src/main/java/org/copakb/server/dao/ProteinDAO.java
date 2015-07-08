package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * ProteinDAO interface.
 * Created by vincekyi on 5/2/15.
 */
public interface ProteinDAO {
    /**
     * Adds a ProteinCurrent object to the database.
     *
     * @param prot Protein object to be added
     * @return Uniprot ID of p if successful, empty string otherwise.
     * @throws HibernateException
     */
    public String addProteinCurrent(ProteinCurrent prot);

    public void updateProteinCurrent(String protein_acc, ProteinCurrent p);

    public boolean deleteProteinCurrent(String protein_acc);

    /**
     * @param a
     * @param b
     * @return true if equal, false otherwise
     */
    public boolean compareProteinCurrent(ProteinCurrent a, ProteinCurrent b);

    public String addProteinHistory(ProteinHistory p);

    public ProteinHistory searchProteinHistory(String uniprot_id);

    public String addSpectrumProteinHistory(SpectrumProteinHistory p);

    public boolean deleteSpectrumProtein(int id);

    public SpectrumProteinHistory searchSpectrumProteinHistory(String protein_acc, int spec_id);

    public int addVersion(Version version);

    public Version searchVersion(int version);

    public Version searchLatestVersion();

    /**
     * Searches for a protein with the given Uniprot ID.
     *
     * @param uniprotID Uniprot ID of the protein as given by www.uniprot.org
     * @return ProteinCurrent object that contains the given Uniprot ID, null if not found.
     */
    public ProteinCurrent searchByID(String uniprotID);

    /**
     * Searches all proteins that start with the given UniProt ID prefix.
     *
     * @param idPrefix UniProt ID prefix.
     * @return List of proteins objects that start with the given UniProt ID prefix.
     */
    public List<ProteinCurrent> searchByLikeID(String idPrefix);

    /**
     * Searches all proteins containing the partial ID.
     *
     * @param idFragment UniProt ID fragment.
     * @return List of proteins containing the partial ID.
     */
    public List<ProteinCurrent> searchByPartialID(String idFragment);

    /**
     * Searches for a protein with the given name.
     *
     * @param proteinName Protein name.
     * @return First ProteinCurrent object found that contains the given protein name, null if not found.
     */
    public ProteinCurrent searchByName(String proteinName);

    /**
     * Searches for proteins containing the partial sequence.
     *
     * @param sequence Partial sequence.
     * @return List of proteins containing the partial sequence.
     */
    public List<ProteinCurrent> searchByPartialSequence(String sequence);

    /**
     * Searches database for all objects in the database that matches the ProteinCurrent specifications
     *
     * @return all the ProteinCurrent objects contained in the database
     * @throws HibernateException
     */
    public List<ProteinCurrent> list();

    /**
     * Searches a limited list of ProteinCurrent objects from the database
     *
     * @param start  beginning index for list
     * @param length number of Protein Currents to be returned
     * @return partial list of specified length of ProteinCurrent objects beginning at the start index
     */
    public List<ProteinCurrent> limitedList(int start, int length);

    /**
     * Adds a DBRef object to the database.
     *
     * @param dbRef DBRef object to be added.
     * @return Uniprot ID of associated protein if successful, empty string otherwise.
     */
    public String addDbRef(DBRef dbRef);

    /**
     * Returns the first DBRef which references the given PDB ID.
     *
     * @param uniprotID The PDB ID to search for.
     * @return A protein that references the given PDB IDl
     */
    public DBRef searchDbRefByID(String uniprotID);

    /**
     * Returns the first protein which references the given PDB ID.
     *
     * @param pdbID The PDB ID to search for.
     * @return A protein that references the given PDB IDl
     */
    public ProteinCurrent searchByPDB(String pdbID);

    /**
     * Add gene information to all relevant protein objects.
     *
     * @param gene defined Gene to be added
     * @return gene name if successful, empty string otherwise
     * @throws HibernateException
     */
    public String addGene(Gene gene);

    /**
     * Searches for a gene with the given name
     *
     * @param name Gene name of the protein
     * @return Gene object that contains the gene information, disease relevance, and relevant proteins
     */
    public Gene searchByGeneName(String name);

    /**
     * Searches for a protein with a gene with the given ensembl ID.
     *
     * @param ensemblID Ensembl id of the protein as given by the www.ensembl.org
     * @return ProteinCurrent object that contains the given Ensembl ID
     */
    public ProteinCurrent searchByEnsg(String ensemblID);

    /**
     * Searches for proteins in the database with the given uniprot ID and
     * matches it with all the relevant gene information
     *
     * @param uniprotID Uniprot ID of the protein as given by www.uniprot.org
     * @return Protein object with defined gene information
     */
    public ProteinCurrent getProteinWithGenes(String uniprotID);

    /**
     * Add GoTerms to all relevant proteins.
     *
     * @param goTerms Set of GoTerms to add.
     * @return GoTerm accession number
     */
    public int addGoTerms(GoTerms goTerms);

    /**
     * Searches for proteins in the database with the given GO accession ID
     *
     * @param GO_accession GO accession of the protein as automatically defined in the database entries
     * @return GOTerms object which includes all of the relevant proteins and terms
     */
    public GoTerms searchByGOAccession(int GO_accession);

    /**
     * Searches for proteins in the database with the given uniprot ID and
     * matches it with all the relevant GO terms
     *
     * @param uniprotID Uniprot ID of the protein as given by www.uniprot.org
     * @return Project object with defined GO terms
     */
    public ProteinCurrent getProteinWithGoTerms(String uniprotID);

    /**
     * Add a species to the database
     *
     * @param sp defined species object with name, id, and list of relevant proteins
     * @return species id if successful, -1 otherwise
     * @throws HibernateException
     */
    public int addSpecies(Species sp);

    /**
     * Searches for a species object by checking species names
     *
     * @param name name of species
     * @return species object that matches the given name
     */
    public Species searchSpecies(String name);

    /**
     * Add SpectrumProtein object
     *
     * @param p defined SpectrumProtein object to be added
     * @return Auto-generated ID if successful, -1 otherwise
     * @throws HibernateException
     */
    public int addSpectrumProtein(SpectrumProtein p);

    /**
     * Search for protein and spectrum information
     *
     * @param uniprotID Uniprot ID of the protein as given by www.uniprot.org
     * @return ProteinCurrent object with the set of spectra defined
     */
    public ProteinCurrent getProteinWithSpectra(String uniprotID);

    /**
     * Searches for SpectrumProtein object from the database
     *
     * @param spectrum_id ID of the spectrum as autogenerated by the database
     * @param protein_acc protein Uniprot ID as used from uniprot.org
     * @return defined SpectrumProtein object
     */
    public SpectrumProtein searchSpectrumProtein(int spectrum_id, String protein_acc);

    public List<SpectrumProtein> searchSpectrumProteins(ProteinCurrent proteinCurrent);

    public List<ProteinCurrent> searchProteinsByPeptide(Peptide peptide);

    public String addHPAProtein(HPAProtein protein);

    public HPAProtein searchHPAByID(String ensemblID);

    public String addAntibody(Antibody antibody);

    public Antibody searchAntibodyByID(String antibodyID);

    /**
     * Search for protein and PTM information
     *
     * @param uniprotID Uniprot ID of the protein as given by www.uniprot.org
     * @return ProteinCurrent object with the PTM defined
     */
    public ProteinCurrent getProteinWithPTMs(String uniprotID);
}
