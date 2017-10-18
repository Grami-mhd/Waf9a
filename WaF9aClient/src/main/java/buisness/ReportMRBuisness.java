package buisness;

import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Discussion;
import service.DiscussionsMRRemote;
import service.ReportedDiscussionsRemote;
 

public class ReportMRBuisness {
	
	private List<Discussion> discu;
	
	private InitialContext ctx;
	private DiscussionsMRRemote proxy;
	
	public ReportMRBuisness() {
		try {
			ctx = new InitialContext();
			proxy=  (DiscussionsMRRemote) ctx.lookup("/WaF9a-ear/WaF9a-ejb/DiscussionsMR!service.DiscussionsMRRemote");
		
		} catch (NamingException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}
	discu=proxy.getDiscussions();
		
	}
	
	public Discussion getDiscussions(int i){
		if(discu.isEmpty())
			return null;
		return discu.get(i);
	}

	public int getCount(){
		return discu.size();
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		ctx.close();
		ctx=null;
		super.finalize();
	}
}
