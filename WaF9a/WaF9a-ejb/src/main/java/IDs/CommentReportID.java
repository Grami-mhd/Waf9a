package IDs;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MessageReportID
 *
 */
@Embeddable
public class CommentReportID implements Serializable {

	
	private int commentId;
	private int userId;
	private static final long serialVersionUID = 1L;

	public CommentReportID() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentId;
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
		CommentReportID other = (CommentReportID) obj;
		if (commentId != other.commentId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}   
	
   
}
