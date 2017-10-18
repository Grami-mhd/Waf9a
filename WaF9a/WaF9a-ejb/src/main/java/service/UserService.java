package service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService implements UserServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	
	private EntityManager em;


    public UserService() {
   
    }


	public void createUser(User user) {
		   em.persist(user);
	}

	
	public List<User> findAllUsers() {
		return em.createQuery("select u from User u", User.class)
				.getResultList();
	}


	public User authenticate(String userName, String password) {
		User found = null;
		String jpql = "select u from User u where u.userName=:userName and u.password=:password";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		try {
			found = query.getSingleResult();
		} catch (Exception ex) {
			Logger.getLogger(UserService.class.getName()).log(
					Level.WARNING,
					"authentication attempt failure with login=" +userName
							+ " and password=" + password);
		}
		return found;
	}


	public boolean loginExists(String userName) {
		boolean exists = false;
		String jpql = "select case when (count(u) > 0)  then true else false end from User u where u.userName=:userName";
		TypedQuery<Boolean> query = em.createQuery(jpql, Boolean.class);
		query.setParameter("userName", userName);
		try {
			exists = query.getSingleResult();
		} catch (NoResultException e) {
			Logger.getLogger(UserService.class.getName()).log(
					Level.WARNING, "no user registred with login=" + userName);
		}
		return exists;
	}

}
