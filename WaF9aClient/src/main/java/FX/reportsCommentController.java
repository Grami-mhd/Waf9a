package FX;

import java.io.IOException;

import org.omg.CORBA.INITIALIZE;

import buisness.CommentReportsBusiness;
import entities.Comment;
import entities.CommentReport;
import entities.DiscussionReport;
import entities.Offer;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class reportsCommentController {
	@FXML
	private TextArea offer;
	@FXML
	private Label date;
	@FXML
	private Label user;
	@FXML
	private VBox comments;
	
	private CommentReportsBusiness crb;

	@FXML
	public void initialize() throws IOException {
		user.setCursor(Cursor.HAND);
		crb = new CommentReportsBusiness();
		if (crb.size() != 0) {
			Offer off = crb.getReportedOffer(0);

			offer.setText(off.getText());
			date.setText(off.getDate().toString());
			user.setText(off.getOwner().getFirstName() + " " + off.getOwner().getLastName());
			user.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					 new Profile().showProfile(off.getOwner());
				}
			});

			for (Comment c : off.getComments()) {
				commentController.c = c;
				comments.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getResource("comment.fxml")));
			}
		}
	}

	
}
