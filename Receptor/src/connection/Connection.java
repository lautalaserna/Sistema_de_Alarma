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
import java.util.Observable;
import java.util.Observer;

import model.Confirmation;
import model.ConfirmationFactory;
import model.Filter;
import model.Message;
import model.Receptor;

public class Connection extends Observable implements Observer {
	private ArrayList<Observable> obs = new ArrayList<Observable>();
	private DatagramSocket socketUDP;
	private DatagramSocket socketEcho;
	private byte[] buffer = new byte[2048];
	private Filter filter;
	private ArrayList<TimeOut> timeOuts;

	public Connection(Filter filter, int port) {
		try {
			this.filter = filter;
			this.socketUDP = new DatagramSocket(port);
			this.socketEcho = new DatagramSocket(port+1);
			this.timeOuts = new ArrayList<TimeOut>();
			listenEcho();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void listenEcho() {
		new Thread() {
			public void run() {
				while(true) {
					try {
						byte[] buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socketEcho.receive(petition);
						socketEcho.send(petition);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public void listen() {
		new Thread() {
			public void run() {
				System.out.println("Receptor: Escuchando");
				while (true) {
					try {
						buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socketUDP.receive(petition);
						
						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
						Message msg = (Message) iStream.readObject();
						iStream.close();
						msg.setServerInetAddress(petition.getAddress()); //Del Sevidor
						msg.setServerPort(petition.getPort()); //Del Servidor

						System.out.println("Receptor: Mensaje Recibido = " + msg.toString() + " (Puerto: " + msg.getPort() + ")");

						TimeOut t = new TimeOut();
						addObservable(t);
						t.starTimerFromMsg(msg);
						timeOuts.add(t);

						Receptor.getInstance().addMessage(msg);

					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
			}
		}.start();
	}

	public void response(boolean isConfirmed, Message msg) {
		try {
			Confirmation c = null;
			
			// Depende si el método es invocado por el jButton o el Timer.
			if (isConfirmed)
				c = ConfirmationFactory.getConfirmation(msg.getInetAddress(), msg.getPort(), "OK");
			else
				c = ConfirmationFactory.getConfirmation(msg.getInetAddress(), msg.getPort(), "KO");
			
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			ObjectOutput output = new ObjectOutputStream(bStream); 
			output.writeObject(c);
			output.close();
			
			buffer = bStream.toByteArray();
			
			DatagramPacket petition = new DatagramPacket(buffer, buffer.length, msg.getServerInetAddress(), msg.getServerPort());
			socketUDP.send(petition);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void suscribe(InetAddress address, int port) {
		new Thread() {
			public void run() {

				DatagramPacket petition;
				try {
					ByteArrayOutputStream bStream = new ByteArrayOutputStream();
					ObjectOutput output = new ObjectOutputStream(bStream);
					output.writeObject(filter);
					output.close();

					buffer = bStream.toByteArray();
					petition = new DatagramPacket(buffer, buffer.length, address, port);
					socketUDP.send(petition);
					System.out.println("Receptor: Suscripción realizada a la IP: " + address.getHostAddress());
				} catch (Exception e) {
					System.out.println("Error al suscribirse");
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void addObservable(Observable o) {
		this.obs.add(o);
		o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	public ArrayList<TimeOut> getTimeOuts() {
		return timeOuts;
	}
}
