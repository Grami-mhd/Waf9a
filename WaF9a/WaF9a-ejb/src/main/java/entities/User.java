package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import webSocket.ServerParent;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private long phone;
	private Date signUpDate=Calendar.getInstance().getTime();	
	@OneToOne(cascade=CascadeType.ALL)
	private Adress adress;
	private boolean Admin=false;
	private int premium;
	private int finalRate =0;
	@ManyToMany(cascade=CascadeType.ALL)
	private Set<User> friends;

	@OneToMany(mappedBy="reciever",cascade=CascadeType.ALL)
	private List<ProposedJob> jobOffers;

	@OneToMany(mappedBy="sender",cascade=CascadeType.ALL)
	private List<ProposedJob> sentOffers;
	
	@ManyToMany(mappedBy="users",fetch=FetchType.EAGER)
	private List<Expertise> expertises;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<ExpertiseDemande> expertisesDemands;
	
	@OneToMany(mappedBy="sender",cascade=CascadeType.ALL)
	private List<FriendRequest> sentFriendRequests;
	
	@OneToMany(mappedBy="receiver",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<FriendRequest> friendsRequests;
	
	@OneToMany(mappedBy="owner",cascade=CascadeType.ALL)
	private List<Offer> offers;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List< Notification > notifications;
	
	@OneToMany(mappedBy="owner",cascade=CascadeType.ALL)
	private List<Discussion> discussions;
	
	@OneToMany(mappedBy="owner",cascade=CascadeType.ALL)
	private List<Message> messages;
	
	@OneToMany(mappedBy="owner",cascade=CascadeType.ALL)
	private List<Reply> replies;
	
	@OneToMany(mappedBy="worker",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<DoneJob> doneJobs;
	
	@OneToMany(mappedBy="boss",cascade=CascadeType.ALL)
	private List<DoneJob> offeredJobs;
	
	@OneToMany(mappedBy="claimer",cascade=CascadeType.ALL)
	private List<Claim> Claims;
	
	@OneToMany(mappedBy="reporter",cascade=CascadeType.ALL)
	private List<MessageReport> reportedMessages;
	
	@OneToMany(mappedBy="reporter",cascade=CascadeType.ALL)
	private List<ReplyReport> reportedReplies;
	
	@OneToMany(mappedBy="reporter",cascade=CascadeType.ALL)
	private List<DiscussionReport> reportedDiscussions;
	
	@OneToMany(mappedBy="owner",cascade=CascadeType.ALL)
	private List<Comment> comments;
	
	@OneToMany(mappedBy="reporter",cascade=CascadeType.ALL)
	private List<CommentReport> reportedComments;
	
	@OneToMany(mappedBy="Approved",cascade=CascadeType.ALL)
	private List<Approve> approved;
	
	@OneToMany(mappedBy="approver",cascade=CascadeType.ALL)
	private List<Approve> othersApproves;
	
	@OneToMany(mappedBy="rated",cascade=CascadeType.ALL)
	private List<Rate> rates;
	
	@OneToMany(mappedBy="rater",cascade=CascadeType.ALL)
	private List<Rate> rated;
	
	private static final long serialVersionUID = 1L;

	@PostUpdate
	public void onPostUpdate(){
		ServerParent.sendToAll("users");
	}
	public User() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}   
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public Date getSignUpDate() {
		return this.signUpDate;
	}

	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	
	public Set<User> getFriends() {
		return friends;
	}
	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}
	public List<FriendRequest> getSentFriendRequests() {
		return sentFriendRequests;
	}
	public void setSentFriendRequests(List<FriendRequest> sentFriendRequests) {
		this.sentFriendRequests = sentFriendRequests;
	}
	public List<FriendRequest> getFriendsRequests() {
		return friendsRequests;
	}
	public void setFriendsRequests(List<FriendRequest> friendsRequests) {
		this.friendsRequests = friendsRequests;
	}
	public boolean isAdmin() {
		return Admin;
	}
	public void setAdmin(boolean admin) {
		Admin = admin;
	}
	public List<Expertise> getExpertises() {
		return expertises;
	}
	public void setExpertises(List<Expertise> expertises) {
		this.expertises = expertises;
	}
	public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	public List<Discussion> getDiscussions() {
		return discussions;
	}
	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public List<DoneJob> getDoneJobs() {
		return doneJobs;
	}
	public void setDoneJobs(List<DoneJob> doneJobs) {
		this.doneJobs = doneJobs;
	}
	public List<DoneJob> getOfferedJobs() {
		return offeredJobs;
	}
	public void setOfferedJobs(List<DoneJob> offeredJobs) {
		this.offeredJobs = offeredJobs;
	}
	
	
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	public List<Claim> getClaims() {
		return Claims;
	}
	public void setClaims(List<Claim> claims) {
		Claims = claims;
	}
	public List<MessageReport> getReportedMessages() {
		return reportedMessages;
	}
	public void setReportedMessages(List<MessageReport> reportedMessages) {
		this.reportedMessages = reportedMessages;
	}
	public List<ReplyReport> getReportedReplies() {
		return reportedReplies;
	}
	
	public void setReportedReplies(List<ReplyReport> reportedReplies) {
		this.reportedReplies = reportedReplies;
	}
	public List<DiscussionReport> getReportedDiscussions() {
		return reportedDiscussions;
	}
	public void setReportedDiscussions(List<DiscussionReport> reportedDiscussions) {
		this.reportedDiscussions = reportedDiscussions;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<CommentReport> getReportedComments() {
		return reportedComments;
	}
	public void setReportedComments(List<CommentReport> reportedComments) {
		this.reportedComments = reportedComments;
	}
	public List<Approve> getApproved() {
		return approved;
	}
	public void setApproved(List<Approve> approved) {
		this.approved = approved;
	}
	public List<Rate> getRates() {
		return rates;
	}
	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}
	public List<Rate> getRated() {
		return rated;
	}
	public void setRated(List<Rate> rated) {
		this.rated = rated;
	}
	@Override
	public int hashCode() {
		
		return 11;
	}
	
	public List<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public int getFinalRate() {
		return finalRate;
	}
	public void setFinalRate(int finalRate) {
		this.finalRate = finalRate;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public int getPremium() {
		return premium;
	}
	public void setPremium(int premium) {
		this.premium = premium;
	}
	public List<ExpertiseDemande> getExpertisesDemands() {
		return expertisesDemands;
	}
	public void setExpertisesDemands(List<ExpertiseDemande> expertisesDemands) {
		this.expertisesDemands = expertisesDemands;
	}
	public List<Approve> getOthersApproves() {
		return othersApproves;
	}
	public void setOthersApproves(List<Approve> othersApproves) {
		this.othersApproves = othersApproves;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<ProposedJob> getJobOffers() {
		return jobOffers;
	}
	public void setJobOffers(List<ProposedJob> jobOffers) {
		this.jobOffers = jobOffers;
	}
	public List<ProposedJob> getSentOffers() {
		return sentOffers;
	}
	public void setSentOffers(List<ProposedJob> sentOffers) {
		this.sentOffers = sentOffers;
	}
   
	
}
