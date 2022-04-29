package connection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import controller.ControllerEmisor;
import model.Message;

public class Connection {
	private DatagramSocket socketUDP;
	private InetAddress inetAdress;
	private byte[] buffer;
	ControllerEmisor ce;
	
	public Connection(ControllerEmisor ce) throws SocketException, UnknownHostException {
		this.socketUDP = new DatagramSocket();
		this.inetAdress = InetAddress.getByName("localhost");
		this.ce = ce;
	}

	public void connect(Message msg, int port) {
		new Thread() {
			public void run() {
				
				try {
					System.out.println("Emisor: Enviando Mensaje");
					ByteArrayOutputStream bStream = new ByteArrayOutputStream();
					ObjectOutput output = new ObjectOutputStream(bStream); 
					output.writeObject(msg);
					output.close();
					
					buffer = bStream.toByteArray();
					
					DatagramPacket petition = new DatagramPacket(buffer, buffer.length, inetAdress, port);
					socketUDP.send(petition);
					System.out.println("Emisor: Mensaje Enviado");
					
					socketUDP.receive(petition);
					ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
					String response = (String) iStream.readObject();
					iStream.close();
					System.out.println("Emisor: Respuesta Recibida = " + response);
					
					// El Controller realiza los cambios
					ce.setConfirmation(response);
					ce.enableBtns();
					
					socketUDP.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
