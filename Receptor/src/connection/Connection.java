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

import model.Message;
import model.Receptor;

public class Connection extends Observable implements Observer{
	private ArrayList<Observable> obs = new ArrayList<Observable>();
	private DatagramSocket socketUDP;
	private byte[] buffer = new byte[2048];
	private Filter filter;
	private ArrayList<TimeOut> timeOuts;

	public Connection(Filter filter, int port) {
		try {
			this.filter = filter;
			this.socketUDP = new DatagramSocket(port);
			this.timeOuts = new ArrayList<TimeOut>();

		} catch (SocketException e) {
			e.printStackTrace();
		}
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
						InetAddress adress = petition.getAddress();
						int port = petition.getPort();

						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
						Message msg = (Message) iStream.readObject();
						iStream.close();
						System.out.println("Receptor: Mensaje Recibido = " + msg.toString());

						// Filtro de mensajes
						if (filter.isAccepted(msg)) {
							System.out.println("Receptort: Mensaje Aceptado");
							msg.setInetAddress(adress);
							msg.setPort(port);
							
							TimeOut t = new TimeOut();
							addObservable(t);
							t.starTimerFromMsg(msg);
							timeOuts.add(t);
							
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
