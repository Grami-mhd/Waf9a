package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Comment;
import entities.CommentReport;
import entities.Notification;
import entities.Offer;
import entities.User;

/**
 * Session Bean implementation class ReportedComments
 */
@Stateless
@LocalBean
public class ReportedComments implements ReportedCommentsRemote {

	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ReportedComments() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Offer> getAllReportedoffers() {
		Set<Offer> off = new HashSet<Offer>();
		List<CommentReport> lcr= em.createQuery("select c from CommentReport c where c.treated = 0 ").getResultList();
		if(lcr.isEmpty()){
			return new ArrayList<Offer>();
		}else{
			for(CommentReport cr:lcr){
				off.add(cr.getComment().getOffer());
			}
			List<Offer> aff = new ArrayList<Offer>();
			for(Offer o : off)
				aff.add(o);
			return aff;
		}
	}

	@Override
	public void declineComment(CommentReport c) {
		em.merge(c);
	}

	@Override
	public void deleteComment(Comment c) {
		em.remove(em.merge(c));
	}

	@Override
	public void blockCommentOwner(User u) {
		em.merge(u);	
	}

	@Override
	public void addNotify(Notification n) {
		em.persist(n);
	}

}
