package service;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.DoneJob;
import entities.Notification;
import entities.Offer;
import entities.User;

/**
 * Session Bean implementation class UserManage
 */
@Stateless
@LocalBean
public class UserManage implements UserManageRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em; 
	
    public UserManage() {
        // TODO Auto-generated constructor stub
    }


	@Override
	public List<User> getPremiumDemand() {
		
		List<User> up= em.createQuery("select c from User c where c.premium = 1 ").getResultList();
			return up;
		}


	@Override
	public void updatePerson(User p) {
		em.merge(p);
		
	}


	@Override
	public List<User> getSearshedUsers(String keyWord) {
		List<User> uS= em.createQuery("select c from User c where c.userName LIKE :pname ").setParameter("pname",  "%" + keyWord + "%").getResultList();
		return uS;
		//return em.createQuery("select c from client c where c.nom=pname",client.class).setParameter(pname, name).getSingleResult();
	}


	@Override
	public List<User> getActiveUsers() {
		List<User> ul= em.createQuery("select c from User c where c.premium >= 0").getResultList();
		return ul;
	}


	@Override
	public List<Offer> getOffers() {
		List<Offer> of= em.createQuery("select o from Offer o where o.date >= CURRENT_DATE").getResultList();
		return of;
	}


	@Override
	public List<DoneJob> getDoneJobs() {
		List<DoneJob> dj= em.createQuery("select d from DoneJob d where d.price >= 0").getResultList();
		return dj;
	}


	@Override
	public Notification addNotif(Notification not) {
		em.persist(not);
		return null;
	}



	

}
