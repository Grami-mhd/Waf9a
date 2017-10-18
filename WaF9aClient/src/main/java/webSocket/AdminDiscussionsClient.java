package webSocket;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.spi.JsonProvider;
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

import entities.AdminMessage;
import javafx.application.Platform;

@ClientEndpoint
public class AdminDiscussionsClient {

	private static Session session = null;
	public static String userName = "grami";

	public static void connect() {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		try {
			session = container.connectToServer(AdminDiscussionsClient.class,
					new URI("ws://localhost:18080/WaF9a-web/adminsdiscussion"));
		} catch (DeploymentException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}

	public static void disconnect() {
		if (session != null)
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) {

		AdminDiscussionsClient.session = session;
		System.out.println("discussionclient end point called ;;;;;;;;;;;;;;;; ");

	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("client error :" + throwable.getMessage());
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("session closed from clients end ......");
	}

	@OnMessage
	public void onMessage(String message) {
		System.out.println(message);
		JsonReader reader = Json.createReader(new StringReader(message));
		JsonObject jsonMessage = reader.readObject();
		
		System.out.println("hhhhhhhhhhh");
		AdminMessage msg = new AdminMessage();
		msg.setDate(jsonMessage.getString("date"));
		msg.setUserName(jsonMessage.getString("userName"));
		msg.setText(jsonMessage.getString("text"));
		System.out.println(msg);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				AdminsDiscussionsController.addMessage(msg);
			}
		});

	}

	public static void send(String s) {
		AdminMessage adm = new AdminMessage();
		adm.setText(s);
		adm.setUserName(userName);
		adm.setDate(Calendar.getInstance().getTime().toString());

		JsonProvider jsp = JsonProvider.provider();
		JsonObjectBuilder objBuilder = jsp.createObjectBuilder();
		objBuilder.add("action", "add");
		objBuilder.add("date", adm.getDate());
		objBuilder.add("text", adm.getText());
		objBuilder.add("userName", adm.getUserName());
		JsonObject obj = objBuilder.build();

		try {
			session.getBasicRemote().sendText(obj.toString());
			System.out.println("messsage sent to server");
		} catch (IOException e) {
			System.out.println("error in sending message ");
			e.printStackTrace();
		}

	}

}
