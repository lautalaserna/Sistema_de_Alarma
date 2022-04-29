package model;

import java.io.Serializable;
import java.net.InetAddress;
import java.time.LocalDateTime;

public class Message implements Serializable{
	private Location loc;
	private Event e;
	private LocalDateTime date;
	private String state;
	private InetAddress inetAddress;
	
	public Message(Location loc, Event e) {
		this.loc = loc;
		this.e = e;
		this.date = LocalDateTime.now();
		this.state = "Pendiente";
	}

	public Location getLoc() {
		return loc;
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
	
	@Override
	public String toString() {
		return loc.getName() + ";" + date.toString() + ";" + e.getEventType();
	}
	
}