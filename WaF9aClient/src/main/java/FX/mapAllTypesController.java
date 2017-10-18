package FX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class mapAllTypesController {
	@FXML
	private Button UsersMarkers;
	@FXML
	private Button DoneJobsMarkers;
	@FXML
	private Button OffersMarkers;
	@FXML
	private GridPane mapF;

	// Event Listener on Button[#UsersMarkers].onAction
	@FXML
	public void UsersMarkersAction(ActionEvent event) {
		try {
			mapF.getChildren().clear();
			mapF.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("mapPanel.fxml")));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
	}
	// Event Listener on Button[#DoneJobsMarkers].onAction
	@FXML
	public void DoneJobsMarkersAction(ActionEvent event) {
		try {
			mapF.getChildren().clear();
			mapF.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("mapPaneloffer.fxml")));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
	}
	// Event Listener on Button[#OffersMarkers].onAction
	@FXML
	public void OffersMarkersAction(ActionEvent event) {
		try {
			mapF.getChildren().clear();
			mapF.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("mapPanelDoneJob.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
	}
}
