package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Adress;
import entities.Comment;
import entities.DoneJob;
import entities.Expertise;
import entities.Offer;
import entities.User;


/**
 * Session Bean implementation class OfferService
 */
@Stateless
@LocalBean
public class OfferService implements OfferServiceLocal {

	
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public OfferService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addoffre(Offer offre) {
		for(Expertise e :offre.getExpertises()){
			e.getOffers().add(offre);
		}
		em.merge(offre);
	}

	@Override
	public void remove(Offer offre) {
		em.remove(em.merge(offre));
	}

	@Override
	public void saveOffre(Offer offre) {
		em.merge(offre);
	}

	@Override
	public List<Offer> findAllOffre() {
		return em.createQuery("select o from Offer o", Offer.class)
				.getResultList();
	}
	@Override
	public int offernumber(User u) {
		
		List<Offer> sd= em.createQuery("select c from Offer c where c.offer.id ="+u.getId()).getResultList();
		return sd.size();
	}

	@Override
	public void clseJob(Comment c) {

		DoneJob d = new DoneJob();
		Adress a = new Adress();
		a.setLatitude(c.getOffer().getAdress().getLatitude());
		a.setLongitude(c.getOffer().getAdress().getLongitude());
		a.setAddress(c.getOffer().getAdress().getAddress());
		em.persist(a);
		d.setAdress(a);
		d.setPrice(c.getPrice());
		d.setDelay(c.getDelay());
		d.setText(c.getOffer().getText());
		d.setWorker(c.getOwner());
		d.setBoss(c.getOffer().getOwner());
		em.merge(d);
		
		//		em.createQuery("delete  from Offer o where o.offerID = ?1").setParameter(1, c.getOffer().getOfferID()).executeUpdate();
	}

}
