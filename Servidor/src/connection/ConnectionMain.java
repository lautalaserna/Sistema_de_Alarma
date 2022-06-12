package connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import model.Confirmation;
import model.Filter;
import model.Message;
import model.Servidor;

public class ConnectionMain implements IConnection{
	private DatagramSocket socketMessage;
	private DatagramSocket socketSuscription;
	private DatagramSocket socketConfirmation;
	private DatagramSocket socketMonitor;
	private DatagramSocket socketRedundancy;
	private DatagramSocket socketHeartbeat;
	private DatagramSocket socketPingEcho;
	private int[] ports;
	private String[] ips;
	
	@Override	
	public void listen() {
		this.ports = ConnUtils.readPorts(ConnUtils.PATH_PRIMARIO);
		this.ips = ConnUtils.readIPs(ConnUtils.PATH_PRIMARIO);
		try {
			socketMessage = new DatagramSocket(ports[1]);
			socketSuscription = new DatagramSocket(ports[2]);
			socketConfirmation = new DatagramSocket(ports[3]);
			socketMonitor = new DatagramSocket(ports[4]);
			socketRedundancy = new DatagramSocket();
			socketHeartbeat = new DatagramSocket();
			socketPingEcho = new DatagramSocket();
			
			listenMessages();
			listenSuscriptions();
			listenConfirmations();
			listenMonitor();
			pingEchoCheck();
		} catch (SocketException e) {
			System.out.println("Error al escuchar");
			e.printStackTrace();
		}
	}
	
