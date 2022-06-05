package connection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;

import model.Confirmation;
import model.Filter;
import model.Message;

public class Connection extends Observable {
	private ArrayList<ReceptorData> receptors = new ArrayList<ReceptorData>();
	// private IConnection con;
	private DatagramSocket socketMessage;
	private DatagramSocket socketSuscription;
	private DatagramSocket socketConfirmation;
	private int[] ports;

	public Connection() throws SocketException, UnknownHostException {
		this.ports = FileUtil.readPorts(FileUtil.PATH);
		this.socketMessage = new DatagramSocket(ports[0]);
		this.socketSuscription = new DatagramSocket(ports[1]);
		this.socketConfirmation = new DatagramSocket(ports[2]);
		//this.activeMsg = 0;
		
		listenMessages(socketMessage);
		listenSuscriptions(socketSuscription, receptors);
		listenConfirmations(socketMessage, socketConfirmation);
		
		// con.listenMessages(socketMessage);
		// con.listenSuscriptions(socketSuscription, receptors);
		// con.listenConfirmations(socketMessage, socketConfirmation);
		// con.listenServidor();
		// con.heartbeat();
	}

	public void listenMessages(DatagramSocket socket) {
		new Thread() {
			public void run() {
				while (true) {
					try {
						System.out.println("Servidor: Escuchando a Emisores");
						byte[] bufferEmisor = new byte[2048];
						DatagramPacket petition = new DatagramPacket(bufferEmisor, bufferEmisor.length);
						socket.receive(petition);
						
						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
						Message msg = (Message) iStream.readObject();
						iStream.close();
										
						msg.setInetAddress(petition.getAddress()); // Address del Emisor
						msg.setPort(petition.getPort()); // Puerto del Emisor
						
						System.out.println("Servidor: Mensaje recibido: " + msg + " (Puerto: " + msg.getPort() + ")");
						setChanged();
						notifyObservers(msg); // Log
												
						if(existReceptor(msg)) {
							sendMsgToReceptors(msg);	
						} else {
							String response = "KO";
							
							System.out.println("Servidor: No se recibió ninguna respuesta. Rta = " + response);
														
							ByteArrayOutputStream bStream = new ByteArrayOutputStream();
							ObjectOutput output = new ObjectOutputStream(bStream);
							output.writeObject(new String(response));
							output.close();
							bufferEmisor = bStream.toByteArray();
							petition = new DatagramPacket(bufferEmisor, bufferEmisor.length, msg.getInetAddress(), msg.getPort());
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
					/*
					if(activeMsg == 0) {
						socketConfirmation = new DatagramSocket(ports[2]);
					}
					activeMsg++;*/
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
								
								/*
								// No sirve, da todo el tiempo true porque localhost siempre es alcanzable
								System.out.println(rd.getAddress().isReachable(2000));
								if(rd.getAddress().isReachable(2000)) {
									socketConfirmation.send(petition);									
								}
								*/
								
								setChanged();
								notifyObservers("Mensaje enviado al Receptor: " + rd.getAddress().getHostAddress() + ":"+ rd.getFilter().getPort());
								
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
						
						setChanged();
						notifyObservers("Respuesta del Receptor: "
								+ "(De: " + petition.getAddress().getHostAddress() + ":" + petition.getPort() + ") "
								+ "(Para: " + c.getAddress().getHostAddress() + ":" + c.getPort() + ") "
								+ "Respuesta: " + c.getValue());
						
						petition = new DatagramPacket(buffer, buffer.length, c.getAddress(), c.getPort());
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
	
	public ArrayList<ReceptorData> getReceptors() {
		return receptors;
	}
}
