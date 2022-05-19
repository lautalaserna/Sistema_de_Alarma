package model;

import connection.Connection;
import controller.ControllerServer;

public class Servidor {

	public static void main(String[] args) {
		try {
			Connection c = new Connection();
			new ControllerServer(c);
		} catch (Exception e) {
			System.out.println("Error al conectar");
			e.printStackTrace();
		} 
	}

}
