package IDs;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RateId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ratedId;
	private int raterId;
	public int getRatedId() {
		return ratedId;
	}
	public void setRatedId(int ratedId) {
		this.ratedId = ratedId;
	}
	public int getRaterId() {
		return raterId;
	}
	public void setRaterId(int raterId) {
		this.raterId = raterId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ratedId;
		result = prime * result + raterId;
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
		RateId other = (RateId) obj;
		if (ratedId != other.ratedId)
			return false;
		if (raterId != other.raterId)
			return false;
		return true;
	}

	
}
