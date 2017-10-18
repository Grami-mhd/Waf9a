package JSF;

import java.util.List;

import javax.ejb.Local;

import entities.FriendRequest;
import entities.Notification;
import entities.ProposedJob;
import entities.User;

@Local
public interface NotificationsLocal {

	public List<Notification> getNotifications(User u);
	public void setSeen(Notification n);
	public int getNumberOfNotSeenNotifications(User u);
	public List<Notification> getAllNotifications(User u);
	public void deleteNotif(Notification n);
	public void acceptFriendRequest(FriendRequest fr);
	public void declineFriendRequest(FriendRequest fr);
	public void acceptJobOffer(ProposedJob pj);
	public void declineJobOffer(ProposedJob pj);
	public User update(User u);
	public void sendtest(Object p);
}
