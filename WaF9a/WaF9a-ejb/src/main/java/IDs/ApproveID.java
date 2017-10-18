package IDs;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ApproveID implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int approverID;
	private int  expertiseID;
	private int approvedID;
	
	public int getApproverID() {
		return approverID;
	}
	public void setApproverID(int approverID) {
		this.approverID = approverID;
	}
	public int getExpertiseID() {
		return expertiseID;
	}
	public void setExpertiseID(int expertiseID) {
		this.expertiseID = expertiseID;
	}
	
	public int getApprovedID() {
		return approvedID;
	}
	public void setApprovedID(int approvedID) {
		this.approvedID = approvedID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + approvedID;
		result = prime * result + approverID;
		result = prime * result + expertiseID;
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
		ApproveID other = (ApproveID) obj;
		if (approvedID != other.approvedID)
			return false;
		if (approverID != other.approverID)
			return false;
		if (expertiseID != other.expertiseID)
			return false;
		return true;
	}
	
	
	
	
	
	
}
