package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Discussion;
import entities.MessageReport;
import entities.ReplyReport;
 


/**
 * Session Bean implementation class Discussions
 */
@Stateless
@LocalBean
public class DiscussionsMR implements DiscussionsMRRemote {

    /**
     * Default constructor. 
     */
	
	
	@PersistenceContext
	private EntityManager em;
	
	
    public DiscussionsMR() {
        // TODO Auto-generated constructor stub
    }
	public List<Discussion> getDiscussions() {
		List<Discussion> sd = new ArrayList<Discussion>();
	
		List<ReplyReport> ldr= em.createQuery("select c from ReplyReport c where c.treated = 0 ").getResultList();
	
		List<MessageReport> lmr= em.createQuery("select m from MessageReport m where m.treated = 0 ").getResultList();
		 
	 Set<Discussion> setDis = new HashSet<>();
	 for(ReplyReport rr: ldr)
	 {
		 setDis.add(rr.getReply().getMessage().getDiscussion());
	
	}
	 for(MessageReport mr: lmr)
	 {
		 setDis.add(mr.getMessage().getDiscussion());
	
	}
	 for (Discussion discussion : setDis) {
		sd.add(discussion);		
	 }
	 return sd;
		
			}

}
