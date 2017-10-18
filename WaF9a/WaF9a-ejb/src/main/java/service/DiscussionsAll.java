package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Discussion;
import entities.DiscussionReport;
import entities.Message;
import entities.MessageReport;

/**
 * Session Bean implementation class DiscussionsAll
 */
@Stateless
@LocalBean
public class DiscussionsAll implements DiscussionsAllLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public DiscussionsAll() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Map<String, List<Discussion>> getDiscussions() {
		
		Map<String, List<Discussion>> ad = new HashMap();
		List<Discussion> ld= em.createQuery("select c from Discussion c where c.created = 1 ").getResultList();
		 for(Discussion d : ld)
		 {
			 if (!ad.containsKey(d.getCategory())){
				 List<Discussion> l = new ArrayList<Discussion>();
				 l.add(d);
				 ad.put(d.getCategory(), l);
			 }
			 else{
				 ad.get(d.getCategory()).add(d);
			 }
		}
		 return ad;
	}

	@Override
	public List<Discussion> searchDiscussion(String dis) {
		List<Discussion> d = new ArrayList<>();
		d=em.createQuery("select c from Discussion c where c.topic LIKE :key ",Discussion.class).setParameter("key", "%"+dis+"%").setMaxResults(20).getResultList();
		System.out.println("disscuAll meth service  "+dis);
		return d;
	}

	@Override
	public void saveMsgRep(MessageReport m) {
		em.merge(m);
	}

	@Override
	public void saveDisRep(DiscussionReport d) {
		em.merge(d);
	}

	@Override
	public void addMesaage(Message m) {
		em.merge(m);
	}

}