package webSocket;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.spi.JsonProvider;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/userNotifications")
@Singleton
public class NotificationsServer extends UserServer {

	private Map<String, UserMessages> messages = new HashMap<>();

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("message recieved::::::::::::::::::::::" + message);
		JsonReader reader = Json.createReader(new StringReader(message));
		JsonObject jsonMessage = reader.readObject();
		String s = jsonMessage.getString("action");
		if (s.equals("user")) {
			String un = jsonMessage.getString("user");
			session.getUserProperties().put("user", un);
			sessions.add(session);
			if(!messages.containsKey(un)){
				messages.put(un, new UserMessages());
			}
		}
		if (s.equals("send")) {
			Message m = decode(message);
			messages.get(session.getUserProperties().get("user")).sendMessage(m);
			if(!messages.containsKey(m.getReciever()))
				messages.put(m.getReciever(),new UserMessages());
			messages.get(m.getReciever()).sendMessage(m);
			sendbyUN(m.getReciever(), encode(m));
			try {
				session.getBasicRemote().sendText(encode(m));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(s.equals("disc")){
			getDiscussion(session, jsonMessage.getString("user"));
		}

	}

	private void getDiscussion(Session session, String un) {
		if(!messages.containsKey(un)){
			messages.put(un, new UserMessages());
		}
		
		for(Message m :messages.get(un).getMessages(un)){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						session.getBasicRemote().sendText(encode(m));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
	}

	private Message decode(String s) {
		Message m = new Message();
		JsonReader reader = Json.createReader(new StringReader(s));
		JsonObject jsonMessage = reader.readObject();
		m.setText(jsonMessage.getString("text"));
		//m.setDate(jsonMessage.getString("date"));
		m.setReciever(jsonMessage.getString("reciever"));
		return m;
	}

	private String encode(Message m) {
		JsonProvider jsp = JsonProvider.provider();
		JsonObjectBuilder objBuilder = jsp.createObjectBuilder();
		objBuilder.add("date", m.getDate());
		objBuilder.add("text", m.getText());
		objBuilder.add("reciever", m.getReciever());
		JsonObject obj = objBuilder.build();

		return obj.toString();
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		sessions.add(session);
		try {
			session.getBasicRemote().sendText("notif");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("eroooooooooooor" + throwable.getMessage());
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {

		sessions.remove(session);
	}

}
