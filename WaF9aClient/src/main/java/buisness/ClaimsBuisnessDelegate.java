package buisness;



import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import FX.ClaimsController1;
import entities.Claim;
import entities.DoneJob;
import entities.Notification;
import entities.User;
import service.ClaimsRemote;


public class ClaimsBuisnessDelegate {
	
	private InitialContext ctx;
	private ClaimsRemote proxy;
	
	public ClaimsBuisnessDelegate(){
		 try {
			
				ctx = new InitialContext();	
				proxy= (ClaimsRemote) ctx.lookup("/WaF9a-ear/WaF9a-ejb/Claims!service.ClaimsRemote");
			
		
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	public Claim getNextNotTreatedClaim(){
		
		return proxy.getNextNotTreatedClaim();
	}
	
	public void claimIsTreated(Claim c){
		proxy.claimIsTreated(c);
	}
	
	public int claimNumber(User u){
		return  proxy.claimNumber(u);
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		ctx.close();
		ctx=null;
		super.finalize();
	}
	
	public int numberOffers(User u){
		return proxy.numberOffers(u);
	}
	
	public int numberDoneJobs(User u){
		return proxy.numberDoneJobs(u);
	}
	
	public void blockUser(User u){
		proxy.blockUser(u);
	}
	
	public void ajoutNotif(Notification n){
		proxy.ajoutNotif(n);
	}
	
	public void decreasePrice(DoneJob x){
		proxy.decreasePrice(x);
	}
	
}

