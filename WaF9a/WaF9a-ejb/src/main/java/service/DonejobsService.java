package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.DoneJob;
import entities.Offer;

/**
 * Session Bean implementation class DonejobsService
 */
@Stateless
@LocalBean
public class DonejobsService implements DonejobsServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
    public DonejobsService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void adddonejob(DoneJob donejob) {
		// TODO Auto-generated method stub
		em.persist(donejob);
	}

	@Override
	public void removedonejob(DoneJob donejob) {
		em.merge(donejob);
		em.createQuery("delete from Donejob d where d.doneJobId = ?1 ").setParameter(1, donejob.getDoneJobId()).executeUpdate();
		
	
	}

	@Override
	public void savedonejob(DoneJob donejob) {
		em.merge(donejob);
	}

	@Override
	public List<DoneJob> findAllOffre() {
		return em.createQuery("select o from Donejob o", DoneJob.class)
				.getResultList();
	}

	@Override
	public List<DoneJob> MyDoneJobs() {
		// TODO Auto-generated method stub
		return null;
	}

}
