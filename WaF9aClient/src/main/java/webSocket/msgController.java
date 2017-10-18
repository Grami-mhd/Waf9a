package webSocket;

import java.net.URL;
import java.util.ResourceBundle;

import entities.AdminMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;

public class msgController implements Initializable{
	@FXML
	private Label user;
	@FXML
	private Label txt;

	@FXML
	private ToolBar bar;
	public static AdminMessage adm;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txt.setTooltip(new Tooltip(adm.getUserName()+" :"+adm.getDate()));
		txt.setWrapText(true);
		txt.setText(adm.getText());
		user.setText(adm.getUserName().charAt(0)+"");
		if(adm.getUserName().equals(AdminDiscussionsClient.userName)){
			bar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		}
		
		
	}

}
