package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import JSF.UsersLocal;

import entities.User;

@ManagedBean
@javax.faces.bean.ViewScoped
public class UsersBean {

	@EJB
	private UsersLocal local;
	
	private Boolean display = true;
	private Boolean displaym = false;
	private Boolean displayr=false;
	private Map<User, String> sortedUsersRate;
	private Map<User, String> sortedUsersDj;
	private List<User> users = new ArrayList<>();
	private String key;
	
	

	@PostConstruct
	public void getExistantUsers() {

		users = local.getAllUsers();

	}

	public void searchUsersByname() {
		displaym = false;
		displayr = false;
		display = true;
		users = local.serachbyname(key);
	}

	public void sortUsersByDoneJobs() {
		display= false;
		displayr=false;
		displaym=true;
		sortedUsersDj = local.sortUsersByDoneJobs();
		System.out.println(sortedUsersDj + "qqqqqqqqqqqqqqq");
	}
	
	
	public void sortUsersByRate(){
		display=false;
		displaym=false;
		displayr=true;
		sortedUsersRate=local.sortUsersByRate();
		System.out.println(sortedUsersRate + "qqqqqqqqqqqqqqq");
	}
	
	

	public void sortUsersBySignupDate() {
		
		displaym=false;
		displayr=false;
		display=true;
		
		users = local.sortBySignUpDate();
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {

		this.users = users;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
	public Boolean getDisplay() {
		return display;
	}

	public void setDisplay(Boolean display) {
		this.display = display;
	}

	public Boolean getDisplaym() {
		return displaym;
	}

	public void setDisplaym(Boolean displaym) {
		this.displaym = displaym;
	}

	
	

	public Boolean getDisplayr() {
		return displayr;
	}

	public void setDisplayr(Boolean displayr) {
		this.displayr = displayr;
	}

	public Map<User, String> getSortedUsersRate() {
		return sortedUsersRate;
	}

	public void setSortedUsersRate(Map<User, String> sortedUsersRate) {
		this.sortedUsersRate = sortedUsersRate;
	}

	public Map<User, String> getSortedUsersDj() {
		return sortedUsersDj;
	}

	public void setSortedUsersDj(Map<User, String> sortedUsersDj) {
		this.sortedUsersDj = sortedUsersDj;
	}

	
	
}
