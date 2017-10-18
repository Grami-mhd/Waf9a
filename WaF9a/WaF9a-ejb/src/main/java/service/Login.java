package service;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.User;

/**
 * Session Bean implementation class Login
 */
@Stateful
@LocalBean
public class Login implements LoginRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
    public Login() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public User checkUserName(String login) {
		Query q = em.createQuery("select c from User c where c.userName =:pane",User.class).setParameter("pane", login);
		if(q.getResultList().isEmpty())
			return null;
		else
			return (User) q.getSingleResult();
		
		
		
	}

	@Override
	public boolean access(User u,String pw) {
		if(u==null)
			return false;
		else{
			if(u.getPassword().equals(pw))
				return true;
			return false;
				
		}
	}

}
