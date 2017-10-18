package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import entities.Claim;
import entities.DoneJob;
import service.ClaimsRemote;

@ManagedBean
@RequestScoped
public class HistoryBean {

	@EJB
	private ClaimsRemote claimsRemote;
	
	@ManagedProperty(value = "#{autentificationBean}")
	private autentificationBean atb;
	
	private List<DoneJob> sd=new ArrayList<DoneJob>();
	
	@PostConstruct
	public void init(){
		sd=claimsRemote.historyDonejob(atb.getUser());
	}

	public autentificationBean getAtb() {
		return atb;
	}

	public void setAtb(autentificationBean atb) {
		this.atb = atb;
	}

	public List<DoneJob> getSd() {
		return sd;
	}

	public void setSd(List<DoneJob> sd) {
		this.sd = sd;
	}
	
	
}
