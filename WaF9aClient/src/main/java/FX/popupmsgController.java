package FX;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;

import java.util.List;
import java.util.Set;

import org.controlsfx.control.Notifications;

import buisness.popupBuisness;
import entities.Message;
import entities.MessageReport;
import entities.Notification;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class popupmsgController {
	@FXML
	private VBox vboxkol;
	@FXML
	private TextArea msgr;
	@FXML
	private HBox hboxreportedby;
	@FXML
	private ScrollPane reportedby;
	@FXML
	private VBox reports;
	@FXML
	private VBox vboxcheckbox;
	@FXML
	private CheckBox notify;
	@FXML
	private CheckBox msgdelete;
	@FXML
	private CheckBox discudelete;
	@FXML
	private CheckBox acountdelete;
	@FXML
	private Button apply;

	public static Message m;
	private Message m1;

	public static MessageReport mr;

	private popupBuisness p;

	@FXML
	public void initialize() {

		p = new popupBuisness();

		m1 = m;
		msgr.setText(m1.getText());
		for (MessageReport mr : m1.getReportes()) {
			HBox h = new HBox();
			Label l = new Label("username :" + mr.getReporter().getUserName() + ", reason:");
			l.setCursor(Cursor.HAND);
			l.setPadding(new Insets(10, 20, 0, 30));
			l.setFont(Font.font("Bold", 16));
			l.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					 new Profile().showProfile(m1.getOwner());
				}

			});

			h.getChildren().add(new ImageView("images/profile.png"));
			h.getChildren().add(l);
			TextArea txt = new TextArea(mr.getReason());
			txt.setEditable(false);
			txt.setMaxHeight(40);
			h.getChildren().add(txt);

			Separator sep = new Separator();
			sep.setPadding(new Insets(10, 0, 10, 0));
			reports.getChildren().add(h);
			reports.getChildren().add(sep);
		}

	}

	// Event Listener on Button.onAction
	@FXML
	public void decline(ActionEvent event) {
		// TODO Autogenerated

		
	
		
		Set<MessageReport> lcr = m1.getReportes();
		for (MessageReport cr : lcr) {
			cr.setTreated(true);
			p.declineMessageRepored(cr);
		}

		
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		
		stage.close();
		Notifications nb = Notifications.create().darkStyle().hideAfter(Duration.seconds(5)).title("Update")
				.text("Message has been treated!").graphic(vboxkol).position(Pos.CENTER).hideCloseButton()
				.onAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						//MainApp.s.close();
					}
				});
		nb.showConfirm(); 

	}

	// Event Listener on Button[#apply].onAction
	@FXML
	public void validate(ActionEvent event) {
		// TODO Autogenerated
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		if (notify.isSelected()) {

			Notification n = new Notification();
			n.setUser(m1.getOwner());
			n.setText("Your Msg:'" + m1.getText() + "' has been reported ");
			n.setType("di" + m1.getDiscussion().getDiscussionID());

			p.addnotify(n);
			Notifications nb = Notifications.create().darkStyle().hideAfter(Duration.seconds(5)).title("Update")
					.text("Notification has been sent !").graphic(vboxkol).position(Pos.CENTER).hideCloseButton()
					.onAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							//MainApp.s.close();
						}
					});
			nb.showConfirm();

		}
		if (acountdelete.isSelected()) {
			p.blockacount(m1.getOwner());
			Notifications nb = Notifications.create().darkStyle().hideAfter(Duration.seconds(5)).title("Update")
					.text("Acount blocked").graphic(vboxkol).position(Pos.CENTER).hideCloseButton()
					.onAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							//MainApp.s.close();
						}
					});
			nb.showConfirm();
			
		}

		if (discudelete.isSelected()) {
			p.deleteDiscu(m1.getDiscussion());
			Notifications nb = Notifications.create().darkStyle().hideAfter(Duration.seconds(5)).title("Update")
					.text("Discussion has been deleted!").graphic(vboxkol).position(Pos.CENTER).hideCloseButton()
					.onAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							//MainApp.s.close();
						}
					});
			nb.showConfirm();
			return;
		}

		if (msgdelete.isSelected()) {
			p.deleteMsg(m1);
		
			Notifications nb = Notifications.create().darkStyle().hideAfter(Duration.seconds(5)).title("Update")
					.text("Message has been deleted!").graphic(vboxkol).position(Pos.CENTER).hideCloseButton()
					.onAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							//MainApp.s.close();
						}
					});
			nb.showConfirm();
			return;
		}

		Set<MessageReport> lcr = m1.getReportes();
		for (MessageReport cr : lcr) {
			cr.setTreated(true);
			p.declineMessageRepored(cr);
		}

	}
}
