package FX;

import javafx.fxml.FXML;
import javafx.geometry.Insets;

import java.util.List;


import buisness.ExpertiseDemandeBuisness;
import entities.Expertise;
import entities.ExpertiseDemande;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import webSocket.ExpertiseDemandesClient;

public class ExpertiseDemandeController {
	@FXML
	private TitledPane titel;
	@FXML
	private VBox demends;
	@FXML
	private Label number;
	private ExpertiseDemandeBuisness bes= new ExpertiseDemandeBuisness();
	
	public static List<ExpertiseDemande> list;	
	private List<ExpertiseDemande> l;
	
	public void initialize(){
		
		l=list;
		titel.setText("list of users that demanded to add an expertise named :"+l.get(0).getExpertiseDemandeId().getName());
		for(ExpertiseDemande ed :l){
			
				HBox h = new HBox();
				Label l = new Label("username :"+ed.getUser().getUserName()+", name : "+ed.getUser().getFirstName()+ " "+ed.getUser().getLastName());
				l.setCursor(Cursor.HAND);
				l.setPadding(new Insets(10, 20, 0, 30));
				l.setFont(Font.font("Bold",16));
				l.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						new Profile().showProfile(ed.getUser());				
					}
					
				});
				
				h.getChildren().add(new ImageView("images/profile.png"));
				VBox v1 = new VBox();
				v1.getChildren().add(l);
				v1.getChildren().add(new Label("   proposed Domain :"+ed.getDomain()));
				h.getChildren().add(v1);
				demends.getChildren().add(h);
				Separator sep = new Separator();
				sep.setPadding(new Insets(10, 0, 10, 0));
				demends.getChildren().add(sep);
			
		}
		number.setText(new ExpertiseDemandeBuisness().getNumberOfSearches(l.get(0).getExpertiseDemandeId().getName())+" persons has searched");
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void accept(ActionEvent event) {
		Expertise ex = new Expertise();
		ex.setName(l.get(0).getExpertiseDemandeId().getName());
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Label("Choose a domain from the proposed list"));
        for(ExpertiseDemande exp : l){
        	RadioButton r = new RadioButton(exp.getDomain());
        	r.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					ex.setDomain(exp.getDomain());
				}
			});
        	dialogVbox.getChildren().add(r);
        }
        Button b = new Button("validate");
        b.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		// TODO Auto-generated method stub
        		
        		
        		bes.accept(ex);
        		ExpertiseDemandesClient.sendMessage("one demande has been accepted");
        		Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        		stage1.close();
//        		Notifications nb = Notifications.create().darkStyle().hideAfter(Duration.seconds(5)).title("Update")
//						.text(" Has been Add successfully !").graphic(titel).position(Pos.CENTER).hideCloseButton()
//						.onAction(new EventHandler<ActionEvent>() {
//							@Override
//							public void handle(ActionEvent event) {
//							}
//						});
//				nb.showConfirm();
				
				
        	}
		});
        dialogVbox.getChildren().add(b);     
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
		
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void decline(ActionEvent event) {
		bes.decline(l.get(0));
		ExpertiseDemandesClient.sendMessage("one demande has been declined");
	}
	
	
}
