package buisness;

import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Discussion;
import entities.DiscussionReport;
import service.ReportedDiscussionsRemote;

public class DiscussionReportsBuisness {

	private InitialContext ctx;
	private ReportedDiscussionsRemote proxy;
	
	public DiscussionReportsBuisness() throws NamingException {
		ctx = new InitialContext();
		proxy=(ReportedDiscussionsRemote)  ctx.lookup("/WaF9a-ear/WaF9a-ejb/ReportedDiscussions!service.ReportedDiscussionsRemote");
	}
	
	public Set<Discussion> getAllReportedDiscussions(){	
		return proxy.getAllReportedDiscussions();
	}
	
	public void deleteDiscussion(Discussion d) {
		proxy.deleteDiscussion(d);
	}
	
	public void declineDiscussionReported(DiscussionReport d) {
		proxy.declineReportedDiscussion(d);		
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		ctx.close();
		ctx=null;
		super.finalize();
	}
}
