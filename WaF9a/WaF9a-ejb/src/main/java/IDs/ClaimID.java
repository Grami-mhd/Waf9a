package IDs;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ClaimID implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private int DoneJobId;
	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getDoneJobId() {
		return DoneJobId;
	}
	public void setDoneJobId(int doneJobId) {
		DoneJobId = doneJobId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + DoneJobId;
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
		ClaimID other = (ClaimID) obj;
		if (DoneJobId != other.DoneJobId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	
}
