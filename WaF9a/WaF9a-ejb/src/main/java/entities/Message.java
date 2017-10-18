package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity

public class Message implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int messageID;
	private String text;
	private Date date=Calendar.getInstance().getTime();
	@ManyToOne
	private User owner;
	@ManyToOne
	private Discussion discussion;
	
	@OneToMany(mappedBy="message",cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	private Set<Reply> replies;
	
	@OneToMany(mappedBy="message",cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	private Set<MessageReport> reportes;
	
	private static final long serialVersionUID = 1L;

	public Message() {
		super();
	}   
	public int getMessageID() {
		return this.messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Discussion getDiscussion() {
		return discussion;
	}
	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public Set<MessageReport> getReportes() {
		return reportes;
	}
	public void setReportes(Set<MessageReport> reportes) {
		this.reportes = reportes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + messageID;
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
		Message other = (Message) obj;
		if (messageID != other.messageID)
			return false;
		return true;
	}
	
   
}
