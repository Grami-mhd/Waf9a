package JSF;

import java.io.Serializable;
import java.util.Comparator;

import entities.Rate;
import entities.User;

public class UserRateComparator implements Serializable,Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		
		int r1=0;
		int r2=0;
		int l1 = o1.getRates().size();
		int l2 = o2.getRates().size();
		
		for (Rate r : o1.getRates()) {
			r1+=r.getRate();
		}
		for (Rate r : o2.getRates()) {
			r2+=r.getRate();
		}
		if(l1!=0 && l2!=0){
			
		if(r1/l1==r2/l2)
			return 1;
		else
		return  r2/l2 - r1/l1;
		}
	return 0;
	
	}

}
