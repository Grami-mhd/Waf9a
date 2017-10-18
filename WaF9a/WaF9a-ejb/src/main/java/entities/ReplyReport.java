package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;
import IDs.ReplyReportID;

/**
 * Entity implementation class for Entity: MessageReport
 *
 */
@Entity
public class ReplyReport implements Serializable {

	@EmbeddedId
	private ReplyReportID replyReportID;
	private Date reportDate=Calendar.getInstance().getTime();
	private String reason;
	private boolean treated=false;
	
	@ManyToOne
	private Reply reply;
	
	@ManyToOne
	private User reporter;
	
	private static final long serialVersionUID = 1L;

	public ReplyReport() {
		super();
		replyReportID = new ReplyReportID();
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
	public ReplyReportID getReplyReportID() {
		return replyReportID;
	}
	public void setReplyReportID(ReplyReportID replyReportID) {
		this.replyReportID = replyReportID;
	}
	public Reply getReply() {
		return reply;
	}
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	public User getReporter() {
		return reporter;
	}
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((replyReportID == null) ? 0 : replyReportID.hashCode());
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
		ReplyReport other = (ReplyReport) obj;
		if (replyReportID == null) {
			if (other.replyReportID != null)
				return false;
		} else if (!replyReportID.equals(other.replyReportID))
			return false;
		return true;
	}
   
}
