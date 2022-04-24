package model;

import java.io.IOException;
import java.net.InetAddress;

public class Location {
	private String ip;
	private String port;
	private String name;
	
	public Location(String name, String port) throws IOException {
		this.name = name;
		this.port = port;
		this.ip = InetAddress.getLocalHost().getHostAddress();
	}
	
	public String getIp() {
		return ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
