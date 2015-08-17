package org.copakb.server.dao;

import org.copakb.server.dao.model.SpectraDataEntry;
import org.copakb.server.dao.model.service.ReferenceSpectrumBundle;

import java.util.List;

/**
 * Created by vincekyi on 8/15/15.
 */
public interface SpectrumDAO {

    public SpectraDataEntry searchBySpecID(int spectrum_id);

    public ReferenceSpectrumBundle searchByID(int spectrum_id);

    public List<SpectraDataEntry> retrieveSpectraList(int precursor_mz);
}
