package model;

import controller.ControllerLocation;
import persistence.Persistence;

public class Emisor {
	private static Emisor instance = null;
	private Location loc;

	private Emisor() {
	}

	public static Emisor getInstance() {
		if (instance == null)
			instance = new Emisor();

		return instance;
	}
	
	public static void main(String[] args) {
		Emisor emisor = Emisor.getInstance();
		Location loc = null;
		loc = Persistence.getLocationFromBin();
		emisor.setLocation(loc);
		new ControllerLocation();
	}
	
	public void setLocation(Location loc) {
		this.loc = loc;
	}
	
	public Location getLocation() {
		return this.loc;
	}

}
