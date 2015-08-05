package org.copakb.server.dao;

import org.copakb.server.dao.model.Peptide;
import org.copakb.server.dao.model.ProteinCurrent;
import org.copakb.server.dao.model.service.ReferencePeptideBundle;
import org.copakb.server.dao.model.service.ReferenceProteinBundle;

/**
 * ServiceDAO interface for CopakbRestService
 * Created by Alan on 8/3/2015.
 */
public interface ServiceDAO {
    /**
     * Retrieves all necessary objects required to create a ReferenceProtein.
     *
     * @param protein Protein to retrieve bundle for.
     * @return ReferenceProteinBundle with necessary objects.
     */
    ReferenceProteinBundle getReferenceProteinBundle(ProteinCurrent protein);

    /**
     * Retrieves all necessary objects required to create a ReferencePeptide.
     *
     * @param peptide Peptide to retrieve bundle for.
     * @return ReferencePeptideBundle with necessary objects.
     */
    ReferencePeptideBundle getReferencePeptideBundle(Peptide peptide);
}
