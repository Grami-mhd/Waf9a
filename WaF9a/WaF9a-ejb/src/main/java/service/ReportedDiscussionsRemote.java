package service;

import java.util.Set;
import javax.ejb.Remote;
import entities.Discussion;
import entities.DiscussionReport;

@Remote
public interface ReportedDiscussionsRemote {
	public Set<Discussion> getAllReportedDiscussions();
	public void deleteDiscussion(Discussion d);
	public void declineReportedDiscussion(DiscussionReport d);
}
