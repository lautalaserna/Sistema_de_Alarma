package model;

import java.io.Serializable;
import java.net.InetAddress;
import java.time.LocalDateTime;

public class Message implements Serializable{
	private String locationName;
	private Event e;
	private LocalDateTime date;
	private String state;
	private int port;
	private InetAddress inetAddress;
	
	public Message(String name, Event e) {
		this.locationName = name;
		this.e = e;
		this.date = LocalDateTime.now();
		this.state = "Pendiente";
	}

	public String getLocationName() {
		return locationName;
	}

	public Event getEvent() {
		return e;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public InetAddress getInetAddress() {
		return inetAddress;
	}
	
	public void setInetAddress(InetAddress inetAddress) {
		this.inetAddress = inetAddress;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	@Override
	public String toString() {
		return "Ubicación: " + locationName + 
			" / Fecha: " + date.getHour() + ":" + date.getMinute() + ":" + date.getSecond() + 
			" / Evento: " + e.getEventType();
	}
}
