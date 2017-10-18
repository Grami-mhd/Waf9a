package service;

import javax.ejb.Remote;
import javax.jws.soap.SOAPBinding.Use;

import entities.User;

@Remote
public interface LoginRemote {

	public User checkUserName(String login);
	public boolean access(User u, String pw);
}
