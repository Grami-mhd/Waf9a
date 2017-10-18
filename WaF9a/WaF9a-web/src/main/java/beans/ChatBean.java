package beans;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import entities.User;
import webSocket.UserServer;

@ManagedBean
@ViewScoped
public class ChatBean {

	
	private Map<String , Boolean> loggedFriends = new HashMap<>();
	
	@PostConstruct
	public void init(){
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		for(User us: user.getFriends()){
			loggedFriends.put(us.getUserName(), UserServer.isLogged(us.getUserName()));
		}
		System.out.println(loggedFriends);
	}

	private String reciever="";
	
	public void recieverput(String s){
		reciever =s;
		System.out.println("putting reciever to"+s);
	}
	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public Map<String, Boolean> getLoggedFriends() {
		return loggedFriends;
	}

	public void setLoggedFriends(Map<String, Boolean> loggedFriends) {
		this.loggedFriends = loggedFriends;
	}
	
	
	
}
