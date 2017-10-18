package FX;

import java.util.List;

import javax.naming.NamingException;

import buisness.TodayBuisness;
import entities.DoneJob;
import entities.Offer;
import entities.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TodayController {
	@FXML
	private  VBox usersVbox;
	@FXML
	private  VBox offersVbox;
	@FXML
	private  VBox doneJobsVBox;

	private static TodayBuisness tb;

	@FXML
	public void initialize() {
		fillDoneJobs();
		fillOffers();
		fillUsers();
	}

	public  void fillUsers() {
		usersVbox.getChildren().clear();
		tb = new TodayBuisness();
		List<User> users = tb.getNewUsers();

		for (User u : users) {
			HBox h = new HBox();
			Label l = new Label(
					"username :" + u.getUserName() + ", name : " + u.getFirstName() + " " + u.getLastName());
			l.setCursor(Cursor.HAND);
			l.setPadding(new Insets(10, 20, 0, 30));
			l.setFont(Font.font("Bold", 16));
			l.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {

					new Profile().showProfile(u);

				}

			});

			h.getChildren().add(new ImageView("images/profile.png"));
			h.getChildren().add(l);
			usersVbox.getChildren().add(h);
			Separator sep = new Separator();
			sep.setPadding(new Insets(10, 0, 10, 0));
			usersVbox.getChildren().add(sep);
		}
	}

	public  void fillOffers() {
		offersVbox.getChildren().clear();
		tb = new TodayBuisness();
		List<Offer> offers = tb.getNewOffers();

		for (Offer o : offers) {
			VBox v = new VBox();
			Label l = new Label("owner :" + o.getOwner().getUserName());
			l.setCursor(Cursor.HAND);
			l.setPadding(new Insets(0, 20, 10, 30));
			l.setFont(Font.font("Bold", 16));

			l.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					new Profile().showProfile(o.getOwner());

				}

			});
			TextArea txt = new TextArea(o.getText());
			txt.setEditable(false);
			txt.setMaxHeight(30);
			v.getChildren().add(l);
			v.getChildren().add(txt);
			if (o.getAdress() != null)
				v.getChildren().add(new Label("   Adress :" + o.getAdress().toString()));
			offersVbox.getChildren().add(v);
			Separator sep = new Separator();
			sep.setPadding(new Insets(10, 0, 10, 0));
			offersVbox.getChildren().add(sep);
		}
	}

	public  void fillDoneJobs() {
		doneJobsVBox.getChildren().clear();
		tb = new TodayBuisness();
		List<DoneJob> jobs = tb.getNewDoneJobs();

		for (DoneJob dj : jobs) {
			VBox v = new VBox();
			Label l = new Label("Job seeker" + dj.getWorker().getUserName() + " Delay :" + dj.getDelay() + " Price :"
					+ dj.getPrice());
			l.setCursor(Cursor.HAND);
			l.setPadding(new Insets(0, 20, 10, 30));
			l.setFont(Font.font("Bold", 16));

			l.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					new Profile().showProfile(dj.getWorker());
				}

			});
			TextArea txt = new TextArea("  " + dj.getText());
			txt.setEditable(false);
			txt.setMaxHeight(30);
			v.getChildren().add(l);
			v.getChildren().add(txt);
			v.getChildren().add(new Label("    in the benifit of :" + dj.getBoss().getUserName()));
			doneJobsVBox.getChildren().add(v);
			Separator sep = new Separator();
			sep.setPadding(new Insets(10, 0, 10, 0));
			doneJobsVBox.getChildren().add(sep);
		}
	}
}
