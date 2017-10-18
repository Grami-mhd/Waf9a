package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import JSF.OfferViewLocal;
import entities.Comment;
import entities.Offer;
import entities.User;



@ManagedBean
@ViewScoped
public class ViewOfferBean {

	private User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	
	private Offer offer = (Offer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("offer");
	
	private Comment c= new Comment();
	
	@EJB
	private OfferViewLocal offerViewLocal;
	public String postComment(){
		c.setOffer(offer);
		System.out.println(user);
		c.setOwner(user);
		offer.getComments().add(c);
		offerViewLocal.updateListCom(offer);
		c= new Comment();
		return "";
	}
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Comment getC() {
		return c;
	}
	public void setC(Comment c) {
		this.c = c;
	}
	
	
	
}
