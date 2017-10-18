package service;

import java.util.List;

import javax.ejb.Local;

import entities.Expertise;
import entities.Offer;



@Local
public interface ExpertiseServiceLocal {
	
	void addoffre(Offer offre); 
	void remove (Offer offre ); 
    void saveOffre(Offer offre ); 
	List<Offer> findAllOffre();
	Expertise findexpertisetById(int id);
	 
	List<Offer> findProductsByCategory( Expertise expertise);
	
	
	void createExpertise(Expertise expertise);
	void saveExpertise(Expertise expertise);
	
	void removeExpertise(Expertise expertise);
	List<Expertise > findAllexpertises();
	 
}
