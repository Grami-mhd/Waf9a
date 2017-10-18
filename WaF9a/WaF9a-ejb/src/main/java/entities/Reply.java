package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reply
 *
 */
@Entity

public class Reply implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int replyId;
	private String text;
	private Date date=Calendar.getInstance().getTime();
	
	@ManyToOne
	private User owner;
	@ManyToOne
	private Message message;
	
	@OneToMany(mappedBy="reply",cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	private Set<ReplyReport> reportes;
	
	private static final long serialVersionUID = 1L;

	public Reply() {
		super();
	}   
	public int getReplyId() {
		return this.replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
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
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Set<ReplyReport> getReportes() {
		return reportes;
	}
	public void setReportes(Set<ReplyReport> reportes) {
		this.reportes = reportes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + replyId;
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
		Reply other = (Reply) obj;
		if (replyId != other.replyId)
			return false;
		return true;
	}
   
}
