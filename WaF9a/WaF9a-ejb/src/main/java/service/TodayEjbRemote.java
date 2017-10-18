package service;


import java.util.List;

import javax.ejb.Remote;

import entities.DoneJob;
import entities.Offer;
import entities.User;

@Remote
public interface TodayEjbRemote {
	
	public List<User> getNewUsers();
	public List<Offer> getNewOffers();
	public List<DoneJob> getNewDoneJobs();
	

}
