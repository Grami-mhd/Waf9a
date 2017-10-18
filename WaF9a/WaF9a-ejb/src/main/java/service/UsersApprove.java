package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Expertise;
import entities.User;

/**
 * Session Bean implementation class UsersApprove
 */
@Stateless
@LocalBean
public class UsersApprove implements UsersApproveRemote {

	/**
	 * Default constructor.
	 */

	@PersistenceContext
	private EntityManager em;

	public UsersApprove() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Map<String, Set<User>>> getUsersApprovePerExpertise() {

		Map<String, Map<String, Set<User>>> fin = new HashMap<String, Map<String, Set<User>>>();
		
		List<Expertise> l = em.createQuery("select e from Expertise e").getResultList();
		Map<String, List<Expertise>> m = new HashMap<String, List<Expertise>>();
		
		for (Expertise e : l) {
			e.setUsers(em
					.createQuery(
							"select u from User u join u.expertises e  where e.expertiseID = " + e.getExpertiseID())
					.getResultList());
			for (User u : e.getUsers()) {
				u.setApproved(
						em.createQuery("select d from Approve d where d.Approved.id =" + u.getId()).getResultList());
			}
			if (m.containsKey(e.getDomain())) {
				m.get(e.getDomain()).add(e);

			} else {
				List<Expertise> ls = new ArrayList<Expertise>();
				ls.add(e);
				m.put(e.getDomain(), ls);
			}
		}
		for (Map.Entry<String, List<Expertise>> me : m.entrySet()) {
			List<Expertise> lis = me.getValue();
			Map<String, Set<User>> mama = new HashMap<String, Set<User>>();
			for (Expertise ex : lis) {

				Set<User> s = new TreeSet<User>(new UserComparator());
				for (User u : ex.getUsers()) {
					s.add(u);
				}
				mama.put(ex.getName(), s);

			}
			fin.put(me.getKey(), mama);
		}
		System.out.println(fin);
		return fin;
	}

}
