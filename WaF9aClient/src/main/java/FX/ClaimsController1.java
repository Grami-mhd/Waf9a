package FX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


import buisness.ClaimsBuisnessDelegate;
import entities.Claim;
import entities.Notification;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class ClaimsController1 {
	@FXML
	private Label username;
	@FXML
	private Label email;
	@FXML
	private Label phone;
	
	@FXML
	private Label username1;
	@FXML
	private Label email1;
	@FXML
	private Label phone1;
	
	@FXML
	private Label count;
	@FXML
	private Label count1;
	
	@FXML
	private TextArea deal;
	
	@FXML
	private CheckBox block;
	
	@FXML
	private CheckBox pricedec;
	
	@FXML
	private CheckBox deldonejob;
	
	private int s;
	public static Claim c;
	
	public static int nc;
	public static int nc1;
	
	
	public static int nf;
	public static int nf1;
	
	public static int nd;
	public static int nd1;
	
	
	
	@FXML
	public void initialize() {
		//AdminHomeController.vbox1.getChildren().clear();
		ClaimsBuisnessDelegate cb = new ClaimsBuisnessDelegate();
		c=cb.getNextNotTreatedClaim();
		nc=cb.claimNumber(c.getClaimer());
		nf=cb.numberOffers(c.getClaimer());
		nd=cb.numberDoneJobs(c.getClaimer());
		count.setText("claimed "+nc+" times \n"+nf+" Offers \n"+nd+" Done Deals");
		if(c!=null){
			username.setText(c.getClaimer().getUserName());
			email.setText(c.getClaimer().getEmail());
			phone.setText(c.getClaimer().getPhone()+"");
			deal.setText(c.getJob().getText());
			
			if(c.getClaimer()==c.getJob().getBoss()){
				username1.setText(c.getJob().getWorker().getUserName());
				email1.setText(c.getJob().getWorker().getEmail());
				phone1.setText(c.getJob().getWorker().getPhone()+"");
				nc1=cb.claimNumber(c.getJob().getWorker());
				nf1=cb.numberOffers(c.getJob().getWorker());
				nd1=cb.numberDoneJobs(c.getJob().getWorker());
				count1.setText("claimed "+nc1+" times \n"+nf1+" Offers \n"+nd1+" Done Deals");
			}
			if(c.getClaimer()==c.getJob().getWorker()){
				username1.setText(c.getJob().getBoss().getUserName());
				email1.setText(c.getJob().getBoss().getEmail());
				phone1.setText(c.getJob().getBoss().getPhone()+"");
				nc1=cb.claimNumber(c.getJob().getBoss());
				nf1=cb.numberOffers(c.getJob().getBoss());
				nd1=cb.numberDoneJobs(c.getJob().getBoss());
				count1.setText("claimed "+nc1+" times \n"+nf1+" Offers \n"+nd1+" Done Deals");
				
			}
		}
		
	}
	
	@FXML
	public void declineClaim(ActionEvent event) {
		
		c.setTreated(true);
		new ClaimsBuisnessDelegate().claimIsTreated(c);
		
		AdminHomeController.vbox1.getChildren().clear();
		
		try {
			AdminHomeController.vbox1.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("Claims.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// Event Listener on Button.onAction
	@FXML
public void acceptClaim(ActionEvent event) {
		
		c.setTreated(true);
		ClaimsBuisnessDelegate cb = new ClaimsBuisnessDelegate();
		
		if(block.isSelected()){
			if(c.getClaimer()==c.getJob().getBoss()){
			c.getJob().getBoss().setPremium(-1);
			cb.blockUser(c.getJob().getWorker());
			}
			if(c.getClaimer()==c.getJob().getWorker()){
			c.getJob().getBoss().setPremium(-1);
			cb.blockUser(c.getJob().getBoss());
			}
			
			cb.claimIsTreated(c);
			AdminHomeController.vbox1.getChildren().clear();
			
			try {
				AdminHomeController.vbox1.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("Claims.fxml")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Your penalty has been confirmed !!! ");

			alert.showAndWait();
			
		}
		
		if(deldonejob.isSelected()){
			Notification n=new Notification();
			if(c.getClaimer()==c.getJob().getBoss()){
				n.setUser(c.getJob().getWorker());
				n.setText("We want to inform you that "+c.getClaimer()+" has claimed about your last job because of "+c.getText());
				n.setType("cl"+c.getClaimID());
			}
			if(c.getClaimer()==c.getJob().getWorker()){
				n.setUser(c.getJob().getBoss());
				n.setText("We want to inform you that "+c.getClaimer()+" has claimed about your last job because of : "+c.getText());
				n.setType("cl"+c.getClaimID());
			}
			cb.ajoutNotif(n);
			
			cb.claimIsTreated(c);
			AdminHomeController.vbox1.getChildren().clear();
			
			try {
				AdminHomeController.vbox1.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("Claims.fxml")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Your penalty has been confirmed !!! ");

			alert.showAndWait();
			
		}
		if(pricedec.isSelected()){
			double f=((0.1)*c.getJob().getPrice());
			s=(int) ((c.getJob().getPrice())-f);
			c.getJob().setPrice(s);
			cb.decreasePrice(c.getJob());
			
			cb.claimIsTreated(c);
			AdminHomeController.vbox1.getChildren().clear();
			
			try {
				AdminHomeController.vbox1.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("Claims.fxml")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Your penalty has been confirmed !!! ");

			alert.showAndWait();
			
		}
		
		if((block.isSelected()== false)&&(pricedec.isSelected()== false)&&(deldonejob.isSelected()== false)){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("Please choose a penalty !!! ");

			alert.showAndWait();
		}
		
	}
	
	// Event Listener on Label[#username].onMouseClicked
		@FXML
		public void profile(MouseEvent event) {
			new Profile().showProfile(c.getClaimer());
		}
		// Event Listener on Label[#username1].onMouseClicked
		@FXML
		public void profile1(MouseEvent event) {
			if(c.getClaimer()==c.getJob().getBoss()){
				new Profile().showProfile(c.getJob().getWorker());
			}
			if(c.getClaimer()==c.getJob().getWorker()){
				new Profile().showProfile(c.getJob().getBoss());
			}
		}
}







