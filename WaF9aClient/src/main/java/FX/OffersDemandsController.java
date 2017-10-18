package FX;


import java.util.List;
import java.util.Map;

import buisness.OffersDemandsBusiness;
import entities.Expertise;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

public class OffersDemandsController {
	
	@FXML
	private Accordion domains;
	
	Map<String, List<Expertise>> m = new OffersDemandsBusiness().getListEpertisePerDomain() ;
	
	
	@FXML
	public void initialize(){
		
		for(Map.Entry<String, List<Expertise>> en : m.entrySet()){
			String s = en.getKey();
			List<Expertise> l =en.getValue();
			
			TitledPane tp = new TitledPane();
			tp.setText(s);
			CategoryAxis x = new CategoryAxis();
			x.setLabel("Expertises");
			NumberAxis y = new NumberAxis();
			y.setLabel("Number");
			BarChart<String,Number> bc = 
		            new BarChart<String,Number>(x,y);
			bc.setTitle("Offers and Users for each expertise");
			
			XYChart.Series s1 = new XYChart.Series();
			s1.setName("Users");
			XYChart.Series s2 = new XYChart.Series();
			s2.setName("Offers");
			for(Expertise ex:l){
				s1.getData().add( new XYChart.Data<>(ex.getName(),ex.getUsers().size()));
				s2.getData().add( new XYChart.Data<>(ex.getName(),ex.getOffers().size()));
			}
			bc.getData().addAll(s1,s2);
			tp.setContent(bc);
		domains.getPanes().add(tp);	
		}
		
	}

}
