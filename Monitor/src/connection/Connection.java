package connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;


public class Connection extends Observable implements Observer{
	private DatagramSocket socketMain;
	private DatagramSocket socketAux;
	private TimeOut timerMain;
	private TimeOut timerAux;
	private boolean isAliveMain;
	private boolean isAliveAux;
	
	public Connection(TimeOut timerMain, TimeOut timerAux) {
		this.timerMain = timerMain;
		this.timerMain.addObserver(this);
		this.timerAux = timerAux;
		this.timerAux.addObserver(this); 
		this.isAliveMain = false;
		this.isAliveAux = false;
		try {
			socketMain = new DatagramSocket(1111);
			socketAux = new DatagramSocket(2222);
			
			listenMain(socketMain);
			listenAux(socketAux);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listenMain(DatagramSocket socket) {
		new Thread() {
			public void run() {
				System.out.println("Monitor: Escuchando un Servidor Principal");
				while (true) {
					try {
						byte[] buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socket.receive(petition);
						
						if(isAliveMain) {
							timerMain.stopTimer();							
						} 
						
						isAliveMain = true;
						setChanged();
						notifyObservers("MAIN ONLINE");
						
						System.out.println("Servidor Main: Alive");
						timerMain.starTimer(3); 
					} catch(Exception e) {
						System.out.println("Error al escuchar el Servidor Principal");
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void listenAux(DatagramSocket socket) {
		new Thread() {
			public void run() {
				System.out.println("Monitor: Escuchando un Servidor Secundario");
				while (true) {
					try {
						byte[] buffer = new byte[2048];
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length);
						socket.receive(petition);
						
						if(isAliveAux) {
							timerAux.stopTimer();							
						} else {
							// Sincronización del Servidor Primario cuando se inicia un Servidor Secundario
							System.out.println("Monitor: Sincronizando...");
							socketMain.send(ConnectionUtils.buildPetition(new String("SYNC"), InetAddress.getByName("localhost"), 7373));
						}
						
						isAliveAux = true;
						setChanged();
						notifyObservers("AUX ONLINE");
						
						System.out.println("Servidor Aux: Alive");
						timerAux.starTimer(3);
						sleep(2000);
						
					} catch(Exception e) {
						System.out.println("Error al escuchar el Servidor Principal");
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.getClass().getName().equals("java.lang.String")) {
			if(((String) arg).equals("MAIN OFFLINE")) {
				this.isAliveMain = false;
				if(isAliveAux) {
					try {
						ByteArrayOutputStream bStream = new ByteArrayOutputStream();
						ObjectOutput output = new ObjectOutputStream(bStream); 
						output.writeObject(new String("SWITCH"));
						output.close();
						
						System.out.println("Se envia algo");
						
						byte[] buffer = bStream.toByteArray();
						DatagramPacket petition = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 4242);
						socketAux.send(petition);
					} catch (IOException e) {
						System.out.println("Error al hacer el Switch");
						e.printStackTrace();
					}
				}
			} else if(((String) arg).equals("AUX OFFLINE")) {
				this.isAliveAux = false;
			}
		}
		
	}
}
