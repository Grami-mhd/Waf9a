package buisness;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.DoneJob;
import entities.Offer;
import entities.User;
import service.TodayEjbRemote;

public class TodayBuisness {

	private InitialContext ctx;
	private TodayEjbRemote l;
	public TodayBuisness() {
		try {
			ctx = new InitialContext();
			l=(TodayEjbRemote)  ctx.lookup("/WaF9a-ear/WaF9a-ejb/TodayEjb!service.TodayEjbRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<User> getNewUsers(){
		
		return l.getNewUsers();
	}
	public List<Offer> getNewOffers(){
		
		return l.getNewOffers();
	}
	public List<DoneJob> getNewDoneJobs(){
		
		return l.getNewDoneJobs();
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		ctx.close();
		ctx=null;
		super.finalize();
	}
}
