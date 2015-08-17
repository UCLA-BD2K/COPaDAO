package org.copakb.server.dao;

import org.copakb.server.dao.model.SpectraDataEntry;
import org.copakb.server.dao.model.service.ReferenceSpectrumBundle;
import org.hibernate.Session;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by vincekyi on 8/15/15.
 */
public class SpectrumDAOImpl extends DAOImpl implements SpectrumDAO {

    private MongoOperations mongoOps;

    private static final String SPECTRA_COLLECTION = "Spectra";

    /**
     * Sets the MongoOps to execute MongoDB operations
     *
     * @param mongoOps
     */
    public SpectrumDAOImpl(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }


    public SpectraDataEntry searchBySpecID(int spectrum_id){
        BasicQuery query = new BasicQuery("{ _id: " + Integer.toString(spectrum_id) + " }");
        List<SpectraDataEntry> results = mongoOps.find(query, SpectraDataEntry.class);
        return results.get(0);
    }

    public ReferenceSpectrumBundle searchByID(int spectrum_id){
        Session session = sessionFactory.openSession();
        org.hibernate.Query query = session.createQuery("SELECT \n" +
                "xcorr, delta_cn, th_precursor_mz, rawfile_id " +
                "FROM " +
                "Spectrum " +
                "WHERE " +
                "spectrum_id = " + Integer.toString(spectrum_id));
        List<Object[]> list = query.list();
        Object[] result = list.get(0);

        session.close();
        return new ReferenceSpectrumBundle(this.searchBySpecID(spectrum_id),
                Double.parseDouble(result[0].toString()),
                Double.parseDouble(result[1].toString()),
                Double.parseDouble(result[2].toString()),
                result[3].toString()
                );

    }

}
