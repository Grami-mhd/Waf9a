package JSF;

import javax.ejb.Remote;

import entities.Claim;
import entities.FriendRequest;
import entities.ProposedJob;

@Remote
public interface ViewUserRemote {

	public void sendFriendRequest(FriendRequest f);
	public void proposejob(ProposedJob pj);
	
}
