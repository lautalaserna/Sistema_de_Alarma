package connection;

import java.io.ByteArrayOutputStream;
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
	private DatagramSocket datagramSocket;
	private InetAddress inetAdress;
	private byte[] buffer;
	ControllerEmisor ce;
	
	public Connection(ControllerEmisor ce) throws SocketException, UnknownHostException {
		this.datagramSocket = new DatagramSocket();
		this.inetAdress = InetAddress.getByName("localhost");
		this.ce = ce;
	}

	public void connect(Message msg, int port) {
		new Thread() {
			public void run() {
				
				try {
					// Construye un objeto a steam de caracteres
					System.out.println("Emisor listorti");
					ByteArrayOutputStream bStream = new ByteArrayOutputStream();
					ObjectOutput output = new ObjectOutputStream(bStream); 
					output.writeObject(msg);
					output.close();
					System.out.println("Se escribio el objeto");
					buffer = bStream.toByteArray();
					DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAdress, port);
					datagramSocket.send(datagramPacket);
					System.out.println("Objeto Enviado");
					datagramSocket.receive(datagramPacket);
					String response = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
					ce.setConfirmation(response);
					datagramSocket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
