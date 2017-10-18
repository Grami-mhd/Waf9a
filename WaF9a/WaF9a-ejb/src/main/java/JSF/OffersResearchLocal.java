package JSF;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import entities.Offer;

@Local
public interface OffersResearchLocal {
	
	public List<Offer> getOffersByKey(String key);
	public List<Offer> getOffersByOwner(String name);
	public List<Offer> SortOffersByDate();
	public List<Offer> getAllOffers();
	
	public Set<String> autoComplete() ;

}
