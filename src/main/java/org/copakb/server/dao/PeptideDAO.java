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

    public Peptide searchById(Integer peptide_id);

    public Peptide searchBySequence(String peptide_sequence);

    public Spectrum searchBySpecId(Integer id);

    public int addSpectrum(Spectrum s);

    public Spectrum searchSpectrum(String ptm_seq, int mod_id, int charge);

    public Species searchSpecies(String name);

    public int addSpecies(Species spec);

    public LibraryModule searchLibraryModuleWithId(int id);

    public LibraryModule searchLibraryModuleWithModule(String lib_mod);

    public int addLibraryModule(LibraryModule libmod);

    public PTM_type searchPtmType(int id);

    public int addPtmType(PTM_type type);

}
