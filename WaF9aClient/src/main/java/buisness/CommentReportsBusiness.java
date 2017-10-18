package buisness;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Comment;
import entities.CommentReport;
import entities.Notification;
import entities.Offer;
import entities.User;
import service.ReportedCommentsRemote;

public class CommentReportsBusiness {

	private InitialContext ctx;
	private ReportedCommentsRemote proxy;
	private List<Offer> l;
	
	
	public CommentReportsBusiness() {
		try {
			ctx = new InitialContext();
			proxy=(ReportedCommentsRemote)  ctx.lookup("/WaF9a-ear/WaF9a-ejb/ReportedComments!service.ReportedCommentsRemote");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l =proxy.getAllReportedoffers();
	}

	public Offer getReportedOffer(int i){			
		return  l.get(i);		
	}
	
	public int size(){
		return l.size();
	}
	
	public void declineCommentRepored(CommentReport c){
		proxy.declineComment(c);
	}
	
	public void deleteCommentReported(Comment c){
		proxy.deleteComment(c);
	}
	
	public void blockCommentOwner(User c){
		proxy.blockCommentOwner(c);
	}
	
	public void addNotification(Notification n){
		proxy.addNotify(n);
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		ctx.close();
		ctx=null;
		super.finalize();
	}
}
