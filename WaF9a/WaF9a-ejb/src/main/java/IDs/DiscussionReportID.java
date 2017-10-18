package IDs;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MessageReportID
 *
 */
@Embeddable
public class DiscussionReportID implements Serializable {

	
	private int discussionId;
	private int userId;
	private static final long serialVersionUID = 1L;

	public DiscussionReportID() {
		super();
	}

	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}



	public int getDiscussionId() {
		return discussionId;
	}



	public void setDiscussionId(int discussionId) {
		this.discussionId = discussionId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + discussionId;
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
		DiscussionReportID other = (DiscussionReportID) obj;
		if (discussionId != other.discussionId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

   
}
