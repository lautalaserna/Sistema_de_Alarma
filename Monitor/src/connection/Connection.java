package connection;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class Connection {
	private DatagramSocket socketMain;
	private DatagramSocket socketAux;
	
	public Connection() {
		try {
			socketMain = new DatagramSocket(1111);
			socketAux = new DatagramSocket(2222);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listenMain(DatagramSocket socket) {
		new Thread() {
			public void run() {
				System.out.println("Monitor: Escuchando un Servidor Principal");
				while (true) {
					try {
						byte[] buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socket.receive(petition);
						
						//startCountDown()
						
						
					} catch(Exception e) {
						System.out.println("Error al escuchar el Servidor Principal");
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void listenAux(DatagramSocket socket) {
		new Thread() {
			public void run() {
				System.out.println("Monitor: Escuchando un Servidor Secundario");
				while (true) {
					try {
						byte[] buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socket.receive(petition);
						
					} catch(Exception e) {
						System.out.println("Error al escuchar el Servidor Secundario");
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
