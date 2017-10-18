package buisness;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.User;
import service.ProfileJPA;
import service.ProfileJPARemote;
import service.ReportedDiscussionsRemote;

public class ProfileBuisness {

	InitialContext ctx;
	private ProfileJPARemote proxy;
	
	public ProfileBuisness() {
		try {
			ctx = new InitialContext();
			proxy=(ProfileJPARemote)  ctx.lookup("/WaF9a-ear/WaF9a-ejb/ProfileJPA!service.ProfileJPARemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}	
	}
	
	public User fill(User u){
		return proxy.fill(u);
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		ctx.close();
		ctx=null;
		super.finalize();
	}
}
