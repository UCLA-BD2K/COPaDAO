package org.copakb.server.dao;

import org.copakb.server.dao.model.*;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by vincekyi on 5/2/15.
 */
public class ProteinDAOImpl implements ProteinDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProteinCurrent> list() throws HibernateException {
        Session session = this.sessionFactory.openSession();
        List<ProteinCurrent> proteinList = session.createCriteria(ProteinCurrent.class).list();
        session.close();
        return proteinList;
    }

    public void addProteinCurrent(ProteinCurrent p) throws HibernateException{
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(p);
        tx.commit();
        session.close();

    }

    public int addSpecies(Species sp) throws HibernateException{
            int result = -1;
            // look for relevant species
            Species existingSpecies = searchSpecies(sp.getSpecies_name()); // add param
            if(existingSpecies!=null)
                return existingSpecies.getSpecies_id();

        if(sp.getProteinCurrents() == null) {
            return -1;
        }
        // if no existing species, then add in according to species
        Iterator iterator = sp.getProteinCurrents().iterator(); //iterate through the Species' Set of assoc. ProteinCurrents

        // check values
        while (iterator.hasNext()){
            ProteinCurrent temp = (ProteinCurrent) iterator.next(); // current ProteinCurrent object being iterated over
            ProteinCurrent prot = this.searchByID(temp.getProtein_acc());
            if(prot==null) {
                this.addProteinCurrent(temp);
                //add protein to the associated species list
                //temp.setProtein_acc(temp.getProtein_acc()); // how to set protein accession? uniprot?
                Set<ProteinCurrent> tempSet = sp.getProteinCurrents();
                tempSet.add(temp);
            }else{
                //temp.setProtein_acc(protein_id);
            }
        }


            Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            result = (int)session.save(sp);

            tx.commit();
            session.close();

            return result;
    }

    public Species searchSpecies(String name) {
        Session session = this.sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Species.class);

        Transaction tx = session.beginTransaction();
        try {
            Criterion nameRestriction = Restrictions.eq("species_name", name);

            criteria.add(Restrictions.and(nameRestriction));
            List<Species> results = criteria.list();
            tx.commit();
            if(results.isEmpty())
                return null;
            return results.get(0);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }

    public ProteinCurrent searchByID(String protein_acc){
        Session session = this.sessionFactory.openSession();
        ProteinCurrent protein = null;

        Transaction tx = session.beginTransaction();
        try {
            protein = (ProteinCurrent)session.get(ProteinCurrent.class, protein_acc);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
        return protein;
    }

    public ProteinCurrent searchByName(String protein_name){
        Session session = this.sessionFactory.openSession();
        ProteinCurrent protein = null;

        Transaction tx = session.beginTransaction();
        try {
            protein = (ProteinCurrent)session.get(ProteinCurrent.class, protein_name);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
        return protein;
    }

    public ProteinCurrent searchByRef(String ref_kb_id){
        Session session = this.sessionFactory.openSession();
        ProteinCurrent protein = null;

        Transaction tx = session.beginTransaction();
        try {
            protein = (ProteinCurrent)session.get(ProteinCurrent.class, ref_kb_id);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
        return protein;
    }

    public ProteinCurrent searchByEnsg(String ENSG_ID){
        Session session = this.sessionFactory.openSession();
        ProteinCurrent protein = null;

        Transaction tx = session.beginTransaction();
        try {
            protein = (ProteinCurrent)session.get(ProteinCurrent.class, ENSG_ID);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
        return protein;
    }



    public ProteinCurrent getProteinWithGoTerms(String uniprotID){
        Session session = this.sessionFactory.openSession();
        ProteinCurrent protein = null;

        Transaction tx = session.beginTransaction();
        try {
            protein = (ProteinCurrent)session.get(ProteinCurrent.class, uniprotID);
            if(protein == null) return null;
            Hibernate.initialize(protein.getGoTerms());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
        return protein;
    }

    public ProteinCurrent getProteinWithGenes(String uniprotID){
        Session session = this.sessionFactory.openSession();
        ProteinCurrent protein = null;

        Transaction tx = session.beginTransaction();
        try {
            protein = (ProteinCurrent)session.get(ProteinCurrent.class, uniprotID);
            if(protein == null) return null;
            Hibernate.initialize(protein.getGenes());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
        return protein;
    }

}
