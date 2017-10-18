package webSocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMessages {

	private Map<String, List<Message>> messages = new HashMap<>();
	private Map<String, Boolean> seenMessages = new HashMap<>();

	public void sendMessage(Message m) {
		if (messages.get(m.getReciever()) != null) {
			messages.get(m.getReciever()).add(m);
		} else {
			List<Message> l = new ArrayList<>();
			l.add(m);
			messages.put(m.getReciever(), l);
		}
	}

	public List<Message> getMessages(String reciever) {
		if (messages.get(reciever) == null)
			messages.put(reciever, new ArrayList<>());
		return messages.get(reciever);

	}

}
