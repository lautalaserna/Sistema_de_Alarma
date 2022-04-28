package connection;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Connection {
	private DatagramSocket datagramSocket;
	private InetAddress inetAdress;
	private byte[] buffer;
	
	public Connection() throws SocketException, UnknownHostException {
		this.datagramSocket = new DatagramSocket();
		this.inetAdress = InetAddress.getByName("localhost");
	}
	
	public void sendMsg() {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
				String msg = "Mensajito de Prueba";
				buffer = msg.getBytes();
				DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAdress, 8080);
				datagramSocket.send(datagramPacket);
				datagramSocket.receive(datagramPacket);
				String response = new String(datagramPacket.getData(),0,datagramPacket.getLength());
				System.out.println("Response from Server: " + response);
				datagramSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	/*
	public static void sendMsg(Message msg) {
		new Thread() {
			public void run() {
				try {
					Socket socket = new Socket(msg.getLoc().getIp(), msg.getLoc().getPort());
					OutputStream outputStream = socket.getOutputStream();
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
					
					objectOutputStream.writeObject(msg);

					// socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.run();
	}
	*/
}
