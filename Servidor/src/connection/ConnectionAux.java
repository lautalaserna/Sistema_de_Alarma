package connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import model.Servidor;

public class ConnectionAux implements IConnection {
	private Connection conn;
	private DatagramSocket socketLogs;
	private DatagramSocket socketSuscription;
	private DatagramSocket socketMonitor;
	private DatagramSocket socketHeartbeat;
	
	public ConnectionAux(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void listen() {
		try {
			// Acomodar
			socketLogs = new DatagramSocket(4040);
			socketSuscription = new DatagramSocket(4141);
			socketMonitor = new DatagramSocket(4242);
			socketHeartbeat = new DatagramSocket();
			
			listenLogs();
			listenSuscriptions();
			listenMonitor();
		} catch (SocketException e) {
			System.out.println("Error en la Redundancia");
			e.printStackTrace();
		}
	}
	
	public void listenLogs() {
		new Thread() {
			public void run() {
				while (true) {
					System.out.println("Servidor: Escuchando Logs");
					try {
						DatagramPacket petition = ConnUtils.buildPetition();
						socketLogs.receive(petition);
						ArrayList<String> logs = (ArrayList<String>) ConnUtils.openPetition(petition);
						Servidor.getInstance().setLogs(logs);
					} catch(Exception e) {
						System.out.println("Servidor: SocketLogs cerrado");
						break;
					}
				}
			}
		}.start();
	}
	
	public void listenSuscriptions() {
		new Thread() {
			public void run() {
				System.out.println("Servidor: Escuchando Receptores");
				while (true) {
					try {
						DatagramPacket petition = ConnUtils.buildPetition();
						socketSuscription.receive(petition);
						ArrayList<ReceptorData> receptors = (ArrayList<ReceptorData>) ConnUtils.openPetition(petition);
						Servidor.getInstance().setReceptors(receptors);
					} catch(Exception e) {
						System.out.println("Servidor: SocketSuscription cerrado");
						break;
					}
				}
			}
		}.start();
	}
	
	public void listenMonitor() {
		new Thread() {
			public void run() {
				System.out.println("Servidor: Escuchando Monitor");
				try {
					socketMonitor.receive(ConnUtils.buildPetition());
					conn.changeToMain();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	@Override
	public void heartbeat() {
		new Thread() {
			public void run() {
				while(true) {
					try {
						// Acomodar
						socketHeartbeat.send(ConnUtils.buildPetition(new String("AUX"), InetAddress.getByName("localhost"), 2222));
						Thread.sleep(2000);
					} catch (Exception e) {
						System.out.println("Servidor: SocketHeartbeat cerrado.");
						break;
					}
				}
			}
		}.start();
	}
	
	@Override
	public void closeConnections() {
		socketLogs.close();
		socketSuscription.close();
		socketMonitor.close();
		socketHeartbeat.close();
	}
	
}
