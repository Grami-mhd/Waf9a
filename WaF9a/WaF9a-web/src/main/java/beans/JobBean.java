package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import entities.Adress;
import entities.Comment;
import entities.Expertise;
import entities.Offer;
import entities.User;

import service.ExpertiseDemandeEJBRemote;
import service.ExpertiseServiceLocal;
import service.OfferServiceLocal;

@ManagedBean
@ViewScoped
public class JobBean {

	private User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	@EJB
	private OfferServiceLocal offreservice;
	@EJB
	private ExpertiseServiceLocal catalog;

	private Offer job = new Offer();

	private List<Expertise> expertises;

	private String exp="";


	public String viewjob(Offer offer) {
		for (Map.Entry<Offer, Boolean> en : mapoffer.entrySet()) {
			en.setValue(false);
		}
		mapoffer.replace(offer, true);
		return "";
	}

	public Map<Offer, Boolean> getMapoffer() {
		return mapoffer;
	}

	public void setMapoffer(Map<Offer, Boolean> mapoffer) {
		this.mapoffer = mapoffer;
	}

	private Map<Offer, Boolean> mapoffer = new HashMap<>();

	public boolean fu(Offer offer) {
		return true;
	}

	@PostConstruct
	public void init() {
		job.setExpertises(new ArrayList<Expertise>());
		job.setAdress(new Adress());
		expertises = catalog.findAllexpertises();
		job.setExpertises(new ArrayList<Expertise>());
		for (Offer O : user.getOffers()) {
			mapoffer.put(O, false);
		}
	}
	public String closeJob(Comment c){
		offreservice.clseJob(c);
		this.doDelete(c.getOffer());
		return "";
	}

	public String doSave() {
		addEXP();
		job.setOwner(user);
		offreservice.addoffre(job);
		job = new Offer();
		job.setExpertises(new ArrayList<Expertise>());
		return  "/pages/ListeJob?faces-redirect=true";
	}

	public void addEXP() {
		for (Expertise e : expertises)
			if (e.getName().equals(exp))
				this.job.getExpertises().add(e);
		System.out.println(job.getExpertises());
	}

	

	public void doDelete(Offer o) {
		this.user.getOffers().remove(o);
		offreservice.remove(o);
	}

	public Offer getJob() {
		return job;
	}

	public void setJob(Offer job) {
		this.job = job;
	}


	public List<Expertise> getExpertises() {
		return expertises;
	}

	public void setExpertises(List<Expertise> expertises) {
		this.expertises = expertises;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}
}
