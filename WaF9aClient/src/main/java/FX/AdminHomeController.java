package FX;

import java.io.IOException;

import javax.naming.NamingException;

import com.sun.javafx.scene.control.skin.LabeledText;
import com.sun.javafx.scene.control.skin.TreeViewSkin;

import buisness.CommentReportsBusiness;
import buisness.DiscussionReportsBuisness;
import buisness.DiscussionsCreationBusiness;
import buisness.ReportMRBuisness;
import entities.Discussion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import notifs.NotificationsBuisness;
import webSocket.ExpertiseDemandesClient;

public class AdminHomeController {
	@FXML
	private AnchorPane content;
	@FXML
	private TreeView pane;
	@FXML
	private Label userName;
	@FXML
	private ImageView picture;
	@FXML
	private VBox dis;
	@FXML
	private VBox vbox;
	@FXML
	private ToolBar toolBar;
	public static VBox vbox1;

	@FXML
	public void initialize() {

		//start the discussions
		try {
			dis.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("../webSocket/AdminsDiscussions.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		vbox1 = vbox;
		pane.setRoot(getItems());
		new NotificationsBuisness().fillToolbar(toolBar);
		
	}

	private TreeItem<String> getItems() {

		TreeItem<String> today = new TreeItem<>("Today", new ImageView("images/today.png"));
		TreeItem<String> stats = new TreeItem<String>("Statistics", new ImageView("images/stats.png"));

		TreeItem<String> location = new TreeItem<String>("Location", new ImageView("images/location.png"));
		stats.getChildren().add(location);
		TreeItem<String> Domain = new TreeItem<String>("Domain", new ImageView("images/domain.png"));
		stats.getChildren().add(Domain);
		TreeItem<String> approve = new TreeItem<String>("Approves", new ImageView("images/approve.png"));
		stats.getChildren().add(approve);

		TreeItem<String> hello = new TreeItem<>("Tasks");

		hello.getChildren().add(today);

		TreeItem<String> mu = new TreeItem<>("Manage Users", new ImageView("images/profile.png"));
		TreeItem<String> exp = new TreeItem<>("Expertises", new ImageView("images/skill.png"));
		TreeItem<String> claims = new TreeItem<>("Claims", new ImageView("images/claim.png"));
		TreeItem<String> discussions = new TreeItem<>("Discussions", new ImageView("images/discussions.png"));
		TreeItem<String> reports = new TreeItem<>("Reports", new ImageView("images/report.png"));
		TreeItem<String> dis = new TreeItem<>("Reported Discussions", new ImageView("images/creport.png"));
		TreeItem<String> mandr = new TreeItem<>("Messages and replies", new ImageView("images/mandrreports.png"));
		TreeItem<String> comments = new TreeItem<>("Comments", new ImageView("images/comment.png"));
		reports.getChildren().add(dis);
		reports.getChildren().add(mandr);
		reports.getChildren().add(comments);

		hello.setExpanded(false);
		hello.getChildren().add(mu);
		hello.getChildren().add(exp);
		hello.getChildren().add(claims);
		hello.getChildren().add(discussions);
		hello.getChildren().add(stats);
		hello.getChildren().add(reports);
		hello.setExpanded(true);
		return hello;
	}

	@FXML
	public void select(MouseEvent event) throws IOException {
		Node l = event.getPickResult().getIntersectedNode();
		if (l instanceof LabeledText) {
			LabeledText lt = (LabeledText) l;
			String s = lt.getText();
			if (s.equals("Reported Discussions")) {
				try {
					DiscussionReportsBuisness drb = new DiscussionReportsBuisness();
					vbox.getChildren().clear();
					for (Discussion d : drb.getAllReportedDiscussions()) {
						ReportedDiscussionController.d = d;
						vbox.getChildren()
								.add((AnchorPane) FXMLLoader.load(getClass().getResource("ReportedDiscussion.fxml")));
					}
				} catch (NamingException e) {

					e.printStackTrace();
				}
			}
			if (s.equals("Today")) {
				vbox.getChildren().clear();
				AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("Today.fxml"));
				vbox.getChildren().add(a);
			}
			if (s.equals("Expertises")) {
				vbox.getChildren().clear();
				AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("Expertises.fxml"));
				vbox.getChildren().add(a);
			}
			if (s.equals("Discussions")) {
				vbox.getChildren().clear();
				for (Discussion d : new DiscussionsCreationBusiness().getDemands()) {
					DiscussionDemandController.d = d;
					AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("DiscussionDemand.fxml"));
					vbox.getChildren().add(a);
				}
			}
			if (s.equals("Domain")) {
				vbox.getChildren().clear();
				AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("OffersDemands.fxml"));
				vbox.getChildren().add(a);
			}
			if (s.equals("Manage Users")) {
				vbox.getChildren().clear();
				AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("ManageUserBigPanel.fxml"));
				vbox.getChildren().add(a);
			}

			if (s.equals("Location")) {
				vbox.getChildren().clear();
				AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("mapAllTypes.fxml"));
				vbox.getChildren().add(a);
			}
			if (s.equals("Approves")) {
				vbox.getChildren().clear();
				vbox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getResource("UsersApproves.fxml")));
			}

			if (s.equals("Claims")) {
				vbox.getChildren().clear();
				vbox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getResource("Claims.fxml")));
			}
			if (s.equals("Comments")) {
				vbox.getChildren().clear();
				vbox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getResource("reportsComment.fxml")));
			}
			if (s.equals("Messages and replies")) {
				ReportMRBuisness drb = new ReportMRBuisness();
				vbox.getChildren().clear();
				// Discussion d = ;
				ReportMRController.d = drb.getDiscussions(0);
				vbox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getResource("ReplyMR.fxml")));
			}

		}
		System.out.println();
	}

	@FXML
	public void logout(ActionEvent event) {

		Stage primaryStage = new Stage();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.close();

		AnchorPane root;
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setWidth(738);
			primaryStage.setHeight(495);
			primaryStage.setTitle("Welcome to WaF9a Admin Dashboard");
			primaryStage.getIcons().add(new Image("images/waf9a.jpg"));
			primaryStage.setResizable(false);

			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
