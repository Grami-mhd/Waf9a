package service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remote;

import entities.Expertise;
import entities.User;

@Remote
public interface UsersApproveRemote {
	
	public Map<String,Map<String,Set<User>>> getUsersApprovePerExpertise();

}
