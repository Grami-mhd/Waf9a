package FX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import buisness.ReportMRBuisness;
import entities.Discussion;
import entities.DiscussionReport;
import entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ReportMRController  {
	@FXML
	private VBox Disscu;
	@FXML
	private VBox messages;
	@FXML
	private TextArea subject;
	@FXML
	private TitledPane Message;
	@FXML
	private Button previous;
	@FXML
	private Label numpage;
	@FXML
	private Button next;
	private static int page =0;
	public static Discussion d;
	public static int i ; 
	//public static Message msg ; 
	
	 @FXML
	public void initialize() {
//	Message.accessibleTextProperty();
		// TODO Auto-generated method stub
		if (d!=null)
			subject.setText(d.getTopic());
		subject.setEditable(false);
		ReportMRBuisness drb = new ReportMRBuisness();
		numpage.setText(page+"/"+drb.getCount());

		for(Message msg :d.getMessages())
		{
			MsgReportController.m=msg;	
			try {
				messages.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("MsgReport.fxml")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	 
	 
	 //pagination 
	 
	 @FXML
	public void pervious(ActionEvent event) {

		if ( page==0)
		return ;
		else {		
			ReportMRBuisness drb = new ReportMRBuisness();

			page--;
			AdminHomeController.vbox1.getChildren().clear();
				ReportMRController.d=drb.getDiscussions(page);
				numpage.setText(page+"/"+drb.getCount());

				try {
					AdminHomeController.vbox1.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("ReplyMR.fxml")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		}
		// Event Listener on Button[#next].onAction
		@FXML
		public void next(ActionEvent event) {
			ReportMRBuisness rmd = new ReportMRBuisness();
			 i=rmd.getCount();
			numpage.setText(page+"/"+i);
			if (page < i-1)	
			{ 
				page++;
				AdminHomeController.vbox1.getChildren().clear();
				ReportMRController.d=rmd.getDiscussions(page);
				try {
					AdminHomeController.vbox1.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("ReplyMR.fxml")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
}
