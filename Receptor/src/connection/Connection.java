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

import controller.ControllerReceptor;
import model.Message;
import model.Receptor;

public class Connection {
	private static Connection instance = null;
	private DatagramSocket datagramSocket;
	private DatagramPacket datagramPacket;
	private byte[] buffer = new byte[2048];
	private InetAddress adress;
	private int port;
	private ControllerReceptor cr;
	private Filter filter;

	private Connection() {

	}

	private Connection(ControllerReceptor cr, Filter filter, int port) {
		try {
			this.filter = filter;
			this.cr = cr;
			this.datagramSocket = new DatagramSocket(port);
			this.datagramPacket = new DatagramPacket(buffer, buffer.length);
			
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstace(ControllerReceptor cr, Filter filter, int port) {
		if (instance == null) {
			instance = new Connection(cr, filter, port);
		}
		return instance;
	}

	public static Connection getInstance() {
		return instance;
	}

	public void listen() {
		new Thread() {
			public void run() {
				while (true) {
					try {
						System.out.println("Receptor Escuchando");
						DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
						datagramSocket.receive(datagramPacket);
						System.out.println("Recibido por el Receptor");
						adress = datagramPacket.getAddress();
						port = datagramPacket.getPort();

						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(datagramPacket.getData()));
						Message msg = (Message) iStream.readObject();
						iStream.close();

						if (filter.isAccepted(msg)) {
							System.out.println("Aceptado");
							msg.setInetAddress(adress);
							Receptor.getInstance().addMessage(msg);
						}
						
						
						try {
							ByteArrayOutputStream bStream = new ByteArrayOutputStream();
							ObjectOutput output = new ObjectOutputStream(bStream); 
							output.writeObject(new String("OK"));
							output.close();
							buffer = bStream.toByteArray();
							datagramPacket = new DatagramPacket(buffer, buffer.length, adress, port);
							datagramSocket.send(datagramPacket);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
			}
		}.start();
	}

	public void response(boolean isConfirmed, InetAddress address, int port) {
		// buffer = (isConfirmed) ? "OK".getBytes() : "KO".getBytes();
		try {
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			ObjectOutput output = new ObjectOutputStream(bStream); 
			output.writeObject(new String("OK"));
			output.close();
			buffer = bStream.toByteArray();
			datagramPacket = new DatagramPacket(buffer, buffer.length, address, port);
			datagramSocket.send(datagramPacket);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
