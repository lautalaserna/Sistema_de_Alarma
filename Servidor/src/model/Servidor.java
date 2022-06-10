package model;

import connection.Connection;
import controller.ControllerSelection;
import controller.ControllerServer;

public class Servidor {

	public static void main(String[] args) {
		try {
			new ControllerSelection();
		} catch (Exception e) {
			System.out.println("Error al conectar");
			e.printStackTrace();
		} 
	}

}
