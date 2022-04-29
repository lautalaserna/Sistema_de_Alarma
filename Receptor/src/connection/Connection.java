package connection;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
	private byte[] buffer = new byte[2048];
	private InetAddress adress;
	private int port;
	private ControllerReceptor cr;

	private Connection() {

	}

	private Connection(ControllerReceptor cr, int port) {
		try {
			this.cr = cr;
			this.datagramSocket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstace(ControllerReceptor cr, int port) {
		if (instance == null) {
			instance = new Connection(cr, port);
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
						System.out.println("Escuchando");
						DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
						datagramSocket.receive(datagramPacket);
						System.out.println("Recibido");
						adress = datagramPacket.getAddress();
						port = datagramPacket.getPort();
						

						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(datagramPacket.getData()));
						Message msg = (Message) iStream.readObject();
						iStream.close();

						msg.setInetAddress(adress);
						Receptor.getInstance().addMessage(msg);

					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
			}
		}.start();
	}

	public void response(boolean isConfirmed, InetAddress address, int port) {
		new Thread() {
			public void run() {
				buffer = (isConfirmed) ? "OK".getBytes() : "KO".getBytes();
				DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, address, port);
				try {
					datagramSocket.send(datagramPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
