package buisness;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Discussion;
import entities.DoneJob;
import entities.Notification;
import entities.Offer;
import entities.User;
import service.ReportedDiscussionsRemote;
import service.UserManageRemote;

public class UserManageBusiness {
	
	private InitialContext ctx;
	private UserManageRemote proxy;
	
	public UserManageBusiness() {
		try {
			ctx = new InitialContext();
			proxy=(UserManageRemote)  ctx.lookup("/WaF9a-ear/WaF9a-ejb/UserManage!service.UserManageRemote");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		public List<User> getPremiumDemand(){
			
			return proxy.getPremiumDemand();
		}
		
		public void updateUser(User o){
			proxy.updatePerson(o);
		}
		
		public List<User> getSearshedUsers(String keyWord ){
			
			return proxy.getSearshedUsers(keyWord);
		}
		
		public List<User> getActiveUsers(){
			
			return proxy.getActiveUsers();
		}
	
		public List<Offer> getOffers(){
			
			return proxy.getOffers();
		}
		
		public List<DoneJob> getDoneJobs(){
			
			return proxy.getDoneJobs();
		}
		
		public Notification addNotif(Notification not){
			
			return proxy.addNotif(not);
		}
		@Override
		protected void finalize() throws Throwable {
			// TODO Auto-generated method stub
			ctx.close();
			ctx=null;
			super.finalize();
		}

}
