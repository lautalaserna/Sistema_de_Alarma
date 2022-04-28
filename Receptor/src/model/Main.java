package model;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;

import connection.Connection;
import connection.Filter;
import controller.ControllerAccept;
import persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		Receptor receptor = Receptor.getInstance();
		Filter filter = null;
		
		try {
			filter = Persistence.getFilterFromBin("filter.bin");
		} catch (Exception e) {
			File file = new File("filter.bin");
			try {
				file.createNewFile();
				filter = new Filter(false,false,false,8080);
			} catch (IOException e1) {
				// O no encontró la IP o hay un problema con el archivo.
				e.printStackTrace();
			}
		}
		
		// Test JList
		
		try {
			receptor.getReg().add(new Message(new Location("Casita 1",8080), new EventAsistenciaMedica()));
			receptor.getReg().add(new Message(new Location("Casita 2",8080), new EventFocoIncendio()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		receptor.setFilter(filter);
		ControllerAccept ca = new ControllerAccept();
		
	}

}
