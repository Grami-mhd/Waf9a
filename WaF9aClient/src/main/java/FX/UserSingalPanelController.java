package FX;

import javafx.fxml.FXML;

import java.util.Optional;

import buisness.UserManageBusiness;
import entities.Expertise;
import entities.Notification;
import entities.User;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;

public class UserSingalPanelController {
	@FXML
	private Label UserNameLab;
	@FXML
	private Label EmailLab;
	@FXML
	private Label PhoneLab;
	@FXML
	private TextArea exp;
	@FXML
	private CheckBox ad;
	public static User u;
	private User us;

	// Event Listener on Label[#UserNameLab].onMouseClicked
	@FXML
	public void profile(MouseEvent event) {
		new Profile().showProfile(us);
	}
	// Event Listener on CheckBox[#ad].onAction
	@FXML
	public void ToAdmin(ActionEvent event) {
		UserManageBusiness uS = new UserManageBusiness();
		if(ad.isSelected()){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("normal user to admin");
			alert.setContentText("Are you ok with this?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				
				us.setAdmin(true);
				uS.updateUser(us);
				
				Notification n = new Notification();
				n.setUser(u);
				n.setText("user is now admin");
				uS.addNotif(n);
				System.out.println(n.getText());
				System.out.println("User has been notified");
			} else {
			    // ... user chose CANCEL or closed the dialog
			}
			
		}
		else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("admin to normal user");
			alert.setContentText("Are you ok with this?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			    // ... user chose OK
				us.setAdmin(false);
				uS.updateUser(us);
				
				Notification n = new Notification();
				n.setUser(u);
				n.setText("user is now normal");
				uS.addNotif(n);
				System.out.println(n.getText());
				System.out.println("User has been notified");
			} else {
			    // ... user chose CANCEL or closed the dialog
			}
			
		}
	
	System.out.println("admin true");
	}
	public void initialize() {
		us=u;
		if(us.isAdmin()==true){
			ad.setSelected(true);
		}
		else {
			ad.setSelected(false);
		}
		
		UserNameLab.setText(us.getFirstName()+" "+us.getLastName());
		EmailLab.setText(us.getEmail());
		PhoneLab.setText(us.getPhone()+"");
		int i=1;
		String a="";
		for (Expertise ex :u.getExpertises()){
			a=a+"domain "+i+" : "+(ex.getDomain())+"\n";
			exp.setText(a);
			i=i+1;
		}
		
    }
}
