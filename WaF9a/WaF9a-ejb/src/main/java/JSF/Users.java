package JSF;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.DoneJob;
import entities.Offer;
import entities.Rate;
import entities.User;

/**
 * Session Bean implementation class Users
 */
@Stateless
@LocalBean
public class Users implements UsersRemote, UsersLocal {

	/**
	 * Default constructor.
	 */

	@PersistenceContext
	EntityManager em;

	public Users() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<User, String> sortUsersByDoneJobs() {

		Map<User, String> m = new TreeMap<User, String>(new userdonejobComparator());

		List<User> lu = new ArrayList<>();
		lu = em.createQuery("SELECT u FROM User u").getResultList();
		List<List<DoneJob>> ldj = new ArrayList<>();
		for (User user : lu) {
			List<DoneJob> ld = new ArrayList<>();
			ld = em.createQuery("select d from DoneJob d where d.worker.id =" + user.getId()).getResultList();
			ldj.add(ld);
			user.setDoneJobs(ld);
			System.out.println(user + "hggggggggggggg" + ld.size());
			m.put(user, "" + ld.size());
		}

		return m;

	}

	@Override
	public Map<User, String> sortUsersByRate() {
		Map<User, String> m = new TreeMap<User, String>(new UserRateComparator());

		List<User> lu = new ArrayList<>();

		lu = em.createQuery("SELECT u FROM User u").getResultList();
		List<List<Rate>> ldr = new ArrayList<>();
		for (User user : lu) {
			List<Rate> lr = new ArrayList<>();
			lr = em.createQuery("select r from Rate r where r.rated.id =" + user.getId()).getResultList();
			ldr.add(lr);
			user.setRates(lr);
			System.out.println(user + "lrlrlrlrlrlrlrlr" + lr.size());

			int r = 0;
			for (Rate rate : lr) {
				r += rate.getRate();
			}
			
			if (lr.size() != 0) {
				user.setFinalRate(r / lr.size());
				m.put(user, "" + r / lr.size());
			}

		}

		return m;

	}

	@Override
	public List<User> getAllUsers() {

		List<User> l = new ArrayList<>();
		l = em.createQuery("SELECT u FROM User u ", User.class).setMaxResults(20).getResultList();

		System.out.println(l + "All users");
		return l;
	}

	@Override
	public List<User> serachbyname(String key) {
		List<User> l = new ArrayList<>();
		l = em.createQuery("SELECT u FROM User u WHERE (u.firstName LIKE :key) OR (u.lastName LIKE :key) ", User.class)
				.setParameter("key", "%" + key + "%").setMaxResults(20).getResultList();
		return l;
	}

	@Override
	public List<User> sortBySignUpDate() {
		List<User> l = new ArrayList<>();

		l = em.createQuery("SELECT u FROM User u ORDER BY u.signUpDate DESC", User.class).setMaxResults(3)
				.getResultList();

		return l;
	}

}
