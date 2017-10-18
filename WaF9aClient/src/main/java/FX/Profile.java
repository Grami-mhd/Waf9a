package FX;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

import entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Profile{

	public  void showProfile(User u){
		try {
			
			ResourceBundle r = new ResourceBundle(){

				@Override
				protected Object handleGetObject(String key) {
					// TODO Auto-generated method stub
					return u;
				}

				@Override
				public Enumeration<String> getKeys() {
					// TODO Auto-generated method stub
					Enumeration<String> unum = new Enumeration<String>() {
						
						@Override
						public String nextElement() {
							// TODO Auto-generated method stub
							return "user";
						}
						
						@Override
						public boolean hasMoreElements() {
							// TODO Auto-generated method stub
							return false;
						}
					};
					return unum;
				}
				
			};
			Stage primaryStage = new Stage();
			AnchorPane root;
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("Profile.fxml"),r);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(u.getUserName());
			primaryStage.getIcons().add(new Image("images/waf9a.jpg"));
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
