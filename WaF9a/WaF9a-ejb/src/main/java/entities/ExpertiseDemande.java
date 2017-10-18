package entities;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

import IDs.ExpertiseDemandeId;
import webSocket.ServerParent;

/**
 * Entity implementation class for Entity: ExpertiseDemande
 *
 */
@Entity

public class ExpertiseDemande implements Serializable {

	   
	@EmbeddedId
	private ExpertiseDemandeId expertiseDemandeId;
	private String domain;
	private boolean treated=false;
	
	
	@ManyToOne
	private User user;
	
	private static final long serialVersionUID = 1L;

	
	
	@PostPersist
	public void onPostPersisit(){
		ServerParent.sendToAll("expdem");
	}
	@PostUpdate
	public void onPostUpdate(){
		ServerParent.sendToAll("expdem");
	}
	
	public ExpertiseDemande() {
		super();
		this.expertiseDemandeId=new ExpertiseDemandeId();
	}

	public ExpertiseDemandeId getExpertiseDemandeId() {
		return expertiseDemandeId;
	}

	public void setExpertiseDemandeId(ExpertiseDemandeId expertiseDemandeId) {
		this.expertiseDemandeId = expertiseDemandeId;
	}

	public boolean isTreated() {
		return treated;
	}

	public void setTreated(boolean treated) {
		this.treated = treated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expertiseDemandeId == null) ? 0 : expertiseDemandeId.hashCode());
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
		ExpertiseDemande other = (ExpertiseDemande) obj;
		if (expertiseDemandeId == null) {
			if (other.expertiseDemandeId != null)
				return false;
		} else if (!expertiseDemandeId.equals(other.expertiseDemandeId))
			return false;
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}   
	
   
}
