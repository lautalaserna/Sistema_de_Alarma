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
	private DatagramSocket socketUDP;
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
			this.socketUDP = new DatagramSocket(port);
			// this.datagramPacket = new DatagramPacket(buffer, buffer.length);

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
						System.out.println("Receptor: Escuchando");
						buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socketUDP.receive(petition);
						adress = petition.getAddress();
						port = petition.getPort();

						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
						Message msg = (Message) iStream.readObject();
						iStream.close();
						System.out.println("Receptor: Mensaje Recibido = " + msg.toString());

						// Filtro de mensajes
						if (filter.isAccepted(msg)) {
							System.out.println("Receptort: Mensaje Aceptado");
							msg.setInetAddress(adress);
							msg.getLoc().setPort(port);
							Receptor.getInstance().addMessage(msg);
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
		try {
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			ObjectOutput output = new ObjectOutputStream(bStream);

			// Depende si el método es invocado por el jButton o el Timer.
			if (isConfirmed)
				output.writeObject(new String("OK"));
			else
				output.writeObject(new String("KO"));
			output.close();
			buffer = bStream.toByteArray();
			
			DatagramPacket petition = new DatagramPacket(buffer, buffer.length, address, port);
			socketUDP.send(petition);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
