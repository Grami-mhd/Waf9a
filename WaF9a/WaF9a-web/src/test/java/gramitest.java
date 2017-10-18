import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entities.Notification;
import entities.User;
import service.UserManageRemote;
@ManagedBean
@RequestScoped
public class gramitest {

	@EJB
	private UserManageRemote usm;
	
	public  void main (){
		Notification n = new Notification();
		n.setText("test notification");
		User u = new User();
		u.setId(1);
		u.setUserName("11");
		n.setUser(u);
		usm.addNotif(n);
	}
}
