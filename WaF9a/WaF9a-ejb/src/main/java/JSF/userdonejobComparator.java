package JSF;

import java.io.Serializable;
import java.util.Comparator;

import entities.User;

public class userdonejobComparator implements Serializable, Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		if(o1.getDoneJobs().size()==o2.getDoneJobs().size())
			return 1;
		else
		return  o2.getDoneJobs().size() -o1.getDoneJobs().size();
	}




}
