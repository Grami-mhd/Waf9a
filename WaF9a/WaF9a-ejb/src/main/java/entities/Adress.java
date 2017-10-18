package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Adress implements Serializable{

	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private int id;
	private static final long serialVersionUID = 1L;
	private double latitude=0;
	private double longitude=0;
	private String address="adress";
	
	
	public Adress() {
		super();	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return address;
	}
	
	
}
