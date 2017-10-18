package buisness;

import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Expertise;

import service.OffersDemandsPerExpertiseRemote;

public class OffersDemandsBusiness {

	private InitialContext ctx;
	private OffersDemandsPerExpertiseRemote proxy;
	
	public OffersDemandsBusiness(){
		
		try {
			
			ctx = new InitialContext();
			proxy=(OffersDemandsPerExpertiseRemote) ctx.lookup("/WaF9a-ear/WaF9a-ejb/OffersDemandsPerExpertise!service.OffersDemandsPerExpertiseRemote");
		
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String, List<Expertise>> getListEpertisePerDomain(){
		
		return proxy.getEpertisePerDomain();
		
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		ctx.close();
		ctx=null;
		super.finalize();
	}
}
