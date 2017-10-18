package FX;

import entities.DiscussionReport;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

public class UserReportController {
	@FXML
	private Label userName;
	@FXML
	private Label date;
	@FXML
	private TextArea reason;
	
	@FXML
	public void initialize(){
		DiscussionReport dr = ReportedDiscussionController.dr;
		reason.setText(dr.getReason());
		date.setText(dr.getReportDate().toString());
		userName.setText(dr.getReporter().getFirstName()+" "+dr.getReporter().getLastName());
	}

}
