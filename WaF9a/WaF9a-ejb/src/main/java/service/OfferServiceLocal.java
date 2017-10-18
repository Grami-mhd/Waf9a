package service;

import java.util.List;

import javax.ejb.Local;

import entities.Comment;
import entities.Offer;
import entities.User;

@Local
public interface OfferServiceLocal {

	void addoffre(Offer offre); 
	void remove (Offer offre ); 
    void saveOffre(Offer offre ); 
	List<Offer> findAllOffre();
	int offernumber(User u);
	void clseJob(Comment c);
}
