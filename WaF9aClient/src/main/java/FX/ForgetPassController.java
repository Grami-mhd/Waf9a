package FX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.LoginRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Notifications;
import org.jboss.sasl.util.UsernamePasswordHashUtil;

import buisness.Mail;
import entities.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ForgetPassController {
	@FXML
	private TextField username;
	@FXML
	private TextField emaila;
	@FXML
	private Button sendmail;
	@FXML
	private Label erreur; 
	

	
	// Event Listener on Button[#sendmail].onAction
	@FXML
	public void sendmail(ActionEvent event) {
		
		
		
		InitialContext ctx;
		
			try {
				ctx = new InitialContext();
				Object obj = ctx.lookup("/WaF9a-ear/WaF9a-ejb/Login!service.LoginRemote");

				LoginRemote l = (LoginRemote) obj;
				User u = l.checkUserName(username.getText());
				if(u!=null){
					if(u.getEmail().equals(emaila.getText())){
						
						erreur.setText("");
						Mail.sendMail(u.getEmail(), "Password Recovery", "Your password is  "+u.getPassword());

						Notifications nb = Notifications.create().darkStyle().hideAfter(Duration.seconds(5)).title("Update")
								.text(" your password has been sent to you via Email!").graphic(username).position(Pos.CENTER).hideCloseButton()
								.onAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
									}
								});
						nb.showConfirm();
						
					}else{
						erreur.setText("Wrong email");
					}
					
					
					
				}else{
					erreur.setText("username not found");
				}
			
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();

		
		
		
	}
}
