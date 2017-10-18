package FX;

import java.io.IOException;

import entities.Discussion;
import entities.DiscussionReport;
import entities.Message;
import entities.Reply;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ViewDiscussionReportedController {
	@FXML
	private Label user;
	@FXML
	private Label date;
	@FXML
	private TextArea topic;
	@FXML
	private VBox messages;

	public static Discussion d;

	@FXML
	public void initialize() {
		user.setText(d.getOwner().getUserName());
		user.setCursor(Cursor.HAND);

		date.setText(d.getDate().toString());
		topic.setText(d.getTopic());
		topic.setEditable(false);
		for (Message m : d.getMessages()) {
			VBox v = new VBox();
			Label l = new Label("Owner :" + m.getOwner().getUserName());
			l.setCursor(Cursor.HAND);
			l.setPadding(new Insets(0, 20, 10, 30));
			l.setFont(Font.font("Bold", 18));
			HBox h = new HBox();

			h.getChildren().add(l);
			Label d = new Label("Date :" + m.getDate().toString());
			d.setPadding(new Insets(0, 20, 10, 30));
			d.setFont(Font.font("Bold", 18));
			h.getChildren().add(d);
			
			l.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					 new Profile().showProfile(m.getOwner());

				}

			});
			TextArea txt = new TextArea(m.getText());
			txt.setEditable(false);
			txt.setMaxHeight(30);
			v.getChildren().add(h);
			v.getChildren().add(txt);
			messages.getChildren().add(v);
			if (!m.getReplies().isEmpty()) {
				for (Reply r : m.getReplies()) {
					VBox v1 = new VBox();
					Label l1 = new Label("Owner :" + r.getOwner().getUserName());
					l1.setCursor(Cursor.HAND);
					l1.setPadding(new Insets(0, 20, 10, 30));
					l1.setFont(Font.font("Bold", 16));
					
					HBox h1 = new HBox();
					h1.getChildren().add(l1);
					
					Label d1 = new Label("Date :" + r.getDate().toString());
					d1.setCursor(Cursor.HAND);
					d1.setPadding(new Insets(0, 20, 10, 30));
					d1.setFont(Font.font("Bold", 16));
					h1.getChildren().add(d1);
					l1.setOnMouseClicked(new EventHandler<Event>() {
						@Override
						public void handle(Event event) {
							 new Profile().showProfile(r.getOwner());

						}

					});
					TextArea txt1 = new TextArea(r.getText());
					txt1.setEditable(false);
					txt1.setMaxHeight(30);
					v1.getChildren().add(h1);
					v1.getChildren().add(txt1);
					v1.setPadding(new Insets(0, 0, 10, 30));
					messages.getChildren().add(v1);
				}
			}

			Separator sep = new Separator();
			sep.setPadding(new Insets(10, 0, 10, 0));
			messages.getChildren().add(sep);
		}
	}

	// Event Listener on Label[#user].onMouseClicked
	@FXML
	public void user(MouseEvent event) {
		// new Profile().showProfile(d.getOwner());
	}
}
