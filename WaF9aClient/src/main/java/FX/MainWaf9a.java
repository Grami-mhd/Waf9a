package FX;

	
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

import javafx.application.Application;
import javafx.stage.Stage;
import webSocket.ExpertiseDemandesClient;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class MainWaf9a extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setWidth(738);
			primaryStage.setHeight(495);
			primaryStage.setTitle("Welcome to WaF9a Admin Dashboard");
			primaryStage.getIcons().add(new Image("images/waf9a.jpg"));
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
