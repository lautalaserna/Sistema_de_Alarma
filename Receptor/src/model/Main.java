package model;

import java.io.File;
import java.io.IOException;

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
			File file = new File("location.bin");
			try {
				file.createNewFile();
				filter = new Filter(null);
			} catch (IOException e1) {
				// O no encontró la IP o hay un problema con el archivo.
				e.printStackTrace();
			}
		}
		
		receptor.setFilter(filter);
		ControllerAccept ca = new ControllerAccept();
		
	}

}
