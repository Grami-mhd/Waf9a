package gramiTest;

import webSocket.AdminDiscussionsClient;

public class Send50Message {

	public static void main(String[] args) {
		AdminDiscussionsClient.connect();
		for(int i=0;i<51;i++){
			AdminDiscussionsClient.send("testing msg n°"+i);
		}
		AdminDiscussionsClient.disconnect();

	}

}
