package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import webSocket.UserServer;

/**
 * Entity implementation class for Entity: ProposedJob
 *
 */
@Entity

public class ProposedJob implements Serializable {

	   
	@Id
	@GeneratedValue
	private int id;
	private String text;
	private int price;
	private int duration;
	private Date date=Calendar.getInstance().getTime();
	@ManyToOne
	private User sender;
	@ManyToOne
	private User reciever;
	private static final long serialVersionUID = 1L;

	
	@PostPersist
	public void postPersist(){
		UserServer.sendToUser(reciever, "job+");
	}
	@PostUpdate
	public void postUpdate(){
		UserServer.sendToUser(reciever, "job+");
	}
	@PostRemove
	public void postRemove(){
		UserServer.sendToUser(reciever, "job-");
	}
	
	public ProposedJob() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}   
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}   
	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReciever() {
		return reciever;
	}
	public void setReciever(User reciever) {
		this.reciever = reciever;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		return 65;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProposedJob other = (ProposedJob) obj;
		if (id != other.id)
			return false;
		return true;
	}
   
}
