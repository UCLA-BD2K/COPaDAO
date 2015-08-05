package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
import org.copakb.server.dao.model.service.ReferenceProteinBundle;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

/**
 * ServiceDAO implementation.
 * Created by Alan on 8/3/2015.
 */
@SuppressWarnings("unchecked")
public class ServiceDAOImpl extends DAOImpl implements ServiceDAO {
    @Override
    public ReferenceProteinBundle getReferenceProteinBundle(ProteinCurrent protein) {
        Session session = sessionFactory.openSession();

        String uniprotID = protein.getProtein_acc();

        ReferenceProteinBundle result = new ReferenceProteinBundle();
        result.setUniprotID(uniprotID);
        result.setDiseases(session.createCriteria(Disease.class, "d")
                .createAlias("d.genes", "g")
                .createAlias("g.proteins", "p")
                .add(Restrictions.eq("p.protein_acc", uniprotID))
                .setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE)
                .list());
        result.setSpectrumProteins(session.createCriteria(SpectrumProtein.class)
                .add(Restrictions.eq("protein.protein_acc", uniprotID))
                .createAlias("peptide", "p")
                .createAlias("libraryModule", "l")
                .setProjection(Projections.projectionList()
                        .add(Projections.property("prevAA"), "prevAA")
                        .add(Projections.property("nextAA"), "nextAA")
                        .add(Projections.property("location"), "location")
                        .add(Projections.property("p.peptide_id"), "peptide.peptide_id")
                        .add(Projections.property("p.peptide_sequence"), "peptide.peptide_sequence")
                        .add(Projections.property("p.molecular_weight"), "peptide.molecular_weight")
                        .add(Projections.property("l.enzyme_Specificity"), "libraryModule.enzyme_Specificity"))
                .setResultTransformer(new AliasToBeanNestedResultTransformer(SpectrumProtein.class))
                .list());
        result.setGoTerms(session.createCriteria(GoTerms.class)
                .createAlias("proteins", "p")
                .add(Restrictions.eq("p.protein_acc", uniprotID))
                .setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE)
                .list());
        // Use first gene, first Wnsembl ID only
        Gene gene = protein.getGenes().iterator().next();
        result.setHPA((HPAProtein) session.createCriteria(HPAProtein.class)
                .add(Restrictions.eq("ensembl_id", gene.getEnsembl_id().split(", ")[0]))
                .setFetchMode("antibodies", FetchMode.JOIN)
                .setMaxResults(1)
                .uniqueResult());

        session.close();
        return result;
    }
}
