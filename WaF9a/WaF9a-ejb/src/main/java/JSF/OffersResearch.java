package JSF;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Expertise;
import entities.Notification;
import entities.Offer;

/**
 * Session Bean implementation class OffersResearch
 */
@Stateless
@LocalBean
public class OffersResearch implements OffersResearchRemote, OffersResearchLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext
	EntityManager em;
	
    public OffersResearch() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Offer> getOffersByKey(String key) {
		
		List<Offer> l = new ArrayList<>();
		l= em.createQuery("SELECT o FROM Offer o WHERE o.text LIKE :key ",Offer.class).setParameter("key", "%"+key+"%").getResultList();
		System.out.println(l+"rffffffffffffffffffffffff"+key);
		
		return l;
	}

	@Override
	public List<Offer> SortOffersByDate() {
		List<Offer> l = new ArrayList<>();
		l= em.createQuery("SELECT o FROM Offer o ORDER BY o.date DESC",Offer.class).getResultList(); 
		System.out.println(l+"nnnnnnn");
		return l;
	}

	@Override
	public List<Offer> getOffersByOwner(String name) {
		List<Offer> l = new ArrayList<>();
		l= em.createQuery("SELECT o FROM Offer o WHERE (o.owner.firstName LIKE :name) OR (o.owner.lastName LIKE :name) ",Offer.class).setParameter("name", "%"+name+"%").setMaxResults(20).getResultList();
		System.out.println(l+"vvvvvvvvvvv"+name);
		
		return l;
	}

	@Override
	public List<Offer> getAllOffers() {
		List<Offer> l = new ArrayList<>();
		l= em.createQuery("SELECT o FROM Offer o ",Offer.class).setMaxResults(20).getResultList();
		
		System.out.println(l+"kkkkkk");
		return l;
	}

	@Override
	public Set<String> autoComplete() {
		
		List<Expertise> l = new ArrayList<>();
		Set<String> st = new HashSet<>();
		
		l= em.createQuery("SELECT e FROM Expertise e ",Expertise.class).getResultList();
		
		for (Expertise e : l) {
			st.add(e.getDomain());
			st.add(e.getName());
		}
		
		System.out.println(st+"5555");
		return st;
	}

}
