package JSF;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;


import entities.Expertise;
import entities.ExpertiseDemande;
import entities.User;

@Local
public interface profileJLocal {

	public Set<User> getAllFriendsByKey(String key, User u);
	public void addExpertiseDemande(ExpertiseDemande expD) ;
	public void deleteFriend(User u);
	public User findUser(int id);
	public void upDateProfile(User usrr);
}
