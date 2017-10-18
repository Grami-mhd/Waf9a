package FX;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import buisness.ExpertiseDemandeBuisness;
import entities.Expertise;
import entities.ExpertiseDemande;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import webSocket.ExpertiseDemandesClient;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

public class ExpertisesController {
	@FXML
	private TreeView tree;
	@FXML
	private VBox vbox;
	private static VBox vbox1;
	
	
	private static ExpertiseDemandeBuisness eb;
	
	private Map<String, List<Expertise>> m;
	
	@FXML
	public void initialize(){
		vbox1=vbox;
		eb=new ExpertiseDemandeBuisness();	
		m=eb.getAllExpertises();
		fillTree();
		ExpertiseDemandesClient.connect();
	}
	
	public void fillTree(){
	
		TreeItem<String> all = new TreeItem<>("domains");
		all.setExpanded(true);
		for(Map.Entry<String, List<Expertise>> em :m.entrySet()){
			List<Expertise> l = em.getValue();
	    	TreeItem<String> ti = new TreeItem<>(em.getKey(),new ImageView("images/btn_expertise.png"));
	    	for(Expertise e : l){
	    		TreeItem<String> t = new TreeItem<>(e.getName());
	    		ti.getChildren().add(t);
	    	}
	    	all.getChildren().add(ti);
		}
		tree.setRoot(all);
	}
	
	public static void fillVbox(){
		vbox1.getChildren().clear();
		System.out.println("hahahhahaha");
		for(Map.Entry<String, List<ExpertiseDemande>> em :eb.getAll().entrySet()){
			ExpertiseDemandeController.list=em.getValue();
			try {
				vbox1.getChildren().add((AnchorPane)FXMLLoader.load(ExpertisesController.class.getResource("ExpertiseDemands.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
