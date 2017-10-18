package gramiTest;

import java.awt.Robot;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Expertise;
import entities.ExpertiseDemande;
import service.ExpertiseDemandeEJBRemote;

public class ExpertiseDemandeBuisness {

	private InitialContext ctx;
	private ExpertiseDemandeEJBRemote proxy;
		
	public ExpertiseDemandeBuisness() {
		try {
			ctx = new InitialContext();
			proxy=(ExpertiseDemandeEJBRemote) ctx.lookup("/WaF9a-ear/WaF9a-ejb/ExpertiseDemandeEJB!service.ExpertiseDemandeEJBRemote");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void accept(ExpertiseDemande ex){
		proxy.addExpertiseDemande(ex);
	}
	
	
	
	
}
