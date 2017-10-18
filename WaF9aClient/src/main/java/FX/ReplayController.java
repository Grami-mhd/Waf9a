package FX;

import java.io.IOException;

import entities.Reply;
import entities.ReplyReport;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

public class ReplayController {
	@FXML
	private Label userName;
	@FXML
	private Label date;
	@FXML
	private TextArea reason;

	public static Reply r;
	private Reply r1;
	
	@FXML
	public void initialize(){
		
	r1= r ; 
	if(!r.getReportes().isEmpty()){
		boolean bo =false;
		for( ReplyReport rr :r.getReportes())
			if(!rr.getTreated())
				bo=true;
		if(bo){
		reason.setStyle("-fx-background-color: red");
		reason.setCursor(Cursor.HAND);
		reason.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {

				popuprelpyController.r=r1;
				
				Stage primaryStage = new Stage();
				AnchorPane root;
				try {
					root = (AnchorPane)FXMLLoader.load(getClass().getResource("popuprelpy.fxml"));
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		
		});
	}}
	
		reason.setText(r1.getText());
		userName.setText(r1.getOwner().getUserName());
		date.setText(r1.getDate().toString());
	}
	// Event Listener on Label[#userName].onMouseClicked
	@FXML
	public void actionbt(MouseEvent event) {
		new Profile().showProfile(r1.getOwner());
	}
}
