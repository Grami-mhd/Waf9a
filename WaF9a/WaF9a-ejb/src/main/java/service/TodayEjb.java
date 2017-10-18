package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.DoneJob;
import entities.Offer;
import entities.User;

/**
 * Session Bean implementation class TodayEjb
 */
@Stateless
@LocalBean
public class TodayEjb implements TodayEjbRemote {

	@PersistenceContext
    private EntityManager em;
	/**
     * Default constructor. 
     */
    public TodayEjb() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<User> getNewUsers() {
		return  em.createQuery("select u from User u where u.signUpDate >= CURRENT_DATE").getResultList();
		
		
	}

	@Override
	public List<Offer> getNewOffers() {
		return  em.createQuery("select u from Offer u where u.date >= CURRENT_DATE").getResultList();

	}

	@Override
	public List<DoneJob> getNewDoneJobs() {
		return  em.createQuery("select u from DoneJob u where u.date >= CURRENT_DATE").getResultList();

	}

}
