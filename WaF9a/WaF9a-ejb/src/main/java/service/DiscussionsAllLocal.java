package service;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import entities.Discussion;
import entities.DiscussionReport;
import entities.Message;
import entities.MessageReport;

@Local
public interface DiscussionsAllLocal {
	public Map<String, List<Discussion>> getDiscussions() ;
	public List<Discussion> searchDiscussion(String dis);
	public void saveDisRep(DiscussionReport d);
	public void saveMsgRep(MessageReport m);
	public void addMesaage(Message m);
}
