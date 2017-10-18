package service;

import java.io.Serializable;
import java.util.Comparator;

import entities.User;

public class UserComparator implements Comparator<User>, Serializable{

	@Override
	public int compare(User o1, User o2) {
		// TODO Auto-generated method stub
		return o1.getApproved().size()-o2.getApproved().size();
	}

}
