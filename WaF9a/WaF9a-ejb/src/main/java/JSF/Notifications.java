package JSF;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.DoneJob;
import entities.FriendRequest;
import entities.Notification;
import entities.ProposedJob;
import entities.User;

/**
 * Session Bean implementation class Notifications
 */
@Stateful
@LocalBean
public class Notifications implements NotificationsRemote, NotificationsLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
	
    public Notifications() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Notification> getNotifications(User u) {
		List<Notification> l = new ArrayList<>();
		l= em.createQuery("select d from Notification d where d.user.id  = ?1 ORDER BY d.date DESC",Notification.class).setParameter(1, u.getId()).setMaxResults(8).getResultList();
		return l;
	}

	@Override
	public void setSeen(Notification n) {
		n.setSeen(true);
		em.merge(n);
		
	}

	@Override
	public int getNumberOfNotSeenNotifications(User u) {
		
		 int n =em.createQuery("select d from Notification d where d.user.id  = ?1 AND d.seen = 0",Notification.class).setParameter(1, u.getId()).getResultList().size();
		 return n;
	}

	@Override
	public List<Notification> getAllNotifications(User u) {
		List<Notification> l = new ArrayList<>();
		l= em.createQuery("select d from Notification d where d.user.id  = ?1 ORDER BY d.date DESC",Notification.class).setParameter(1, u.getId()).getResultList();
		return l;
	}

	@Override
	public void deleteNotif(Notification n) {
		em.remove(em.merge(n));
		
	}

	@Override
	public void acceptFriendRequest(FriendRequest fr) {

		User sender =fr.getSender();
		User reciever =fr.getReceiver();
		sender.setFriends(new HashSet<>( em.createQuery("SELECT u FROM User u WHERE ?1 MEMBER OF u.friends",User.class).setParameter(1, sender).getResultList()));
		reciever.setFriends( new HashSet<>(em.createQuery("SELECT u FROM User u WHERE ?1 MEMBER OF u.friends",User.class).setParameter(1, reciever).getResultList()));
	
		sender.getFriends().add(reciever);
		reciever.getFriends().add(sender);
		em.merge(sender);
		em.merge(reciever);
		System.out.println(sender.getFriends()+" ffffffffffffffffffffff"+reciever.getFriends());
		this.sendNotification(sender, "your Contacting demande has been accepted", "fr"+reciever.getId());
		em.remove(em.merge(fr));
	}

	@Override
	public void declineFriendRequest(FriendRequest fr) {
		this.sendNotification(fr.getSender(),
				"your contacting request to "+fr.getReceiver().getFirstName() 
				+" "+fr.getReceiver().getLastName()+" has been declined ", "");
		
		em.createQuery("delete from FriendRequest f where f.friendRequestID.senderID = ?1 and f.friendRequestID.recieverID = ?2")
				.setParameter(1, fr.getSender().getId()).setParameter(2, fr.getReceiver().getId()).executeUpdate();
	}
	
	private void sendNotification(User u,String txt,String type){
		Notification n = new Notification();
		n.setText(txt);
		n.setUser(u);
		n.setType(type);
		em.merge(n);
	}

	@Override
	public void acceptJobOffer(ProposedJob pj) {
		DoneJob d = new DoneJob();
		d.setBoss(pj.getSender());
		d.setWorker(pj.getReciever());
		d.setPrice(pj.getPrice());
		d.setDelay(pj.getDuration());
		d.setText(pj.getText());
		this.sendNotification(pj.getSender(), "your job proposal "+pj.getText()+" to Mr."+pj.getReciever().getUserName()+" has been accepted", "pj"+pj.getReciever().getId());
		em.merge(d);
		em.remove(em.merge(pj));
		
	}

	@Override
	public void declineJobOffer(ProposedJob pj) {
		this.sendNotification(pj.getSender(), "your job proposal "+pj.getText()+" to Mr."+pj.getReciever().getUserName()+" has been declined", "");
		em.remove(em.merge(pj));
	}

	@Override
	public User update(User u) {
		
		em.refresh(u);
		
		return u;
		
	}

	@Override
	public void sendtest(Object p) {
		em.persist(p);
		
	}

}
