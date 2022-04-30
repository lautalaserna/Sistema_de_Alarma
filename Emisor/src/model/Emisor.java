package model;

import java.io.File;
import java.io.IOException;

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
		
		try {
			loc = Persistence.getLocationFromBin("location.bin");
		} catch (Exception e) {
			File f = new File("location.bin");
			try {
				f.createNewFile();
				loc = new Location();
			} catch (IOException e1) {
				// O no encontró la IP o hay un problema con el archivo.
				e.printStackTrace();
			}
		}
		
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
