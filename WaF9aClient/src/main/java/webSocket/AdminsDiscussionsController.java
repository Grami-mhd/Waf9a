package webSocket;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.AdminMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.ScrollEvent;

public class AdminsDiscussionsController implements Initializable {
	@FXML
	private VBox vbox;
	@FXML
	private TextArea msg;

	@FXML
	private ScrollPane scrol;
	private static ScrollPane ss;
	private static VBox messages;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		messages = vbox;
		ss=scrol;
		scrol.setVvalue(1.0);
		AdminDiscussionsClient.connect();
	}

	// Event Listener on ScrollPane.onScrollFinished
	@FXML
	public void scrollFinished(ScrollEvent event) {
		System.out.println("scollllllll end ");
	}

	// Event Listener on Button.onAction
	@FXML
	public void send(ActionEvent event) {
		AdminDiscussionsClient.send(msg.getText());
		msg.setText("");
	}

	public static void addMessage(AdminMessage msg) {
		
		try {
			msgController.adm=msg;
			AnchorPane a=(AnchorPane) FXMLLoader.load(AdminsDiscussionsController.class.getResource("msg.fxml"));
			messages.getChildren().add(a);	
			ss.setVvalue(1.0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
