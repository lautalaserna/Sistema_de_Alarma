package connection;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Connection {
	private Filter filter;
	private DatagramSocket datagramSocket;
	private byte[] buffer = new byte[256];

	public Connection(Filter filter, int port) throws SocketException {
		this.filter = filter;
		this.datagramSocket = new DatagramSocket(port);
	}

	public void listen() {
		new Thread() {
			public void run() {
				while (true) {
					try {
						DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
						datagramSocket.receive(datagramPacket);
						InetAddress inetAdress = datagramPacket.getAddress();
						int port = datagramPacket.getPort();
						String msg = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
						System.out.println("Msg from Client: " + msg);
						datagramPacket = new DatagramPacket(buffer, buffer.length, inetAdress, port);
						datagramSocket.send(datagramPacket);
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
			}
		}.run();
	}

	/*
	 * public void listen(int port) { new Thread() { public void run() { try {
	 * ServerSocket ss = new ServerSocket(port); while (true) { Socket socket =
	 * ss.accept(); InputStream inputStream = socket.getInputStream();
	 * ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
	 * Message m = (Message) objectInputStream.readObject();
	 * 
	 * // Chekear filtro
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); } } }.run(); }
	 */

}
