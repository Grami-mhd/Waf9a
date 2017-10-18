package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Discussion;
import entities.Message;
import entities.MessageReport;
import entities.Notification;
import entities.Reply;
import entities.ReplyReport;
import entities.User;

/**
 * Session Bean implementation class PopupService
 */
@Stateless
public class PopupService implements PopupServiceRemote {

	@PersistenceContext
	EntityManager em ;
    /**
     * Default constructor. 
     */
    public PopupService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addnotify(Notification n) {
		// TODO Auto-generated method stub
		
		em.persist(n);
	}

	@Override
	public void deleteMsg(Message dm) {
		
	//em.createQuery("delete from Message m  where m.messageID ="+dm.getMessageID()).executeUpdate();

		//em.remove(entity);
		em.remove(em.merge(dm));
	}

	@Override
	public void deleteDiscu(Discussion dd) {
		// TODO Auto-generated method stub
		em.remove(em.merge(dd));
		
	}

	@Override
	public void blockacount(User bu) {
		// TODO Auto-generated method stub
		bu.setPremium(-1);
		em.merge(bu);
		

		
	}

	@Override
	public void deleteReplies(Reply dp) {
		// TODO Auto-generated method stub
		em.remove(em.merge(dp));
	}

	@Override
	public void update(MessageReport mr) {
		// TODO Auto-generated method stub
		em.merge(mr);
		
	}

	@Override
	public void updatereplies(ReplyReport rr) {
		// TODO Auto-generated method stub
		em.merge(rr);

	}

}
