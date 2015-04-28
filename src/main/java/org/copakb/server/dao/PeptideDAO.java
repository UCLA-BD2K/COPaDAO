package org.copakb.server.dao;

import org.copakb.server.dao.model.Peptide;
import org.copakb.server.dao.model.Spectrum;

import java.util.List;

/**
 * Created by vincekyi on 4/16/15.
 */
public interface PeptideDAO {

    public void addPeptide(Peptide p);

    public List<Peptide> list();

    public Peptide searchById(Integer peptide_id);

    public Peptide searchBySequence(String peptide_sequence);

    public Spectrum searchBySpecId(Integer id);
}
