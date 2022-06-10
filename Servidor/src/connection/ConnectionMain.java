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

import model.Confirmation;
import model.Filter;
import model.Message;

public class ConnectionMain extends Observable implements IConnection{
	private ArrayList<ReceptorData> receptors = new ArrayList<ReceptorData>();
	private DatagramSocket socketMessage;
	private DatagramSocket socketSuscription;
	private DatagramSocket socketConfirmation;
	private DatagramSocket socketRedundancy;
	private int[] ports;
		
	@Override	
	public void listen() {
		this.ports = FileUtil.readPorts(FileUtil.PATH);		
		try {
			socketMessage = new DatagramSocket(ports[0]);
			socketSuscription = new DatagramSocket(ports[1]);
			socketConfirmation = new DatagramSocket(ports[2]);
			socketRedundancy = new DatagramSocket();
			
			listenMessages(socketMessage);
			listenSuscriptions(socketSuscription, receptors);
			listenConfirmations(socketMessage, socketConfirmation);
			pingEchoChek();
		} catch (SocketException e) {
			System.out.println("Error al escuchar");
			e.printStackTrace();
		}
		
	}
	
	public void pingEchoChek() {
		new Thread() {
			public void run() {
				while(true) {
					for(ReceptorData rd : receptors) {
						System.out.println(rd.getFilter().getPort());
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void listenMessages(DatagramSocket socket) {
		new Thread() {
			public void run() {
				while (true) {
					try {
						System.out.println("Servidor: Escuchando a Emisores");
						byte[] buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socket.receive(petition);
						
						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
						Message msg = (Message) iStream.readObject();
						iStream.close();
										
						msg.setInetAddress(petition.getAddress()); // Address del Emisor
						msg.setPort(petition.getPort()); // Puerto del Emisor
						
						System.out.println("Servidor: Mensaje recibido: " + msg + " (Puerto: " + msg.getPort() + ")");
						
						//String log = "Nuevo Mensaje: "+ 
						//				"(Desde: " + msg.getInetAddress().getHostAddress() + ":" + msg.getPort() + 
						//				") " + msg.toString()
						
						// Servidor.getInstance().addLog(log)
						setChanged();
						notifyObservers(msg); // Log
						
						// Temporal
						ByteArrayOutputStream bStream = new ByteArrayOutputStream();
						ObjectOutput output = new ObjectOutputStream(bStream); 
						output.writeObject(msg);
						output.close();
						buffer = bStream.toByteArray();
						petition = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 4040);
						socketRedundancy.send(petition);
						
						if(existReceptor(msg)) {
							sendMsgToReceptors(msg);	
						} else {
							String response = "KO";
							
							System.out.println("Servidor: No se recibió ninguna respuesta. Rta = " + response);
														
							bStream = new ByteArrayOutputStream();
							output = new ObjectOutputStream(bStream);
							output.writeObject(new String(response));
							output.close();
							buffer = bStream.toByteArray();
							petition = new DatagramPacket(buffer, buffer.length, msg.getInetAddress(), msg.getPort());
							socket.send(petition);
						}
						
					} catch(Exception e) {
						System.out.println("Error al conectar con el Emisor");
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void sendMsgToReceptors(Message msg) {
		new Thread() {
			public void run() {
				try {
					for (ReceptorData rd : receptors) {
						if(rd.getFilter().isAccepted(msg)) {
							try {
								ByteArrayOutputStream bStream = new ByteArrayOutputStream();
								ObjectOutput output = new ObjectOutputStream(bStream); 
								output.writeObject(msg);
								output.close();
								
								byte[] buffer = bStream.toByteArray();
								DatagramPacket petition = new DatagramPacket(buffer, buffer.length, rd.getAddress(), rd.getFilter().getPort());
								socketConfirmation.send(petition);
								
								String log = "Mensaje enviado al Receptor: " + rd.getAddress().getHostAddress() + ":"+ rd.getFilter().getPort();
								
								setChanged();
								notifyObservers(log);
								
								bStream = new ByteArrayOutputStream();
								output = new ObjectOutputStream(bStream);
								output.writeObject(new String(log));
								output.close();
								buffer = bStream.toByteArray();
								
								petition = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 4242);
								socketMessage.send(petition);
								
							} catch (IOException e) {
								System.out.println("Error enviar un mensaje a los Receptores");
								e.printStackTrace();
							}
						}
					}
				} catch (Exception e) {
					System.out.println("Error al conectar con la Confirmación");
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	public void listenConfirmations(DatagramSocket socketMessage, DatagramSocket socketConfirmation) {
		new Thread() {
			public void run() {
				System.out.println("Servidor: Esperando Confirmaciones");
				while (true) {
					try {
						byte[] buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socketConfirmation.receive(petition);
						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
						
						System.out.println(iStream.getClass());
						Confirmation c = (Confirmation) iStream.readObject();
						iStream.close();
						
						ByteArrayOutputStream bStream = new ByteArrayOutputStream();
						ObjectOutput output = new ObjectOutputStream(bStream);
						output.writeObject(new String(c.getValue()));
						output.close();
						buffer = bStream.toByteArray();
						
						String log = "Respuesta del Receptor: "
									+ "(De: " + petition.getAddress().getHostAddress() + ":" + petition.getPort() + ") "
									+ "(Para: " + c.getAddress().getHostAddress() + ":" + c.getPort() + ") "
									+ "Respuesta: " + c.getValue();
						
						setChanged();
						notifyObservers(log);
						
						petition = new DatagramPacket(buffer, buffer.length, c.getAddress(), c.getPort());
						socketMessage.send(petition);
						
						bStream = new ByteArrayOutputStream();
						output = new ObjectOutputStream(bStream);
						output.writeObject(new String(log));
						output.close();
						buffer = bStream.toByteArray();
						
						petition = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 4242);
						socketMessage.send(petition);
					} catch(Exception e) {
						System.out.println("Error al recibir una Confirmación");
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void listenSuscriptions(DatagramSocket socket, ArrayList<ReceptorData> receptors) {
		new Thread() {
			public void run() {
				System.out.println("Servidor: Escuchando a Receptores");
				while (true) {
					try {
						byte[] buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socket.receive(petition);
						
						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
						Filter f = (Filter) iStream.readObject();
						iStream.close();
						
						ReceptorData rd = new ReceptorData(f,petition.getAddress());
						setChanged();
						notifyObservers(rd);
						
						ByteArrayOutputStream bStream = new ByteArrayOutputStream();
						ObjectOutput output = new ObjectOutputStream(bStream); 
						output.writeObject(rd);
						output.close();
						buffer = bStream.toByteArray();
						petition = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 4141);
						socketRedundancy.send(petition);
						
						System.out.println("Servidor: Receptor suscripto: " + rd.toString());						
						receptors.add(rd);	
					} catch(Exception e) {
						System.out.println("Error al suscribirse un Receptor");
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public boolean existReceptor(Message msg) {
		boolean res = false;
		for (ReceptorData rd : receptors) {
			if(rd.getFilter().isAccepted(msg)) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	@Override
	public ArrayList<ReceptorData> getReceptors() {
		return receptors;
	}
	
	@Override
	public void heartbeat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeConnections() {
		socketMessage.close();
		socketSuscription.close();
		socketConfirmation.close();
		socketRedundancy.close();
	}
	
}
