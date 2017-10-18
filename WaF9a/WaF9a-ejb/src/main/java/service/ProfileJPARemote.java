package service;

import javax.ejb.Remote;

import entities.User;

@Remote
public interface ProfileJPARemote {

	public User fill(User u);
}
