package buisness;

import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.User;
import service.UsersApproveRemote;

public class UserApprovesBuisnessDelegate {
	
	private InitialContext ctx;
	private UsersApproveRemote proxy;
	
	public UserApprovesBuisnessDelegate() {
		
		 try {
				
				ctx = new InitialContext();	
				proxy= (UsersApproveRemote) ctx.lookup("/WaF9a-ear/WaF9a-ejb/UsersApprove!service.UsersApproveRemote");
			
		
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
public Map<String,Map<String,Set<User>>> getUsersApprovePerExpertise(){
		
		return proxy.getUsersApprovePerExpertise();
	}
@Override
protected void finalize() throws Throwable {
	// TODO Auto-generated method stub
	ctx.close();
	ctx=null;
	super.finalize();
}
}
