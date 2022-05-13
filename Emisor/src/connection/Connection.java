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
import java.util.Observable;

import model.Emisor;
import model.Message;

public class Connection extends Observable{
	private TimeOut timeOut;
	private DatagramSocket socketUDP;
	private InetAddress inetAdress;
	private byte[] buffer;
	
	public Connection(TimeOut timeOut) throws SocketException, UnknownHostException {
		this.socketUDP = new DatagramSocket();
		this.inetAdress = InetAddress.getByName(Emisor.getInstance().getLocation().getIp()); //Broadcast
		this.timeOut = timeOut;
		timeOut.starTimer();
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
					timeOut.stopTimer();
					
					ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
					String response = (String) iStream.readObject();
					iStream.close();
					System.out.println("Emisor: Respuesta Recibida = " + response);
					
					// El Controller realiza los cambios
					setChanged();
					notifyObservers(response);
					
					socketUDP.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
