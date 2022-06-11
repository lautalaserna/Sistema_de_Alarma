package connection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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
			socketLogs = new DatagramSocket(4040);
			socketSuscription = new DatagramSocket(4141);
			socketMonitor = new DatagramSocket(4242);
			socketHeartbeat = new DatagramSocket();
			
			listenLogs(socketLogs);
			listenSuscriptions(socketSuscription);
			listenMonitor(socketMonitor);
		} catch (SocketException e) {
			System.out.println("Error en la Redundancia");
			e.printStackTrace();
		}
	}
	
	public void listenLogs(DatagramSocket socket) {
		new Thread() {
			public void run() {
				while (true) {
					System.out.println("Servidor: Escuchando Mensajes");
					try {
						byte[] buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socket.receive(petition);
						
						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
						ArrayList<String> logs = (ArrayList<String>) iStream.readObject();
						iStream.close();
						
						Servidor.getInstance().setLogs(logs);
					} catch(Exception e) {
						System.out.println("Servidor: SocketLogs cerrado");
						break;
					}
				}
			}
		}.start();
	}
	
	public void listenSuscriptions(DatagramSocket socket) {
		new Thread() {
			public void run() {
				System.out.println("Servidor: Escuchando a Receptores");
				while (true) {
					try {
						byte[] buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socket.receive(petition);
						
						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
						ArrayList<ReceptorData> receptors = (ArrayList<ReceptorData>) iStream.readObject();
						iStream.close();
						
						Servidor.getInstance().setReceptors(receptors);
						
						System.out.println("Servidor: LLegó la lista del Primario.");
					} catch(Exception e) {
						System.out.println("Servidor: SocketSuscription cerrado");
						break;
					}
				}
			}
		}.start();
	}
	
	public void listenMonitor(DatagramSocket socket) {
		new Thread() {
			public void run() {
				System.out.println("Servidor: Escuchando Monitor");
				try {
					byte[] buffer = new byte[2048];
					DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
					socket.receive(petition);
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
						byte[] buffer = new byte[2048];
						
						ByteArrayOutputStream bStream = new ByteArrayOutputStream();
						ObjectOutput output = new ObjectOutputStream(bStream);
						output.writeObject(new String("AUX"));
						output.close();
						buffer = bStream.toByteArray();
						
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length,InetAddress.getByName("localhost"),2222);
						socketHeartbeat.send(petition);
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

	@Override
	public boolean switchConnection() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
