package FX;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import buisness.UserManageBusiness;
import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class mapPanelController implements Initializable, MapComponentInitializedListener {

	@FXML
	private Button button;

	@FXML
	private GoogleMapView mapView;

	private GoogleMap map;

	private UserManageBusiness umb;
	public static User u;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		umb= new UserManageBusiness();
		mapView.addMapInializedListener(this);

	}

	@Override
	public void mapInitialized() {

		MapOptions mapOptions = new MapOptions();
		mapOptions.center(new LatLong(34.30714385628804, 11.304931640625)).mapType(MapTypeIdEnum.ROADMAP).overviewMapControl(false)
		.panControl(false).rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(false)
		.zoom(7);
		map = mapView.createMap(mapOptions);

		for (User u : umb.getActiveUsers()) {
			System.out.println(u.getAdress());
			LatLong a = new LatLong(u.getAdress().getLatitude(), u.getAdress().getLongitude());
			MarkerOptions markerOptions00 = new MarkerOptions();
			markerOptions00.title(u.getUserName()).animation(Animation.BOUNCE);
			//markerOptions00.icon("/images/m.ico");
			markerOptions00.animation(Animation.BOUNCE);
			markerOptions00.position(a);
			Marker aM = new Marker(markerOptions00);
			map.addMarker(aM);
		}
//		InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
//		infoWindowOptions.content("<h2>Fred Wilkie</h2>" + "Current Location: Safeway<br>" + "ETA: 45 minutes");

	}
}