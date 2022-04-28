package model;

import connection.Connection;

public class Emisor {
	private static Emisor instance = null;
	Location loc;

	private Emisor() {
	}

	public static Emisor getInstance() {
		if (instance == null)
			instance = new Emisor();

		return instance;
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}
	
	public Location getLocation() {
		return this.loc;
	}

	public void sendMsg(Event e) {
		// Connection.sendMsg(MsgFactory.getMessage(loc, e));
	}

}
