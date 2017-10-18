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

/**
 * Session Bean implementation class OffersDemandsPerExpertise
 */
@Stateless
@LocalBean
public class OffersDemandsPerExpertise implements OffersDemandsPerExpertiseRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
	
    public OffersDemandsPerExpertise() {
        // TODO Auto-generated constructor stub
    }
    //for each domain accord list of expertise
    public Map<String, List<Expertise>> getEpertisePerDomain(){
    	
    	List<Expertise> l = em.createQuery("select e from Expertise e").getResultList();
		Map<String, List<Expertise>> m = new HashMap<String, List<Expertise>>();
		for(Expertise e : l){
			e.setOffers(em.createQuery("select f from Offer f join f.expertises e  where e.expertiseID = "+e.getExpertiseID()).getResultList());
			e.setUsers(em.createQuery("select u from User u join u.expertises e  where e.expertiseID = "+e.getExpertiseID()).getResultList());

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

}
