package model;

import java.net.InetAddress;

import connection.Connection;
import connection.ReceptorData;
import controller.ControllerServer;

public class Servidor {

	public static void main(String[] args) {
		try {
			Connection c = new Connection(1010, 1011);
			new ControllerServer(c);
			c.listenEmisores();
			c.listenReceptores();
		} catch (Exception e) {
			System.out.println("Error al conectar");
			e.printStackTrace();
		} 
	}

}
