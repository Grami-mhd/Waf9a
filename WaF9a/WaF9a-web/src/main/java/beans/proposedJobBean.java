package beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Null;

import JSF.ViewUserRemote;
import entities.ExpertiseDemande;
import entities.ProposedJob;

@ManagedBean
@ViewScoped
public class proposedJobBean {
	
	@EJB
	private ViewUserRemote userRemote;
	
	@ManagedProperty(value = "#{autentificationBean}")
	private autentificationBean atb;
	
	private ProposedJob pj=new ProposedJob();

	public String proposejob(){
		
		pj.setReciever(ClaimBean.notfriend1);
		pj.setSender(atb.getUser());
		
		userRemote.proposejob(pj);
		
		
		pj.setText("");
		
		return "/historyClaims?faces-redirect=true";
		
	}

public autentificationBean getAtb() {
	return atb;
}

public void setAtb(autentificationBean atb) {
	this.atb = atb;
}

public ProposedJob getPj() {
	return pj;
}

public void setPj(ProposedJob pj) {
	this.pj = pj;
}
	

}
