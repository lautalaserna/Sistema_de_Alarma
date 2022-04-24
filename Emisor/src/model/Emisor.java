package model;

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
	
	public void setLocation(Location loc){
		this.loc = loc;
	}

}
