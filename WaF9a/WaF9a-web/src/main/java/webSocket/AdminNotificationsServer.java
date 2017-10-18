package webSocket;

import javax.ejb.Singleton;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/adminNotifications")
@Singleton
public class AdminNotificationsServer extends ServerParent{
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("an error accured in admin notification Server");
	}
	@OnMessage
	public void onMessage(String message) {
		System.out.println("server recieved Message wich is impossible for this one !!:"+message);
	}
	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		System.out.println("admin notification server was called ...");
		sessions.add(session);
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("one session was removed from notifications server ;)");
		sessions.remove(session);
	}
}
