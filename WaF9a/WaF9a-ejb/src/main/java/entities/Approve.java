package entities;

import java.io.Serializable;
import javax.persistence.*;

import IDs.ApproveID;

/**
 * Entity implementation class for Entity: Approve
 *
 */
@Entity
public class Approve implements Serializable {

	@EmbeddedId
	private ApproveID approveId;
	
	@ManyToOne
	private User Approved ; 
	
	@ManyToOne
	private Expertise expertise;
	
	@ManyToOne
	private User approver ;
	
	private static final long serialVersionUID = 1L;

	public Approve() {
		super();
		approveId = new ApproveID();
		
	}

	public ApproveID getApproveId() {
		return approveId;
	}

	public void setApproveId(ApproveID approveId) {
		this.approveId = approveId;
	}

	public User getApproved() {
		return Approved;
	}

	public void setApproved(User approved) {
		Approved = approved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Approved == null) ? 0 : Approved.hashCode());
		result = prime * result + ((approveId == null) ? 0 : approveId.hashCode());
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
		Approve other = (Approve) obj;
		if (Approved == null) {
			if (other.Approved != null)
				return false;
		} else if (!Approved.equals(other.Approved))
			return false;
		if (approveId == null) {
			if (other.approveId != null)
				return false;
		} else if (!approveId.equals(other.approveId))
			return false;
		return true;
	}

	public Expertise getExpertise() {
		return expertise;
	}

	public void setExpertise(Expertise expertise) {
		this.expertise = expertise;
	}

	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}
	
   
}
