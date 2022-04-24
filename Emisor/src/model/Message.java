package model;

import java.time.LocalDateTime;

public class Message {
	int id;
	Location loc;
	Event e;
	LocalDateTime date;
	boolean isActive;
	
	public Message(Location loc, Event e) {
		this.id = 0;
		this.loc = loc;
		this.e = e;
		this.date = LocalDateTime.now();
		this.isActive = false;
	}

	public int getId() {
		return id;
	}

	public Location getLoc() {
		return loc;
	}

	public Event getE() {
		return e;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setId(int id) {
		this.id = id;
	}

}
