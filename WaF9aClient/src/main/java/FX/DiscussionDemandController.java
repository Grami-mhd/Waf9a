package FX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Optional;

import buisness.DiscussionsCreationBusiness;
import entities.Discussion;
import entities.Notification;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;


public class DiscussionDemandController {
	@FXML
	private TextArea discussionDemand;
	
	
	public static Discussion d;
	private Discussion disc;
	
	DiscussionsCreationBusiness db = new DiscussionsCreationBusiness();
	
	@FXML
	public void initialize(){
		
		disc =d;
		
		discussionDemand.setText("User "+ disc.getOwner().getFirstName()+" "+disc.getOwner().getFirstName() +" has demanded to create a discussion named : "+disc.getTopic());
		
	}

	// Event Listener on Button.onAction
	@FXML
	public void decline(ActionEvent event) {
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		//alert.setHeaderText("Look, a Confirmation Dialog");
		alert.setContentText("By declining you are going to delete this discussion , are you sure you want to proceed ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			//notif
			Notification n = new Notification();
			n.setUser(d.getOwner());
			n.setText("your demand to create the discussion named <<"+d.getTopic()+">> has been declined !!");
			n.setType("di"+d.getDiscussionID());
			db.Notify(n);
			//deleting
			db.decline(d);
			
			AdminHomeController.vbox1.getChildren().clear();
			for(Discussion d : new DiscussionsCreationBusiness().getDemands()){
				DiscussionDemandController.d=d;
		    			AnchorPane a;
						try {
							a = (AnchorPane)FXMLLoader.load(getClass().getResource("DiscussionDemand.fxml"));
						
							AdminHomeController.vbox1.getChildren().add(a);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			
						
		    			
			}
		} else {
		    // ... user chose CANCEL or closed the dialog
		}

		
	}
	// Event Listener on Button.onAction
	@FXML
	public void accept(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		//alert.setHeaderText("Look, a Confirmation Dialog");
		alert.setContentText("Are you sure you want to accept the creation of this discussion?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			//notif
			Notification n = new Notification();
			n.setUser(d.getOwner());
			n.setText("your demand to create the discussion named <<"+d.getTopic()+">> has been accepted !!");
			db.Notify(n);
			//accept
			db.accept(d);
			AdminHomeController.vbox1.getChildren().clear();
			for(Discussion d : new DiscussionsCreationBusiness().getDemands()){
				DiscussionDemandController.d=d;
		    			AnchorPane a;
						try {
							a = (AnchorPane)FXMLLoader.load(getClass().getResource("DiscussionDemand.fxml"));
						
							AdminHomeController.vbox1.getChildren().add(a);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		
						
						
			}
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
		}

		
	
	// Event Listener on Button.onAction
	@FXML
	public void viewProfile(ActionEvent event) {
		new Profile().showProfile(d.getOwner());
	}
}
