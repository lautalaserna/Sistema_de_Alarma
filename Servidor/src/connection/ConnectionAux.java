package connection;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Observable;

import model.Message;
import model.Servidor;

public class ConnectionAux implements IConnection {
	private DatagramSocket socketMessage;
	private DatagramSocket socketSuscription;
	private DatagramSocket socketRedundancy;
		
	@Override
	public void listen() {
		try {
			socketMessage = new DatagramSocket(4040);
			socketSuscription = new DatagramSocket(4141);
			socketRedundancy = new DatagramSocket(4242);
			
			listenLogs(socketMessage);
			listenSuscriptions(socketSuscription);
			listenRedundancy(socketRedundancy);
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
						System.out.println("Error al recibir un Mensaje");
						e.printStackTrace();
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
						
						System.out.println("Servidor Secundario: LLegó la lista del Primario.");
					} catch(Exception e) {
						System.out.println("Error al recibir una Suscripción");
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void listenRedundancy(DatagramSocket socket) {
		new Thread() {
			public void run() {
				System.out.println("Servidor: Escuchando Redundancia");
				while (true) {
					try {
						byte[] buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socket.receive(petition);
						
						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
						ArrayList<String> logs = (ArrayList<String>) iStream.readObject();
						iStream.close();
						Servidor.getInstance().setLogs(logs);
						
					} catch(Exception e) {
						System.out.println("Error al suscribirse un Receptor");
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	@Override
	public void heartbeat() {
		// TODO Auto-generated method stub
	}

	@Override
	public void closeConnections() {
		socketMessage.close();
		socketSuscription.close();
		socketRedundancy.close();
	}
	
}
