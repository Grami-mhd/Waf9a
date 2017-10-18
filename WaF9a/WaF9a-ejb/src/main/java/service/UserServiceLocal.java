package service;

import java.util.List;

import javax.ejb.Local;

import entities.User;



@Local
public interface UserServiceLocal {
	void createUser(User user);
	List<User> findAllUsers();
	User authenticate(String userName, String password);
	boolean loginExists(String userName);
}
