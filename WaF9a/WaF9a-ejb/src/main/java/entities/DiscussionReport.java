package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

import IDs.DiscussionReportID;

/**
 * Entity implementation class for Entity: MessageReport
 *
 */
@Entity
public class DiscussionReport implements Serializable {

	@EmbeddedId
	private DiscussionReportID discussionReportID;
	private Date reportDate=Calendar.getInstance().getTime();
	private String reason;
	private boolean treated=false;
	
	@ManyToOne
	private Discussion discussion;
	
	@ManyToOne
	private User reporter;
	
	
	private static final long serialVersionUID = 1L;

	public DiscussionReport() {
		super();
		 this.discussionReportID = new DiscussionReportID();
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
	public DiscussionReportID getDiscussionReportID() {
		return discussionReportID;
	}
	public void setDiscussionReportID(DiscussionReportID discussionReportID) {
		this.discussionReportID = discussionReportID;
	}
	public Discussion getDiscussion() {
		return discussion;
	}
	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
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
		result = prime * result + ((discussionReportID == null) ? 0 : discussionReportID.hashCode());
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
		DiscussionReport other = (DiscussionReport) obj;
		if (discussionReportID == null) {
			if (other.discussionReportID != null)
				return false;
		} else if (!discussionReportID.equals(other.discussionReportID))
			return false;
		return true;
	}
   
}
