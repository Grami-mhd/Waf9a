package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
 
import entities.DoneJob;
import entities.Offer;
import entities.User;
 
import service.DonejobsServiceLocal;
 
@ManagedBean
@ViewScoped
public class DonejobBean {
	
	@EJB
	private DonejobsServiceLocal donejobservice; 
	private List<DoneJob> donejobs ; 
 	private User u =(User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
 	private DoneJob done = new DoneJob();

	
 	
 	
 	public void doSave() {
		
		done.setWorker(u);
		System.out.println(u);
		donejobservice.adddonejob(done);
		
		
	}
 	

	public void doDelete(DoneJob d) {
	
		donejobservice.removedonejob(d) ;
		u.getDoneJobs().remove(d);
	//	donejobs =donejobservice.findAllOffre();

	}



	public User getU() {
		return u;
	}



	public void setU(User u) {
		this.u = u;
	}
	
	 
	
public void setDonejobs(List<DoneJob> donejobs) {
	this.donejobs = donejobs;
}public List<DoneJob> getDonejobs() {
	return donejobs;
}
	
	
	
	
}
