package model;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import connection.Connection;
import controller.ControllerLocation;
import persistence.Persistence;

public class Main {

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
		ControllerLocation cl = new ControllerLocation();
		
	}

}
