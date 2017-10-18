package service;

import java.util.Set;

import javax.ejb.Remote;

import entities.Discussion;
import entities.Notification;

@Remote
public interface DiscussionCreationRemote {

	public Set<Discussion> getAllDiscussionDemands();
	public void acceptDemand(Discussion d);
	public void declineDemand(Discussion d);
	public void Notif(Notification n);
}
