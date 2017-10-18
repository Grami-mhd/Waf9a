package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.websocket.Session;

import org.primefaces.context.RequestContext;

import entities.User;
import service.LoginRemote;
import service.ProfileJPARemote;
import service.UserServiceLocal;

@ManagedBean(name="autentificationBean")
@SessionScoped
public class autentificationBean  {

	@EJB
	private UserServiceLocal authenticationServiceLocal;
	
	private User user = new User();
	private boolean loggedIn;
	private boolean logged = false;
	@EJB
	private LoginRemote loginremote;
	@EJB
	private ProfileJPARemote profileJPARemote;

	public String login() {
		User u1 = loginremote.checkUserName(user.getUserName());
		if (u1 != null) {
			if (loginremote.access(u1, user.getPassword())) {
				user = profileJPARemote.fill(u1);
				logged = true;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
				return "";
			}
		}
		return "";
	}

	public String doLogin(){
		User u1=authenticationServiceLocal.authenticate(user.getUserName(), user.getPassword());
		if(u1!=null){
			user=u1;
			loggedIn=true;
			//return "welcome?faces-redirect=true";
			return "";
		}
		FacesMessage msg= new FacesMessage("bad Credentials");
		//	FacesContext.getCurrentInstance().addMessage("form_login:connect", msg);
		
		return "";
	}
	
	
	@PostConstruct
	public void initModel() {
		user = new User();
		loggedIn = false;
		logged = false;
	}	



	public String doLogout() {
		 
		initModel();
		String  navigateTo = "/welcome?faces-redirect=true";
		return navigateTo;
	}
	
	
	
	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public void refresh(){
		user=profileJPARemote.fill(user);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().replace("user", user);
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
}
