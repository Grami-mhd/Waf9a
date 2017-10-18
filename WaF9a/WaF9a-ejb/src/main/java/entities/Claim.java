package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

import IDs.ClaimID;
import webSocket.ServerParent;

/**
 * Entity implementation class for Entity: Claim
 *
 */
@Entity

public class Claim implements Serializable {

	   
	@EmbeddedId
	private ClaimID claimID;
	private Date date=Calendar.getInstance().getTime();
	private String text;
	private String status="Not treated yet";
	private boolean treated=false;
	
	@ManyToOne
	private User claimer;
	
	@ManyToOne
	private DoneJob job;
	
	
	
	
	private static final long serialVersionUID = 1L;

	@PostPersist
	public void onPostPersisit(){
		ServerParent.sendToAll("claim");
	}
	
	@PostUpdate
	public void onPostUpdate(){
		ServerParent.sendToAll("claim");
	}
	
	@PostRemove
	public void onPostDelete(){
		ServerParent.sendToAll("claim");
	}
	public Claim() {
		super();
		this.claimID = new ClaimID();
	}   
	public ClaimID getClaimID() {
		return this.claimID;
	}

	 
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}   
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}   
	public boolean getTreated() {
		return this.treated;
	}

	public void setTreated(boolean treated) {
		this.treated = treated;
	}
	public User getClaimer() {
		return claimer;
	}
	public void setClaimer(User claimer) {
		this.claimer = claimer;
		this.claimID.setUserId(claimer.getId());
	}
	public DoneJob getJob() {
		return job;
	}
	public void setJob(DoneJob job) {
		this.job = job;
		this.claimID.setDoneJobId(job.getDoneJobId());
	}
	public void setClaimID(ClaimID claimID) {
		this.claimID = claimID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((claimID == null) ? 0 : claimID.hashCode());
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
		Claim other = (Claim) obj;
		if (claimID == null) {
			if (other.claimID != null)
				return false;
		} else if (!claimID.equals(other.claimID))
			return false;
		return true;
	}
	
   
}
