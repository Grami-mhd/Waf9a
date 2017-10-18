package buisness;


import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import FX.DiscussionDemandController;
import entities.Discussion;
import entities.Notification;
import service.DiscussionCreationRemote;


public class DiscussionsCreationBusiness {

	private InitialContext ctx;
	private DiscussionCreationRemote proxy;
	
	public DiscussionsCreationBusiness() {
		try {
			ctx = new InitialContext();

			proxy=(DiscussionCreationRemote) ctx.lookup("/WaF9a-ear/WaF9a-ejb/DiscussionCreation!service.DiscussionCreationRemote");
		
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Set<Discussion> getDemands(){
		return proxy.getAllDiscussionDemands();
	}
	
	public void accept(Discussion d){
		proxy.acceptDemand(d);
	}
	
	public void decline(Discussion d){
		proxy.declineDemand(d);
		
	}
	
	public void Notify(Notification n){
		proxy.Notif(n);
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		ctx.close();
		ctx=null;
		super.finalize();
	}
}
