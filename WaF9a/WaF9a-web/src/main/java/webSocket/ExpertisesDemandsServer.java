package webSocket;


import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/expertiseDemands")
@Singleton
public class ExpertisesDemandsServer {
	
	private Set<Session> sessions= new HashSet<>();
	
	@Resource
	private ManagedExecutorService mes;
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("server end called .....hahhah");
		sessions.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		sessions.remove(session);
		System.out.println("one connection has been closed");
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("errooooooooor :" +throwable.getMessage());
	}
	
	@OnMessage
	public void onMessage(String message) {
		for(Session s :sessions){
			mes.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						s.getBasicRemote().sendText("refresh");
						System.out.println("sending to session"+s.getId());
					} catch (Exception e) {
						System.out.println("erroooooor ");
						e.printStackTrace();
					}
					
				}
			});
			
		}
	}
	
	
	
	
}
