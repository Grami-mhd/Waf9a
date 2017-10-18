package JSF;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import entities.User;

@Local
public interface UsersLocal {
	
	public List<User> getAllUsers();
	public Map<User, String> sortUsersByDoneJobs();
	public List<User> serachbyname(String key);
	public List<User> sortBySignUpDate();
	public Map<User, String> sortUsersByRate();
}
