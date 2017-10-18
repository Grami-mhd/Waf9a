package FX;

import javafx.fxml.FXML;
import javafx.geometry.Insets;

import java.util.List;
import java.util.Set;

import buisness.CommentReportsBusiness;
import entities.Comment;
import entities.CommentReport;
import entities.Notification;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.Event;

public class ViewCommentReportedController {
	@FXML
	private CheckBox notify;
	@FXML
	private CheckBox deleteComment;
	@FXML
	private CheckBox deleteAccount;
	@FXML
	private VBox users;
	@FXML
	private TextArea comment;

	private CommentReportsBusiness crb;
	public static Comment c;
	private Comment c1;

	@FXML
	public void initialize() {
		this.c1 = c;
		comment.setText(c1.getText());

		for (CommentReport cr : c1.getReportes()) {
			HBox h = new HBox();
			Label l = new Label(cr.getReporter().getFirstName() + " " + cr.getReporter().getLastName());
			Label r = new Label(" Reason: ");
			l.setCursor(Cursor.HAND);
			l.setPadding(new Insets(10, 0, 10, 0));
			l.setMinWidth(150);
			l.setFont(Font.font("Bold", 16));
			l.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					 new Profile().showProfile(cr.getReporter());
				}

			});

			r.setFont(Font.font("Bold", 16));
			r.setMinWidth(100);
			r.setPadding(new Insets(10, 0, 10, 0));
			
			h.getChildren().add(new ImageView("images/profile.png"));
			h.getChildren().add(l);
			h.getChildren().add(r);

			TextArea txt = new TextArea(cr.getReason());
			txt.setEditable(false);
			txt.setMaxHeight(20);
			txt.maxWidth(100);
			txt.setPadding(new Insets(10, 0, 10, 0));
			h.getChildren().add(txt);

			Separator sep = new Separator();
			sep.setPadding(new Insets(10, 0, 10, 0));
			users.getChildren().add(h);
			users.getChildren().add(sep);
		}
		crb = new CommentReportsBusiness();
	}

	// Event Listener on Button.onAction
	@FXML
	public void decline(ActionEvent event) {

		Set<CommentReport> lcr = c1.getReportes();
		for (CommentReport cr : lcr) {
			cr.setTreated(true);
			crb.declineCommentRepored(cr);
		}

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();

	}

	// Event Listener on Button.onAction
	@FXML
	public void validate(ActionEvent event) {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();

		if (notify.isSelected()) {
			Notification n = new Notification();
			n.setUser(c1.getOwner());
			n.setType("of" + c1.getOffer().getOfferID());
			n.setText(" Your comment been reported ");
			crb.addNotification(n);
		}

		if (deleteAccount.isSelected()) {
			c1.getOwner().setPremium(-1);
			crb.blockCommentOwner(c1.getOwner());
		}

		if (deleteComment.isSelected() == true) {
			crb.deleteCommentReported(c1);
			return;
		}

		Set<CommentReport> lcr = c1.getReportes();
		for (CommentReport cr : lcr) {
			cr.setTreated(true);
			crb.declineCommentRepored(cr);
		}

	}
}