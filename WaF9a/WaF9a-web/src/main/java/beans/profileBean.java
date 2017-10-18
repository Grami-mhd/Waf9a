package beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;


import IDs.ExpertiseDemandeId;
import JSF.profileJLocal;
import entities.Expertise;
import entities.ExpertiseDemande;
import entities.User;
import service.ProfileJPARemote;

@ManagedBean
@SessionScoped
public class profileBean {

	@ManagedProperty(value = "#{autentificationBean}")
	private autentificationBean atb;
	
	@EJB
	private profileJLocal profileJLocal;
	@EJB
	private ProfileJPARemote ProfileJPARemote;
	
	private String key;
	private Expertise exp;
	private ExpertiseDemande expD= new ExpertiseDemande();
	private ExpertiseDemandeId expId;
	private boolean displayForm = false;

	private Set<User> friends =new HashSet<>();
	private List<Expertise> e  =new ArrayList<>();
	
	
	public String doAddExp(){
		String navTo = " ";
		expD.getExpertiseDemandeId().setUserId(atb.getUser().getId());	
		expD.setUser(atb.getUser());
		profileJLocal.addExpertiseDemande(expD);
		initialization();	
		return navTo;
	}

	public String doDeleteFriend(User us){
		String navTo = " ";
	
		System.out.println(us.getFirstName());
		friends =  ProfileJPARemote.fill(atb.getUser()).getFriends();
		System.out.println("9bal"+friends);
		friends.remove(us);
		
		System.out.println("ba3ad"+friends);
		User usr = profileJLocal.findUser(atb.getUser().getId());
		usr.setFriends(friends);
		System.out.println("usr"+usr.getFriends());
		
		profileJLocal.deleteFriend(usr);
		
		initialization();
		
		return navTo;
	}

	
	public Expertise getExp() {
		return exp;
	}

	public void setExp(Expertise exp) {
		this.exp = exp;
	}

	@PostConstruct
	public void init() {
		displayForm =false;
		exp = new Expertise();
		friends =  ProfileJPARemote.fill(atb.getUser()).getFriends();
		
	}
	
	public void searchFriends(){
		friends =profileJLocal.getAllFriendsByKey(key, atb.getUser());
	}
	
	public void initialization (){
		expD=new ExpertiseDemande();
		e = ProfileJPARemote.fill(atb.getUser()).getExpertises();
	}
	
	/*public String updateUser(){
		String navTo = " ";
		displayForm=false;
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		profileJLocal.upDateProfile(atb.getUser());
		
		return navTo;
	}*/

	public void up(){
		displayForm=false;
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		profileJLocal.upDateProfile(atb.getUser());
	}
	
	public String diplayFormButton()
	{
		String navTo = " ";

		displayForm =true;
		return navTo;
	}
	
	public autentificationBean getAtb() {
		return atb;
	}

	public void setAtb(autentificationBean atb) {
		this.atb = atb;
	}

	public profileJLocal getProfileJLocal() {
		return profileJLocal;
	}

	public void setProfileJLocal(profileJLocal profileJLocal) {
		this.profileJLocal = profileJLocal;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}
	public ExpertiseDemandeId getExpId() {
		return expId;
	}

	public void setExpId(ExpertiseDemandeId expId) {
		this.expId = expId;
	}

	public ExpertiseDemande getExpD() {
		return expD;
	}

	public void setExpD(ExpertiseDemande expD) {
		this.expD = expD;
	}

	public boolean isDisplayForm() {
		return displayForm;
	}

	public void setDisplayForm(boolean displayForm) {
		this.displayForm = displayForm;
	}
	
}
