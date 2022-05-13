package model;

import java.net.InetAddress;

import connection.Connection;
import connection.Filter;

public class Servidor {

	public static void main(String[] args) {
		try {
			Connection c = new Connection(1010, 1011);
			c.getReceptors().add(new ReceptorData(new Filter(true,true,true,9090),InetAddress.getByName("localhost")));
			c.listenEmisores();
			c.listenReceptores();
		} catch (Exception e) {
			System.out.println("Sonaste");
			e.printStackTrace();
		} 
	}

}
