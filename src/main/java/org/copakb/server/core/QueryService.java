package org.copakb.server.core;

/**
 * Created by vincekyi on 5/20/15.
 */
public interface QueryService {
    //Todo return types

    //Basic query functionality
    void queryProtein(String uniprotID);
    void queryProteinList(String[] uniprotIDs);
    void queryPeptide(String sequence);
    void queryDisease(String disease);
    void retrieveReport(String token);
}
