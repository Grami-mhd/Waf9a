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

/**
 * Session Bean implementation class ReportedDiscussions
 */
@Stateless
@LocalBean
public class ReportedDiscussions implements ReportedDiscussionsRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
    
	public ReportedDiscussions() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Set<Discussion> getAllReportedDiscussions() {
		Set<Discussion> sd = new HashSet<Discussion>();
		List<DiscussionReport> ldr= em.createQuery("select c from DiscussionReport c where c.treated = 0 ").getResultList();
		if(ldr.isEmpty()){
			return sd;
		}else{
			for(DiscussionReport dr:ldr){
				sd.add(dr.getDiscussion());
			}
			return sd;
		}
	}

	@Override
	public void deleteDiscussion(Discussion d) {
		em.remove(em.merge(d));
	}

	@Override
	public void declineReportedDiscussion(DiscussionReport d) {
		em.merge(d);
	}

}
