package FX;

import java.io.IOException;

import entities.Message;
import entities.MessageReport;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.TitledPane;

import javafx.scene.control.TextArea;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MsgReportController {
	@FXML
	private VBox msg;
	@FXML
	private Label userName;
	@FXML
	private Label date;
	@FXML
	private TextArea reasonmsg;
	@FXML
	private TitledPane Reply;
	@FXML
	private VBox replies;

	public static Message m;
	private Message m1;

	public void initialize() {
		// TODO Auto-generated method stub
		m1 = m;
		// date.setText();
		reasonmsg.setText(m1.getText());
		userName.setText(m1.getOwner().getUserName());
		date.setText(m1.getDate().toString());

		reasonmsg.setEditable(false);

		if (!m.getReportes().isEmpty()) {
			boolean bo = false;
			for (MessageReport mrr : m.getReportes())
				if (!mrr.getTreated())
					bo = true;
			if (bo) {
				reasonmsg.setStyle("-fx-background-color: red");
				reasonmsg.setCursor(Cursor.HAND);
				reasonmsg.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {

						popupmsgController.m = m1;

						Stage primaryStage = new Stage();
						AnchorPane root;
						try {
							root = (AnchorPane) FXMLLoader.load(getClass().getResource("popupmsg.fxml"));
							Scene scene = new Scene(root);
							primaryStage.setScene(scene);
							primaryStage.show();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					};

				});
			}
		}

		for (entities.Reply replay : m1.getReplies()) {

			try {
				ReplayController.r = replay;
				replies.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getResource("Replay.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Event Listener on Label[#userName].onMouseClicked
	@FXML
	public void name(MouseEvent event) {
		new Profile().showProfile(m1.getOwner());
	}
}
