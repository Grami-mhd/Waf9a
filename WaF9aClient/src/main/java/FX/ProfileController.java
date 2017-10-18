package FX;

import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;

import buisness.ProfileBuisness;
import entities.Approve;
import entities.Claim;
import entities.Discussion;
import entities.DoneJob;
import entities.Expertise;
import entities.Offer;
import entities.Rate;
import entities.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.Cursor;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ProfileController implements Initializable{
	@FXML
	private Label name;
	@FXML
	private Label username;
	@FXML
	private Label email;
	@FXML
	private Label phone;
	@FXML
	private Label adress;
	@FXML
	private Label date;
	@FXML
	private StackedBarChart chart;
	@FXML
	private VBox expertises;
	@FXML
	private VBox approves;
	@FXML
	private VBox offers;
	@FXML
	private VBox doneJobs;
	@FXML
	private VBox offeredJobs;
	@FXML
	private VBox discussions;
	@FXML
	private VBox claims;
	@FXML
	private Label postedMessages;
	@FXML
	private Label postedReplys;
	@FXML
	private Label postedComments;
	@FXML
	private Label reportedMessages;
	@FXML
	private Label reportedReplys;
	@FXML
	private Label reportedComments;
	@FXML
	private Label reportedDiscussions;
	private User u;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//personal infos
		this.u = new ProfileBuisness().fill((User) resources.getObject("user"));
		this.name.setText(u.getFirstName()+" "+u.getLastName());
		if (u.getAdress()!=null)
			
		this.adress.setText(u.getAdress().toString());
		this.date.setText(u.getSignUpDate().toString());
		this.email.setText(u.getEmail());
		this.username.setText(u.getUserName());
		this.phone.setText("(+216)"+u.getPhone());
		//expertises
		Label labab = new Label("Expertises :");
		labab.setCursor(Cursor.HAND);
		labab.setPadding(new Insets(10, 20, 20, 0));
		labab.setFont(Font.font("Bold",16));
		this.expertises.getChildren().add(labab);
		for(Expertise e :u.getExpertises()){
			this.expertises.getChildren().add(new Label(e.getDomain()+" :"+e.getName()));
		}
		//approves
		Label lab = new Label("Approves :");
		lab.setCursor(Cursor.HAND);
		lab.setPadding(new Insets(10, 20, 20, 00));
		lab.setFont(Font.font("Bold",16));
		this.approves.getChildren().add(lab);
		for(Approve a : u.getApproved()){
			this.approves.getChildren().add(new Label(a.getApprover().getUserName()+" -> "+a.getExpertise().getName()));
			
		}
		//statistics 
		this.postedMessages.setText("posted "+u.getMessages().size()+" message");
		this.postedComments.setText("posted "+u.getComments().size()+" Comment");
		this.postedReplys.setText("posted "+u.getReplies().size()+" replies");
		this.reportedComments.setText("reported "+u.getReportedComments().size()+" Comment");
		this.reportedDiscussions.setText("reported "+u.getReportedDiscussions().size()+" discussion");
		this.reportedMessages.setText("reported "+u.getReportedMessages().size()+" message");
		this.reportedReplys.setText("reported "+u.getReportedReplies().size()+" reply");
		
		//rates 
		int s1=0,s2=0,s3=0,s4=0,s5=0;
		
		for(Rate r :u.getRates()){
			if(r.getRate()==1)
				s1++;
			if(r.getRate()==2)
				s2++;
			if(r.getRate()==3)
				s3++;
			if(r.getRate()==4)
				s4++;
			if(r.getRate()==5)
				s5++;
			
		}
		
		XYChart.Series serie =new XYChart.Series<>();
		serie.setName(u.getUserName());
		serie.getData().add(new XYChart.Data<>("Star",s1));
		serie.getData().add(new XYChart.Data<>("2 Stars",s2));
		serie.getData().add(new XYChart.Data<>("3 Stars",s3));
		serie.getData().add(new XYChart.Data<>("4 Stars",s4));
		serie.getData().add(new XYChart.Data<>("5 Stars",s5));
		this.chart.getData().add(serie);
		
		//offers
		for(Offer o : u.getOffers() ){
			VBox v = new VBox();
			TextArea txt = new TextArea(o.getText());
			txt.setEditable(false);
			txt.setMaxHeight(30);
			v.getChildren().add(txt);
			if(o.getAdress()!=null)
			v.getChildren().add(new Label(o.getAdress().toString()));
			offers.getChildren().add(v);
			Separator sep = new Separator();
			sep.setPadding(new Insets(10, 0, 10, 0));
			offers.getChildren().add(sep); 		
		}
		
		//done jobs
		for(DoneJob d : u.getDoneJobs() ){
			VBox v = new VBox();
			Label l = new Label("Delay :"+d.getDelay()+" Price :"+d.getPrice());
			l.setPadding(new Insets(0, 20, 10, 30));
			l.setFont(Font.font("Bold",16));
			v.getChildren().add(l);
			TextArea txt = new TextArea(d.getText());
			txt.setEditable(false);
			txt.setMaxHeight(30);
			v.getChildren().add(txt);
			//v.getChildren().add(new Label(d.getAdress().toString()));
			v.getChildren().add(new Label("in the benefit of :"+d.getBoss().getUserName()));

			doneJobs.getChildren().add(v);
			Separator sep = new Separator();
			sep.setPadding(new Insets(10, 0, 10, 0));
			doneJobs.getChildren().add(sep); 		
		}
		//offered jobs
		for(DoneJob d : u.getOfferedJobs() ){
			VBox v = new VBox();
			Label l = new Label("Job offered to "+d.getWorker().getUserName()+". Delay :"+d.getDelay()+" Price :"+d.getPrice());
			l.setPadding(new Insets(0, 20, 10, 30));
			l.setFont(Font.font("Bold",16));
			v.getChildren().add(l);
			TextArea txt = new TextArea(d.getText());
			txt.setEditable(false);
			txt.setMaxHeight(30);
			v.getChildren().add(txt);
			//v.getChildren().add(new Label(d.getAdress().toString()));
			offeredJobs.getChildren().add(v);
			Separator sep = new Separator();
			sep.setPadding(new Insets(10, 0, 10, 0));
			offeredJobs.getChildren().add(sep); 		
		}
		//discussions
		for(Discussion d : u.getDiscussions()){
			VBox v = new VBox();
			Label l = new Label("Subject :");
			l.setPadding(new Insets(0, 20, 10, 30));
			l.setFont(Font.font("Bold",16));
			v.getChildren().add(l);
			TextArea txt = new TextArea(d.getTopic());
			txt.setEditable(false);
			txt.setMaxHeight(30);
			v.getChildren().add(txt);
			v.getChildren().add(new Label(d.getMessages().size()+" Message"));
			discussions.getChildren().add(v);
			Separator sep = new Separator();
			sep.setPadding(new Insets(10, 0, 10, 0));
			discussions.getChildren().add(sep);
		}
		//claims
		for(Claim c : u.getClaims()){
			VBox v = new VBox();
			Label l = new Label("Claim : ");
			l.setPadding(new Insets(0, 20, 10, 30));
			l.setFont(Font.font("Bold",16));
			v.getChildren().add(l);
			TextArea txt = new TextArea(c.getText());
			txt.setEditable(false);
			txt.setMaxHeight(30);
			v.getChildren().add(txt);
			v.getChildren().add(new Label("status :"+c.getStatus()));
			claims.getChildren().add(v);
			Separator sep = new Separator();
			sep.setPadding(new Insets(10, 0, 10, 0));
			claims.getChildren().add(sep);
		}
		
		
	}

	
}
