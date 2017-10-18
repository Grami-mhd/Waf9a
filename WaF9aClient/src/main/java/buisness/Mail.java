package buisness;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import service.SendmailEJBRemote;

public abstract class Mail {
	
	public static void sendMail(String to, String subject, String text){
		
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			Object obj =  ctx.lookup("/WaF9a-ear/WaF9a-ejb/SendmailEJB!service.SendmailEJBRemote");
			SendmailEJBRemote e =(SendmailEJBRemote) obj;
			
			e.send(to, subject, text);
			ctx.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
