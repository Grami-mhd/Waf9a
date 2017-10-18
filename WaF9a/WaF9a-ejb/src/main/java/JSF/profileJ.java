package JSF;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Expertise;
import entities.ExpertiseDemande;
import entities.User;

/**
 * Session Bean implementation class profileJ
 */
@Stateless
@LocalBean
public class profileJ implements profileJLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
    public profileJ() {
        // TODO Auto-generated constructor stub
    }


 
    @Override
	public void addExpertiseDemande(ExpertiseDemande expD) {
		em.merge(expD);
		
	}

	@Override
	public Set<User> getAllFriendsByKey(String key, User u) {
		 
		 List<User>l=new ArrayList<>();
		 l=em.createQuery("select u from User  AS u WHERE (:user MEMBER OF u.friends) AND (u.firstName like :key)",User.class).setParameter("user", u).setParameter("key", "%"+key+"%").getResultList();
		System.out.println(l+"good"+key);
		 return new HashSet<>(l);
	}



	
	
	public void deleteFriend(User u){
		
		em.merge(u);
		//em.createQuery("delete u from User  AS u WHERE (:user MEMBER OF u.friends) AND (u.id = :key)",User.class).setParameter("user", u).setParameter("key", us.getId()).executeUpdate();
	}



	@Override
	public User findUser(int id) {
		return em.createQuery("SELECT p from User p where p.id="+id, User.class).getSingleResult();
		
	}



	@Override
	public void upDateProfile(User usrr) {
		System.out.println(usrr.getLastName()+"kkkkkkkkkkkkkkkkkkkkkk");
		
		
		em.merge(usrr);
		
		em.flush();
		
	}

}
