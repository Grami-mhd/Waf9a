package JSF;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Comment;
import entities.Offer;
import entities.User;

/**
 * Session Bean implementation class OfferView
 */
@Stateless
@LocalBean
public class OfferView implements OfferViewLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
    public OfferView() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public List<Offer> getAllOffers() {
		List<Offer>of=new ArrayList<Offer>();
		 of=em.createQuery("SELECT o from Offer o ", Offer.class).getResultList();
		 return of;
	}
	@Override
	public void updateListCom(Offer o) {
		em.merge(o);
		
	}

	

    
}
