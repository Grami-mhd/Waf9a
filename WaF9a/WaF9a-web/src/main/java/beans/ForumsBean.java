package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.resource.spi.DissociatableManagedConnection;

import entities.Discussion;
import entities.DiscussionReport;
import entities.Message;
import entities.MessageReport;
import entities.User;
import service.DiscussionsAllLocal;

@ManagedBean
@javax.faces.bean.ViewScoped
public class ForumsBean {

	@EJB
	private DiscussionsAllLocal discussionsAllLocal;
	
	private Map<String, List<Discussion>> listdiscussion;
	
	public static Discussion discussion = new Discussion();
	public static Set<Message> msgs = new HashSet<>();
	private List<Discussion> discussions = new ArrayList<>();
	private MessageReport msgR;
	private DiscussionReport disR;
	private Message msg = new Message();
	private User user =(User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") ;
	
	private String topic;
	
	private boolean formDisplay = false;
	
	
	@PostConstruct
	public void initialisation(){
		listdiscussion = discussionsAllLocal.getDiscussions();
	}
	
	public String addMes(){
		msg.setOwner(user);
		msg.setDiscussion(discussion);
		discussionsAllLocal.addMesaage(msg);
		return "/pages/viewDiscussion?faces-redirect=true";
	}
	
	public String viewDiscussion(Discussion d){
		String navTo="";
		System.out.println(d.getTopic()+"       "+d.getDiscussionID());
		discussion = d;
		setMsgs(d.getMessages());
		navTo="/pages/viewDiscussion?faces-redirect=true";
		return navTo;
	}
	
	public void searchDis(){
		System.out.println("bean string "+topic);
		discussions = discussionsAllLocal.searchDiscussion(topic);
		listdiscussion = new HashMap<>();
	}
	private Discussion d ;
	public void doNew(Discussion d){
		System.out.println("\n"+user.getFirstName());
		this.d = d;
		disR = new DiscussionReport();
		formDisplay = true;
		//return "";
	}

	public String doSave(){
		disR.setReporter(user);
		disR.setDiscussion(d);
		disR.setTreated(false);
		discussionsAllLocal.saveDisRep(disR);
		initialisation();
		formDisplay = false;
		return "";
	}
	
	public Map<String, List<Discussion>> getListdiscussion() {
		return listdiscussion;
	}

	public void setListdiscussion(Map<String, List<Discussion>> listdiscussion) {
		this.listdiscussion = listdiscussion;
	}

	public boolean isFormDisplay() {
		return formDisplay;
	}

	public void setFormDisplay(boolean formDisplay) {
		this.formDisplay = formDisplay;
	}

	public Discussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}

	public List<Discussion> getDiscussions() {
		return discussions;
	}

	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}


	public Set<Message> getMsgs() {
		return msgs;
	}


	public void setMsgs(Set<Message> msgs) {
		this.msgs = msgs;
	}

	public MessageReport getMsgR() {
		return msgR;
	}

	public void setMsgR(MessageReport msgR) {
		this.msgR = msgR;
	}

	public DiscussionReport getDisR() {
		return disR;
	}

	public void setDisR(DiscussionReport disR) {
		this.disR = disR;
	}

	public Discussion getD() {
		return d;
	}

	public void setD(Discussion d) {
		this.d = d;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
