package model;

import java.io.Serializable;
import java.net.InetAddress;

public class Confirmation implements Serializable{
	private InetAddress address;
	private int port;
	private String value;
	
	public Confirmation(InetAddress address, int port, String value) {
		this.address = address;
		this.port = port;
		this.value = value;
	}

	public int getPort() {
		return port;
	}

	public InetAddress getAddress() {
		return address;
	}

	public String getValue() {
		return value;
	}
}
