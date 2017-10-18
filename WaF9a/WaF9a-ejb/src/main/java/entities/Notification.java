package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

import webSocket.UserServer;

/**
 * Entity implementation class for Entity: Notification
 *
 */
@Entity

public class Notification implements Serializable {

	   
	@Id
	@GeneratedValue
	private int notificationId;
	private boolean seen=false;
	private String text;
	private String type="";
	private Date date=Calendar.getInstance().getTime();
	
	@ManyToOne
	private User user;
	private static final long serialVersionUID = 1L;

	@PostPersist
	public void postPersist(){
		UserServer.sendToUser(user, "notif+");
	}
	@PostUpdate
	public void postUpdate(){
		UserServer.sendToUser(user, "notif-");
	}
	@PostRemove
	public void postRemove(){
		UserServer.sendToUser(user, "notif-");
	}
	
	
	
	public Notification() {
		super();
	}   
	public int getNotificationId() {
		return this.notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}   
	public boolean getSeen() {
		return this.seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + notificationId;
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
		Notification other = (Notification) obj;
		if (notificationId != other.notificationId)
			return false;
		return true;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
   
}
