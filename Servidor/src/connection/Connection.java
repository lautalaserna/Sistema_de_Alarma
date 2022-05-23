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

import model.Filter;
import model.Message;

public class Connection extends Observable {
	private ArrayList<ReceptorData> receptors = new ArrayList<ReceptorData>();
	private DatagramSocket socketEmisor;
	private DatagramSocket socketReceptor;
	private DatagramSocket socketConfirmation;
	private byte[] bufferEmisor = new byte[2048];
	private byte[] bufferReceptor = new byte[2048];
	private int[] ports;
	private int activeMsg;

	public Connection() throws SocketException, UnknownHostException {
		this.ports = FileUtil.readPorts(FileUtil.PATH);
		this.socketEmisor = new DatagramSocket(ports[0]);
		this.socketReceptor = new DatagramSocket(ports[1]);
		
		this.activeMsg = 0;
		
		listenEmisores();
		listenReceptores();
	}

	public void listenEmisores() {
		new Thread() {
			public void run() {
				while (true) {
					try {
						System.out.println("Servidor: Escuchando a Emisores");
						bufferEmisor = new byte[2048];
						DatagramPacket petition = new DatagramPacket(bufferEmisor, bufferEmisor.length);
						socketEmisor.receive(petition);
						
						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
						Message msg = (Message) iStream.readObject();
						iStream.close();
						
						System.out.println("Se recibió un mensaje del puerto: " + petition.getPort());
						System.out.println("El mensaje actualmente tiene el puerto: " + msg.getPort());
						
						msg.setInetAddress(petition.getAddress());
						msg.setPort(petition.getPort());
						
						System.out.println("Servidor: Mensaje recibido: " + msg + "(" + msg.getPort() + ")");
						setChanged();
						notifyObservers(msg);
												
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
							socketEmisor.send(petition);
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
					if(activeMsg == 0) {
						socketConfirmation = new DatagramSocket(ports[2]);
					}
					activeMsg++;
					System.out.println(activeMsg);
					for (ReceptorData rd : receptors) {
						if(rd.getFilter().isAccepted(msg)) {
							try {
								ByteArrayOutputStream bStream = new ByteArrayOutputStream();
								ObjectOutput output = new ObjectOutputStream(bStream); 
								output.writeObject(msg);
								output.close();
								
								bufferEmisor = bStream.toByteArray();
								System.out.println("Se envia el mensaje al puerto: " + rd.getFilter().getPort());
								DatagramPacket petition = new DatagramPacket(bufferEmisor, bufferEmisor.length, rd.getAddress(), rd.getFilter().getPort());
								socketConfirmation.send(petition);
								
								setChanged();
								notifyObservers("Mensaje enviado al Receptor: " + rd.getAddress().getHostAddress() + ":"+ rd.getFilter().getPort());
								
							} catch (IOException e) {
								System.out.println("Error enviar un mensaje a los Receptores");
								e.printStackTrace();
							}
						}
					}
					DatagramPacket petition = new DatagramPacket(bufferEmisor, bufferEmisor.length);
					
					socketConfirmation.receive(petition);
					activeMsg--;
					if(activeMsg == 0)
						socketConfirmation.close();
					
					System.out.println(activeMsg);
					
					System.out.println("Se recibe la confirmación del puerto: " + petition.getPort());
					
					ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
					String response = (String) iStream.readObject();
					iStream.close();
					System.out.println("Servidor: Respuesta Recibida = " + response);
					
					setChanged();
					notifyObservers("Respuesta del Receptor: "
							+ "(De: " + petition.getAddress().getHostAddress() + ":" + petition.getPort() + ") "
							+ "(Para: " + msg.getInetAddress().getHostAddress() + ":" + msg.getPort() + ") "
							+ "Respuesta: " + response);
					
					bufferEmisor = petition.getData();
					System.out.println("Se envia la confimarción al puerto: " + msg.getPort());
					petition = new DatagramPacket(bufferEmisor, bufferEmisor.length, msg.getInetAddress(), msg.getPort());
					socketEmisor.send(petition);
					
				} catch (Exception e) {
					System.out.println("A ver q onda");
					e.printStackTrace();
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
	
	public void listenReceptores() {
		new Thread() {
			public void run() {
				System.out.println("Servidor: Escuchando a Receptores");
				while (true) {
					try {
						bufferReceptor = new byte[2048];
						DatagramPacket petition = new DatagramPacket(bufferReceptor, bufferReceptor.length);
						socketReceptor.receive(petition);
						
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
	
	public ArrayList<ReceptorData> getReceptors() {
		return receptors;
	}
}
