package model;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Location implements Serializable{
	private String ip;
	private int port = 8080;
	private String name = "";
	
	public Location() throws IOException {
		this.ip = InetAddress.getLocalHost().getHostAddress();
	}
	
	public Location(String name, int port) throws IOException {
		this.name = name;
		this.port = port;
		this.ip = InetAddress.getLocalHost().getHostAddress();
	}

	public String getIp() {
		return ip;
	}

	public void setIp() throws IOException {
		this.ip = InetAddress.getLocalHost().getHostAddress();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
