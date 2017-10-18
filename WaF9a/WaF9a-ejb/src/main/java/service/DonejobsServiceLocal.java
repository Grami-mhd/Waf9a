package service;

import java.util.List;

import javax.ejb.Local;

import entities.DoneJob;

 

@Local
public interface DonejobsServiceLocal {
	void adddonejob(DoneJob donejob); 
	void removedonejob (DoneJob donejob ); 
    void savedonejob(DoneJob donejob); 
	List<DoneJob > findAllOffre();
 
	public List<DoneJob> MyDoneJobs( ); 
}
