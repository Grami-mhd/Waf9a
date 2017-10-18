package webSocket;

import java.util.Calendar;
import java.util.Date;

public class Message {

	private String text;
	private String date = Calendar.getInstance().getTime().toLocaleString();
	private String reciever;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public Message(String text, String reciever) {
		super();
		this.text = text;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
