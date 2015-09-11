package org.copakb.server.dao;

import org.apache.commons.lang3.text.WordUtils;
import org.copakb.server.dao.model.*;
import org.copakb.server.dao.model.service.ReferencePeptideBundle;
import org.copakb.server.dao.model.service.ReferenceProteinBundle;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        result.setSpectrumProteins(session.createCriteria(SpectrumProtein.class)
                .add(Restrictions.eq("protein.protein_acc", uniprotID))
                .createAlias("peptide", "p")
                .createAlias("libraryModule", "l")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("prevAA"), "prevAA")
                        .add(Projections.property("nextAA"), "nextAA")
                        .add(Projections.property("location"), "location")
                        .add(Projections.property("p.peptide_id"), "peptide.peptide_id")
                        .add(Projections.property("p.peptide_sequence"), "peptide.peptide_sequence")
                        .add(Projections.property("p.molecular_weight"), "peptide.molecular_weight")
                        .add(Projections.property("l.enzyme_Specificity"), "libraryModule.enzyme_Specificity")))
                .setResultTransformer(new AliasToBeanNestedResultTransformer(SpectrumProtein.class))
                .list());
        result.setGoTerms(session.createCriteria(GoTerms.class)
                .createAlias("proteins", "p")
                .add(Restrictions.eq("p.protein_acc", uniprotID))
                .setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE)
                .list());

        if (protein.getGene() != null && !protein.getGene().isEmpty()) {
            Gene2 gene = protein.getGene().iterator().next(); // Use first gene only
            result.setDiseases(session.createCriteria(Disease.class)
                    .createAlias("genes", "g")
                    .add(Restrictions.eq("g.ensembl_id", gene.getEnsembl_id()))
                    .setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE)
                    .list());
            result.setHPA((HPAProtein) session.createCriteria(HPAProtein.class)
                    .add(Restrictions.eq("ensembl_id", gene.getEnsembl_id()))
                    .setFetchMode("antibodies", FetchMode.JOIN)
                    .setMaxResults(1)
                    .uniqueResult());
        } else {
            result.setDiseases(new ArrayList<Disease>());
            result.setHPA(null);
        }

        session.close();
        return result;
    }

    @Override
    public ReferencePeptideBundle getReferencePeptideBundle(Peptide peptide) {
        Session session = sessionFactory.openSession();

        ReferencePeptideBundle result = new ReferencePeptideBundle();
        result.setPeptideID(peptide.getPeptide_id());
        result.setSpectrumProteins(session.createCriteria(SpectrumProtein.class)
                .add(Restrictions.eq("peptide.peptide_id", peptide.getPeptide_id()))
                .createAlias("protein", "p")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("location"), "location")
                        .add(Projections.property("p.protein_acc"), "protein.protein_acc")
                        .add(Projections.property("p.protein_name"), "protein.protein_name")
                        .add(Projections.property("p.species"), "protein.species")
                        .add(Projections.property("p.chromosome"), "protein.chromosome")
                        .add(Projections.property("spectrum"), "spectrum")))
                .setResultTransformer(new AliasToBeanNestedResultTransformer(SpectrumProtein.class))
                .list());

        session.close();
        return result;
    }

    @Override
    public List<String> getLibModNames() {
        Session session = sessionFactory.openSession();

        Map<Integer, String> modules = new TreeMap<>(); // TreeMap sorts integer keys by default
        for (LibraryModule module : (List<LibraryModule>) session.createCriteria(LibraryModule.class)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .list()) {
            modules.put(module.getMod_id(),
                    module.getMod_id() + ". " + WordUtils.capitalize(module.getLib_mod().replaceAll("_", " ")));
        }

        return new ArrayList<>(modules.values());
    }
}
