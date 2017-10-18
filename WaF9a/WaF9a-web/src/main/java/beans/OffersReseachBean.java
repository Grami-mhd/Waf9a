package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import JSF.OffersResearchLocal;

import entities.Offer;

@ManagedBean
@javax.faces.bean.ViewScoped
public class OffersReseachBean {

	@EJB
	private OffersResearchLocal offersResearchLocal;

	private String key;
	
	//private String ownerName ;
	
	
	private String selectedItem; 
	private List<String> availableItems; 
	
	private Set<String> ac ;
	
	//private List<Offer> allOffers = new ArrayList<>();
	private List<Offer> offers = new ArrayList<>();
	//private List<Offer> sortedOffers = new ArrayList<>();
	//private List<Offer> OffersbyOwnerName = new ArrayList<>();

	
	
	
	public void SearchOffers() {
		System.out.println(key);
		offers = offersResearchLocal.getOffersByKey(key);
	}	
	

	public void SortOffers() {
		System.out.println("nnnnn");
		offers = offersResearchLocal.SortOffersByDate();
	}
	
	public void SearchOffersByOwnerName() {
		System.out.println("vvvvvvvvvvv");
		offers = offersResearchLocal.getOffersByOwner(key);
	}
	
	@PostConstruct
	public void getExistantOffers(){

		offers= offersResearchLocal.getAllOffers();
		System.out.println("tttttt"+offers);
	}
	
//	public void setAutoComplete(){
//		ac= offersResearchLocal.autoComplete();
//		
//		System.out.println(ac+"aaaaaaaaaaaaa");
//	}
	
	public void init(){
		ac= offersResearchLocal.autoComplete();
		availableItems = new ArrayList<>(); 
		for (String string : ac) {
			
			availableItems.add(string);
			
		}
		System.out.println(availableItems+"commmmmmmmmmmmmmm");
		
	}
	
	public List<SelectItem> complete(String s) {
	    List<SelectItem> filtrados = new ArrayList<SelectItem>();
	    for (String ss : availableItems) {
	        if (ss.contains(s)) {filtrados.add(new SelectItem(ss));}
	    }
	    return filtrados;
	}
	 

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}


	public String getSelectedItem() {
		return selectedItem;
	}


	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}


	public List<String> getAvailableItems() {
		return availableItems;
	}


	public void setAvailableItems(List<String> availableItems) {
		this.availableItems = availableItems;
	}


	


	


	

	
	


	


	
	
	
	
	

	
	
	
	

}
