package entities;

import java.io.Serializable;
import javax.persistence.*;

import IDs.RateId;

/**
 * Entity implementation class for Entity: Rate
 *
 */
@Entity

public class Rate implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rate;
		result = prime * result + ((rateId == null) ? 0 : rateId.hashCode());
		result = prime * result + ((rated == null) ? 0 : rated.hashCode());
		result = prime * result + ((rater == null) ? 0 : rater.hashCode());
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
		Rate other = (Rate) obj;
		if (rate != other.rate)
			return false;
		if (rateId == null) {
			if (other.rateId != null)
				return false;
		} else if (!rateId.equals(other.rateId))
			return false;
		if (rated == null) {
			if (other.rated != null)
				return false;
		} else if (!rated.equals(other.rated))
			return false;
		if (rater == null) {
			if (other.rater != null)
				return false;
		} else if (!rater.equals(other.rater))
			return false;
		return true;
	}

	@EmbeddedId
	private RateId rateId;
	
	private int rate;
	@ManyToOne
	private User rated;
	@ManyToOne
	private User rater;
	
	private static final long serialVersionUID = 1L;

	public Rate() {
		super();
		this.rateId=new RateId();
	}   
	public int getRate() {
		return this.rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
   
}
