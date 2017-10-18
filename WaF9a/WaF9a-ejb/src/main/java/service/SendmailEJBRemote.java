package service;

import javax.ejb.Remote;

@Remote
public interface SendmailEJBRemote {
	public void send(String too,String subject,String txt);
}
