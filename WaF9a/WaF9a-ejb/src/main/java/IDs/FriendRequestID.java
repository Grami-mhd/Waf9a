package IDs;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class FriendRequestID implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int senderID;
	private int recieverID;
	
	
	
	public int getSenderID() {
		return senderID;
	}
	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}
	public int getRecieverID() {
		return recieverID;
	}
	public void setRecieverID(int recieverID) {
		this.recieverID = recieverID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + recieverID;
		result = prime * result + senderID;
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
		FriendRequestID other = (FriendRequestID) obj;
		if (recieverID != other.recieverID)
			return false;
		if (senderID != other.senderID)
			return false;
		return true;
	}
	
	
	
	
	
	
}
