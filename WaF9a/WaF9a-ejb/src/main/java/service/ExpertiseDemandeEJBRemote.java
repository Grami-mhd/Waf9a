package service;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import entities.Expertise;
import entities.ExpertiseDemande;

@Remote
public interface ExpertiseDemandeEJBRemote {

	public Map<String, List<ExpertiseDemande>> getAllDemands();
	public Map<String, List<Expertise>> getAllExpertises();
	public int getNbr(String s);
	public void declineExpertise(ExpertiseDemande d);
	public void acceptExpertise(Expertise d);
	public void addExpertiseDemande(ExpertiseDemande ed);
}
