package FX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.scene.control.TextField;

import java.io.IOException;

import javax.naming.NamingException;

import buisness.UserManageBusiness;
import entities.Discussion;
import entities.User;
import javafx.event.ActionEvent;

import javafx.scene.layout.VBox;

public class ManageUserBigPanelController {
	@FXML
	private VBox vboxiiiii;
	@FXML
	private TextField mot;
	@FXML
	private HBox hboxiii;
	private String motji;
	private  UserManageBusiness umb;

	// Event Listener on Button.onAction
	@FXML
	public void search(ActionEvent event) {
		motji=mot.getText();
		
		hboxiii.getChildren().clear();
		for(User u1 : umb.getSearshedUsers(motji)){
			UserSingalPanelController.u=u1;
			try {
				hboxiii.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("UserSingalPanel.fxml")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
		
	}
	
	@FXML
	public void initialize() {
		 umb = new UserManageBusiness();
		vboxiiiii.getChildren().clear();
		for(User u : umb.getPremiumDemand()){
			try {
				UserDemandPanelController.u=u;
				vboxiiiii.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("UserDemandPanel.fxml")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
}
	}}
