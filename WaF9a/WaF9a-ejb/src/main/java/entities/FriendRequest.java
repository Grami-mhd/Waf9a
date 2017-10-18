package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

import IDs.FriendRequestID;
import webSocket.UserServer;

/**
 * Entity implementation class for Entity: FriendRequest
 *
 */
@Entity

public class FriendRequest implements Serializable {

	   
	@EmbeddedId
	private FriendRequestID friendRequestID;
	private boolean isSeen=false;
	private Date date= Calendar.getInstance().getTime();
	private String text="I want to add you to my network :D";

	@ManyToOne
	private User sender;
	
	@ManyToOne
	private User receiver;
	
	private static final long serialVersionUID = 1L;

	
	@PostPersist
	public void postPersist(){
		UserServer.sendToUser(receiver, "friend+");
	}
	@PostUpdate
	public void postUpdate(){
		UserServer.sendToUser(receiver, "friend+");
	}
	@PostRemove
	public void postRemove(){
		UserServer.sendToUser(receiver, "friend-");
	}

	
	
	public FriendRequest() {
		super();
		this.friendRequestID= new FriendRequestID();
	}   
	public FriendRequestID getFriendRequestID() {
		return this.friendRequestID;
	}

	public void setFriendRequestID(FriendRequestID friendRequestID) {
		this.friendRequestID = friendRequestID;
	}  
	 
	public boolean getIsSeen() {
		return this.isSeen;
	}

	public void setIsSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
		this.friendRequestID.setSenderID(sender.getId());
	}
	public User getReceiver() {
		return receiver;
		
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
		this.friendRequestID.setRecieverID(receiver.getId());
	}
	public void setSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friendRequestID == null) ? 0 : friendRequestID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendRequest other = (FriendRequest) obj;
		if (friendRequestID == null) {
			if (other.friendRequestID != null)
				return false;
		} else if (!friendRequestID.equals(other.friendRequestID))
			return false;
		return true;
	}
   
}
