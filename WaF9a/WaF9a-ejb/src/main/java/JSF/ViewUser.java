package JSF;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.FriendRequest;
import entities.ProposedJob;

/**
 * Session Bean implementation class ViewUser
 */
@Stateless
@LocalBean
public class ViewUser implements ViewUserRemote, ViewUserLocal {

    /**
     * Default constructor. 
     */
	
	
	@PersistenceContext
	private EntityManager em;
	
	
    public ViewUser() {
        // TODO Auto-generated constructor stub
    }


	@Override
	public void sendFriendRequest(FriendRequest f) {
		em.persist(f);
	}


	@Override
	public void proposejob(ProposedJob pj) {
		em.persist(pj);	
	}

}
