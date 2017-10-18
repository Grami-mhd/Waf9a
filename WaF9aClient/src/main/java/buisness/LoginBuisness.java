package buisness;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.User;
import service.LoginRemote;

public class LoginBuisness {

	private LoginRemote l;

	public LoginBuisness() {
		
	}

	public User checkUserName(String s) {
		try {
			l = (LoginRemote) InitialContext.doLookup("/WaF9a-ear/WaF9a-ejb/Login!service.LoginRemote");

			System.out.println("dddddddddddddddddddddddddddddddddddddddd");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User u = l.checkUserName(s);
		
		return u;
	}

	public boolean access(User u, String pw) {
		try {
			l = (LoginRemote) InitialContext.doLookup("/WaF9a-ear/WaF9a-ejb/Login!service.LoginRemote");

			System.out.println("dddddddddddddddddddddddddddddddddddddddd");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l.access(u, pw);
	}

}
