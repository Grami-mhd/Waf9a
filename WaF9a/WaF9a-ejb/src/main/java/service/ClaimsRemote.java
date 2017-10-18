package service;






import javax.ejb.Remote;

import entities.Claim;
import entities.DoneJob;
import entities.Notification;
import entities.User;


@Remote
public interface ClaimsRemote {
	public Claim getNextNotTreatedClaim();
	public void claimIsTreated(Claim c);
	
	public int claimNumber(User u);
	public int claimaboutNumber();
	public int numberOffers(User u);
	public int numberDoneJobs(User u);
	
	public void blockUser(User u);
	public void ajoutNotif (Notification n);
	public void decreasePrice(DoneJob x);
	
	/****************Client*********/
	
	public void ajoutClaim(Claim c);
	public java.util.List<Claim> historyClaim(User u);
	public java.util.List<DoneJob> historyDonejob(User u);
	

}
