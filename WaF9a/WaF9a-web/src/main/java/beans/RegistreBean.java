package beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import entities.Adress;
import entities.User;

import service.UserManageRemote;
import service.UserServiceLocal;


@ManagedBean
@RequestScoped
public class RegistreBean {

	
	
	@EJB
	private UserServiceLocal userServiceLocal;

	@EJB
	private UserManageRemote customerService;

	@ManagedProperty("#{autentificationBean}")
	private autentificationBean authBean;

	private User user;
	private Adress adress; 

	public void RegisterBean() {
	}

	@PostConstruct
	public void init() {
		adress = new Adress(); 
		user = new User();
		user.setAdress(adress);
	}

	public String doSignUp() {
		String navigateTo = "";
		customerService.updatePerson(user);
		authBean.setUser(user);
		navigateTo = authBean.doLogin();
		return navigateTo;
	}


	
	 
	
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public autentificationBean getAuthBean() {
		return authBean;
	}

	public void setAuthBean(autentificationBean authBean) {
		this.authBean = authBean;
	}
	
	
	
	
}
