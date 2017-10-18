package entities;

import java.io.Serializable;

import javax.persistence.*;

import IDs.SerachID;

/**
 * Entity implementation class for Entity: Search
 *
 */
@Entity

public class Search implements Serializable {

	@EmbeddedId
	private SerachID searchId;

	private static final long serialVersionUID = 1L;

	public Search() {
		super();
		searchId=new SerachID();
	}

	public SerachID getSearchId() {
		return searchId;
	}

	public void setSearchId(SerachID searchId) {
		this.searchId = searchId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((searchId == null) ? 0 : searchId.hashCode());
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
		Search other = (Search) obj;
		if (searchId == null) {
			if (other.searchId != null)
				return false;
		} else if (!searchId.equals(other.searchId))
			return false;
		return true;
	}   
	
   
}
