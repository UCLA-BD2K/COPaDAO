package org.copakb.server.dao;

import org.copakb.server.dao.model.Spectrum;
import org.copakb.server.dao.model.service.ReferenceSpectrumBundle;

import java.util.List;

/**
 * SpectrumDAO interface.
 * Created by vincekyi on 8/15/15.
 */
public interface SpectrumDAO {
    Spectrum searchBySpecID(int spectrum_id);

    ReferenceSpectrumBundle searchByID(int spectrum_id);

    List<Spectrum> retrieveSpectraList(int precursor_mz);

    void addSpectraInfo(Spectrum s);
}
