package connection;

import java.io.IOException;
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
			socketMain = new DatagramSocket(1111); //Acomodar
			socketAux = new DatagramSocket(2222); //Acomodar
			
			listenMain(socketMain);
			listenAux(socketAux);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void listenMain(DatagramSocket socket) {
		new Thread() {
			public void run() {
				System.out.println("Monitor: Escuchando Servidor Principal");
				while (true) {
					try {
						DatagramPacket petition = ConnUtils.buildPetition();
						socket.receive(petition);						
						if(isAliveMain) {
							timerMain.stopTimer();							
						} 
						isAliveMain = true;
						setChanged();
						notifyObservers("MAIN ONLINE");
						timerMain.starTimer(3); 
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void listenAux(DatagramSocket socket) {
		new Thread() {
			public void run() {
				System.out.println("Monitor: Escuchando Servidor Secundario");
				while (true) {
					try {
						DatagramPacket petition = ConnUtils.buildPetition();
						socket.receive(petition);						
						if(isAliveAux) {
							timerAux.stopTimer();							
						} else {
							System.out.println("Monitor: Sincronizando...");
							socketMain.send(ConnUtils.buildPetition(new String("SYNC"), InetAddress.getByName("localhost"), 7373)); // Acomodar
						}
						isAliveAux = true;
						setChanged();
						notifyObservers("AUX ONLINE");						
						timerAux.starTimer(3);
						sleep(2000);
					} catch(Exception e) {
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
						socketAux.send(ConnUtils.buildPetition(new String("SWITCH"), InetAddress.getByName("localhost"), 4242)); // Acomodar
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if(((String) arg).equals("AUX OFFLINE")) {
				this.isAliveAux = false;
			}
		}
		
	}
}
