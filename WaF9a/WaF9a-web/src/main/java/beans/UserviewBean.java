package beans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import JSF.ViewUserRemote;
import entities.FriendRequest;
import entities.User;
import service.ProfileJPA;

@ManagedBean
@ViewScoped
public class UserviewBean {

	@EJB
	private ViewUserRemote userRemote;
	
	
	@ManagedProperty(value = "#{autentificationBean}")
	private autentificationBean atb;
	
	
	private User view =(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("view");
	
public String addfriendreq(){
		FriendRequest f=new FriendRequest();
		f.setReceiver(view);
		f.setSender(atb.getUser());
		
		userRemote.sendFriendRequest(f);
		return "";
	}
public ViewUserRemote getUserRemote() {
	return userRemote;
}
public void setUserRemote(ViewUserRemote userRemote) {
	this.userRemote = userRemote;
}

public autentificationBean getAtb() {
	return atb;
}
public void setAtb(autentificationBean atb) {
	this.atb = atb;
}

public User getView() {
	return view;
}
public void setView(User view) {
	this.view = view;
}

	
}
