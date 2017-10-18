package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Expertise;
import entities.Offer;
  

/**
 * Session Bean implementation class ExpertiseService
 */
@Stateless
@LocalBean
public class ExpertiseService implements ExpertiseServiceLocal {

	
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ExpertiseService() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public void addoffre(Offer offre) {
		em.persist(offre);
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
	public List<Offer> findProductsByCategory(Expertise expertise) {
		return em
				.createQuery("select p from Offer p where p.expertise=:c",
						Offer.class).setParameter("c", expertise)
				.getResultList();
	}

	@Override
	public void createExpertise(Expertise expertise) {
		em.persist(expertise);
		
	}

	@Override
	public void saveExpertise(Expertise expertise) {
		// TODO Auto-generated method stub
		em.merge(expertise);
	}

	@Override
	public void removeExpertise(Expertise expertise) {
		// TODO Auto-generated method stub
		em.remove(em.merge(expertise));
	}

	@Override
	public List<Expertise> findAllexpertises() {
		return em.createQuery("select o from Expertise o", Expertise.class)
				.getResultList();
	}

	@Override
	public Expertise findexpertisetById(int id) {
		return em.find(Expertise.class, id);
	}

 

}
