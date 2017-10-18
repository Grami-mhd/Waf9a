package service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Discussion;
import entities.DiscussionReport;
import entities.Notification;

/**
 * Session Bean implementation class DiscussionCreation
 */
@Stateless
@LocalBean
public class DiscussionCreation implements DiscussionCreationRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
	
    public DiscussionCreation() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Set<Discussion> getAllDiscussionDemands() {
		
		Set<Discussion> sd = new HashSet<Discussion>();
		List<Discussion> ld= em.createQuery("select c from Discussion c where c.created = 0 ").getResultList();
		if(ld.isEmpty()){
			return sd;
		}else{
			for(Discussion d:ld){
				sd.add(d);
			}
			return sd;
		}
	}

	

	@Override
	public void acceptDemand(Discussion d) {
		
		d.setCreated(true);
		em.merge(d);
		
	}

	@Override
	public void declineDemand(Discussion d) {
		
		em.remove(em.merge(d));
	
	}

	

	@Override
	public void Notif(Notification n) {
		
		em.persist(n);
	}

	
	
	

}
