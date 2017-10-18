package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.ExpertiseDemande;

/**
 * Session Bean implementation class NotificationsAdminEJB
 */
@Stateless
@LocalBean
public class NotificationsAdminEJB implements NotificationsAdminEJBRemote {

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public NotificationsAdminEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMUser() {

		int n;
		n= Integer.parseInt(""+(Long) em.createQuery("select COUNT(u) from User u where u.premium = 1").getSingleResult());
		return n;
	}

	@Override
	public int getDiscs() {

		int n;
		n= Integer.parseInt(""+(Long) em.createQuery("select COUNT(c) from Discussion c where c.created = 0").getSingleResult());

		return n;
	}

	@Override
	public int getClaims() {
		int n;
		n= Integer.parseInt(""+(Long) em.createQuery("select COUNT(c) from Claim c where c.treated = 0").getSingleResult());

		return n;
	}

	@Override
	public int getExpertises() {
		int n;
		List<ExpertiseDemande> l = em.createQuery("select e from ExpertiseDemande e where e.treated = 0 ").getResultList();
		Map<String, List<ExpertiseDemande>> m = new HashMap<String, List<ExpertiseDemande>>();
		
		for(ExpertiseDemande e : l){
			if(!m.containsKey(e.getExpertiseDemandeId().getName())){
				m.put(e.getExpertiseDemandeId().getName(), null);
			}
		}
		return m.size();
	}

}
