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
	private byte[] bufferEmisor = new byte[2048];
	private byte[] bufferReceptor = new byte[2048];
	private boolean existReceptor = false;

	public Connection() throws SocketException, UnknownHostException {
		int ports[] = FileUtil.readPorts(FileUtil.PATH);
		this.socketEmisor = new DatagramSocket(ports[0]);
		this.socketReceptor = new DatagramSocket(ports[1]);
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
						
						msg.setInetAddress(petition.getAddress());
						msg.setPort(petition.getPort());
						
						System.out.println("Servidor: Mensaje recibido: " + msg);
						setChanged();
						notifyObservers(msg);
						
						sendMsgToReceptors(msg);
						
						String response;
						String log;
						Thread.sleep(5000); // Espera 5 segundos para verificar si hay algún Receptor para el tipo de Evento.
						if(existReceptor) {
							existReceptor = false;
							socketEmisor.receive(petition);
							iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
							response = (String) iStream.readObject();
							iStream.close();
							System.out.println("Servidor: Respuesta Recibida = " + response);
							
							log = "Respuesta del Receptor: "
									+ "(De: " + petition.getAddress().getHostAddress() + ":" + petition.getPort() + ") "
									+ "(Para: " + msg.getInetAddress().getHostAddress() + ":" + msg.getPort() + ") "
									+ "Respuesta: " + response;
							
							bufferEmisor = petition.getData();
						} else {
							response = "KO";
							log = "No se encuentran Receptores activos para el Evento de " + msg.getEvent().getEventType();
							System.out.println("Servidor: No se recibió ninguna respuesta. Rta = " + response);
							
							ByteArrayOutputStream bStream = new ByteArrayOutputStream();
							ObjectOutput output = new ObjectOutputStream(bStream);
							output.writeObject(new String(response));
							output.close();
							bufferEmisor = bStream.toByteArray();
						}
						
						
						setChanged();
						notifyObservers(log);
						
						petition = new DatagramPacket(bufferEmisor, bufferEmisor.length, msg.getInetAddress(), msg.getPort());
						socketEmisor.send(petition);
						
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
				for (ReceptorData rd : receptors) {
					if(rd.getFilter().isAccepted(msg)) {
						existReceptor = true;
						System.out.println("Servidor: Existe al menos un Receptor");
						try {
							ByteArrayOutputStream bStream = new ByteArrayOutputStream();
							ObjectOutput output = new ObjectOutputStream(bStream); 
							output.writeObject(msg);
							output.close();
							
							bufferEmisor = bStream.toByteArray();
							
							DatagramPacket petition = new DatagramPacket(bufferEmisor, bufferEmisor.length, rd.getAddress(), rd.getFilter().getPort());
							socketEmisor.send(petition);
							System.out.println("Servidor: Mensaje Enviado");
							
						} catch (IOException e) {
							System.out.println("Error enviar un mensaje a los Receptores");
							e.printStackTrace();
						}
					}
				}
			}
		}.start();
	}
	
	public void listenReceptores() {
		new Thread() {
			public void run() {
				while (true) {
					try {
						System.out.println("Servidor: Escuchando a Receptores");
						bufferReceptor = new byte[2048];
						DatagramPacket petition = new DatagramPacket(bufferReceptor, bufferReceptor.length);
						socketReceptor.receive(petition);
						
						ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
						Filter f = (Filter) iStream.readObject();
						iStream.close();
						
						ReceptorData rd = new ReceptorData(f,petition.getAddress());
						setChanged();
						notifyObservers(rd);
						System.out.println("Servidor: Receptor suscripto:");
						System.out.println("- IP: " + rd.getAddress().getHostAddress());
						System.out.println("- Puerto: " + rd.getFilter().getPort());
						System.out.println("- AM: " + rd.getFilter().isAcceptAM());
						System.out.println("- PS: " + rd.getFilter().isAcceptPS());
						System.out.println("- FI: " + rd.getFilter().isAcceptFI());
						
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
