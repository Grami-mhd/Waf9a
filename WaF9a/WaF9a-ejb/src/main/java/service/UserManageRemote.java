package service;

import java.util.List;

import javax.ejb.Remote;

import entities.DoneJob;
import entities.Notification;
import entities.Offer;
import entities.User;


@Remote
public interface UserManageRemote {
	public List<User> getPremiumDemand();
	public void updatePerson(User p);
	public List<User> getSearshedUsers(String keyWord );
	
	public List<User> getActiveUsers();
	public List<Offer> getOffers();
	public List<DoneJob> getDoneJobs();
	
	public Notification addNotif(Notification not);

}
