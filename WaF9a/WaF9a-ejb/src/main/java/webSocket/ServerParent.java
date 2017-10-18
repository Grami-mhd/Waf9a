package webSocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.Session;

public abstract class ServerParent {


	protected static Set<Session> sessions = new HashSet<>();
	
	
	public static void sendToAll(String message){
		for(Session s :sessions){
			Thread t = new Thread(new Runnable() {		
				@Override
				public void run() {
					try {
						s.getBasicRemote().sendText(message);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			t.start();
		}
	}

}
