package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Offer
 *
 */
@Entity

public class Offer implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int offerID;
	private String text;
	private Date date=Calendar.getInstance().getTime();
	@OneToOne(cascade=CascadeType.ALL)
	private Adress adress;
	
	@ManyToOne
	private User owner;
	
	@OneToMany(mappedBy="offer",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Comment> Comments;
	
	@ManyToMany(mappedBy="offers",cascade=CascadeType.ALL , fetch=FetchType.EAGER)
	private List<Expertise> expertises;
	
	
	private static final long serialVersionUID = 1L;

	public Offer() {
		super();
		this.adress= new Adress();
	}   
	public int getOfferID() {
		return this.offerID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	public List<Expertise> getExpertises() {
		return expertises;
	}
	public void setExpertises(List<Expertise> expertises) {
		this.expertises = expertises;
	}
	public List<Comment> getComments() {
		return Comments;
	}
	public void setComments(List<Comment> comments) {
		Comments = comments;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + offerID;
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
		Offer other = (Offer) obj;
		if (offerID != other.offerID)
			return false;
		return true;
	}
   
	
}
