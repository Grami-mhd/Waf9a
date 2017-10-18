package JSF;

import java.util.List;

import javax.ejb.Local;

import entities.Comment;
import entities.Offer;

@Local
public interface OfferViewLocal {

	public List<Offer> getAllOffers();
	public void updateListCom(Offer o);
	
	}
