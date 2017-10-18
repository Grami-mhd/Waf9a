package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Expertise;
import entities.ExpertiseDemande;

/**
 * Session Bean implementation class ExpertiseDemandeEJB
 */
@Stateless
@LocalBean
public class ExpertiseDemandeEJB implements ExpertiseDemandeEJBRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
	
    public ExpertiseDemandeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Map<String, List<ExpertiseDemande>> getAllDemands() {
		
		List<ExpertiseDemande> l = em.createQuery("select e from ExpertiseDemande e where e.treated = 0 ").getResultList();
		Map<String, List<ExpertiseDemande>> m = new HashMap<String, List<ExpertiseDemande>>();
		
		for(ExpertiseDemande e : l){
			if(!m.containsKey(e.getExpertiseDemandeId().getName())){
				List<ExpertiseDemande> ls= em.createQuery("select e from ExpertiseDemande e where e.expertiseDemandeId.name = :nn").setParameter("nn", e.getExpertiseDemandeId().getName()).getResultList();
				m.put(e.getExpertiseDemandeId().getName(), ls);
			}
		}
		return m;
	}

	@Override
	public Map<String, List<Expertise>> getAllExpertises() {
		List<Expertise> l = em.createQuery("select e from Expertise e").getResultList();
		Map<String, List<Expertise>> m = new HashMap<String, List<Expertise>>();
		for(Expertise e : l){
			if(m.containsKey(e.getDomain())){
				m.get(e.getDomain()).add(e);
			}else{
				List<Expertise> ls= new ArrayList<Expertise>();
				ls.add(e);
				m.put(e.getDomain(), ls);
			}
		}
		
		return m;
	}

	@Override
	public int getNbr(String s) {
		return em.createQuery("select s from Search s where s.searchId.name = :nn").setParameter("nn", s).getResultList().size();
	}

	@Override
	public void declineExpertise(ExpertiseDemande d) {
		d.setTreated(true);	
		em.createQuery("update ExpertiseDemande set treated = 1 where expertiseDemandeId.name = :nam").setParameter("nam", d.getExpertiseDemandeId().getName()).executeUpdate();
		em.merge(d);
	}

	@Override
	public void acceptExpertise(Expertise d) {
		em.createQuery("delete from ExpertiseDemande d where d.expertiseDemandeId.name = :nam").setParameter("nam", d.getName()).executeUpdate();
		em.createQuery("delete from Search s where s.searchId.name = :nam").setParameter("nam", d.getName()).executeUpdate();		
		em.persist(d);
	}

	@Override
	public void addExpertiseDemande(ExpertiseDemande ed) {
		em.persist(ed);
	}

}
