package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

import IDs.CommentReportID;

/**
 * Entity implementation class for Entity: MessageReport
 *
 */
@Entity
public class CommentReport implements Serializable {

	@EmbeddedId
	private CommentReportID CommentReportID;
	private Date reportDate=Calendar.getInstance().getTime();
	private String reason;
	private boolean treated=false;
	
	@ManyToOne
	private Comment comment;
	
	@ManyToOne
	private User reporter;
	
	private static final long serialVersionUID = 1L;

	public CommentReport() {
		super();
		this.CommentReportID = new CommentReportID();
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CommentReportID == null) ? 0 : CommentReportID.hashCode());
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
		CommentReport other = (CommentReport) obj;
		if (CommentReportID == null) {
			if (other.CommentReportID != null)
				return false;
		} else if (!CommentReportID.equals(other.CommentReportID))
			return false;
		return true;
	}
	public CommentReportID getCommentReportID() {
		return CommentReportID;
	}
	public void setCommentReportID(CommentReportID commentReportID) {
		CommentReportID = commentReportID;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
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
   
}
