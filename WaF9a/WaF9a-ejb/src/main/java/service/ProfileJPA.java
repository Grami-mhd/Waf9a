package service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.resource.spi.AuthenticationMechanism;


import entities.Discussion;
import entities.ProposedJob;
import entities.User;

/**
 * Session Bean implementation class ProfileJPA
 */
@Stateless
@LocalBean
public class ProfileJPA implements ProfileJPARemote {

	
	@PersistenceContext
	private EntityManager em;
	
	
    /**
     * Default constructor. 
     */
    public ProfileJPA() {
    	
    }
	@Override
	public User fill(User u) {
		u=em.find(User.class, u.getId());
		u.setDiscussions(em.createQuery("select d from Discussion d where d.owner.id ="+u.getId()).getResultList());
		u.setOfferedJobs(em.createQuery("select d from DoneJob d where d.boss.id ="+u.getId()).getResultList());
		u.setOffers(em.createQuery("select d from Offer d where d.owner.id ="+u.getId()).getResultList());
		u.setRates(em.createQuery("select d from Rate d where d.rated.id ="+u.getId()).getResultList());
		u.setDoneJobs(em.createQuery("select d from DoneJob d where d.worker.id ="+u.getId()).getResultList());
		u.setClaims(em.createQuery("select d from Claim d where d.claimer.id ="+u.getId()).getResultList());
		u.setReportedReplies(em.createQuery("select d from ReplyReport d where d.replyReportID.userId ="+u.getId()).getResultList());
		u.setReportedComments(em.createQuery("select d from CommentReport d where d.CommentReportID.userId ="+u.getId()).getResultList());
		u.setReportedDiscussions(em.createQuery("select d from DiscussionReport d where d.discussionReportID.userId ="+u.getId()).getResultList());
		u.setReportedMessages(em.createQuery("select d from MessageReport d where d.reporter.id ="+u.getId()).getResultList());
		u.setApproved(em.createQuery("select d from Approve d where d.Approved.id ="+u.getId()).getResultList());
		u.setMessages(em.createQuery("select d from Message d where d.owner.id ="+u.getId()).getResultList());
		u.setComments(em.createQuery("select d from Comment d where d.owner.id ="+u.getId()).getResultList());
		u.setReplies(em.createQuery("select d from Reply d where d.owner.id ="+u.getId()).getResultList());	
		u.setJobOffers(em.createQuery("select d from ProposedJob d where d.reciever.id = ?1",ProposedJob.class).setParameter(1, u.getId()).getResultList());
		u.setFriends( new HashSet<>(em.createQuery("SELECT u FROM User u WHERE ?1 MEMBER OF u.friends",User.class).setParameter(1, u).getResultList()));
		return u;
		
		
	}

}
