package service;

import javax.ejb.Remote;

import entities.Discussion;
import entities.Message;
import entities.MessageReport;
import entities.Notification;
import entities.Reply;
import entities.ReplyReport;
import entities.User;

@Remote
public interface PopupServiceRemote {
	public void addnotify(Notification n);
	public void deleteMsg(Message dm);
	public void deleteReplies(Reply dp);
	public void deleteDiscu( Discussion dd); 
	public void blockacount(User bu );
	public void update(MessageReport mr);
	public void updatereplies(ReplyReport rr);


}
