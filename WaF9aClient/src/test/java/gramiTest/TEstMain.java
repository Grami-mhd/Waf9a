package gramiTest;

import IDs.ExpertiseDemandeId;
import buisness.LoginBuisness;
import entities.ExpertiseDemande;
import entities.User;

public class TEstMain {

	public static void main(String[] args) {
		ExpertiseDemande ed= new ExpertiseDemande();
		ed.setDomain("test");
		ExpertiseDemandeId expdid = new ExpertiseDemandeId();
		expdid.setName("expertise Hello 3.0");
		User u = new LoginBuisness().checkUserName("11");
		expdid.setUserId(u.getId());
		ed.setExpertiseDemandeId(expdid);
		ed.setUser(u);
		new ExpertiseDemandeBuisness().accept(ed);
	}

}
