package FX;

import java.util.Map;
import java.util.Set;

import buisness.UserApprovesBuisnessDelegate;
import entities.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UsersApprovesController {

	private Map<String, Map<String, Set<User>>> m;

	@FXML
	private Accordion domains;

	@FXML
	public void initialize() {

		m = new UserApprovesBuisnessDelegate().getUsersApprovePerExpertise();

		for (Map.Entry<String, Map<String, Set<User>>> en : m.entrySet()) {
			String domain = en.getKey();
			Map<String, Set<User>> expUser = en.getValue();
			TitledPane tpp = new TitledPane();
			Accordion tp = new Accordion();
			tpp.setText(domain);
			tpp.setContent(tp);
			

			for (Map.Entry<String, Set<User>> en1 : expUser.entrySet()) {
				String expertise = en1.getKey();
				Set<User> l = en1.getValue();

				TitledPane tp1 = new TitledPane();
				tp1.setPadding(new Insets(20, 20, 20, 20));
				tp1.setExpanded(true);
				tp1.setText(expertise);

				VBox hb = new VBox();

				for (User user : l) {
					HBox hb1 = new HBox();
					VBox vb = new VBox();
					ImageView foto = new ImageView("images/profile.png");
					vb.setPadding(new Insets(0,0,0,20));
					Label name = new Label("User :      " + user.getFirstName() + " " + user.getLastName());
					name.setCursor(Cursor.HAND);
					name.setFont(new Font(16));
					name.setOnMouseClicked(new EventHandler<Event>() {
						public void handle(Event event) {
							new Profile().showProfile(user);
						};
					});
					
					Label email = new Label("Email :      " + user.getEmail());
					Label phone = new Label("Phone :      " + user.getPhone() + "");
					hb1.getChildren().add(foto);
					vb.getChildren().addAll(name, email, phone);
					hb1.getChildren().add(vb);
					Separator s = new Separator();
					s.setPadding(new Insets(10,0,10,0));
					hb.getChildren().add(hb1);
					hb.getChildren().add(s);
				}

				tp1.setContent(hb);

				tp.getPanes().add(tp1);

			}
				domains.getPanes().add(tpp);
		}
	}
}
