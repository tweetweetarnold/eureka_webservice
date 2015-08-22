package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.javadocmd.simplelatlng.LatLng;

@Entity
public class Canteen {
	@Id
	private int canteenId;
	private String canteenName;
	private String address;
	private LatLng location;
	private Date createDate;
	

}
