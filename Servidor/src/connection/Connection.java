package connection;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;


public class Connection extends Observable{
	//private ArrayList<Receptor> receptors = new ArrayList<Receptor>();
	private DatagramSocket socketUDP;
	private InetAddress inetAdress;
	private byte[] buffer;
	
	public Connection() throws SocketException, UnknownHostException {
		this.socketUDP = new DatagramSocket();
		this.inetAdress = InetAddress.getByName("127.255.255.255"); //Broadcast
		//this.timeOut = timeOut;
		//timeOut.starTimer();
	}
	
	public void listenEmisores() {
		
	}
	
	
	public void listenReceptores() {
		
	}
	
	public void sendMsg() {
		
	}
}
