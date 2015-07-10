package org.copakb.server.dao;

import org.copakb.server.dao.model.*;

import java.util.List;

/**
 * Created by vincekyi on 4/16/15.
 */
public interface PeptideDAO {

    public int addPeptide(Peptide p);
    public List<Peptide> list();
    public List<Peptide> limitedList(int start, int length);

    public int getLocation(Peptide peptide, ProteinCurrent protein);


    /* Search for Peptide objects */

    public Peptide searchById(Integer peptide_id);
    public Peptide searchBySequence(String peptide_sequence);
    public Spectrum searchBySpecId(Integer id);
    public List<Peptide> searchByPartialSequence(String sequence);


    /* Spectrum add & search */

    public List<Spectrum> searchSpectrum(String ptm_seq, int mod_id, int charge);

    public int addSpectrum(Spectrum s);
    public String getSpectrum(int spec_id); // todo: return string of spectrum file (stored on harddrive)

    public void updateSpectrumSpecies(int spec_id, Spectrum spectrum);

    public void updateSpectrumFeature(int spec_id, Spectrum spectrum);


    /* Species add & search */

    public Species searchSpecies(String name);

    public Species searchSpecies(int id);
    public int addSpecies(Species spec);


    /* Library Modules add & search */

    public LibraryModule searchLibraryModuleWithId(int id);

    public LibraryModule searchLibraryModuleWithModule(String lib_mod);
    public int addLibraryModule(LibraryModule libmod);


    /* PTM type add & search */

    public PTM_type searchPtmType(int id);
    public int addPtmType(PTM_type type);

}
