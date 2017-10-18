package service;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import entities.Expertise;

@Remote
public interface OffersDemandsPerExpertiseRemote {

	public Map<String, List<Expertise>> getEpertisePerDomain();
	
}
