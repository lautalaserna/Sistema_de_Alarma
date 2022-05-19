package connection;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerData implements Serializable {
	private InetAddress address;
	private int port;
	
	public ServerData() {
		try {
			this.address = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
			this.port = 2020;
		} catch (UnknownHostException e) {
			System.out.println("Error al obtener la IP");
			e.printStackTrace();
		}
	}
	
	public ServerData(InetAddress address, int port) {
		this.address = address;
		this.port = port;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
