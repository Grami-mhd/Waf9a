package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entities.Offer;
import entities.User;

@ManagedBean
@RequestScoped
public class redirectionsBean {

	public String gotoForums(){
		return "/pages/forums?faces-redirect=true";
	}
	public String gotoSeatchUsers(){
		return "/pages/users?faces-redirect=true";
	}
	public String gotoSeachOfeers(){
		return "/pages/offersresearch?faces-redirect=true";
	}
	public String gotoOffers(){
		return "/pages/offers?faces-redirect=true";
	}
	public String gotoProfile(){
		return "/pages/profile?faces-redirect=true";
	}
	public String gotonotifications(){
		return "/pages/notifications?faces-redirect=true";
	}
	public String gotoMessages(){
		return "/pages/messages?faces-redirect=true";
	}
	
	public String gotoRegister(){
		return "/registre?faces-redirect=true";
	}
	public String gotoMyOffers(){
		return "/pages/ListeJob?faces-redirect=true";
	}
	public String gotoMyDoneJobs(){
		return "/historydonejobs?faces-redirect=true";
	}
	public String gotoAddOffer(){
		return "/pages/addoffer?faces-redirect=true";
	}
	public String gotoOffer(Offer of){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("offer", of);
		return "/pages/offer?faces-redirect=true";
	}
	public String gotoUser(User of){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("view", of);
		return "/pages/userView?faces-redirect=true";
	}
}
