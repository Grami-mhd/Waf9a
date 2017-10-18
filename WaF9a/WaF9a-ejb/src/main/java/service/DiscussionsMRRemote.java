package service;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entities.Discussion;

@Remote
public interface DiscussionsMRRemote {
	public List<Discussion> getDiscussions() ;
}
