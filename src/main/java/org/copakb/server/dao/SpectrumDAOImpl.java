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
 * Spectrum DAO implementation.
 * Created by vincekyi on 8/15/15.
 */
public class SpectrumDAOImpl extends DAOImpl implements SpectrumDAO {
    private MongoOperations mongoOps;

    /**
     * Sets the MongoOps to execute MongoDB operations
     *
     * @param mongoOps MongoOperations object
     */
    public SpectrumDAOImpl(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    public SpectraDataEntry searchBySpecID(int spectrum_id) {
        BasicQuery query = new BasicQuery("{ _id: " + Integer.toString(spectrum_id) + " }");
        List<SpectraDataEntry> results = mongoOps.find(query, SpectraDataEntry.class);
        if (results.isEmpty()) {
            return null;
        }

        return results.get(0);
    }

    public ReferenceSpectrumBundle searchByID(int spectrum_id){
        Session session = sessionFactory.openSession();
        org.hibernate.Query query = session.createQuery("SELECT " +
                "xcorr, delta_cn, th_precursor_mz, rawfile_id " +
                "FROM " +
                "Spectrum " +
                "WHERE " +
                "spectrum_id = " + Integer.toString(spectrum_id));
        List<Object[]> list = query.list();
        Object[] result = list.get(0);

        session.close();

        return new ReferenceSpectrumBundle(searchBySpecID(spectrum_id),
                Double.parseDouble(result[0].toString()),
                Double.parseDouble(result[1].toString()),
                Double.parseDouble(result[2].toString()),
                result[3].toString()
                );
    }

    public List<SpectraDataEntry> retrieveSpectraList(int precursor_mz) {
        Query query = new Query();
        query.addCriteria(Criteria.where("precursor_mz").lte(precursor_mz+1).gte(precursor_mz-1));
        query.fields()
                .include("spectrum_id")
                .include("ptm_sequence")
                .include("precursor_mz")
                .include("charge_state")
                .include("species_name")
                .include("organelle");
        return mongoOps.find(query, SpectraDataEntry.class);
    }

    public void addSpectraInfo(SpectraDataEntry s) {
        mongoOps.save(s);
    }
}
