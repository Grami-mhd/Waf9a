package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import webSocket.ServerParent;


/**
 * Entity implementation class for Entity: Discussion
 *
 */
@Entity

public class Discussion implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int DiscussionID;
	private String category;
	private Date date;
	private String topic;
	private boolean created=false;
	@OneToMany(mappedBy="discussion",cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	private Set<Message> messages;
	
	@ManyToOne
	private User owner;
	
	@OneToMany(mappedBy="discussion",cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	private Set<DiscussionReport> reports;
	
	private static final long serialVersionUID = 1L;

	@PostUpdate
	public void onPostUpdate(){
		ServerParent.sendToAll("disc");
	}
	
	@PostPersist
	public void OnPostPersist(){
		ServerParent.sendToAll("disc");
	}
	
	@PostRemove
	public void onPostRemove(){
		ServerParent.sendToAll("disc");
	}
	
	public Discussion() {
		super();
	}   
	public int getDiscussionID() {
		return this.DiscussionID;
	}

	public void setDiscussionID(int DiscussionID) {
		this.DiscussionID = DiscussionID;
	}   
	public String getCategorie() {
		return this.category;
	}

	public void setCategorie(String categorie) {
		this.category = categorie;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}   
	public boolean getCreated() {
		return this.created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Set<Message> getMessages() {
		return messages;
	}
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Set<DiscussionReport> getReports() {
		return reports;
	}
	public void setReports(Set<DiscussionReport> reports) {
		this.reports = reports;
	}
	@Override
	public int hashCode() {
		
		return 8;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discussion other = (Discussion) obj;
		if (DiscussionID != other.DiscussionID)
			return false;
		return true;
	}
   
}
