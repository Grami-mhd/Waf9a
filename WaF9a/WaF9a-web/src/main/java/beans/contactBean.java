package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.SendmailEJBRemote;

@ManagedBean
@ViewScoped
public class contactBean {

	private String email;
	private String title;
	private String txt;
	private String lat;
	private String longi;
	private String adress;
	
	
	@EJB
	private SendmailEJBRemote sendmailEJBRemote;
	
	public String send(){
		System.out.println(lat+" fff "+longi+adress);
		return "";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	
	
	
	
	
}
