package FX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import notifs.NotificationsBuisness;
import service.LoginRemote;
import webSocket.AdminDiscussionsClient;
import webSocket.ExpertiseDemandesClient;

import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import buisness.LoginBuisness;
import entities.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

//import javax.mail.Session;

public class LoginController {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Label error;
	@FXML
	private ImageView img;

	@FXML
	private Label error1;

	public static User u;
	private User u1;

	// Event Listener on Button.onAction
	@FXML
	public void loginAction(ActionEvent event) {

		if (username.getText().equals("")) {
			error.setText("Fill in username");
			return;
		}
		if (password.getText().equals("")) {
			error.setText("Fill in password");
			return;
		}

		LoginBuisness l = new LoginBuisness();
		User u = l.checkUserName(username.getText());
		if (u != null) {
			if (l.access(u, password.getText())) {
				if (u.getPremium() == -1)
					error.setText("Account blocked");
				else if (u.isAdmin()) {

					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.close();
					Stage primaryStage = new Stage();
					primaryStage.setTitle("Admin Dashboard");
					primaryStage.getIcons().add(new Image("images/waf9a.jpg"));
					AdminDiscussionsClient.userName=username.getText();
					primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

						@Override
						public void handle(WindowEvent event) {
							ExpertiseDemandesClient.disconnect();
							AdminDiscussionsClient.disconnect();
						}
					});
					AnchorPane root;
					try {

						root = (AnchorPane) FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
						Scene scene = new Scene(root);
						primaryStage.setScene(scene);
						primaryStage.setMaximized(true);
						primaryStage.show();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else
					error.setText("Not An Admin");
			} else
				error.setText("wrong Password");
		} else
			error.setText("wrong username");

	}

	@FXML
	public void forgetpwd(MouseEvent event) {
		// String f =username.getText();
		// System.out.println(f);

		// username.setText(u1.getEmail());

		// u1.setEmail(f);

		Stage primaryStage = new Stage();
		AnchorPane root;
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("ForgetPass.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Mail.sendMail("sara.chebbi.5@gmail.com", "Password Recovery", "Your
		// password is "+u1.getPassword());
		// buisness.Email.sendmail();

	}

}
