package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entities.Claim;
import entities.DoneJob;
import entities.Notification;
import entities.User;
import service.ClaimsRemote;
import service.ProfileJPA;

@ManagedBean
@RequestScoped
public class ClaimBean {

	@EJB
	private ClaimsRemote claimsRemote;
	@EJB
	private ProfileJPA userfill;

	@ManagedProperty(value = "#{autentificationBean}")
	private autentificationBean atb;

	private Claim c = new Claim();
	private User u = new User();
	private List<Claim> sd = new ArrayList<Claim>();

	public static DoneJob donej;
	public static User notfriend1 ;
	
	
	@PostConstruct
	public void init() {
		sd = claimsRemote.historyClaim(atb.getUser());
	}

	public void seeClaimedAbout(Claim c) {
		if (c.getJob().getBoss() != atb.getUser())
			notfriend1=c.getJob().getWorker();
		else
			notfriend1=c.getJob().getBoss();
		

	}

	public String ajoutClaim() {
		String navTo = "";

		c.setJob(donej);
		c.setStatus("test");
		c.setTreated(false);
		c.setClaimer(atb.getUser());
		java.util.Date date = new java.util.Date();
		c.setDate(date);
		claimsRemote.ajoutClaim(c);
		c.setText("");
		navTo = "/Claims?faces-redirect=true";
		return navTo;

	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public void claimerAboutId(DoneJob d) {
		donej = d;

	}

	public Claim getC() {
		return c;
	}

	public void setC(Claim c) {
		this.c = c;
	}

	public autentificationBean getAtb() {
		return atb;
	}

	public void setAtb(autentificationBean atb) {
		this.atb = atb;
	}

	public List<Claim> getSd() {
		return sd;
	}

	public void setSd(List<Claim> sd) {
		this.sd = sd;
	}

}
