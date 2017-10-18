package service;

import java.util.List;
import javax.ejb.Remote;

import entities.Comment;
import entities.CommentReport;
import entities.Notification;
import entities.Offer;
import entities.User;

@Remote
public interface ReportedCommentsRemote {
	public List<Offer> getAllReportedoffers();
	public void declineComment(CommentReport c);
	public void deleteComment(Comment c);
	public void blockCommentOwner(User u);
	public void addNotify(Notification n);
}
