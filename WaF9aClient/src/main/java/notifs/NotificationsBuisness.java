package notifs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import FX.AdminHomeController;
import FX.DiscussionDemandController;
import buisness.DiscussionsCreationBusiness;
import entities.Discussion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import notifs.Icons.AnimationType;
import service.NotificationsAdminEJBRemote;

@ClientEndpoint
public class NotificationsBuisness {

	private Session session;
	private NotificationsAdminEJBRemote e;
	private Icons mUsers;
	private Icons exper;
	private Icons disc;
	private Icons claims;

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		this.session = session;
		System.out.println("notificationsBuisness Client end Clalled.......");
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("connection closed from client");
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("error in Client " + throwable.getMessage());
	}

	@OnMessage
	public void onMessage(String message) {
		System.out.println("Client recieved Message :" + message);
		ConnectToEJB();
		if (message.equals("expdem")) {
			exper.setLabText(e.getExpertises());
			return;
		}
		if(message.equals("users")){
			mUsers.setLabText(e.getMUser());
			return;
		}
		if(message.equals("disc")){
			disc.setLabText(e.getDiscs());
			return;
		}
		if(message.equals("claim")){
			claims.setLabText(e.getClaims());
			return;
		}
	}

	public void connect() {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		try {
			session = container.connectToServer(this, new URI("ws://localhost:18080/WaF9a-web/adminNotifications"));
		} catch (DeploymentException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}

	public void disconnect() {
		if (session != null)
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	private void ConnectToEJB(){
		// connect to ejb
				try {
					e = (NotificationsAdminEJBRemote) InitialContext
							.doLookup("/WaF9a-ear/WaF9a-ejb/NotificationsAdminEJB!service.NotificationsAdminEJBRemote");
				} catch (NamingException e) {
					e.printStackTrace();
				}
	}

	public void fillToolbar(ToolBar toolbar) {

		ConnectToEJB();
		// manage users
		mUsers = new Icons(e.getMUser(), "images/profile.png", AnimationType.JUMP);

		mUsers.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				try {
					AdminHomeController.vbox1.getChildren().clear();
					AnchorPane a;
					a = (AnchorPane) FXMLLoader.load(getClass().getResource("../FX/ManageUserBigPanel.fxml"));
					AdminHomeController.vbox1.getChildren().add(a);
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		});
		toolbar.getItems().add(mUsers);

		// expertise demends
		exper = new Icons(e.getExpertises(), "images/skill.png", AnimationType.JUMP);
		exper.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				try {
					AdminHomeController.vbox1.getChildren().clear();
					AnchorPane a;
					a = (AnchorPane) FXMLLoader.load(getClass().getResource("../FX/Expertises.fxml"));
					AdminHomeController.vbox1.getChildren().add(a);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		toolbar.getItems().add(exper);

		// claims
		claims = new Icons(e.getClaims(), "images/claim.png", AnimationType.JUMP);
		claims.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				try {
					AdminHomeController.vbox1.getChildren().clear();
					AnchorPane a;
					a = (AnchorPane) FXMLLoader.load(getClass().getResource("../FX/Claims.fxml"));
					AdminHomeController.vbox1.getChildren().add(a);
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		});
		toolbar.getItems().add(claims);

		// disc
		disc = new Icons(e.getDiscs(), "images/discussions.png", AnimationType.JUMP);
		disc.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				try {
					AdminHomeController.vbox1.getChildren().clear();
					for (Discussion d : new DiscussionsCreationBusiness().getDemands()) {
						DiscussionDemandController.d = d;
						AnchorPane a = (AnchorPane) FXMLLoader
								.load(getClass().getResource("../FX/DiscussionDemand.fxml"));
						AdminHomeController.vbox1.getChildren().add(a);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		toolbar.getItems().add(disc);

		// connect to webSocket
		connect();

	}

}
