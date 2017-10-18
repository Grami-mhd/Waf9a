package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Claim;
import entities.DoneJob;
import entities.Notification;
import entities.User;


/**
 * Session Bean implementation class Claims
 */
@Stateless
@LocalBean
public class Claims implements ClaimsRemote {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext
	private EntityManager em;
	
    public Claims() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Claim getNextNotTreatedClaim() {
		 
		List<Claim> sd= em.createQuery("select c from Claim c where c.treated = 0").getResultList();
		if(sd.isEmpty())
			return null;
		return sd.get(0);
	}

	@Override
	public void claimIsTreated(Claim c) {
		em.merge(c);
		
	}

	@Override
	public int claimNumber(User u) {
		
		List<Claim> sd= em.createQuery("select c from Claim c where c.claimer.id ="+u.getId()).getResultList();
		return sd.size();
	}

	@Override
	public int claimaboutNumber() {
		
		//List<Claim> sd= em.createQuery("select c from Claim c ").getResultList();
		//DoneJob d= em.createQuery("select d from DoneJob d where d.doneJobId="+sd.get(0).getJob().getDoneJobId()).getResultList();
		//if(sd.get(0).getClaimer()==d.getBoss())
		//if(sd.get(0).getClaimer()==sd1.get(0)
		
		
		//if(sd.get(0).getClaimer()==sd.get(0).getJob().getBoss()){
			//n++;
		//}
		return 0;
	}

	@Override
	public int numberOffers(User u) {
		
		List<Claim> sd=em.createQuery("select d from DoneJob d where d.boss.id ="+u.getId(),Claim.class).getResultList();
		return sd.size();
		
	}

	@Override
	public int numberDoneJobs(User u) {
		
		List<Claim> sd=em.createQuery("select d from DoneJob d where d.worker.id ="+u.getId(),Claim.class).getResultList();
		return sd.size();
		
	}

	@Override
	public void blockUser(User u) {
		em.merge(u);
	}

	
	public void ajoutNotif(Notification n) {
		em.persist(n);
		
	}

	@Override
	public void decreasePrice(DoneJob x) {
		em.merge(x);
		
	}

	@Override
	public void ajoutClaim(Claim c) {
		em.persist(c);
		
	}

	@Override
	public List<Claim> historyClaim(User u) {
		
		List<Claim> sd=em.createQuery("select c from Claim c where c.claimer.id ="+u.getId(),Claim.class).getResultList();
		System.out.println(sd);
		return sd;
	}

	@Override
	public List<DoneJob> historyDonejob(User u) {
		
		List<DoneJob> sd=em.createQuery("select d from DoneJob d where d.worker.id ="+u.getId(),DoneJob.class).getResultList();
		System.out.println(sd);
		return sd;
		
	}

	

	

}
