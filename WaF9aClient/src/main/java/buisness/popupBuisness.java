package buisness;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Claim;
import entities.Discussion;
import entities.Message;
import entities.MessageReport;
import entities.Notification;
import entities.Reply;
import entities.ReplyReport;
import entities.User;
import service.ClaimsRemote;
import service.PopupServiceRemote;

public class popupBuisness {
	private InitialContext ctx;
	private PopupServiceRemote proxy;
	
	public  popupBuisness()  {
		try {
			ctx = new InitialContext();
			proxy= (PopupServiceRemote) ctx.lookup("/WaF9a-ear/WaF9a-ejb/PopupService!service.PopupServiceRemote");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
	}

	public void addnotify(Notification n){
		proxy.addnotify(n);
		
	}
	public void deleteMsg(Message dm){
		proxy.deleteMsg(dm);
	}
	public void deleteReplies(Reply dp){
		proxy.deleteReplies(dp);
	}
	public void deleteDiscu( Discussion dd){
		proxy.deleteDiscu(dd);
	}
	public void blockacount(User bu ){
		proxy.blockacount(bu);
	}
	public void declineMessageRepored(MessageReport mr)
	{
		proxy.update(mr);
	}
	 

	public void declineReplyRepored(ReplyReport rr) {
		// TODO Auto-generated method stub
		proxy.updatereplies(rr);

	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		ctx.close();
		ctx=null;
		super.finalize();
	}
	
}
