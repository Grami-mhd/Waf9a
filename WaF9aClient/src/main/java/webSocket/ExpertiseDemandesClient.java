package webSocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import FX.ExpertisesController;
import javafx.application.Platform;

@ClientEndpoint
public class ExpertiseDemandesClient {

	private static Session session = null;

	public static void connect() {
		
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		try {
			if( session != null)
				session.close();
			session = container.connectToServer(ExpertiseDemandesClient.class,
					new URI("ws://localhost:18080/WaF9a-web/expertiseDemands"));
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
	public void onOpen(Session session) {
		ExpertiseDemandesClient.session = session;
		System.out.println("client called !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		ExpertisesController.fillVbox();
	}

	@OnMessage
	public void onMessage(String message) {
		System.out.println("client recieved :"+message  );
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				ExpertisesController.fillVbox();
				System.out.println("refresshing");
			}
		});
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("erooooooooooooooooooooor"+throwable.getMessage());
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("connection closed in the client");
	}

	public static void sendMessage(String s) {
		try {
			if (session != null) {
				session.getBasicRemote().sendText(s);
				System.out.println("message " + s + " sent to server");
			}
			else{
				System.out.println("session is null");
			}
		} catch (Exception e) {
			System.out.println("sending failed");
			e.printStackTrace();
		}
	}

}
