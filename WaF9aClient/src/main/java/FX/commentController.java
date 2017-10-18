package FX;

import java.io.IOException;

import entities.Comment;
import entities.CommentReport;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class commentController {
	@FXML
	private Label userC;
	@FXML
	private Label dateC;
	@FXML
	private Label price;
	@FXML
	private Label delay;
	@FXML
	private TextArea commentC;

	public static Comment c;
	private Comment c1;

	@FXML
	public void initialize() {
		c1 = c;
		userC.setText(c1.getOwner().getFirstName() + ", " + c1.getOwner().getLastName());
		userC.setCursor(Cursor.HAND);
		userC.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				 new Profile().showProfile(c1.getOwner());
			}
		});
		delay.setText(c1.getDelay()+" day(s)");
		price.setText(""+c1.getPrice());
		dateC.setText(c1.getDate().toString());
		commentC.setText(c1.getText());
		if (!c1.getReportes().isEmpty()) {
			commentC.setStyle("-fx-background-color: red");
			commentC.setCursor(Cursor.HAND);
			commentC.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					ViewCommentReportedController.c= c1;
					
					Stage primaryStage = new Stage();
					AnchorPane root;
					try {
						root = (AnchorPane)FXMLLoader.load(getClass().getResource("ViewCommentReported.fxml"));
						Scene scene = new Scene(root);
						primaryStage.setScene(scene);
						primaryStage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}

	}
}