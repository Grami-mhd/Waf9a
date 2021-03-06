package FX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Optional;

import buisness.UserManageBusiness;
import entities.Expertise;
import entities.Notification;
import entities.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class UserDemandPanelController {
	@FXML
	private TextArea exper;
	@FXML
	private Label userName;
	public static User u;
	private User u1;

	// Event Listener on Label[#userName].onMouseClicked
	@FXML
	public void profile(MouseEvent event) {
		new Profile().showProfile(u1);
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void accept(ActionEvent event) {
		// TODO Autogenerated
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("this User "+u.getUserName()+" will be premium");
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    // ... user chose OK
			UserManageBusiness ub = new UserManageBusiness();
			u1.setPremium(2);
			ub.updateUser(u1);
			AdminHomeController.vbox1.getChildren().clear();
			AnchorPane a;
			try {
				a = (AnchorPane)FXMLLoader.load(getClass().getResource("ManageUserBigPanel.fxml"));
				AdminHomeController.vbox1.getChildren().add(a);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Notification n = new Notification();
			n.setUser(u1);
			n.setText("user is now premium");
			ub.addNotif(n);
			System.out.println(n.getText());
			System.out.println("User has been notified");
		System.out.println("done 2");
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void decline(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("decline  Premium demand");
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    // ... user chose OK
			UserManageBusiness ub = new UserManageBusiness();
			u1.setPremium(0);
			ub.updateUser(u1);
		
		
		System.out.println("done 0");
		AdminHomeController.vbox1.getChildren().clear();
		AnchorPane a;
		try {
			a = (AnchorPane)FXMLLoader.load(getClass().getResource("ManageUserBigPanel.fxml"));
			AdminHomeController.vbox1.getChildren().add(a);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Notification n = new Notification();
		n.setUser(u1);
		n.setText("premium demand has been declined");
		ub.addNotif(n);
		System.out.println(n.getText());
		System.out.println("User has been notified");
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
	}
	public void initialize() {
		u1=u;
		userName.setText(u1.getFirstName()+" "+u1.getLastName());
		
		int i=1;
		String a="";
		for (Expertise ex :u.getExpertises()){
			a=a+"domain "+i+" : "+(ex.getDomain())+"\n";
			exper.setText(a);
			i=i+1;
		}
    }
}
