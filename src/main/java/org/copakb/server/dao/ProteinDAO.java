package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

import java.util.Set;

/**
 * Created by vincekyi on 5/2/15.
 */
public interface ProteinDAO {

    public String addProteinCurrent(ProteinCurrent prot);
    public List<ProteinCurrent> list();
    public List<ProteinCurrent> limitedList(int start, int length);


    /* Searches for Protein objects */

    public ProteinCurrent searchByID(String uniprotID);
    public List<ProteinCurrent> searchByLikeID(String uniprotID);
    public ProteinCurrent searchByName(String proteinName);
    public ProteinCurrent searchByRef(String refKbId);
    public ProteinCurrent searchByEnsg(String ensgID);
    public List<ProteinCurrent> searchByPartialID(String idFragment);
    public List<ProteinCurrent> searchByPartialSequence(String sequence);

    public List<ProteinCurrent> searchProteinsByPeptide(Peptide peptide); // get all the proteins through spectrum protein

    public String addProteinHistory(ProteinHistory p);
    public ProteinHistory searchProteinHistory(String uniprot_id);
    public boolean compareProteinCurrent(ProteinCurrent a, ProteinCurrent b);
    public void updateProteinCurrent(String protein_acc, ProteinCurrent p);
    public boolean deleteProteinCurrent(String protein_acc);


    /* Gene completion, add & search */

    public ProteinCurrent getProteinWithGenes(String uniprotID);
    public Gene searchByGeneName(String name);
    public String addGene(Gene g);


    /* GO term completion, add & search */

    public ProteinCurrent getProteinWithGoTerms(String uniprotID);
    public GoTerms searchByGOAccession(int GO_accession);
    public int addGoTerms(GoTerms goTerms);


    /* PTM completion */

    public ProteinCurrent getProteinWithPTMs(String uniprotID);


    /* Spectrum completion, add & search */

    public ProteinCurrent getProteinWithSpectra(String uniprotID);
    public int addSpectrumProtein(SpectrumProtein p);
    public SpectrumProtein searchSpectrumProtein(int spectrum_id, String protein_acc);


    /* Species add & search */

    public int addSpecies(Species sp);
    public Species searchSpecies(String name);


    /* HPA add & search */

    public String addHPAProtein(HPAProtein protein);
    public HPAProtein searchHPAByID(String ensemblID);


    /* Antibody add & search */

    public String addAntibody(Antibody antibody);
    public Antibody searchAntibodyByID(String antibodyID);


    /* Database reference add & search */

    public String addDbRef(DBRef dbRef);

    public DBRef searchDbRefByID(String uniprotID);
    public ProteinCurrent searchByPDB(String pdbID);

    /* Version add & search */

    public Version searchVersion(int version);
    public int addVersion(Version version);
    public Version searchLatestVersion();

}
