package service;

import javax.ejb.Remote;

@Remote
public interface NotificationsAdminEJBRemote {

	public int getMUser();
	public int getDiscs();
	public int getClaims();
	public int getExpertises();
	
}
