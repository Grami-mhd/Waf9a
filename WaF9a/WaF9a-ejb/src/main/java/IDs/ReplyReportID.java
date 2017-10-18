package IDs;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MessageReportID
 *
 */
@Embeddable
public class ReplyReportID implements Serializable {

	
	private int replyId;
	private int userId;
	private static final long serialVersionUID = 1L;

	public ReplyReportID() {
		super();
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + replyId;
		result = prime * result + userId;
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
		ReplyReportID other = (ReplyReportID) obj;
		if (replyId != other.replyId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}   
	
   
}
