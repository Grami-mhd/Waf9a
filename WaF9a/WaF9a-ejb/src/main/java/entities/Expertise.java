package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import webSocket.ServerParent;

/**
 * Entity implementation class for Entity: Expertise
 *
 */
@Entity

public class Expertise implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int expertiseID;
	private String Domain;
	private String name;
	@ManyToMany
	private List<User> users;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Offer> offers;
	
	@OneToMany(mappedBy="expertise",cascade=CascadeType.ALL)
	private List<Approve> approves;
	
	@PostPersist
	public void onPostPersisit(){
		ServerParent.sendToAll("expdem");
	}
	
	
	private static final long serialVersionUID = 1L;

	public Expertise() {
		super();
	}   
	public int getExpertiseID() {
		return this.expertiseID;
	}

	public void setExpertiseID(int expertiseID) {
		this.expertiseID = expertiseID;
	}   
	public String getDomain() {
		return this.Domain;
	}

	public void setDomain(String Domain) {
		this.Domain = Domain;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Expertise other = (Expertise) obj;
		if (expertiseID != other.expertiseID)
			return false;
		return true;
	}
	public List<Approve> getApproves() {
		return approves;
	}
	public void setApproves(List<Approve> approves) {
		this.approves = approves;
	}
	@Override
	public String toString() {
		return  name ;
	}
   
}
