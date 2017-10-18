package webSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

import entities.User;

public abstract class UserServer {

	public static Set<Session> sessions= new HashSet<>();
	
	public static void sendToUser(User user,String msg){
		try {
			for(Session s:sessions){
				if(user.getUserName().equals(s.getUserProperties().get("user")))
					s.getBasicRemote().sendText(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void sendbyUN(String un,String msg){
		try {
			for(Session s:sessions){
				if(un.equals(s.getUserProperties().get("user")))
					s.getBasicRemote().sendText(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean isLogged(String un){
		for(Session s:sessions){
			if(un.equals(s.getUserProperties().get("user")))
				return true;
		}
		return false;
	}
}
