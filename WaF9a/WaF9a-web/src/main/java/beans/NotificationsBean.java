package beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import JSF.NotificationsLocal;
import entities.FriendRequest;
import entities.Notification;
import entities.ProposedJob;
import entities.User;
import service.UserManageRemote;

@ManagedBean
@SessionScoped
public class NotificationsBean {
	//
	@ManagedProperty(value = "#{autentificationBean}")
	private autentificationBean atb;

	@EJB
	private UserManageRemote umr;
	// private Session session = null;
	private int nbr;
	@EJB
	private NotificationsLocal notificationsLocal;

	private List<Notification> notifications = new ArrayList<>();
	private List<Notification> allNitifs = new ArrayList<>();
	

	//friends

	public void acceptDemande(FriendRequest fr){
		fr.getReceiver().getFriendsRequests().remove(fr);
		notificationsLocal.acceptFriendRequest(fr);
		
	}
	public void DeclineDemande(FriendRequest fr){
		fr.getReceiver().getFriendsRequests().remove(fr);
		notificationsLocal.declineFriendRequest(fr);
		
	}
	
	public String sendToProfile(User user){
		return "";
	}
	//job offers
	public void acceptJobOffer(ProposedJob pj){
		atb.getUser().getJobOffers().remove(pj);
		notificationsLocal.acceptJobOffer(pj);
	}
	public void DeclineJobOffer(ProposedJob pj){
		atb.getUser().getJobOffers().remove(pj);
		notificationsLocal.declineJobOffer(pj);
	}
	// Notifications
	public void getallNotifications() {

		System.out.println("this method is now called time ");
		notifications = notificationsLocal.getNotifications(atb.getUser());
		for (Notification n : notifications) {
			n.setSeen(!n.getSeen());
		}
		UpdateNotificationsNumber();
		System.out.println(nbr);
	}

	public String findAllNotifs(){
		allNitifs=notificationsLocal.getAllNotifications(atb.getUser());
		
		for (Notification n : allNitifs) {
			n.setSeen(!n.getSeen());
		}
		return "/pages/notifications?faces-redirect=true";
	}
	public void UpdateNotificationsNumber() {
		this.nbr = notificationsLocal.getNumberOfNotSeenNotifications(atb.getUser());

	}

	public String Seen(Notification n) {
		notificationsLocal.setSeen(n);
		getallNotifications();
		return "";
	}
	public String seenNot(Notification n){
		int nb=allNitifs.indexOf(n);
		notificationsLocal.setSeen(n);
		allNitifs.get(nb).setSeen(false);
		
		return "";
	}
	public String deleteNotif(Notification n){
		notificationsLocal.deleteNotif(n);
		allNitifs.remove(n);
		return "";
	}
	public String seeAll(){
		for(Notification n : allNitifs){
			notificationsLocal.setSeen(n);
			n.setSeen(false);
		}
		return "";
	}
	// Getters and setters
	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public autentificationBean getAtb() {
		return atb;
	}

	public void setAtb(autentificationBean atb) {
		this.atb = atb;
	}

	public int getNbr() {
		return nbr;
	}
	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

	public List<Notification> getAllNitifs() {
		return allNitifs;
	}

	public void setAllNitifs(List<Notification> allNitifs) {
		this.allNitifs = allNitifs;
	}

	//test
	public String sendNotif() {
		Notification n = new Notification();
		n.setText("nnnotif test");
		n.setUser(atb.getUser());
		umr.addNotif(n);
		return "";
	}
	public void sendJobProposel(){
		ProposedJob p = new ProposedJob();
		p.setDuration(15);
		p.setPrice(1000);
		p.setReciever(atb.getUser());
		p.setSender(atb.getUser());
		notificationsLocal.sendtest(p);
		
	}
	
}