	public void listenMessages() {
		new Thread() {
			public void run() {
				System.out.println("Servidor: Escuchando Emisores");
				while (true) {
					try {
						DatagramPacket petition = ConnUtils.buildPetition();
						socketMessage.receive(petition);
						Message msg = (Message) ConnUtils.openPetition(petition);
						msg.setInetAddress(petition.getAddress()); // Address del Emisor
						msg.setPort(petition.getPort()); // Puerto del Emisor
						System.out.println("Servidor: Mensaje recibido: " + msg + " (Puerto: " + msg.getPort() + ")");
						String log = "Nuevo Mensaje: (Desde: " + msg.getInetAddress().getHostAddress() + ":" + msg.getPort() + ") " + msg.toString();
						Servidor.getInstance().addLog(log);
						socketRedundancy.send(ConnUtils.buildPetition(Servidor.getInstance().getLogs(),InetAddress.getByName(ips[1]), ports[5]));
						if(existReceptor(msg)) {
							sendMsgToReceptors(msg);	
						} else {
							String response = "KO";
							System.out.println("Servidor: No se recibió ninguna respuesta. Rta = " + response);
							socketRedundancy.send(ConnUtils.buildPetition(new String(response), msg.getInetAddress(), msg.getPort()));
						}
					} catch(Exception e) {
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
					for (ReceptorData rd : Servidor.getInstance().getReceptors()) {
						if(rd.getFilter().isAccepted(msg)) {
							try {
								socketConfirmation.send(ConnUtils.buildPetition(msg, rd.getAddress(), rd.getFilter().getPort()));
								String log = "Mensaje enviado al Receptor: " + rd.getAddress().getHostAddress() + ":"+ rd.getFilter().getPort();
								Servidor.getInstance().addLog(log);
								socketRedundancy.send(ConnUtils.buildPetition(Servidor.getInstance().getLogs(), InetAddress.getByName(ips[1]), ports[5]));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	public void listenSuscriptions() {
		new Thread() {
			public void run() {
				System.out.println("Servidor: Escuchando a Receptores");
				while (true) {
					try {
						DatagramPacket petition = ConnUtils.buildPetition();
						socketSuscription.receive(petition);
						Filter f = (Filter) ConnUtils.openPetition(petition);
						ReceptorData rd = new ReceptorData(f,petition.getAddress());
						Servidor.getInstance().addReceptor(rd);
						String log = "Receptor Suscripto: " + rd.toString();
						Servidor.getInstance().addLog(log);
						socketRedundancy.send(ConnUtils.buildPetition(Servidor.getInstance().getLogs(), InetAddress.getByName(ips[1]), ports[5]));
						socketRedundancy.send(ConnUtils.buildPetition(Servidor.getInstance().getReceptors(), InetAddress.getByName(ips[1]), ports[6]));
						System.out.println("Servidor: Receptor suscripto: " + rd.toString());						
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void listenConfirmations() {
		new Thread() {
			public void run() {
				System.out.println("Servidor: Escuchando Confirmaciones");
				while (true) {
					try {
						DatagramPacket petition = ConnUtils.buildPetition();
						socketConfirmation.receive(petition);			
						Confirmation c = (Confirmation) ConnUtils.openPetition(petition);
						String log = "Respuesta del Receptor: "
									+ "(De: " + petition.getAddress().getHostAddress() + ":" + petition.getPort() + ") "
									+ "(Para: " + c.getAddress().getHostAddress() + ":" + c.getPort() + ") "
									+ "Respuesta: " + c.getValue();
						Servidor.getInstance().addLog(log);
						socketRedundancy.send(ConnUtils.buildPetition(new String(c.getValue()), c.getAddress(), c.getPort()));
						socketRedundancy.send(ConnUtils.buildPetition(Servidor.getInstance().getLogs(), InetAddress.getByName(ips[1]), ports[5]));
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void listenMonitor() {
		new Thread() {
			public void run() {
				System.out.println("Servidor: Escuchando Monitor");
				while(true) {
					try {
						DatagramPacket petition = ConnUtils.buildPetition();
						socketMonitor.receive(petition);
						socketRedundancy.send(ConnUtils.buildPetition(Servidor.getInstance().getLogs(), InetAddress.getByName(ips[1]), ports[5]));
						socketRedundancy.send(ConnUtils.buildPetition(Servidor.getInstance().getReceptors(), InetAddress.getByName(ips[1]), ports[6]));
						System.out.println("Servidor Main: Receptores Sincronizados");
					} catch (Exception e) {
						System.out.println("Error al escuchar el Monitor");
					}
				}
			}
		}.start();
	}
	
	@Override
	public void heartbeat() {
		new Thread() {
			public void run() {
				while(true) {
					try {
						socketHeartbeat.send(ConnUtils.buildPetition(new String("MAIN"), InetAddress.getByName(ips[0]), ports[0]));
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	@Override
	public void closeConnections() {
		socketMessage.close();
		socketSuscription.close();
		socketConfirmation.close();
		socketRedundancy.close();
		socketHeartbeat.close();
	}
	
	public void pingEchoCheck() {
		new Thread() {
			public void run() {
				while(true) {
					try {
						int i = 0;
						while(i < Servidor.getInstance().getReceptors().size()) {
							try {
								ReceptorData rd = Servidor.getInstance().getReceptors().get(i);
								DatagramPacket petition = ConnUtils.buildPetition(new String ("PING"), rd.getAddress(), rd.getFilter().getPort() + 1);
								socketPingEcho.send(petition);
								socketPingEcho.setSoTimeout(500);
								socketPingEcho.receive(petition);
								i++;
							}catch (SocketTimeoutException e) {
									Servidor.getInstance().getReceptors().remove(i);
									Servidor.getInstance().setReceptors(Servidor.getInstance().getReceptors());
									try {
										socketRedundancy.send(ConnUtils.buildPetition(Servidor.getInstance().getReceptors(), InetAddress.getByName(ips[1]), ports[6]));
									} catch (IOException e1) {
										e1.printStackTrace();
									} 
							}
							catch (Exception e) {
								e.printStackTrace();
							}
						}
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public boolean existReceptor(Message msg) {
		boolean res = false;
		for (ReceptorData rd : Servidor.getInstance().getReceptors()) {
			if(rd.getFilter().isAccepted(msg)) {
				res = true;
				break;
			}
		}
		return res;
	}

}
