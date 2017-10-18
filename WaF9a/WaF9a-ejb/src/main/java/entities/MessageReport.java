package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

import IDs.MessageReportID;

/**
 * Entity implementation class for Entity: MessageReport
 *
 */
@Entity
public class MessageReport implements Serializable {

	@EmbeddedId
	private MessageReportID messageReportID;
	private Date reportDate=Calendar.getInstance().getTime();
	private String reason;
	private boolean treated=false;
	
	@ManyToOne
	private Message message;
	
	@ManyToOne
	private User reporter;
	
	private static final long serialVersionUID = 1L;

	public MessageReport() {
		super();
		messageReportID = new MessageReportID();
	}   
	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}   
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}   
	public boolean getTreated() {
		return this.treated;
	}

	public void setTreated(boolean treated) {
		this.treated = treated;
	}
	public MessageReportID getMessageReportID() {
		return messageReportID;
	}
	public void setMessageReportID(MessageReportID messageReportID) {
		this.messageReportID = messageReportID;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public User getReporter() {
		return reporter;
	}
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((messageReportID == null) ? 0 : messageReportID.hashCode());
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
		MessageReport other = (MessageReport) obj;
		if (messageReportID == null) {
			if (other.messageReportID != null)
				return false;
		} else if (!messageReportID.equals(other.messageReportID))
			return false;
		return true;
	}
   
}
